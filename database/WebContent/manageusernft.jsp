<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<a href="login.jsp"target ="_self" > logout</a><br><br> 
</head>

<center><h1>Manage your NFTs</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 

<a href="activitypage.jsp" target="_self">Return to Previous Page</a>
<br /><br />

		   
		        <form action="listMintedNFTs" method="get">
 				<input type="submit"  name="listMintedNFTs" value="Show Your Minted NFTs" />
     			 </form> <br><br>
     			 
     			   <form action="boughtNFTs" method="get">
 				<input type="submit"  name="boughtNFTs" value="Show Your Purchased NFTs" />
     			 </form> <br><br>
     			 
     			   <form action="soldNFTs" method="get">
 				<input type="submit"  name="soldNFTs" value="Show Your Sold NFTs" />
     			 </form> <br><br>
		 </center>
	</body>



    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Insert Title Here</h2></caption>
            <tr>
              	<th>NFT Id</th>
                <th>NFT Name</th>
                <th>Description</th>
                <th>Listing Price</th>
                <th>Image URL</th>
                <th>Listing Time Duration</th>
                <th>Owner</th>
            </tr>
            <c:forEach var="NFTs" items="${listNFT2}">
                <tr style="text-align:center">
               	    <td>"${NFTs.NFTid}" </td>         	    
                    <td>${NFTs.nftName}"</td>
                    <td>"${NFTs.nftDescription}"</td>
                    <td>"${NFTs.listingPrice}"</td>
                    <td><img src="${NFTs.uploadNFT}" width= "200" height="200"></td>
                    <td>"${NFTs.listingTime}" days</td>
                    <td>"${NFTs.nftOwner}"</td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>