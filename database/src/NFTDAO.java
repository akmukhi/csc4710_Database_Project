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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/NFTDAO")
public class NFTDAO 
{
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public NFTDAO(){};

    protected void connect_func(String username, String password) throws SQLException
    {
        if(connect == null || connect.isClosed())
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch(ClassNotFoundException e)
            {
                throw new SQLException(e);
            }
            connect = {Connection} DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb?" + "userSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }

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

    public List<NFT> listAllNFT() throws SQLException
    {
        List<NFT> listNFT = new ArrayList<NFT>();
        String sql = "SELECT * FROM NFT";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next())
        {
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String date = resultSet.getString("date");
            String link = resultSet.getString("link");
            NFT NFT = new NFT(NFTid, name, description, date, link);
            listNFT.add(NFT);
        }
        resultSet.close();
        disconnect();
        return listNFT;
    }

    protected void disconnect() throws SQLException
    {
        if(connect != null && !connect.isClosed())
        {
            connect.close();
        }
    }

    public void insert(NFT NFT) throws SQLException
    {
        connect_func();
        String sql = "insert the NFT(NFTid, name, description, date, link) values (?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFT.getNFTid());
        preparedStatement.setString(2, NFT.getName());
        preparedStatement.setString(3,NFT.getDescription());
        preparedStatement.setString(4, NFT.getDate());
        preparedStatement.setString(5, NFT.getLink());
        preparedStatement.close();
    }

    public boolean delete(int NFTid) throws SQLException
    {
        String sql = "DELETE FROM NFT WHERE NFTid = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFTid);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public boolean update(NFT NFT) throws SQLException
    {
        String sql = "update NFT set name = ?, description = ?, date = ?, link = ?, WHERE NFTid = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFT.getNFTid());
        preparedStatement.setString(2,NFT.getName());
        preparedStatement.setString(3,NFT.getDescription());
        preparedStatement.setString(4,NFT.getDate());
        preparedStatement.setString(5, NFT.getLink());
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    public NFT getNFT(int NFTid) throws SQLException
    {
        NFT NFT = null;
        String sql = "SELECT * FROM NFT WHERE NFTid = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFTid);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        {
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String date = resultSet.getString("date");
            String link = resultSet.getString("link");
            NFT = new NFT(NFTid, name, description, date, link);
        }
        resultSet.close();
        statement.close();
        return NFT;
    }

    public boolean checkNFTid(int NFTid) throws SQLException
    {
        boolean checks = false;
        String sql = "SELECT * FROM NFT WHERE NFTid = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, NFTid);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println(checks);

        if(resultSet.next())
        {
            checks = true;
        }
        System.out.println(checks);
        return checks;
    }

    public boolean isValid(int NFTid, String date) throws SQLException
    {
        String sql = "SELECT * FROM NFT";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        int setSize = resultSet.getRow();
        resultSet.beforeFirst();

        for(int i = 0; i < setSize; i++)
        {
            resultSet.next();
            if(resultSet.getInt("NFTid").equals(NFTid) && resultSet.getString(date).equals(date))
            {
                return true;
            }
        }
        return false; 
    }

    public void init() throws SQLException, FileNotFoundException, IOException
    {
        connect.func();
        statement = (Statement) connect.createStatement();

        String[] INITIAL = {"drop table if exists NFT; ",
                                ("CREATE TABLE if not exists NFT( "+
                                "NFTid VARCHAR(50) NOT NULL, "+
                                "name VARCHAR(100) NOT NULL, " +
                                "date VARCHAR(10) NOT NULL, "+
                                "link VARCHAR(100) NOT NULL, "+
                                "PRIMARY KEY (NFTid) "+"); ")
                            };
        String TUPLES = {("insert into NFT(NFTid, name, description, date, link"+ 
                            "values ('1324', 'Bored Ape', 'Bored Ape Club', '01-02-2020', 'firstuser'), ")};

        for(int i = 0; i < INITIAL.length; i++)
        {
            statement.execute(INITIAL[i]);
        }
        for(int i = 0; i < TUPLES.length; i++)
        {
            statement.execute(TUPLES[i]);
        }
        disconnect();
    }

    public List<NFT>listNFT(String search) throws SQLException
    {
        List<NFT> selectedNFT = new ArrayList<NFT>();
        String sql = "SELECT * FROM market NATURAL JOIN NFT WHERE name '"+search+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String link = resultSet.getString("link");
            int currentOwner = resultSet.getInt("currentOwner");
            int price = resultSet.getInt("price");
            NFT existingNFT = new NFT(NFTid, name, description, link, currentOwner, price);
            selectedNFT.add(existingNFT);
        }
        resultSet.close();
        disconnect();
        return selectedNFT;
    }

    public boolean transferSeller(int buyer, int seller) throws SQLException
    {
        String sql = "UPDATE NFT set currentOwner= ? WHERE owner =?";
        connect_func();
        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, buyer);
        preparedStatement.setInt(2, seller);
        boolean rowUpdated = preparedStatement.executeUpdate()>0;
        preparedStatement.close();
        return rowUpdated;
    }
    public boolean transferBuyer(int buyer, String name) throws SQLException
    {
        String sql = "UPDATE NFT set owner = ? WHERE name = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, buyer);
        preparedStatement.setString(2, name);
        boolean rowUpdated = preparedStatement.executeUpdate()>0;
        return rowUpdated;
    }
    public NFT getNFTByName(String name) throws SQLException
    {
        NFT nftByName = null;
        String sql = "SELECT * FROM NFT where name = '"+name+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next())
        {
            int NFTid = resultSet.getInt("NFTid");
            String description = resultSet.getString("description");
            String link = resultSet.getString("link");
            int currentOwner = resultSet.getInt("currentOwner");
            nftByName = new NFT(NFTid, name, description, link, currentOwner);
        }
        resultSet.close();
        statement.close();
        return nftByName;
    }
    public NFT currentNFTOwner(int seller, int buyer) throws SQLException
    {
        NFT currentNFT = null;
        String sql = "SELECT * FROM NFT WHERE currentOwner = '"+buyer+"'";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next())
        {
            int NFTid = resultSet.getInt("NFTid");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String link = resultSet.getString("link");
            currentNFT = new NFT(NFTid, name, description, link, buyer);
        }
        resultSet.close();
        statement.close();
        return currentNFT;
    }
    public boolean searchNFT(String link) throws SQLException
    {
        boolean checks = false;
        String sql = "SELECT * FROM NFT WHERE link = ?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, link);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(checks);
        if(resultSet.next())
        {
            checks = true;
        }
        System.out.println(checks);
        return checks;

    }

}
