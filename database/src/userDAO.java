import java.io.FileNotFoundException;
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
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
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
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String address_street_num = resultSet.getString("address_street_num"); 
            String address_street = resultSet.getString("address_street"); 
            String address_city = resultSet.getString("address_city"); 
            String address_state = resultSet.getString("address_state"); 
            String address_zip_code = resultSet.getString("address_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");

             
            user users = new user(email,firstName, lastName, password, birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code, cash_bal,PPS_bal);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, birthday,address_street_num, address_street,address_city,address_state,address_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getaddress_street_num());		
			preparedStatement.setString(7, users.getaddress_street());		
			preparedStatement.setString(8, users.getaddress_city());		
			preparedStatement.setString(9, users.getaddress_state());		
			preparedStatement.setString(10, users.getaddress_zip_code());		
			preparedStatement.setInt(11, users.getCash_bal());		
			preparedStatement.setInt(12, users.getPPS_bal());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,address_street_num =?, address_street=?,address_city=?,address_state=?,address_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getaddress_street_num());		
		preparedStatement.setString(7, users.getaddress_street());		
		preparedStatement.setString(8, users.getaddress_city());		
		preparedStatement.setString(9, users.getaddress_state());		
		preparedStatement.setString(10, users.getaddress_zip_code());		
		preparedStatement.setInt(11, users.getCash_bal());		
		preparedStatement.setInt(12, users.getPPS_bal());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String address_street_num = resultSet.getString("address_street_num"); 
            String address_street = resultSet.getString("address_street"); 
            String address_city = resultSet.getString("address_city"); 
            String address_state = resultSet.getString("address_state"); 
            String address_zip_code = resultSet.getString("address_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            user = new user(email, firstName, lastName, password, birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code,cash_bal,PPS_bal);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    public List<user> profilesearch(String profilesearch) throws SQLException {
        List<user> users = new ArrayList<user>();        
        String sql = "SELECT * FROM user WHERE email LIKE '%"+profilesearch+"%'"; 
        System.out.println(sql);
        try {
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        System.out.println(resultSet);
        while (resultSet.next()) {
        	String email = resultSet.getString("email");
             
           users.add(new user(email));
        }        
        resultSet.close();
        disconnect();
        
    } catch(SQLException e) {
    	System.out.println(e.toString());
    }   
  
        return users;
    }
    
  /*  public String viewprofile(String email) throws SQLException {
    	String sql = "SELECT * FROM User WERE email = ?";
     try {
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        disconnect();
    	 }
    	 
    	 catch(SQLException e) {
    	System.out.println(e.toString());
    	 }   
        
    	return email;
    } */

    public List<user> viewprofilelist(String username) throws SQLException {
    	List<user> users = new ArrayList<user>(); 
     	String sql = "SELECT * FROM User WHERE email = '"+username+"'";
        System.out.println(sql);
     	 try {
         connect_func();      
         statement = (Statement) connect.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
         while (resultSet.next()) {
         	String email = resultSet.getString("email");
             String firstName = resultSet.getString("firstName");
             String lastName = resultSet.getString("lastName");
              
             users.add(new user(email, firstName, lastName));
         }                 
         resultSet.close();
     	 }     	 
     	 catch(SQLException e) {
     	System.out.println(e.toString());
     	 }   

     	return users;
     }
