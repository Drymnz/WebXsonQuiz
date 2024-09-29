package servlets;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;
import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reactions.DataBaseListQuizAttempt;
import reports.Filter;
import reports.ReportErrorInterpreter;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "ServletSQLKV", urlPatterns = {"/ServletSQLKV"})
public class ServletSQLKV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String textArea = request.getParameter("textArea");

        ArrayList<ReportErrorInterpreter> errorMessage = null;

        if (textArea != null || !textArea.trim().isEmpty()) {
            AnalyzerSQLKV analyzer = new AnalyzerSQLKV(textArea);
            analyzer.Analyze();
            if (analyzer.isError()) {
                 errorMessage = analyzer.getListError();
            } else {
                ArrayList<QuizAttempt> listFilter = (new Filter(new DataBaseListQuizAttempt(), analyzer)).filterList();
                request.setAttribute("listQuizAttempt", listFilter);
                request.getRequestDispatcher("/report.jsp").forward(request, response);
            }
        } 

        if (errorMessage != null) {
            request.setAttribute("listErrores", errorMessage);
            request.getRequestDispatcher("/report.jsp").forward(request, response);
        }

    }

}
