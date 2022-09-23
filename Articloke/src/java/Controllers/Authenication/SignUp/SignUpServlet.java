/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Authenication.SignUp;

import Respiratory.User.UserDAO;
import Respiratory.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "";
        try {
            String username = request.getParameter("usernamePara");
            String password = request.getParameter("passwordPara");
            String confirm = request.getParameter("confirmPara");
            String firstName = request.getParameter("firstNamePara");
            String lastName = request.getParameter("lastNamePara");

            request.setAttribute("usernamePara", username);
            request.setAttribute("firstNamePara", firstName);
            request.setAttribute("lastNamePara", lastName);
            if (ErrorMessage.checkUsernameExist(username) != null) {
                request.setAttribute("usernameError", ErrorMessage.checkUsernameExist(username));
                URL = "Auth.jsp";
            } else if (ErrorMessage.checkUsernameLength(username) != null
                    || ErrorMessage.checkPasswordLength(password) != null
                    || ErrorMessage.checkPasswordConfirm(password, confirm) != null
                    || ErrorMessage.checkFirstNameLength(firstName) != null
                    || ErrorMessage.checkLastNameLength(lastName) != null) {
                request.setAttribute("usernameError", ErrorMessage.checkUsernameLength(username));
                request.setAttribute("passwordError", ErrorMessage.checkPasswordLength(password));
                request.setAttribute("confirmError", ErrorMessage.checkPasswordConfirm(password, confirm));
                request.setAttribute("firstNameError", ErrorMessage.checkFirstNameLength(firstName));
                request.setAttribute("lastNameError", ErrorMessage.checkLastNameLength(lastName));
                URL = "Auth.jsp";
            } else {
                UserDTO user = new UserDTO(username, URL, password, firstName, lastName, null, null, null, null, null, null);
                try {
                    UserDAO.addUser(user);
                } catch (SQLException ex) {
                    System.out.println("SQLException - SignUp");
                } catch (ClassNotFoundException ex) {
                    System.out.println("ClassNotFoundException - SignUp");
                }
                Cookie usernameC = new Cookie("username", username);
                Cookie passwordC = new Cookie("password", password);
                usernameC.setMaxAge(60 * 5);
                passwordC.setMaxAge(60 * 5);
                response.addCookie(usernameC);
                response.addCookie(passwordC);

                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

                URL = "view.jsp";
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(URL);
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
