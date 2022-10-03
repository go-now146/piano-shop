/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanntk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private final String loginController = "LoginController";
    private final String searchController = "SearchController";
    private final String loginPage = "login.html";
    private final String updateController = "UpdateController";
    private final String deleteController = "DeleteController";
    private final String nullController = "NullController";
    private final String addCourse = "AddItemController";
    private final String viewCourse = "viewCart.jsp";
    private final String deletedItem = "DeleteItemServlet";
    private final String createAccount = "CreateAccountServlet";
   // private final String homePage = "Homecontroller";
     private final String homePage = "ListProduct";
     private final String updateCart = "UpdateCartServlet";
 //   private final String create_Err = ;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String button = request.getParameter("btAction");
        String url = homePage;
        try  {
            if(button == null){
               
            }else if(button.equals("Search")){
                url = searchController;
            }else if(button.equals("Login")){
                url = loginController;
            }else if(button.equals("Update")){
                url = updateController;
            }else if(button.equals("Delete")){
                url = deleteController;
            }else if(button.equals("Add Course To Your Cart")){
                url = addCourse;
            }else if(button.equals("View Your Cart")) {
                url = viewCourse ;
            }else if (button.equals("Update Quantity")){
                url = updateCart;
            }else if(button.equals("Remove Selected Items")) {
                url = deletedItem ;
            }else if(button.equals("Create New Account")) {
                url = createAccount;
            }
            
        }catch(Exception e){
           
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
