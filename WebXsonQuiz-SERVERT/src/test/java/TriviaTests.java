import org.junit.jupiter.api.Test;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TriviaTests {

    private String NEW_TRIVIA = "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 450,\n" + //
                "  \"NOMBRE\": \"Cultura de Guatemala\",\n" + //
                "  \"TEMA\": \"cultura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testNewTrivia() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(NEW_TRIVIA);
        trivias.Analyze();
        Assertions.assertTrue(!trivias.isError());
    }

    private String ADD_COMPONET = "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_himno_guatemala\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"CAMPO_TEXTO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Nombre del autor de la letra del Himno de Guatemala: \",\n" + //
                "  \"RESPUESTA\": \"José Joaquín Palma\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testAddComponet() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(ADD_COMPONET);
        trivias.Analyze();
        Assertions.assertTrue(!trivias.isError());
    }

    private String allTriviaComplent = "<?xson version=\"1.0\" ?>\n" + //
                "\n" + //
                "<!realizar_solicitudes>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 455,\n" + //
                "  \"NOMBRE\": \"Cultura de Guatemala\",\n" + //
                "  \"TEMA\": \"cultura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_himno_guatemala\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"CAMPO_TEXTO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Nombre del autor de la letra del Himno de Guatemala: \",\n" + //
                "  \"RESPUESTA\": \"José Joaquín Palma\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_soldado_presidente\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"RADIO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿A quién se le conoce como el soldado del pueblo?\",\n" + //
                "  \"OPCIONES\": \"Jacobo Arbenz|José Arévalo|Jorge Ubico|Otto Pérez Molina\",\n" + //
                "  \"RESPUESTA\": \"Jacobo Arbenz\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_revolucion_octubre\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Quiénes fueron parte de la revolución del 20 de octubre?\",\n" + //
                "  \"OPCIONES\": \"Jacobo Arbenz|José Arévalo|Jorge Ubico|Juan José Arévalo\",\n" + //
                "  \"RESPUESTA\": \"Jacobo Arbenz|José Arévalo|Juan José Arévalo\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!fin_solicitudes_realizada>";

    @Test
    public void testAllTrival() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(allTriviaComplent);
        trivias.Analyze();
        Assertions.assertTrue(!trivias.isError());
    }

}
