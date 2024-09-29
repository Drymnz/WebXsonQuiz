package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;
import java.util.ArrayList;
import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reports.FilterTriviasData;
import reports.ReportErrorInterpreter;
import reports.UserRequestReport;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "SVUsers", urlPatterns = {"/SvUsers"})
public class SVUsers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String textArea = request.getParameter("textArea");
        String userId = request.getParameter("userId");

        ArrayList<ReportErrorInterpreter> errorMessage = new ArrayList();

        if (textArea != null || !textArea.trim().isEmpty() || userId != null || !userId.trim().isEmpty()) {
            AnalyzerManagerUser analizer = new AnalyzerManagerUser(textArea);
            analizer.Analyze();
            if (analizer.isError()) {
                errorMessage = analizer.getListError();
            } else {
                RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer, new DataBaseListUser(), new DataBaseListTrivia(), new User(userId, "", "", "", ""));
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        ArrayList<Trivia> listTrivia = null;

        if (userId != null || !userId.trim().isEmpty()) {
            //listTrivia = (new FilterTriviasData(new DataBaseListTrivia())).getListTriviasAll();
            listTrivia = (new FilterTriviasData(new DataBaseListTrivia())).getListTriviasByIdUser(userId);
            request.setAttribute("listTrivia", listTrivia);
            request.getRequestDispatcher("/export_import.jsp").forward(request, response);
        } 
    }
}
