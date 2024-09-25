package servlets;

import Lengua.LanguageConstants;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;
import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import com.cunoc.webxsonquiz.data.servert.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reactions.DataBaseListQuizAttempt;
import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reports.Filter;
import reports.UserRequestReport;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "ServletSQLKV", urlPatterns = {"/ServletSQLKV"})
public class ServletSQLKV extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String textArea = request.getParameter("textArea");

        String errorMessage = null;
        
        if (textArea == null || textArea.trim().isEmpty() ) {
            errorMessage = LanguageConstants.EMPTY_TEXT;
        } else {
            AnalyzerSQLKV analyzer = new AnalyzerSQLKV(textArea);
            analyzer.Analyze();
            ArrayList<QuizAttempt> listFilter = (new Filter(new DataBaseListQuizAttempt(), analyzer)).filterList();
            request.setAttribute("listQuizAttempt", listFilter);
            //request.setAttribute("resultsText", (new UserRequestReport(requetSystaxValidator).reportString()));
            // Redirigimos a la página JSP donde se mostrarán los resultados
            request.getRequestDispatcher("/report.jsp").forward(request, response);
        }
        
        if (errorMessage != null) {
            request.setAttribute("results", errorMessage);
            request.getRequestDispatcher("/report.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
