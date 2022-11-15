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
            <c:forEach var ="history" items="${NFTHistory}">
                <c:if test ="${history.user == buyer.user}">
                    <c:forEach var = "NFT" items="${list}">
                        <c:if test= "${history.NFTid == NFT.NFTid && history.transaction == 'has been sold'}">
                            <h3><c:out value= "${NFT.description}"></c:out></h3>
                            <img src = <c:out value= "${NFT.link}"></c:out>>
                            <p><c:out value="${NFT.description}"></c:out></p>
                        </c:if>
                    </c:forEach>

                </c:if>
            </c:forEach>
        </body>
    </html>
