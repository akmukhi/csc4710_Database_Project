<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE>
    <html>
        <head>
            <meta charset = "ISO-8859-1">
            <title>Listing an NFT</title>
        </head>
        <body>
            <div class = "listBlock">
                <form action="List" methods ="posts">
                    <p>${errorOne}</p>
                    <h1>List NFT</h1>
                    NFT: <input type ="text" name ="name"><br>
                    Please input the price of the NFT<input type ="number" name = "price">
                    When should the NFT not be listed<input type ="text" name = "date">
                    <input type = "submit" value="List"/>
                </form>
            </div>
            <h1>NFTS that have been created by you.</h1>
            <c:forEach var ="history" items="${user}">
                <c:if test ="${history.user_name == currentUser.user_name}">
                    <c:forEach var = "NFT" items="${list}">
                        <c:if test= "${history.NFTid == NFT.NFTid && history.transactionId == 'has been sold'}">
                            <h3><c:out value= "${NFT.name}"></c:out></h3>
                            <img src = <c:out value= "${NFT.link}"></c:out>>
                        </c:if>
                    </c:forEach>

                </c:if>
            </c:forEach>

            <h2>NFTS that have been bought/sold by you.</h2>
    <!--        <c:forEach var = "transactionHistory" items="${user}">
                <c:if test = "${transactionHistory.user_name == transactionHistory.user_name}">
                    <c:forEach var = "NFT" items = "${nft}">
                        <c:if test= "${transactionHistory.NFTid == NFT.NFTid && transactionHistory.}">
                            <h3><c:out value="${NFT.nftName}"></c:out></h3>
                            <img scr = <c:out value = "${NFT.uploadNFT}"></c:out>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>-->
        </body>
    </html>