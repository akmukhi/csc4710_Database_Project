<!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="IOS-8859-1">
        <title>History</title>
    </head>
    <body>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of History</h2></caption>
                <tr>
                    <th>History</th>
                    <th>User</th>
                    <th>NFTid</th>
                    <th>transactionDetails</th>
                    <th>transaction</th>
                    <th>Date</th>
                </tr>
                <c:forEach var ="NFTHistory" items="${NFTHistory}">
                    <tr style="text-align:center">
                        <td><c:out value="${NFTHistory.event}"></c:out></td>
                        <td><c:out value="${NFTHistory.user}"></c:out></td>
                        <td><c:out value="${NFTHistory.NFTid}"></c:out></td>
                        <td><c:out value="${NFTHistory.transactionDetails}"></c:out></td>
                        <td><c:out value="${NFTHistory.transaction}"></c:out></td>
                        <td><c:out value="${NFTHistory.date}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>-->