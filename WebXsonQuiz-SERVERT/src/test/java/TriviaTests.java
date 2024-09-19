import org.junit.jupiter.api.Test;

import reactions.SystemAcess;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerLogin;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import com.cunoc.webxsonquiz.data.servert.User;



import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TriviaTests {

    private String NEW_TRIVIA = "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
                "  \"NOMBRE\": \"Cultura de Guatemala\",\n" + //
                "  \"TEMA\": \"cultura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testSystemAcces() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(NEW_TRIVIA);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }


}
