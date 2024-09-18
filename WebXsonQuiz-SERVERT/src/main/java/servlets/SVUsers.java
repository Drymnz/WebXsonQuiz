package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser("");
        analizer.Anilisar();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser());
        requetSystaxValidator.checkRequests();
        requetSystaxValidator.upDataBase();        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
