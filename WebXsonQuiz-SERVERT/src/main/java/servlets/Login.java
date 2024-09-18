/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user != null && !user.trim().isEmpty()) {
            User userclient = (new SystemAcess(user)).loginSystem();
            // Almacenar el objeto en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", userclient);

            // Redirigir a la siguiente página JSP
            response.sendRedirect("user_manager.jsp");
        } else {
            request.setAttribute("result", "Usuario no ingresado.");
        }
    }
}
