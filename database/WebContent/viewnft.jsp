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
<title>Activity page</title>
</head>

<center><h1>Welcome</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 </center>
	</body>



    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Viewing selected NFT</h2></caption>
            <tr>
              	<th>NFT Id</th>
                <th>NFT Name</th>
                <th>Description</th>
                <th>Listing Price</th>
                <th>Image URL</th>
                <th>Listing Time Duration</th>
                <th>Owner</th>
                <th></th>
            </tr>
            <c:forEach var="NFTs" items="${listNFT}">
                <tr style="text-align:center">
               	    <td>"${NFTs.NFTid}" </td>
                    <td>"${NFTs.nftName}" </td>
                    <td>"${NFTs.nftDescription}"</td>
                    <td>"${NFTs.listingPrice}"</td>
                    <td><img src="${NFTs.uploadNFT}" width= "200" height="200"></td>
                    <td>"${NFTs.listingTime}" days</td>
                    <td>"${NFTs.nftOwner}"</td> 
                    <td><form action="buy"><input  name="buy" type="submit" value="buy"/>	</form></td>
                 </tr>
            </c:forEach>
          </table>       
    <br />
   	<a href="manageNFT.jsp" target="_self">Return to HomePage</a>
   <br />
	</div>
<body>

</body>
</html>
