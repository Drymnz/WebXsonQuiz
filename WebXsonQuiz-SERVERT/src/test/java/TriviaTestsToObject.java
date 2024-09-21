import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectComponentTrivia;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectTrivia;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TriviaTestsToObject {

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
    public void ParserTestToNewTriviaObject() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(NEW_TRIVIA);
        trivias.Anilisar();
        ConverterAnalyzerToObjectTrivia converterTrivia = new ConverterAnalyzerToObjectTrivia(new User("", "", "", "", ""));
        Trivia newTrivia = converterTrivia.newTrivia(trivias.getListRquest().get(0));
        Assertions.assertTrue(!trivias.isError() && newTrivia != null);
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
    public void ParserTestToNewComponentTriviaObject() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(ADD_COMPONET);
        trivias.Anilisar();
        ConverterAnalyzerToObjectComponentTrivia converterComponentTrivia = new ConverterAnalyzerToObjectComponentTrivia();
        ComponentTrivia newComponentTrivia = converterComponentTrivia.newComponent(trivias.getListRquest().get(0));
        Assertions.assertTrue(!trivias.isError() && newComponentTrivia != null);
    }

    private String allTriviaComplent = "<?xson version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
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
                "  \"TIEMPO_PREGUNTA\": 60,\n" + //
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
    public void testAllTrivalInObject() {
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(allTriviaComplent);
        trivias.Anilisar();
        ArrayList<Trivia> listTrivia = new ArrayList<>();
        ArrayList<ComponentTrivia> listComponentTrivia = new ArrayList<>();
        ConverterAnalyzerToObjectComponentTrivia converterComponentTrivia = new ConverterAnalyzerToObjectComponentTrivia();
        ConverterAnalyzerToObjectTrivia converterTrivia = new ConverterAnalyzerToObjectTrivia(new User("Bj", "", "", "", ""));
        for (RequestAnalyzer iterable_element : trivias.getListRquest()) {
            switch (iterable_element.getType()) {
                case AGREGAR_COMPONENTE:
                case ELIMINAR_COMPONENTE:
                case MODIFICAR_COMPONENTE:
                listComponentTrivia.add(converterComponentTrivia.newComponent(iterable_element));
                break;
                case ELIMINAR_TRIVIA:
                case MODIFICAR_TRIVIA:
                case NEW_TRIVIA:
                listTrivia.add(converterTrivia.newTrivia(iterable_element));
                    break;
                default:
                    break;
            }
        }
        Assertions.assertTrue(!trivias.isError() && ((listTrivia.size()+listComponentTrivia.size()) == 8));
    }

}
