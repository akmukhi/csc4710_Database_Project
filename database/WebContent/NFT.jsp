<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>NFT trading plateform</title>
        </head>
        <a href="login.jsp" target ="_self" > logout</a><br><br>

        <h1>List of all NFTs</h1>
        <div align="center">
            <table border="1" cellpadding="6">
                <caption><h2>List of NFTs</h2></caption>
                <tr>
                    <th>NFT id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Date</th>
                    <th>Link</th>
                </tr>
                <c:forEach var="NFTs" items="${getNFT}">
                    <tr style="text-align:center">
                        <td>"${NFT.NFTid}"</td>
                        <td>"${NFT.Name}"</td>
                        <td>"${NFT.Description}"</td>
                        <td>"${NFT.Date}"</td>
                        <td>"${NFT.Link}"</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </html>