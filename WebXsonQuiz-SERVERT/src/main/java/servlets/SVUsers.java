package servlets;

import Lengua.LanguageConstants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import javax.servlet.http.HttpSession;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reports.UserRequestReport;

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
        String textArea = request.getParameter("textArea");
        String errorMessage = null;
        
        if (textArea == null || textArea.trim().isEmpty()) {
            errorMessage = LanguageConstants.EMPTY_TEXT;
        } else {
            AnalyzerManagerUser analizer = new AnalyzerManagerUser(textArea);
            analizer.Anilisar();
            RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser());
            requetSystaxValidator.checkRequests();
            requetSystaxValidator.upDataBase(); 
            HttpSession session = request.getSession();
            request.setAttribute("resultsText", (new UserRequestReport(requetSystaxValidator).reportString()));
            request.getRequestDispatcher("user_manager.jsp").forward(request, response);
        }
        
        if (errorMessage != null) {
            request.setAttribute("results", errorMessage);
            request.getRequestDispatcher("/user_manager.jsp").forward(request, response);
        }
    }
}
