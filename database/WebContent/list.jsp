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
        </body>
    </html>