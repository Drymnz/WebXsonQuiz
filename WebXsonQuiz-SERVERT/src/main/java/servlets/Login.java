/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import Lengua.LanguageConstants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cunoc.webxsonquiz.data.servert.User;

import reactions.SystemAcess;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String errorMessage = null;
        
        if (user == null || user.trim().isEmpty()) {
            errorMessage = LanguageConstants.EMPTY_TEXT;
        } else {
            User userclient = (new SystemAcess(user)).loginSystem();
            if (userclient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", userclient);
                response.sendRedirect("user_manager.jsp");
                return; // Importante: salir del método después de la redirección
            } else {
                errorMessage = LanguageConstants.DATA_USER_INCORECT;
            }
        }
        
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
