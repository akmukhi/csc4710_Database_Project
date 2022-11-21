<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Create an NFT</title>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="createnft">
			<table border="1" cellpadding="5">
				<tr>
					<th>NFT Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftName" size="45"  value="NFTName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Description: </th>
					<td align="center" colspan="3">
						<input type="text" name="nftDescription" size="45" value="NFTDescription" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Listing Price: </th>
					<td align="center" colspan="3">
						<input type="number" name="listingPrice" size="45" value="ListingPrice" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Upload NFT: </th>
					<td align="center" colspan="3">
						<input type="text" name="uploadNFT" size="45" value="URL" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Listing Time</th>
					<td align="center" colspan="3">
						<input type="text" name="listingTime" size="45" value="ListingTime" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Owner</th>
					<td align="center" colspan="3">
						<input type="text" name="nftOwner" size="45" value="nftOwner" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="createnft"/>
					</td>
				</tr>
			</table>
			<a href="activitypage.jsp" target="_self">Return to Previous Page</a>
		</form>
	</div>

	<h1>NFT that current user has created</h1>
	<div class = "flexcontainer">
		<c:forEach var = "history" items="${NFTHistory}">
			<c:if test = "${history.user_name == currentUser.user_name}">
				<c:forEach var = "nft" items="${list}">
					<c:if test ="${history.NFTid == nft.NFTid && history.transaction == 'listed'}">
						<h3><c:out value = "${nft.NFTid}"></c:out></h3>
						<img src = <c:out value= "${nft.link}"></c:out>
						<p><c:out value = "${nft.nftDescription}"></c:out></p>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
	</div>
</body>