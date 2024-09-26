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
import java.util.ArrayList;
import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reports.ReportErrorInterpreter;
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
        String userId = request.getParameter("userId");

        ArrayList<ReportErrorInterpreter> errorMessage = new ArrayList();

        if (textArea == null || textArea.trim().isEmpty() || userId == null || userId.trim().isEmpty()) {

        } else {
            AnalyzerManagerUser analizer = new AnalyzerManagerUser(textArea);
            analizer.Analyze();
            RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer, new DataBaseListUser(), new DataBaseListTrivia(), new User(userId, "", "", "", ""));
            if (requetSystaxValidator.isErroLexicoOrSyntanc()) {
                errorMessage = requetSystaxValidator.getListError();
            } else {
                requetSystaxValidator.checkRequests();
                requetSystaxValidator.upDataBase();
                request.setAttribute("resultsText", (new UserRequestReport(requetSystaxValidator).reportString()));
                request.getRequestDispatcher("user_manager.jsp").forward(request, response);
            }
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute("listErrores", errorMessage);
            request.getRequestDispatcher("/user_manager.jsp").forward(request, response);
        }
    }
}
