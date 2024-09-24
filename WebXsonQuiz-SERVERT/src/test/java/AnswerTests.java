import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.User;

import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;
import reports.UserRequestReport;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class AnswerTests {

    private String textosdepurevatestListRequery = "<?xson version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "<!-- Ejemplo de creación de usuario -->\n" + //
                "<!realizar_solicitud: \"USUARIO_NUEVO\" >\n" + //
                "{ \"DATOS_USUARIO\":[{\n" + //
                "\"USUARIO\": \"maria_garcia\",\n" + //
                "\"PASSWORD\": \"c0ntr@s3ña_s3gur@\",\n" + //
                "\"NOMBRE\": \"María García\",\n" + //
                "\"INSTITUCION\": \"Universidad XYZ\"\n" + //
                "}]\n" + //
                "}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!-- Ejemplo de modificación de usuario -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_USUARIO\" >\n" + //
                "{ \"DATOS_USUARIO\":[{\n" + //
                "\"USUARIO_ANTIGUO\": \"maria_garcia\",\n" + //
                "\"USUARIO_NUEVO\": \"maria_garcia_2024\",\n" + //
                "\"NUEVO_PASSWORD\": \"nu3v@_c0ntr@s3ña_2024\",\n" + //
                "\"INSTITUCION\": \"Instituto ABC\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!-- Ejemplo de eliminación de usuario -->\n" + //
                "<!realizar_solicitud: \"ELIMINAR_USUARIO\" >\n" + //
                "{ \"DATOS_USUARIO\":[{\n" + //
                "\"USUARIO\": \"maria_garcia_2024\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!-- Ejemplo de creación de otro usuario -->\n" + //
                "<!realizar_solicitud: \"USUARIO_NUEVO\" >\n" + //
                "{ \"DATOS_USUARIO\":[{\n" + //
                "\"USUARIO\": \"juan_perez\",\n" + //
                "\"PASSWORD\": \"p@ssw0rd_123\",\n" + //
                "\"NOMBRE\": \"Juan Pérez\",\n" + //
                "\"INSTITUCION\": \"Colegio Técnico ABC\"\n" + //
                "}]\n" + //
                "}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!-- Ejemplo de modificación parcial de usuario -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_USUARIO\" >\n" + //
                "{ \"DATOS_USUARIO\":[{\n" + //
                "\"USUARIO_ANTIGUO\": \"juan_perez\",\n" + //
                "\"NUEVO_PASSWORD\": \"nu3v0_p@ssw0rd_456\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!fin_solicitudes_realizada>";
    @Test
    public void testReportSystemAccesUser() {
       AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepurevatestListRequery);
        analizer.Analyze();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser(),new DataBaseListTrivia(),new User("Bj", "", "", "", ""));
        requetSystaxValidator.checkRequests();
        Assertions.assertTrue(!(new UserRequestReport(requetSystaxValidator).reportString().isEmpty()));
    }

}
