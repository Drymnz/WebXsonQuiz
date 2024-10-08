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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String errorMessage = null;
        
        if (user == null || user.trim().isEmpty()) {
            errorMessage = LanguageConstants.EMPTY_TEXT;
        } else {
            SystemAcess systemAcess = (new SystemAcess(user));
            User userclient = systemAcess.loginSystem();
            if (userclient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", userclient);
                response.sendRedirect("user_manager.jsp");
                return; // Importante: salir del método después de la redirección
            } else {
                errorMessage = LanguageConstants.DATA_USER_INCORECT;
                if (systemAcess.getErrorToken() != null) {
                    errorMessage += "\n"+systemAcess.getErrorToken();
                }
            }
        }
        
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
