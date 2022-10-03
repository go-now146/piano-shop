<%-- 
    Document   : viewCart
    Created on : Aug 12, 2022, 12:58:18 PM
    Author     : Admin
--%>

<%@page import="java.awt.ItemSelectable"%>
<%@page import="java.util.Map"%>
<%@page import="loanntk.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Store</title>
    </head>
    <body>
        <%
            // 1. Cust goes to cart place
            if (session != null) {
                // 2. Cust takes the cart
                CartObj cart = (CartObj)session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItems() != null) {
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Title</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                                                 </tr>
                            </thead>
                            <tbody>
                            <form action="MainController">
                                <%
                                    Map<String, Integer> items = cart.getItems();
                                    int count = 0;
                                    for (Map.Entry item : items.entrySet()) {
                                %>
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        .</td>
                                        <td>
                                            <%= item.getKey() %>
                                        </td>
                                        <td>
                                            <%= item.getValue() %>
                                        </td>
                                         <td>
                                            <input type="checkbox" name="chkItem"
                                                   value="<%= item.getKey() %>" />
                                        </td>
                                    </tr>
                                <%
                                    } // end traverse entry
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="courseStore.html">Add More Course To Your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" name="btAction" />
                                        <input type="submit" name="btAction" value="Update Quantity">
                                    </td>
                                </tr>
                            </form>
                            </tbody>
                        </table>
                                
                        </form>
                        <%
                        return;
                    } // end items have existed
                } // end cart has existed
            } // end cart place has existed
        %>
        <h2>
            No cart is existed!!!
        </h2>
    </body>
</html>
