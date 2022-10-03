<%-- 
    Document   : List
    Created on : Feb 19, 2021, 5:18:51 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1px solid black">
            <tr>
                <th>username</th>
                <th>password</th>
                <th>lastname</th>
                <th>isAdmin</th>
            </tr>
            <c:forEach begin="1" end="2">
                <tr> 
                    <td>ADMIN</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach begin="1" end="${endP}" var="i">
            <a href="#">${i}</a>
        </c:forEach>
    </body>
</html>
