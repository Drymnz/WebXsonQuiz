import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.User;

import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */

public class TestManagerUser {

    

    @Test
    public void testNesUser() {
        String textosdepureva = "<!realizar_solicitud: \"USUARIO_NUEVO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619\", \"PASSWORD\": \"12345678\", \"NOMBRE\": \"JUAN PEREZ\",“INSTITUCION” :\"CUNOC\" }] }\n"
            +"<fin_solicitud_realizada!>\n";
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepureva);
        analizer.Anilisar();
        boolean goodText= false;
        for (RequestAnalyzer element : analizer.getListRquest()) {
            goodText = element.getType() == ListRequests.NEW_USER;
        }
        Assertions.assertTrue(goodText);
    }

    @Test
    public void testUserModify() {
        String textosdepureva = "<!realizar_solicitud: \"MODIFICAR_USUARIO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO_ANTIGUO\": \"juanito619\",\n" + //
                                "\"USUARIO_NUEVO\": \"juanito619lopez\",\n" + //
                                "\"NUEVO_PASSWORD\": \"12345678910\" }] }\n"
            +"<fin_solicitud_realizada!>\n";
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepureva);
        analizer.Anilisar();
        boolean goodText= false;
        for (RequestAnalyzer element : analizer.getListRquest()) {
            goodText = element.getType() == ListRequests.MODIFICAR_USUARIO;
        }
        Assertions.assertTrue(goodText);
    }

    @Test
    public void testUserDel() {
        String textosdepureva = "<!realizar_solicitud: \"ELIMINAR_USUARIO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619lopez\" }] }\n"
            +"<fin_solicitud_realizada!>\n";
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepureva);
        analizer.Anilisar();
        boolean goodText= false;
        for (RequestAnalyzer element : analizer.getListRquest()) {
            goodText = element.getType() == ListRequests.ELIMINAR_USUARIO;
        }
        Assertions.assertTrue(goodText);
    }

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
    public void testListRequery() {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepurevatestListRequery);
        analizer.Anilisar();
        boolean goodText= !analizer.isError() && analizer.getListRquest().size() == 5;
        Assertions.assertTrue(goodText);
    }

    @Test
    public void testRequestSyntaxValidatorManagerUser() {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(textosdepurevatestListRequery);
        analizer.Anilisar();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser(),new DataBaseListTrivia(),new User("Bj", "", "", "", ""));
        requetSystaxValidator.checkRequests();
        boolean goodText= !analizer.isError() && analizer.getListRquest().size() == 5;
        requetSystaxValidator.upDataBase(); 
        Assertions.assertTrue(goodText);
    }
}
