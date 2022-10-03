<%-- 
    Document   : search
    Created on : Aug 16, 2022, 1:56:00 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="loanntk.registration.RegistrationDTO"%>
<%@page import="loanntk.registration.RegistrationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Search Page</h1>
        <font color="red">
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                for (Cookie cookie : cookies) {
                    String temp = cookie.getName();
                    if (!temp.equals("JESSSIONID")) {
                        username = temp;
                    }
                }
        %>
        Welcome, <%= username%>
        <%
            }

        %>
        </font>
        <form action="MainController" method="POST">
            Search Value
            <input type="text" name="txtSearchValue" value="" /> <br/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
        <%            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %>


        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>

            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "MainController?btAction=Delete&pk=" + dto.getUsername() + "&lastSearchValue=" + searchValue;

                %>
            <form action="MainController">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkRole" value="ADMIN" 
                               <%
                                   if (dto.isIsAdmin()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />

                        <input type="submit" name="btAction" value="Update" />
                       
                    </td>

                </tr>
                 
            </form>
            <%
                }
            %>
        </tbody>

    </table> </br>
    
         <a href="login.html" >Continue Login</a>
    <%
    } else {
    %>
    <h2>No record is matched!!!</h2>
    <%
            }
        }
    %>
</body>
</html>
