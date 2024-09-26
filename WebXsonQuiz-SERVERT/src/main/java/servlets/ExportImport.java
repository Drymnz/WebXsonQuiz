package servlets;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBaseTrivia;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.google.gson.Gson;
import fileManager.FileInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import reactions.DataBaseListTrivia;
import reports.FilterTriviasData;

/**
 *
 * @author drymnz
 */
@WebServlet(name = "ExportImport", urlPatterns = {"/ExportImport"})
//solo esto era para cargar un archivo
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ExportImport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID de la trivia a exportar desde el formulario
        String triviaId = request.getParameter("triviaId");

        // Simular la obtención de la trivia (en un caso real, accederías a la base de datos)
        Trivia triviaToExport = (new FilterTriviasData(new DataBaseListTrivia())).getTriviaId(triviaId);;

        if (triviaToExport != null) {
            // Configurar la respuesta para descargar el archivo CSV
            response.setContentType("text/xtriv");
            response.setHeader("Content-Disposition", "attachment; filename=\"trivia_" + triviaId + ".xtriv\"");

            // Generar el contenido del archivo CSV
            PrintWriter writer = response.getWriter();
            Gson gson = new Gson();
            String json = gson.toJson(triviaToExport);
            writer.println(json);
            writer.flush();
            writer.close();
        } else {
            response.getWriter().println("Error: No se encontró la trivia para exportar.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el archivo subido
        Part filePart = request.getPart("file");

        // Verificar si se ha subido un archivo
        if (filePart != null && filePart.getSize() > 0) {
            // Leer el contenido del archivo CSV
            String fileContent = partToString(filePart);
            AnalyzerDataBaseTrivia analyzer = new AnalyzerDataBaseTrivia(fileContent);
            analyzer.Analyze();
            if (analyzer.isError()) {
                request.setAttribute("listErrores", analyzer.getListError());
                request.getRequestDispatcher("/export_import.jsp").forward(request, response);
            } else {
                ArrayList<Trivia> importedTrivias = analyzer.getListTrivia();
                // Guardar las trivias importadas en el contexto de la aplicación o base de datos
                // Aquí se guarda en el contexto de la aplicación (puede adaptarse a la base de datos)
                getServletContext().setAttribute("listTrivia", importedTrivias);
                // Redirigir a una página de confirmación o mostrar un mensaje de éxito
                response.getWriter().println("Importación completada con éxito.");
            }
        } else {
            // Si no se seleccionó ningún archivo, mostrar un mensaje de error
            response.getWriter().println("Error: No se seleccionó ningún archivo.");
        }
    }

    private String partToString(Part part) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = part.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
