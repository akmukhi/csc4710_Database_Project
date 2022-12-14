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
	    private statisticsDAO statisticsDAO = new statisticsDAO();
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
	    	statisticsDAO = new statisticsDAO();
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
        	 case "/listactivenft": 
                 listactivenft(request, response);           	
                 break;
        	 case "/showinactivenfts": 
                 showinactivenfts(request, response);           	
                 break;
        	 case "/profilesearch": 
                 profilesearch(request, response);           	
                 break;
        	 case "/viewprofilelist":
        		 viewprofilelist(request, response);
        	 case "/viewnftpage":
        		 viewnftpage(request, response);
        	 case "/listMintedNFTs":
        		 listMintedNFTs(request, response);
        	 case "/soldNFTs":
        		 soldNFTs(request, response);
        	 case "/boughtNFTs":
        		 boughtNFTs(request, response);
 			case "/diamondHands":
				diamond(request, response);
			case "/goodBuyer":
				goodBuyer(request, response);
			case "/bigSeller":
				bigSeller(request, response);
			case "/bigBuyer":
				bigBuyer(request, response);
			case "/bigCreator":
				bigCreator(request, response);	
			 case "/listMaxOwners":
			    	listMaxOwners(request, response);
			    	break;
		    case "/listCommonNFTs":
		    	listCommonNFTs(request, response);
		    	break;
		    case "listInactiveUsers":
		    	listInactiveUsers(request, response);
		    	break;
		    case "/listPaperHands":
		    	listPaperHands(request, response);
		    	break;
		    case "/listStatistics":
		    	ListStatistics(request, response);
		    	break;
	    	}

   	
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    //PROJECT 4
	    //aakash implementation
	    //diamond implementation
		private void diamond(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("diamond hands started: 00000000000000000000000000000");
			List<user> diamondHands = userDAO.diamond();
			request.setAttribute("diamondHands", diamondHands);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request, response);
			System.out.println("diamond hands ended: 11111111111111111111111111111111");
		}

		//good buyer implementation
		private void goodBuyer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Good Buyer started: 000000000000000000");
			List<user> goodBuyer = userDAO.goodBuyer();
			request.setAttribute("goodBuyer", goodBuyer);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("Good Buyer ended: 1111111111111111111111111");
		}

		//Big Seller
		private void bigSeller(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Big Seller started: 000000000000000000");
			List<user> bigSeller = userDAO.bigSeller();
			request.setAttribute("bigSeller", bigSeller);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("Big Seller ended: 1111111111111111111111111");
		}
	    
		//Big Creators
		private void bigCreator(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Big Creators started: 000000000000000000");
			List<user> bigCreator = userDAO.bigCreator();
			request.setAttribute("bigCreator", bigCreator);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("Big Creator ended: 1111111111111111111111111");
		}
		
		//Big buyer implementation
		private void bigBuyer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Big Buyer started: 000000000000000000");
			List<user> bigBuyer = userDAO.bigBuyer();
			request.setAttribute("bigBuyer", bigBuyer);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("Big Buyer ended: 1111111111111111111111111");
		}
	    
	    //Reehams Implementation
		//LIST MAX OWNERS
	    private void listMaxOwners(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("listNFT started: 00000000000000000000000000000000000");
 	       
			List<nft> NFTs = nftDAO.listMaxOwners();
			request.setAttribute("listNFT3", NFTs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("managerootviewnft.jsp");
			dispatcher.forward(request, response);
			
			
 	        System.out.println("listNFT finished: 111111111111111111111111111111111111");
 	    }
	    
    
	    //lists common NFT
	    private void listCommonNFTs(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("listNFT started: 00000000000000000000000000000000000");
			String User1 = request.getParameter("User1");
			String User2 = request.getParameter("User2");
			List<nft> NFTs = nftDAO.listCommonNFTs(User1, User2);
			request.setAttribute("listNFT3", NFTs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("managerootviewnft.jsp");
			dispatcher.forward(request, response);
			
			
 	        System.out.println("listNFT finished: 111111111111111111111111111111111111");
 	    }
    //REEHAM USER
		private void listInactiveUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Inactive Users started: 000000000000000000");
			List<user> users = userDAO.listInactiveUsers();
			request.setAttribute("listUser", users);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("InactiveUsers ended: 1111111111111111111111111");
		}
		private void listPaperHands(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("Paper Hands started: 000000000000000000");
			List<user> users = userDAO.listPaperHands();
			request.setAttribute("listUser", users);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("Paper Hands ended: 1111111111111111111111111");
		}

		private void ListStatistics(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			System.out.println("List Stat started: 000000000000000000");
			String user = request.getParameter("User");
			List<statistics> stats = statisticsDAO.ListStatistics(user);
			request.setAttribute("listSTAT", stats);
			request.getRequestDispatcher("managerootviewuser.jsp").forward(request,response);
			System.out.println("List Stat ended: 1111111111111111111111111");
		}
	    
	    //AAKASH LIST//////////////////////////////////////////////////////
		    private void listMintedNFTs(HttpServletRequest request, HttpServletResponse response)
	 	            throws SQLException, IOException, ServletException {
	 	        System.out.println("listNFT started: 00000000000000000000000000000000000");
	 	       
	 	       System.out.println(currentUser);
	 	        
	 	        
				List<nft> NFTs = nftDAO.listOwnernft(currentUser);
				request.setAttribute("listNFT2", NFTs);
				RequestDispatcher dispatcher = request.getRequestDispatcher("manageusernft.jsp");
				dispatcher.forward(request, response);
				
				
	 	        System.out.println("listNFT finished: 111111111111111111111111111111111111");
	 	    }
	    
		private void soldNFTs(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ServletException
		{
			System.out.println("Sold NFTS has started: 00000000000000000000");
			
			
			
			List<nft> NFTs = nftDAO.listSold(currentUser);
			request.setAttribute("listNFT2", NFTs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("manageusernft.jsp");
			dispatcher.forward(request, response);
			
			
			System.out.println("soldNFT finished: 11111111111111111111111111");
		}
		
		private void boughtNFTs(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			System.out.println("Bought NFTS has started: 00000000000000000000");
					
			
			List<nft> NFTs  = nftDAO.listBought(currentUser);
			request.setAttribute("listNFT2", NFTs );
			RequestDispatcher dispatcher = request.getRequestDispatcher("manageusernft.jsp");
			dispatcher.forward(request, response);
			
			
			System.out.println("Bought NFT finished: 11111111111111111111111111");
		}    
	    
	    //////////////////////////////////////////////////////////////////
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> NFTs = userDAO.listAllUsers();
	        request.setAttribute("listUser", NFTs );       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersearch.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }

	    private void viewprofilelist(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("viewprofile started: 00000000000000000000000000000000000");
	        String email = request.getParameter("email");
	        System.out.println(email);
	        
	        List<user> users = userDAO.viewprofilelist(email);
	        System.out.println(users);
	        request.setAttribute("listUser", users);     
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewprofile.jsp");
			dispatcher.forward(request, response);
	        	        
	        
	        System.out.println("viewprofile finished: 111111111111111111111111111111111111");
	    }
	    
	    private void viewnftpage(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("viewprofile started: 00000000000000000000000000000000000");
	        String nftName = request.getParameter("nftName");
	        System.out.println(nftName);
	        
	        List<nft> NFTs = nftDAO.viewnftpage(nftName);
	        System.out.println(NFTs);
	        request.setAttribute("listNFT", NFTs);     
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewnft.jsp");
			dispatcher.forward(request, response);
	        	        
	        
	        System.out.println("viewprofile finished: 111111111111111111111111111111111111");
	    }


	    private void listNFTs(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("listNFT started: 00000000000000000000000000000000000");

 	     
 	        List<nft> NFTs = nftDAO.listnfts();
 	        System.out.println(NFTs);
 	        request.setAttribute("listNFT", NFTs);  
			

			user user = userDAO.getUser(currentUser);
			request.setAttribute("currentUser", user);
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("manageNFT.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("listNFT finished: 111111111111111111111111111111111111");
	 	    }
	    private void showinactivenfts(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("listNFT started: 00000000000000000000000000000000000");

 	     
 	        List<nft> NFTs = nftDAO.showinactivenfts();
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
	    
	    private void profilesearch(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("search started: 00000000000000000000000000000000000");

 	        String email = request.getParameter("profilesearch");
 	        List<user> users = userDAO.profilesearch(email);
 	        System.out.println(users);
 	        request.setAttribute("listUser", users);       
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("usersearch.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("search finished: 111111111111111111111111111111111111");
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
	    	
	    	System.out.println(username);
	    	System.out.println(nftName);
	    	
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
			
			nftDAO.transfer(nftName, nftOwner);
			request.setAttribute("noNFTStr", "You don't own any NFT's");
			RequestDispatcher dispatcher = request.getRequestDispatcher("transfering.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
		
		private void listactivenft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("list started: 0000000");
			String nftName = request.getParameter("nftName");
			int active = 1;
			
			nftDAO.listactivenft(nftName, active);
			RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
			dispatcher.forward(request, response);
			System.out.println("list finished: 11111111111");
		}
	
	
	        
	    
}
	        
	        
	    
	        
	        
	        
	    


