package servlets;

import Lengua.LanguageConstants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import com.cunoc.webxsonquiz.data.servert.User;
import javax.servlet.http.HttpSession;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reactions.SystemAcess;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "SVUsers", urlPatterns = {"/SvUsers"})
public class SVUsers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("textArea");
        String errorMessage = null;
        
        if (user == null || user.trim().isEmpty()) {
            errorMessage = LanguageConstants.EMPTY_TEXT;
        } else {
            // insertar el texto y analiar
            //if (userclient != null) {
            //    HttpSession session = request.getSession();
            //    session.setAttribute("usuario", userclient);
            //    response.sendRedirect("user_manager.jsp");
            //    return; // Importante: salir del método después de la redirección
            //} else {
            //    errorMessage = LanguageConstants.DATA_USER_INCORECT;
            //}
        }
        
        if (errorMessage != null) {
            request.setAttribute("errores", errorMessage);
            request.getRequestDispatcher("/user_manager.jsp").forward(request, response);
        }
    }
}
