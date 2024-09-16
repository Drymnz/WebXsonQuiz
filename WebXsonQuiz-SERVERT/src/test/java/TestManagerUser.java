import org.junit.jupiter.api.Test;

import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

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
}
