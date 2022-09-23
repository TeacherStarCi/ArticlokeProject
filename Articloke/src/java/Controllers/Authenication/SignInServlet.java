package Controllers.Authenication;

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

public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "";
        try {
            String username = request.getParameter("usernamePara");
            String password = request.getParameter("passwordPara");

            UserDTO user = null;
            try {
                user = UserDAO.getUserUsernamePassword(username, password);
            } catch (SQLException ex) {
                System.out.println("SQLException - SignIn");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException - SignIn");
            }
            if (user == null) {
                URL = "invalid.html";
            } else {
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
