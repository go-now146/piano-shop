/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanntk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loanntk.registration.RegistrationDAO;
import loanntk.registration.RegistrationDTO;
import loanntk.registration.RegistrationErr;

/**
 *
 * @author Admin
 */
public class CreateAccountServlet extends HttpServlet {

    private final String errorAccount = "createNewAccount.jsp";
    private final String loginPage = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
        RegistrationErr error = new RegistrationErr();
        String url = errorAccount;
        boolean checkValidation = false;

        try  {
            //1.Check all user's constraint
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                checkValidation = true;
                error.setUsernameLengthErr("Username reqquired input 6 - 20 character");
            }

            if (password.trim().length() < 6 || password.trim().length() > 30) {
                checkValidation = true;
                error.setPasswordLengthErr("Password required input 6 - 30 character");

            } else if (!confirm.trim().equals(password.trim())) {
                checkValidation = true;
                error.setConfirmNotMatch("Incorrect password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                checkValidation = true;
                error.setFullNameLengthErr("Fullname require input 6 - 50 character");
            }
                if (checkValidation) {
                    request.setAttribute("RegistrationErr", error);
                } else {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.insertRecord(username, password, fullname, false);

                    if (result) {
                        url = loginPage;
                    }
                }
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _SQL" + msg);
            if (msg.contains("duplicate")) {
                error.setUsernameIsExitsted(username + "is existed");
                request.setAttribute("RegistrationErr", error);
            }

        } catch (NamingException ex) {
            log("CreateAccountServlet _Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
           
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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