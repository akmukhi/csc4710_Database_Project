<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All User list</title>
</head>
<body>
   <div align="center">
   <h2>Search People</h2>
    <br />
   	<a href="manageNFT.jsp" target="_self">Return to HomePage</a>
   <br />
    <br />
    <form action="profilesearch" method="post">
                <input type="text" name="profilesearch" size="45"
                    onfocus="this.value=''"> <input type="submit"
                    value="Search People" />
      </form>
      
        <table border="1" cellpadding="5">
        <br /> <br />
            <tr>
				<th>Users</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                	<td>
					<form action="viewprofilelist" method="get">
					<input  name="email" type="submit" value="${users.email}" onClick="viewprofilelist"/>
					</form>
					</td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>