//PROJECT 4 IMP
    //AAKASH PART
    //Part 1 Big creator
    public List<user> bigCreator() throws SQLException
    {
        List<user> listUser = new ArrayList<user>();
        userDAO user = new userDAO();
        String sql = "SELECT creator_id, COUNT(creator_id) AS 'value_occurence' FROM Transaction_History"
        +" GROUP BY creator_id"+" ORDER BY 'value_occurence' DESC LIMIT 1;";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        statement = (Statement) connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            String email = resultSet.getString("email");
            listUser.add(user.getUser(email));
        }
        resultSet.close();
        disconnect();
        return listUser;
    }

    //Part 2 Big Seller
    public List<user> bigSeller() throws SQLException
    {
        List<user> listUser = new ArrayList<user>();
        userDAO user = new userDAO();
        String sql = "SELECT previous_owner, COUNT(previous_owner) AS 'value_occurence' FROM Transfer_History"
        +" GROUP BY previous_owner"+"ORDER BY 'value_occurence' DESC LIMIT 1;";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        statement = (Statement) connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            String email = resultSet.getString("email");
            listUser.add(user.getUser(email));
        }
        resultSet.close();
        disconnect();
        return listUser;
    }

    //Part 3 Big Buyer
    public List<user> bigBuyer() throws SQLException
    {
        List<user> listUser = new ArrayList<user>();
        userDAO user = new userDAO();
        String sql = "SELECT customer_id, COUNT(customer_id) AS 'value_occurence' FROM Transaction_History"+" GROUP BY customer_id"
        +" ORDER BY 'value_occurence' DESC LIMIT 1;";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        statement = (Statement) connect.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            String email = resultSet.getString("email");
            listUser.add(user.getUser(email));
        }
        resultSet.close();
        disconnect();
        return listUser;
    }
    
    
    //Part 6 diamond hands
    public List<user> diamond() throws SQLException
    {
        List<user> listUser = new ArrayList<user>();
        userDAO user = new userDAO();
        String sql = "SELECT DISTINCT current_owner\r\n"+
        "FROM Transfer_History WHERE current_owner NOT IN (SELECT previous_owner FROM Transfer_History);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String email = resultSet.getString("email");
            listUser.add(user.getUser(email));
        }
        resultSet.close();
        disconnect();
        return listUser;
    }
    
    //Part 8 good buyer
    public List<user> goodBuyer() throws SQLException
    {
        List<user> listUser = new ArrayList<user>();
        userDAO user = new userDAO();
        String sql = "SELECT customer_id, COUNT(customer_id) AS 'value_occurence'"
        		+ "FROM Transaction_History"
        		+ "GROUP BY customer_id WHERE 'value_occurence' >= 3"
        		+ "ORDER BY 'value_occurence'; ";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            String email = resultSet.getString("email");
            listUser.add(user.getUser(email));
        }
        resultSet.close();
        disconnect();
        return listUser;
    }
    
    //REEHAM
    public List<user> listInactiveUsers() throws SQLException {
    	List<user> users = new ArrayList<user>(); 
     	String sql = "SELECT * FROM users u LEFT JOIN list li ON u.UserName = li.original_lister\"\n"
     			+ "     			+ \" LEFT JOIN NFT_Ledger nft ON u.UserName = nft.NFTOwner\"\n"
     			+ "     			+ \" LEFT JOIN transaction_history th ON u.UserName = th.Previous_Owner \"\n"
     			+ "     			+ \"WHERE li.original_lister IS NULL AND nft.nftOwner IS NULL AND th.Previous_Owner IS NULL";
        System.out.println(sql);
     	 try {
         connect_func();      
         statement = (Statement) connect.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
         while (resultSet.next()) {
         	String email = resultSet.getString("email");
             String firstName = resultSet.getString("firstName");
             String lastName = resultSet.getString("lastName");
              
             users.add(new user(email, firstName, lastName));
         }                 
         resultSet.close();
     	 }     	 
     	 catch(SQLException e) {
     	System.out.println(e.toString());
     	 }   

     	return users;
     }
    //REEHAM
    public List<user> listPaperHands() throws SQLException {
    	List<user> users = new ArrayList<user>(); 
     	String sql = "SELECT * FROM users u LEFT JOIN list li ON u.UserName = li.Creator LEFT JOIN "
     			+ "NFT_Ledger nft ON u.UserName = nft.NFTOwner LEFT JOIN transaction_history th ON u.UserName"
     			+ "= th.PreviousOwner WHERE li.Creator IS NULL AND nft.nftOwner IS NULL AND th.PreviousOwner IS NULL";
        System.out.println(sql);
     	 try {
         connect_func();      
         statement = (Statement) connect.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);
         while (resultSet.next()) {
         	String email = resultSet.getString("email");
             String firstName = resultSet.getString("firstName");
             String lastName = resultSet.getString("lastName");
              
             users.add(new user(email, firstName, lastName));
         }                 
         resultSet.close();
     	 }     	 
     	 catch(SQLException e) {
     	System.out.println(e.toString());
     	 }   

     	return users;
     }
    
    
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "birthday DATE NOT NULL, " +
					            "address_street_num VARCHAR(4) , "+ 
					            "address_street VARCHAR(30) , "+ 
					            "address_city VARCHAR(20)," + 
					            "address_state VARCHAR(2),"+ 
					            "address_zip_code VARCHAR(5),"+ 
					            "cash_bal DECIMAL(13,2) DEFAULT 1000,"+ 
					            "PPS_bal DECIMAL(13,2) DEFAULT 0,"+
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, address_street_num, address_street, address_city, address_state, address_zip_code, cash_bal, PPS_bal)"+
        			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','1000', '0'),"+
			    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','1000', '0'),"+
			    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','1000', '0'),"+
			    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','1000', '0'),"+
			    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','1000', '0'),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','1000', '0'),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','1000', '0'),"+
			    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','1000', '0'),"+
			    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','1000', '0'),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','1000', '0'),"+
			    			"('root', 'default', 'default','pass1234', '0000-00-00', '0000', 'Default', 'Default', '0', '00000','1000','1000000000');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
