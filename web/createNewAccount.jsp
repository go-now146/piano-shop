<%-- 
    Document   : createNewAccount
    Created on : Aug 12, 2022, 1:22:21 PM
    Author     : Admin
--%>

<%@page import="loanntk.registration.RegistrationErr"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account </title>
    </head>
    <body>
        <h1>Create Account </h1>
        <form action="MainController"/>

        Username* <input type="text" name="txtUsername" value="<%= request.getParameter("txtUsername")%>" /> e.g 6 - 20 chars<br/>
        <font color="red">
        <%
            RegistrationErr error = (RegistrationErr) request.getAttribute("RegistrationErr");
            if (error != null) {
                if (error.getUsernameLengthErr() != null) {
        %>

        <%= error.getUsernameLengthErr()%> <br/>
        <%
                }
            }
        %>

        </font>


        Password* <input type="password" name="txtPassword" value="" /> e.g 6 - 30 chars<br/>
        <font color="red">
        <%
            if (error != null) {
                if (error.getPasswordLengthErr() != null) {
        %>

        <%= error.getPasswordLengthErr()%> <br/>
        <%
                }
            }
        %>

        </font>



        Confirm*  <input type="password" name="txtConfirm" value="" /><br/>
        <font color="red">
        <%
            if (error != null) {
                if (error.getConfirmNotMatch() != null) {
        %>

        <%= error.getConfirmNotMatch()%> <br/>
        <%
                }
            }
        %>

        </font>




        Full Name*  <input type="text" name="txtFullName" value="" /> e.g 2 - 50 chars<br/>
        <font color="red">
        <%
            if (error != null) {
                if (error.getFullNameLengthErr() != null) {
        %>

        <%= error.getFullNameLengthErr()%> <br/>
        <%
                }
            }
        %>

        </font>

        <input type="submit" value="Create New Account" name="btAction" />
        <input type="reset" value="Reset"/> 

    </form><br/>
    <font color="red">
    <%
        if (error != null) {
            if (error.getUsernameIsExitsted() != null) {
    %>

    <%= error.getUsernameIsExitsted()%> <br/>
    <%
            }
        }
    %>

    </font>


</body>


</html>
