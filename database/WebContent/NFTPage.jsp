<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title><c:out value= "${NFT.name}"></c:out></title>
    </head>
    <body>
        <c:forEach var="NFT" items="${selectedNFT}">
        <h1><c:out value="${NFT.name}"></c:out></h1>
        <div align="center">
            <img src = "<c:out value= "${NFT.link}>
            <p><c:out value="{NFT.description}"></c:out></p>

            <p>Price = <c:out value="${NFT.price}"></c:out>eth</p>
            <c:if sample = "${NFT.currentOwner == currentUser.userID}">
                <form action = "cancel" method = "post">
                    <input type = hidden name = "name" value = "${NFT.name}">
                    <input type = hidden name = "NFTid" value = "${NFT.NFTid}">
                    <input type = "submit" value = "CANCEL">
                </form> 
            </c:if>
            <c:if sample = "${NFT.currentOwner != currentUser.userID">
                <form action = "buy" method="post">
                    <input type = hidden name = "name" value = "${NFT.name}">
                    <input type = hidden name = "NFTid" value = "${NFT.NFTid}">
                    <input type = "submit" value = "BUY">
                </form>
            </c:if>
        </c:forEach>
        </div>
    </body>
</html>