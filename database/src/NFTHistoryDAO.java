/*import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/NFTHistoryDAO")
public class NFTHistoryDAO 
{
    private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
    public NFTHistoryDAO(){}

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

    public void insertUser(user users) throws SQLException
    {
        connect_func();
        String sql = "INSERT INTO RECORD(user, transactionDetails, transaction, date) values (?, ?, ?, NOW())";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1,users.user_name);
        preparedStatement.setString(2,users.password);
        preparedStatement.setString(3,"The user has been created");
        preparedStatement.setString(4, "created");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void recordOfSale(user buyer, user seller, nft selectedNft) throws SQLException
    {
        connect_func();
        String sql = "INSERT into Record(user, NFTid, transactionDetails, transaction, date) values (?, ?, ?, ?, NOW())";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, buyer.user_name);
        preparedStatement.setInt(2, selectedNft.NFTid);
        preparedStatement.setString(3, buyer.firstName + " has purchaseed this NFT " + selectedNft.name + "NFT: "+ selectedNft.NFTid +" From " +seller.firstName);
        preparedStatement.setString(4, "this is not available");

        String sql2 = "INSERT into Record(user, NFTid, transactionDetails, transaction, date) values (?, ?, ?, ?, NOW())";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.setString(1, seller.firstName);
        preparedStatement.setInt(2, selectedNft.NFTid);
        preparedStatement.setString(3, seller.firstName + "has sold the NFT: "+ selectedNft.name +" This NFT's id is: "+ selectedNft.NFTid +"From "+ buyer.firstName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void transfer(user seller, user buyer, nft selectedNft) throws SQLException
    {
        connect_func();
        String sql = "INSERT into Record(user, NFTid, transactionDetails, transaction, date) values (?, ?, ?, ?, NOW())";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, seller.firstName);
        preparedStatement.setInt(2, selectedNft.NFTid);
        preparedStatement.setString(3, seller.firstName +" has transferred ownership of the NFT " +selectedNft.name +" to " +buyer.firstName);
        preparedStatement.setString(4, "The transfer is complete");
        preparedStatement.executeUpdate();

        String sql2 = "INSERT into Record(user, NFTid, transactionDetails, transaction, date) values (?, ?, ?, ?, NOW())";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.setString(1, buyer.firstName);
        preparedStatement.setInt(2, selectedNft.NFTid);
        preparedStatement.setString(3, buyer.firstName + " has received the NFT "+ selectedNft.name + " from " +seller.firstName);
        preparedStatement.setString(4, "Transfer has completed");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<NFTHistory> listNftHistories() throws SQLException
    {
        List<NFTHistory> listNFTHistory = new ArrayList<NFTHistory>();
        String sql = "SELECT * FROM Record";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int event = resultSet.getInt("event");
            int user = resultSet.getInt("user");
            int NFTid = resultSet.getInt("NFTid");
            String transactionDetails = resultSet.getString("transactionDetails");
            String transaction = resultSet.getString("transaction");
            Timestamp date = resultSet.getTimestamp("date");
            NFTHistory history = new NFTHistory(event, user, NFTid, transactionDetails, transaction, date);
            listNFTHistory.add(history);
        } 
        resultSet.close();
        disconnect();
        return listNFTHistory;
    }

    public void init() throws SQLException, FileNotFoundException, IOException
    {
        connect_func();
        statement = (Statement) connect.createStatement();
        String[] INITIAL1 =
        {
            "use testdb",
            "DROP TABLE if exists Record;",
            ("CREATE TABLE if not exists Record( "+
            "event INTEGER AUTO_INCREMENT PRIMARY KEY,"+
            "user INTEGER,"+
            "NFTid INTEGER,"+
            "transactionDetails VARCHAR(6000),"+
            "transaction VARCHAR(100),"+
            "date TIMESTAMP);")            
        };
        String[] TUPLES1 = 
        {
            ("INSERT into Record(user, NFTid, transactionDetails, transaction, date)"+
            "values ('1', '1', 'user has been created', 'create', NOW()),"+
            "('2', '3', 'user has been created', 'create', NOW()),"+
            "('3', '4', 'user has been created', 'create', NOW()),"+
            "('4', '5', 'user has been created', 'create', NOW()),"+
            "('5', '6', 'user has been created', 'create', NOW()),"+
            "('6', '7', 'user has been created', 'create', NOW()),"+
            "('7', '8, 'user has been created', 'create', NOW()),"+
            "('8', '9', 'user has been created', 'create', NOW()),"+
            "('9', '10', 'user has been created', 'create', NOW());")
        };

        for(int i = 0; i < INITIAL1.length; i++)
        {
            statement.execute(INITIAL1[i]);
        }
        for(int i = 0; i < TUPLES1.length; i++)
        {
            statement.execute(TUPLES1[i]);
        }
        disconnect();
    }

    
}
*/