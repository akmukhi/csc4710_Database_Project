import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
/**
 * Servlet implementation class Connect
 */
@WebServlet("/nftDAO")
public class nftDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public nftDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(nft nfts) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into nft_ledger(nftName ,nftDescription ,listingPrice ,uploadnft ,listingTime, nftOwner) values (?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, nfts.getnftName());
			preparedStatement.setString(2, nfts.getNftDescription());
			preparedStatement.setInt(3, nfts.getListingPrice());
			preparedStatement.setString(4, nfts.getUploadNFT());
			preparedStatement.setString(5, nfts.getListingTime());		
			preparedStatement.setString(6, nfts.getnftOwner());	
			
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public List<nft> listnfts() throws SQLException {
        List<nft> nfts = new ArrayList<nft>();        
        String sql = "SELECT * FROM nft_Ledger"; 
        try {
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int NFTid = resultSet.getInt("NFTid");
            String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String uploadnft = resultSet.getString("uploadnft");
            String listingTime = resultSet.getString("listingTime");
            String nftOwner = resultSet.getString("nftOwner"); 
             
            nfts.add(new nft(NFTid, nftName, nftDescription, listingPrice, uploadnft, listingTime, nftOwner));
        }        
        resultSet.close();
    } catch(SQLException e) {
    	System.out.println(e.toString());
    }   
  
        return nfts;
    }

    public boolean checknft(String nftName) throws SQLException { //DUPLICATE
    	boolean checks = false;
    	String sql = "SELECT * FROM nft_ledger WHERE nftName = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, nftName);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }


    public boolean decfunds(String nftOwner, int listingPrice, int cash_bal) throws SQLException {
    	String sql2 = "UPDATE User SET cash_bal = ? WHERE email = ?";
    	connect_func();
 
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
    	int difference = listingPrice - cash_bal;
    	preparedStatement.setInt(1, difference);
    	preparedStatement.setString(2, nftOwner);

    	
    	boolean rowUpdated = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowUpdated;
    	
    }
    
    public int getlistingprice(String nftName) throws SQLException {
    	String sql = "select listingPrice from nft_Ledger WHERE nftName = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	nftName = resultSet.getString("nftName");
    	int listingPrice  = resultSet.getInt("listingPrice");
    	preparedStatement.close();
    	
    	return listingPrice;
    }
    
    public int getcashbal(String email) throws SQLException{
    	String sql = "select cash_bal from USER WHERE email = ? ";
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	ResultSet resultSet1 = preparedStatement.executeQuery();
    	int cash_bal = resultSet1.getInt("cash_bal");
    	preparedStatement.close();
    	
    	return cash_bal;
    }
   
    
 
    public List<nft> search(String nftsearch) throws SQLException {
        List<nft> nfts = new ArrayList<nft>();        
        String sql = "SELECT * FROM nft_Ledger WHERE nftName LIKE '%"+nftsearch+"%'"; 
        System.out.println(sql);
        try {
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        System.out.println(resultSet);
        while (resultSet.next()) {
        	int NFTid = resultSet.getInt("NFTid");
        	String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String uploadnft = resultSet.getString("uploadnft");
            String listingTime = resultSet.getString("listingTime");
            String nftOwner = resultSet.getString("nftOwner"); 
             
            nfts.add(new nft(NFTid, nftName, nftDescription, listingPrice, uploadnft, listingTime, nftOwner));
        }        
        resultSet.close();
        disconnect();
    } catch(SQLException e) {
    	System.out.println(e.toString());
    }   
  
        return nfts;
    }

   //AAKASH WORK 
 
    public List<nft> listSelectedNFT(String search) throws SQLException
    {
        List<nft> selectedNFT = new ArrayList<nft>();
        String sql = "SELECT * FROM transaction NATURAL JOIN NFT WHERE nftName = '"+search+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int NFTid = resultSet.getInt("NFTid");
            String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String uploadNFT = resultSet.getString("uploadNFT");
            String listingTime = resultSet.getString("listingTime");
            String nftOwner = resultSet.getString("nftOwner");
            nft pickedNFT = new nft(NFTid, nftName, nftDescription,listingPrice,uploadNFT,listingTime,nftOwner);
            selectedNFT.add(pickedNFT);
        }
        resultSet.close();
        disconnect();
        return selectedNFT;
    }

	public List<nft> listOwnernft(String owner) throws SQLException
	{
		List<nft> ownednft = new ArrayList<nft>();
		String sql = "SELECT * FROM NFT_ledger WHERE nftOwner = '"+owner+"'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
        	String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String uploadnft = resultSet.getString("uploadnft");
            String listingTime = resultSet.getString("listingTime");
            String nftOwner = resultSet.getString("nftOwner"); 
            ownednft.add(new nft(NFTid, nftName, nftDescription, listingPrice, uploadnft, listingTime, nftOwner));
		}
		resultSet.close();
		disconnect();
		return ownednft;
	}
e
	public boolean transfer(String buyer, String seller) throws SQLException
	{
		String sql = "UPDATE NFT_ledger set nftOwner= ? WHERE nftOwner =?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, buyer);
		preparedStatement.setString(2, seller);
		boolean rowUpdated = preparedStatement.executeUpdate()>0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean transferToBuyer(String buyer, String name) throws SQLException
	{
		String sql = "UPDATE nft_ledger set nftOwner= ? WHERE nftName = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, buyer);
		preparedStatement.setString(2, name);
		boolean rowUpdated = preparedStatement.executeUpdate()>0;
		preparedStatement.close();
		return rowUpdated;
	}

	public nft getnftOwner(String seller, String buyer) throws SQLException
	{
		nft selectednft = null;
		String sql = "SELECT * FROM nft_ledger WHERE nftOwner = '"+seller+"'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet =  statement.executeQuery(sql);
		if(resultSet.next())
		{
			int NFTid = resultSet.getInt("NFTid");
        	String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String uploadnft = resultSet.getString("uploadnft");
            String listingTime = resultSet.getString("listingTime");
            String nftOwner = resultSet.getString("nftOwner"); 
            selectednft = new nft(NFTid, nftName, nftDescription, listingPrice, uploadnft, listingTime, nftOwner);
		}
		resultSet.close();
		statement.close();
		return selectednft;
	}



	public boolean checknftFile(String url) throws SQLException
	{
		boolean check = false;
		String sql = "SELECT * FROM NFT_ledger WHERE url = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, url);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println(check);
		if(resultSet.next())
		{
			check = true;
		}
		System.out.println(check);
		return check;
	}


	public nft getNFT(String name) throws SQLException
    {
        nft selectedNFT = null;
        String sql = "SELECT * FROM NFT_Ledger where nftName = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        {
        	int NFTid = resultSet.getInt("NFTid");
            String nftName = resultSet.getString("nftName");
            String nftDescription = resultSet.getString("nftDescription");
            int listingPrice = resultSet.getInt("listingPrice");
            String listingTime = resultSet.getString("listingTime");
            String uploadNFT = resultSet.getString("uploadNFT");
            String nftOwner = resultSet.getString("nftOwner");
            selectedNFT = new nft(NFTid, nftName, nftDescription, listingPrice, uploadNFT, listingTime, nftOwner);
        }
        resultSet.close();
        preparedStatement.close();
        return selectedNFT;
    }
    
    
	
	

}