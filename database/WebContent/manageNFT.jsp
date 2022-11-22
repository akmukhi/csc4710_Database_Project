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
		 <a href="createnft.jsp"target ="_self" > Create NFT</a><br><br> 
		 <a href="transfering.jsp"target ="_self" >Complete a transfer</a><br><br>
		  <a href="list.jsp"target ="_self" >List an NFT</a><br><br>
		   <a href="usersearch.jsp"target ="_self" >Search Users</a><br><br>
		   <a href="manageusernft.jsp"target ="_self" >Manage your NFTs</a><br><br>
		   
		        <form action="showinactivenfts" method="post">
 <input type="submit"  name="showinactivenfts" value="Show Your Inactive NFTs" />
      </form> <br><br>
		 </center>
	</body>

    <div align="center">
    <form action="nftsearch" method="post">
                <input type="text" name="nftsearch" size="45"
                    onfocus="this.value=''"> <input type="submit"
                    value="Search Nft" />
      </form>

        <table border="1" cellpadding="6">
            <caption><h2>All available NFTs</h2></caption>
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
                    <td>
                    <form action="viewnftpage" method="get">
                    <input name="nftName" type="submit" value="${NFTs.nftName}" onClick="viewnftpage"/>
                    </form>
                     </td>
                    <td>"${NFTs.nftDescription}"</td>
                    <td>"${NFTs.listingPrice}"</td>
                    <td><img src="${NFTs.uploadNFT}" width= "200" height="200"></td>
                    <td>"${NFTs.listingTime}" days</td>
                    <td>"${NFTs.nftOwner}"</td> <td><form action="buy"><input  name="buy" type="submit" value="buy"/>	</form></td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>
