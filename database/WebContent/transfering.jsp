<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Transfering an NFT</title>
    </head>
    <body>
        <h1>NFT Transfer Page</h1>
        <h3>Your NFTS:</h3>
        <div class ="flexcontainer">
            <c:forEach var="NFTS" items ="${NFTuser}">
                <div class ="UserNFT">
                    <h4 class ="name"><c:out value ="${NFT.name}"></c:out></h4>
                    <p class ="description"><c:out value ="${NFT.link}"></c:out></p>
                </div>
            </c:forEach>
        </div>
        <div align="center">
            <form action="transfer">
                <table border = "1" cellpadding="4">
                    <tr>
                        <th>Transfer an NFT to a User</th>
                        <td align = "center" colspan="3">
                            <input type ="text" name ="nftOwner" size="34" value="nftOwner" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>NFT that you want to transfer: </th>
                        <td align="center" colspan="3">
                        <input type="text" name="nftName"  value="nftName" onfocus="this.value=''" size="34">
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="4">
                            <input type="submit" value="transfer">
                        </td>
                    </tr>
                </table>
            </form>
        </div>


    </body>
</html>
