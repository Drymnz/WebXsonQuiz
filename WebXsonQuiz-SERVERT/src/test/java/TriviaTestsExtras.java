import org.junit.jupiter.api.Test;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TriviaTestsExtras {

    private String MODIFY_TRIVIA = "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 250,\n" + //
                "  \"NOMBRE\": \"Historia y Cultura de Guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testModifyTrivia() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(MODIFY_TRIVIA);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }

    private String DEL_COMPONET = "<!realizar_solicitud: \"ELIMINAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_soldado_presidente\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testDelComponet() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(DEL_COMPONET);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }

    private String DEL_TRIVIA = "<!realizar_solicitud: \"ELIMINAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testDelTrivia() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(DEL_TRIVIA);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }

    private String MODIFY_COMPONET = "<!realizar_solicitud: \"MODIFICAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_revolucion_octubre\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"INDICE\": \"2\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Seleccione a los líderes de la Revolución del 20 de octubre de 1944:\",\n" + //
                "  \"OPCIONES\": \"Jacobo Arbenz|José Arévalo|Jorge Ubico|Juan José Arévalo|Manuel Estrada Cabrera\",\n" + //
                "  \"RESPUESTA\": \"Jacobo Arbenz|José Arévalo|Juan José Arévalo\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>";

    @Test
    public void testModifyComponent() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(MODIFY_COMPONET);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }

    private String ALL_TRIVIA = "<?xson version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 600,\n" + //
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
                "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 670,\n" + //
                "  \"NOMBRE\": \"Historia y Cultura de Guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"ELIMINAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_soldado_presidente\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"MODIFICAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_revolucion_octubre\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"INDICE\": \"2\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Seleccione a los líderes de la Revolución del 20 de octubre de 1944:\",\n" + //
                "  \"OPCIONES\": \"Jacobo Arbenz|José Arévalo|Jorge Ubico|Juan José Arévalo|Manuel Estrada Cabrera\",\n" + //
                "  \"RESPUESTA\": \"Jacobo Arbenz|José Arévalo|Juan José Arévalo\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"ELIMINAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!fin_solicitudes_realizada>";

    @Test
    public void testAllTrivia() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(ALL_TRIVIA);
        trivias.Anilisar();
        Assertions.assertTrue(!trivias.isError());
    }

}
