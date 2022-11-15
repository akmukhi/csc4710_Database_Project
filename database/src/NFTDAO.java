import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet("/NFTDAO")
public class NFTDAO
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public NFTDAO() {}
	
	protected void connect_func() throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
			System.out.println(connect);
		}
	}
	
	protected void disconnect() throws SQLException
	{
		if(connect != null && !connect.isClosed())
		{
			connect.close();
		}
	}
	
	public void connect_func(String username, String password) throws SQLException
	{
		if(connect == null || connect.isClosed())
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
			catch(ClassNotFoundException e)
			{
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"+"useSSL=false&user=" +username+"&password="+password);
			System.out.println(connect);
		}
	}
	
	public List<NFT> listSelectedNFT(String search) throws SQLException
	{
		List<NFT> selectedNFT = new ArrayList<NFT>();
		String sql = "SELECT * FROM transaction NATURAL JOIN NFT WHERE name = '"+search+"'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String nft = resultSet.getString("nft");
			int currentOwner = resultSet.getInt("currentOwner");
			int price = resultSet.getInt("price");
			NFT pickedNFT = new NFT(NFTid, name, description, nft, currentOwner, price);
			selectedNFT.add(pickedNFT);
		}
		resultSet.close();
		disconnect();
		return selectedNFT;
	}
	
	public List<NFT> listOwnerNFT(int currentOwner) throws SQLException
	{
		List<NFT> ownedNft = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFT WHERE owner = '"+currentOwner+"'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String nft = resultSet.getString("nft");
			currentOwner = resultSet.getInt("currentOwner");
			NFT userNFT = new NFT(NFTid, name, description, nft, currentOwner);
			ownedNft.add(userNFT);
		}
		resultSet.close();
		disconnect();
		return ownedNft;
	}

	public boolean transfer(int buyer, int seller) throws SQLException
	{
		String sql = "UPDATE NFT set currentOwner= ? WHERE currentOwner =?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, buyer);
		preparedStatement.setInt(2, seller);
		boolean rowUpdated = preparedStatement.executeUpdate()>0;
		preparedStatement.close();
		return rowUpdated;
	}
	
	public boolean transferToBuyer(int buyer, String name) throws SQLException
	{
		String sql = "UPDATE NFT set currentOwner= ? WHERE name = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, buyer);
		preparedStatement.setString(2, name);
		boolean rowUpdated = preparedStatement.executeUpdate()>0;
		preparedStatement.close();
		return rowUpdated;
	}
	
	public NFT getNFT(int NFTid) throws SQLException
	{
		NFT selectedNFT = null;
		String sql = "SELECT * FROM NFT where NFTid = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, NFTid);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String nft = resultSet.getString("nft");
			int currentOwner = resultSet.getInt("currentOwner");
			selectedNFT = new NFT(NFTid, name, description, nft, currentOwner);
		}
		resultSet.close();
		preparedStatement.close();
		return selectedNFT;
	}
	
	public NFT getNFTOwner(int seller, int buyer) throws SQLException
	{
		NFT selectedNFT = null;
		String sql = "SELECT * FROM NFT WHERE currentOwner = '"+seller+"'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet =  statement.executeQuery(sql);
		if(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String nft = resultSet.getString("nft");
			selectedNFT = new NFT(NFTid, name, description, nft, buyer);
		}
		resultSet.close();
		statement.close();
		return selectedNFT;
	}

	
	public void insertNFT(NFT selectedNFT) throws SQLException
	{
		connect_func();
		String sql = "INSERT INTO NFT(name, description, nft, currentOwner) values (?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, selectedNFT.getName());
		preparedStatement.setString(2, selectedNFT.getDescription());
		preparedStatement.setString(3, selectedNFT.getNft());
		preparedStatement.setInt(4, selectedNFT.getCurrentOwner());
		preparedStatement.executeUpdate();
		preparedStatement.close();	
	}
	
	public boolean checkNFT(String name) throws SQLException
	{
		boolean check = false;
		String sql = "SELECT * FROM NFT WHERE name = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1,  name);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println(check);
		if(resultSet.next())
		{
			check = true;
		}
		System.out.println(check);
		return check;
	}
	
	public boolean checkNFTFile(String nft) throws SQLException
	{
		boolean check = false;
		String sql = "SELECT * FROM NFT WHERE nft = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, nft);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println(check);
		if(resultSet.next())
		{
			check = true;
		}
		System.out.println(check);
		return check;
	}
	
	public List<NFT> listAllNFT() throws SQLException
	{
		List<NFT> listNFTS = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFT";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String nft = resultSet.getString("nft");
			int currentOwner = resultSet.getInt("currentOwner");
			NFT newNFT = new NFT(NFTid, name, description, nft, currentOwner);
			listNFTS.add(newNFT);
		}
		resultSet.close();
		disconnect();
		return listNFTS;
	}

	public void init() throws SQLException, FileNotFoundException, IOException
	{
		connect_func();
		statement = (Statement) connect.createStatement();
		String[] INITIAL = 
		{
			"USE testdb;",
			"DROP TABLE if exists NFT;",
			("CREATE TABLE if not exists NFT( "+
			"NFTid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"name VARCHAR(40),"+
			"description VARCHAR(500),"+
			"nft VARCHAR(4000),"+
			"currentOwner INTEGER,"+
			"FOREIGN KEY(currentOwner) REFERENCES user(user_name)"
			+ ");")
		};

		String[] TUPLES =
		{
			("INSERT into NFT(name, description, nft, currentOwner"+
			"values (")
		}

	}

	

}
