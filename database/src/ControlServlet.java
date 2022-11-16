import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private nftDAO nftDAO = new nftDAO();
	    private listDAO listDAO = new listDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	nftDAO = new nftDAO();
	    	listDAO = new listDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/createnft":
        		createnft(request, response);
        		break;
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/listUser": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/listNFTs": 
                 System.out.println("The action is: listNFT");
                 listNFTs(request, response);           	
                 break;
        	 case "/transfer": 
                 transfer(request, response);           	
                 break;
        	 case "/nftsearch": 
                 nftsearch(request, response);           	
                 break;
        	 case "/buy": 
                 buy(request, response);           	
                 break;
        	 case "/list": 
                 listTrade(request, response);           	
                 break;
	    	}
        	
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }

	    private void listNFTs(HttpServletRequest request, HttpServletResponse response)
	 	            throws SQLException, IOException, ServletException {
	 	        System.out.println("listNFT started: 00000000000000000000000000000000000");

	 	     
	 	        List<nft> NFTs = nftDAO.listnfts();
	 	        System.out.println(NFTs);
	 	        request.setAttribute("listNFT", NFTs);       
	 	        RequestDispatcher dispatcher = request.getRequestDispatcher("manageNFT.jsp");       
	 	        dispatcher.forward(request, response);
	 	     
	 	        System.out.println("listNFT finished: 111111111111111111111111111111111111");
	 	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String address_street_num = request.getParameter("address_street_num"); 
	   	 	String address_street = request.getParameter("address_street"); 
	   	 	String address_city = request.getParameter("address_city"); 
	   	 	String address_state = request.getParameter("address_state"); 
	   	 	String address_zip_code = request.getParameter("address_zip_code"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, birthday, address_street_num,  address_street,  address_city,  address_state,  address_zip_code, 1000,0);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }  
	    
	    
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	    private void createnft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String nftName = request.getParameter("nftName");
	   	 	String nftDescription  = request.getParameter("nftDescription");
	   	 	int listingPrice = Integer.parseInt(request.getParameter("listingPrice"));
	   	 	String uploadNFT = request.getParameter("uploadNFT");
	   	 	String listingTime = request.getParameter("listingTime");
	   	 	String nftOwner = request.getParameter("nftOwner");
	   	 	if(!nftDAO.checknft(nftName)) {
		   	 		System.out.println("Creation Successful!");
		            nft nfts = new nft(nftName ,nftDescription ,listingPrice ,uploadNFT ,listingTime, nftOwner);
		   	 		nftDAO.insert(nfts);
		   	 		response.sendRedirect("activitypage.jsp");
	   	 	}
	   	 	else
	   	 	{
	   	 	System.out.println("Failed");
	  		 request.setAttribute("error","Registration failed");
	  		 request.getRequestDispatcher("createnft.jsp").forward(request, response);
	   	 		
	   	 	}

	    } 
	    
	    
	    private void nftsearch(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("search started: 00000000000000000000000000000000000");

 	        String nftName = request.getParameter("nftsearch");
 	        List<nft> NFTs = nftDAO.search(nftName);
 	        System.out.println(NFTs);
 	        request.setAttribute("listNFT", NFTs);       
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("manageNFT.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("search finished: 111111111111111111111111111111111111");
 	    }
	    
	    private void buy(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
	    	System.out.println("buy started: 00000000000000000000000000000000000");
	    	String nftName = request.getParameter("nftName");
	    	user username = userDAO.getUser(currentUser);
	    	
	    	nftDAO.transfer(nftName, username.email);
	    	System.out.println(nftName);
	    	System.out.println(username.email);
	    	nftDAO.decfunds(username.email, nftDAO.getlistingprice(nftName), nftDAO.getcashbal(username.email));
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("manageNFT.jsp");       
	        dispatcher.forward(request, response);
	        System.out.println("buy finished: 111111111111111111111111111111111111");
	    	
	    }
	    //AAKASH WORK
	    
		private void listTrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException
		{
			System.out.println("listTrade started: 00000");
			List<list> list = listDAO.listTransactions();
			request.setAttribute("listTrade", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
			dispatcher.forward(request, response);
			System.out.println("listTrade finished: 11111111");
		}

		private void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String nftOwner = request.getParameter("nftOwner");
			request.setAttribute("noNFTStr", "You don't own any NFT's");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/activity");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	
	        
	    
}