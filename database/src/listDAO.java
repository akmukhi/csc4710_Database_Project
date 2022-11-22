import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/listDAO")
public class listDAO 
{
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public listDAO(){}

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

    protected void disconnect() throws SQLException
    {
        if(connect != null && !connect.isClosed())
        {
            connect.close();
        }
    }
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
    public List<list> listing() throws SQLException
    {
        List<list> listing = new ArrayList<list>();
        String sql = "SELECT n.name, n.link, m.* FROM list m JOIN NFT_ledger n ON m.name = n.nftName";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int transactionID = resultSet.getInt("transactionID");
            Timestamp date = resultSet.getTimestamp("date");
            int price = resultSet.getInt("price");
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String link = resultSet.getString("link");
            list l = new list(transactionID, date, price, NFTid, name, link);
            listing.add(l);
        }
        resultSet.close();
        disconnect();
        return listing;
    }
    public boolean insert(Date date, int price, int NFTid) throws SQLException
    {
        String sql = "INSERT INTO list(date, price, NFTid) values (?, ?, ?)";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setDate(1,date);
        preparedStatement.setInt(2, price);
        preparedStatement.setInt(3,NFTid);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public List<list> getList(String Id) throws SQLException
    {
        List<list> l = new ArrayList<list>();
        int NFTid = Integer.parseInt(Id);
        String sql = "SELECT * FROM list where name= ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFTid);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if(resultSet.next())
        {
            int transactionID = resultSet.getInt("transactionID");
            Timestamp date = resultSet.getTimestamp("date");
            int price = resultSet.getInt("price");
            l = new list(transactionID, date, price, NFTid);
        }
        resultSet.close();
        preparedStatement.close();

        return l;
    }

    public List<list> listTransactions() throws SQLException
    {
        List<list> l = new ArrayList<list>();
        String sql = "SELECT * FROM list NATURAL JOIN NFT";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int transactionID = resultSet.getInt("transactionID");
            Timestamp date = resultSet.getTimestamp("date");
            int price = resultSet.getInt("price");
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            list transaction = new list(transactionID, date, price, NFTid, name);
            l.add(transaction);
        }
        resultSet.close();
        disconnect();
        return l;
    }

    public List<list> listBought(String buyer) throws SQLException
    {
        List<list> x = new ArrayList<list>();
        String sql = "SELECT * FROM Transaction_History WHERE nftOwner = '"+buyer+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int transactionID = resultSet.getInt("transactionID");
            Timestamp date = resultSet.getTimestamp("date");
            int price = resultSet.getInt("price");
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String link = resultSet.getString("link");
            list buyerTransaction = new list(transactionID, date, price, NFTid, name, link);
            x.add(buyerTransaction);
        }
        resultSet.close();
        disconnect();
        return x;  
    }
    public List<list> listSold(String seller) throws SQLException
    {
        List<list> y = new ArrayList<list>();
        String sql = "SELECT * FROM Transaction_History WHERE nftOwner = '"+seller+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int transactionID = resultSet.getInt("transactionID");
            Timestamp date = resultSet.getTimestamp("date");
            int price = resultSet.getInt("price");
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String link = resultSet.getString("link");
            list sellerTransaction = new list(transactionID, date, price, NFTid, name, link);
            y.add(sellerTransaction);
        }
        resultSet.close();
        disconnect();
        return y;
    }


    public boolean delete(int NFTid) throws SQLException
    {
        String sql = "DELETE FROM lsit WHERE NFTid = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFTid);
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public ArrayList<Integer> deleteDate() throws SQLException
    {
        ArrayList<Integer> DeleteNFT = new ArrayList<Integer>();
        connect_func();
        String sql1 = "SELECT NFTid FROM list WHERE date <= SYSDATE()";
        String sql2 = "DELETE FROM list WHERE date <= SYSDATE()";

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement.executeQuery(sql1);
        while(resultSet.next())
        {
            int NFTid = resultSet.getInt(1);
            DeleteNFT.add(NFTid);
        }
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return DeleteNFT;
    }

    public void init() throws SQLException, FileNotFoundException, IOException
    {
        connect_func();
        statement = (Statement) connect.createStatement();
        String[] INITIAL = {"use testdb"};
        String[] TUPLES = {("INSERTS into list")};

        for(int i = 0; i< INITIAL.length; i++)
        {
            statement.execute(INITIAL[i]);
        }
        for(int i = 0; i < TUPLES.length; i++)
        {
            statement.execute(TUPLES[i]);
        }
        disconnect();
    }
}