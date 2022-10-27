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
   	    String user_name = resultSet.getString("user_name");
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

             
            user users = new user(email,user_name,firstName, lastName, password, birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code, cash_bal,PPS_bal);
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
		String sql = "insert into User(email, user_name, firstName, lastName, password, birthday,address_street_num, address_street,address_city,address_state,address_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
	   		preparedStatement.setString(2, users.getUserName());
			preparedStatement.setString(3, users.getFirstName());
			preparedStatement.setString(4, users.getLastName());
			preparedStatement.setString(5, users.getPassword());
			preparedStatement.setString(6, users.getBirthday());
			preparedStatement.setString(7, users.getAddress_street_num());		
			preparedStatement.setString(8, users.getAddress_street());		
			preparedStatement.setString(9, users.getAddress_city());		
			preparedStatement.setString(10, users.getAddress_state());		
			preparedStatement.setString(11, users.getAddress_zip_code());		
			preparedStatement.setInt(12, users.getCash_bal());		
			preparedStatement.setInt(13, users.getPPS_bal());		

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
        String sql = "update User set user_name=?,firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
	    	preparedStatement.setString(2, users.getUserName());
		preparedStatement.setString(3, users.getFirstName());
		preparedStatement.setString(4, users.getLastName());
		preparedStatement.setString(5, users.getPassword());
		preparedStatement.setString(6, users.getBirthday());
		preparedStatement.setString(7, users.getAddress_street_num());		
		preparedStatement.setString(8, users.getAddress_street());		
		preparedStatement.setString(9, users.getAddress_city());		
		preparedStatement.setString(10, users.getAddress_state());		
		preparedStatement.setString(11, users.getAddress_zip_code());		
		preparedStatement.setInt(12, users.getCash_bal());		
		preparedStatement.setInt(13, users.getPPS_bal());
         
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
	    String user_name = resultSet.getString("user_name");
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
            user = new user(email, user_name,firstName, lastName, password, birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code,cash_bal,PPS_bal);
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
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
						"drop table if exists User; ",
						"CREATE TABLE if not exists User(" +
						    "email VARCHAR(50) NOT NULL, " +
						    "user_name VARCHAR(50) NOT NULL," +
						    "firstName VARCHAR(10) NOT NULL," +
						    "lastName VARCHAR(10) NOT NULL," +
						    "password VARCHAR(20) NOT NULL," +
						    "birthday DATE NOT NULL," +
						    "address_street_num VARCHAR(4), " +
						    "address_street VARCHAR(30), " +
						    "address_city VARCHAR(20), " +
						    "address_state VARCHAR(2), " +
						    "address_zip_code VARCHAR(5), " +
						    "cash_bal DECIMAL(13,2) DEFAULT 0, " +
						    "PPS_bal DECIMAL(13,2) DEFAULT 0, " +
						    "userid int NOT NULL AUTO_INCREMENT, " +
						    "PRIMARY KEY (userid) "+"); "
        					};
        String[] TUPLES = {("insert into User(email, user_name, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal)"+
        			"values ('susie@gmail.com', 'SusieG', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','1000', '0'),"+
			    		 	"('don@gmail.com', 'DonC', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','1000', '0'),"+
			    	 	 	"('margarita@gmail.com', 'MargaritaL','Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','1000', '0'),"+
			    		 	"('jo@gmail.com', 'JoB', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','1000', '0'),"+
			    		 	"('wallace@gmail.com', 'WallaceM', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','1000', '0'),"+
			    		 	"('amelia@gmail.com','AmeliaP', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','1000', '0'),"+
			    			"('sophie@gmail.com', 'SophieP','Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','1000', '0'),"+
			    			"('angelo@gmail.com', 'AngeloF','Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','1000', '0'),"+
			    			"('rudy@gmail.com', 'RudyS','Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','1000', '0'),"+
			    			"('jeannette@gmail.com', 'JeannetteS','Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','1000', '0'),"+
			    			"('root', 'defaultuser','default', 'default','pass1234', '0000-00-00', '0000', 'Default', 'Default', '0', '00000','1000','1000000000');")
			    			};
	String[] INITIAL2 = {"use testdb;",
    "CREATE TABLE if not exists Addresses( ",
    "address_street_num VARCHAR(4), " +
    "address_street VARCHAR(30),  " +
    "address_city VARCHAR(20),  " +
    "address_state VARCHAR(2),  " +
    "address_zip_code VARCHAR(5),  " +
    "username VARCHAR(50) NOT NULL,  " +
    "userid int NOT NULL,  " +
    "CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES User(userid),  " +
    "address_id int NOT NULL AUTO_INCREMENT,  " +
    "PRIMARY KEY(address_id) "+"); "}; 
	    
	String[] TUPLES2 = {("Insert into Addresses(address_street_num, address_street, address_city, address_state, address_zip_code, username)" +
			     "values('1234','whatever street','detroit','MI','48202','SusieG',1)," +
			     "('1000', 'hi street', 'mama', 'MO', '12345','DonC',2)," +
			     "('1234', 'ivan street', 'tata','CO','12561','MargaritaL',3)," +
			     "('3214','marko street', 'brat', 'DU', '54321','JoB',4)," +
			     "('4500', 'frey street', 'sestra', 'MI', '48202','WallaceM',5)," +
			     "('1245', 'm8s street', 'baka', 'IL', '48000','AmeliaP',6)," +
			     "('2468', 'yolos street', 'ides', 'CM', '24680','SophieP',7)," +
			     "('4680', 'egypt street', 'lolas', 'DT', '13579','AngeloF',8)," +
			     "( '1234', 'sign street', 'samo ne tu','MH', '09876','RudyS',9)," +
			     "( '0981', 'snoop street', 'kojik', 'HW', '87654','JeannetteS',10)," +
			      "('0000', 'Default', 'Default', '0', '00000','defaultuser', 11);") };
	    
	    String[] INITIAL3 = {"drop table if exists NFT_Ledger;",
"CREATE TABLE if not exists NFT_Ledger(" +
    "NFT_name VARCHAR(150) NOT NULL, " +
    "price DECIMAL(13,2) DEFAULT 0, " +
    "FILENAME varchar(100) NOT NULL, " +
    "description VARCHAR(100), " +
    "userid int NOT NULL, " +
    "CONSTRAINT fk_NFTuserid FOREIGN KEY (userid) REFERENCES User(userid), " +
    "NFTid int NOT NULL auto_increment, " +
   " PRIMARY KEY (NFTid) " + "); "};
	    
	    String[] TUPLES3 = {"Insert into NFT_Ledger(NFT_name, price, FILENAME, description, userid)" +
"values('CAT', 100.00, 'CATNFT', 'Cat NFT', 1), " +
"('DOG', 150.00, 'DOGNFT', 'DOG NFT', 2), " +
"('DOLPHIN', 150.00, 'DOLPHINNFT', 'DOLPHIN NFT', 3), " +
"('WOLF', 150.00, 'WOLFNFT', 'WOLF NFT', 4), " +
"('BAT', 150.00, 'BATNFT', 'BAT NFT', 5), " +
"('HAWK', 150.00, 'HAWKNFT', 'HAWK NFT', 6), " +
"('EAGLE', 150.00, 'EAGLENFT', 'EAGLE NFT', 7), " +
"('BIRD', 150.00, 'BIRDNFT', 'BIRD NFT', 8), " +
"('HORSE', 150.00, 'HORSENFT', 'HORSE NFT', 9), " +
"('WHALE', 150.00, 'WHALENFT', 'WHALE NFT', 10), " +
"('DEFAULT', 150.00, 'DEFAULTNFT', 'DEFAULT NFT', 11); "};
	    
	    String[] INITIAL4 = {"drop table if exists Transaction_History;",
("CREATE TABLE if not exists Transaction_History( " +
	"NFTid int NOT NULL," +
    "CONSTRAINT fk_TH_NFTId FOREIGN KEY(NFTid) REFERENCES NFT_Ledger(NFTid)," +
    "creator_id VARCHAR(80)," +
    "customer_id VARCHAR(80)," +
    "transaction_date DATETIME," +
    "transactionId int NOT NULL auto_increment," +
    "PRIMARY KEY (transactionId) "+"); ")};
	    String[] TUPLES4 = {"Insert Into Transaction_History(NFTid, creator_id, customer_id, transaction_date)" +
"values (1,'SusieG', 'JeanetteS', '2022-10-26')," +
"(2,'JeanetteS', 'SusieG', '2022-10-24')," +
"(3,'SusieG', 'JoB', '2022-09-08')," +
"(4,'AmeliaP', 'JeanetteS', '2022-05-23')," +
"(5,'AngeloF', 'AmeliaP', '2022-07-06')," +
"(6,'SusieG', 'RudyS', '2022-03-16')," +
"(7,'SusieG', 'AngeloF', '2022-03-14')," +
"(8,'RudyS', 'JeanetteS', '2022-01-20')," +
"(9,'WallaceM', 'DonC', '2022-09-21')," +
"(10,'SusieG', 'MargaritaL', '2022-10-21')," +
"(11,'DonC', 'MargaritaL', '2022-07-01');" };
	    
	    String[] INITIAL5 = {"drop table if exists Transfer_History;",
    "CREATE TABLE if not exists Transfer_History(, " + 
    "NFTid int NOT NULL, " + 
    "CONSTRAINT fk_TrH_NFTId FOREIGN KEY(NFTid) REFERENCES NFT_Ledger(NFTid), " + 
    "current_owner VARCHAR(60), " + 
    "previous_owner VARCHAR(60), " + 
    "transfer_date DATETIME, " + 
    "transferId int NOT NULL auto_increment, " + 
    "PRIMARY KEY (transferId) "+"); "};
	    
	    String[] TUPLES5 = {"(Insert Into Transfer_History(NFTid, current_owner, previous_owner, transfer_date)" +
"values(1,'SusieG', 'JeanetteS', '2022-10-26')," +
"(2,'AmeliaP', 'DonC', '2022-09-15')," +
"(3,'MargaritaL', 'RudyS', '2022-09-23')," +
"(4,'SusieG', 'AmeliaP', '2022-08-15')," +
"(5,'RudyS', 'JeanetteS', '2022-07-08')," +
"(6,'DonC', 'MargaritaL', '2022-04-03')," +
"(7,'SusieG', 'JeanetteS', '2022-10-20')," +
"(8,'MargaritaL', 'JeanetteS', '2022-01-13')," +
"(9,'RudyS', 'DonC', '2022-01-04')," +
"(10,'SusieG', 'JeanetteS', '2022-03-05')," +
"(11,'DonC', 'JeanetteS', '2022-06-17');" };

        
        //for loop to put these in database
for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
for (int i = 0; i < INITIAL2.length; i++)	
        	statement.execute(INITIAL2[i]);
for (int i = 0; i < TUPLES2.length; i++)	
        	statement.execute(TUPLES2[i]);
for (int i = 0; i < INITIAL3.length; i++)	
        	statement.execute(INITIAL3[i]);
for (int i = 0; i < TUPLES3.length; i++)	
        	statement.execute(TUPLES3[i]);
for (int i = 0; i < INITIAL4.length; i++)	
        	statement.execute(INITIAL4[i]);
for (int i = 0; i < TUPLES4.length; i++)	
        	statement.execute(TUPLES4[i]);
for (int i = 0; i < INITIAL5.length; i++)	
        	statement.execute(INITIAL5[i]);
for (int i = 0; i < TUPLES5.length; i++)	
        	statement.execute(TUPLES5[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
