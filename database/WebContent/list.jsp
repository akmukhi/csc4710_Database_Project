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
                <form action="list" methods ="posts">
                    <p>${errorOne}</p>
                    <h1>List NFT</h1>
                    NFT: <input type ="text" name ="name"><br>
                    Please input the price of the NFT<input type ="number" name = "price">
                    When should the NFT not be listed<input type ="text" name = "date">
                    <input type = "submit" value="List"/>
                </form>
            </div>
            <form action="listMintedNFTs">
            <h1>NFTS that have been created by you.</h1>
            <c:forEach var ="listMintedNFTs" items="${user}">
                <c:if test ="${listMintedNFTs.user_name == currentUser.user_name}">
                    <c:forEach var = "NFT" items="${list}">
                        <c:if test= "${listMintedNFTs.NFTid == NFT.NFTid && history.transactionId == 'created'}">
                            <h3><c:out value= "${NFT.name}"></c:out></h3>
                            <img src = <c:out value= "${NFT.link}"></c:out>>
                        </c:if>
                    </c:forEach>

                </c:if>
            </c:forEach>
            </form>
            <form action ="soldNFTs">
            <h2>NFTS that have been sold by you.</h2>
            <c:forEach var ="sold" items="${user}">
                <c:if test="${sold.user_name == currentUser.user_name}">
                    <c:forEach var = "soldNFT" items="${list}">
                        <c:if test="${sold.NFTid == soldNFT.NFTid && sold.transactionID == 'sold'}">
                            <h3><c:out value="${soldNFT.name}"></c:out></h3>
                            <img src = <c:out value="${soldNFT.link}"></c:out>>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
            </form> 
            <form action ="boughtNFTs">
            <h2>NFTS that have been bought by you.</h2>
            <c:forEach var ="bought" items="${user}">
                <c:if test="${bought.user_name == currentUser.user_name}">
                    <c:forEach var = "boughtNFT" items="${list}">
                        <c:if test="${bought.NFTid == boughtNFT.NFTid && sold.transactionID == 'bought'}">
                            <h3><c:out value="${boughtNFT.name}"></c:out></h3>
                            <img src = <c:out value="${boughtNFT.link}"></c:out>>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
            </form>
        </body>
    </html>