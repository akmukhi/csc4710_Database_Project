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
</body>
