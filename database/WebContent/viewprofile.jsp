<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>View Profile</title>
</head>
<body>
   <div align="center">
   <h2>Viewing </h2>
      <table border="1" cellpadding="6">
            <caption><h2>Profile</h2></caption>
            <tr>
              	<th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
               	    <td>"${users.email}" </td>
                    <td>"${users.firstName}" </td>
                    <td>"${users.lastName}"</td>
                 </tr>
            </c:forEach>
          </table>
    <br />
   	<a href="manageNFT.jsp" target="_self">Return to HomePage</a>
   <br />
    <br />
    </div>   
</body>
</html>
