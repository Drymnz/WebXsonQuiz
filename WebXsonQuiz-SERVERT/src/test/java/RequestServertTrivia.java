import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;
import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBaseTrivia;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectComponentTrivia;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectTrivia;
import fileManager.FileInput;
import fileManager.FileOutput;
import reactions.ConstantSystem;
import reactions.DataBaseListTrivia;
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class RequestServertTrivia {

    
    /*
     * this.fileDataBase = new File(ConstantSystem.SYSTEM_DIR,
     * ConstantSystem.NAME_FILE_DATA_BASE_USER);
     * String json = (new FileInput().cargarArchivoTexto(this.fileDataBase));
     * this.analyzer = new AnalyzerDataBase(json);
     */

    @Test
    public void testToSaveTriviaToDataFile() {
        ArrayList<Trivia> listTrivia = listTrivias();

        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, ConstantSystem.NAME_FILE_DATA_BASE_TRIVIA);

        // Convertir la lista de objetos a JSON
        Gson gson = new Gson();
        String json = gson.toJson(listTrivia);

        Assertions.assertTrue((new FileOutput()).aguardarTexto(archivoTxt, json));
    }
    private String allTriviaComplent = "<?xson version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "<!-- 0 -->\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
                "  \"NOMBRE\": \"Cultura de Guatemala\",\n" + //
                "  \"TEMA\": \"cultura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 0 -->\n" + //
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
                "<!-- 1 -->\n" + //
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
                "<!-- 2 -->\n" + //
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
                "\n" + //
                "\n" + //
                "\n" + //
                "\n" + //
                "<!fin_solicitudes_realizada>";
    private ArrayList<Trivia> listTrivias(){
        AnalyzerManagerUser trivias = new AnalyzerManagerUser(allTriviaComplent);
        trivias.Anilisar();
        ArrayList<Trivia> listTrivia = new ArrayList<>();
        ArrayList<ComponentTrivia> listComponentTrivia = new ArrayList<>();
        ConverterAnalyzerToObjectComponentTrivia converterComponentTrivia = new ConverterAnalyzerToObjectComponentTrivia();
        ConverterAnalyzerToObjectTrivia converterTrivia = new ConverterAnalyzerToObjectTrivia(new User("Bj", "", "", "", ""));
        for (RequestAnalyzer iterable_element : trivias.getListRquest()) {
            switch (iterable_element.getType()) {
                case AGREGAR_COMPONENTE:
                listComponentTrivia.add(converterComponentTrivia.newComponent(iterable_element));
                break;
                case NEW_TRIVIA:
                listTrivia.add(converterTrivia.newTrivia(iterable_element));
                listTrivia.add(converterTrivia.newTrivia(iterable_element));
                    break;
                default:
                    break;
            }
        }
        for (ComponentTrivia elementComponent : listComponentTrivia) {
            listTrivia.get(0).addComponent(elementComponent);
            listTrivia.get(1).addComponent(elementComponent);
        }
        return listTrivia;
    }

    @Test
    public void testToLoadFileToTrivia() {
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, ConstantSystem.NAME_FILE_DATA_BASE_TRIVIA);
        String stringFile = (new FileInput().cargarArchivoTexto(archivoTxt));

        AnalyzerDataBaseTrivia analyzer = new AnalyzerDataBaseTrivia(stringFile);
        analyzer.Anilisar();
        Assertions.assertTrue(analyzer.getListTrivia().size() == 2);
    }

    private String solicitudTrivias = "<?xson version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "<!-- 0 -->\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
                "  \"NOMBRE\": \"Cultura de Guatemala\",\n" + //
                "  \"TEMA\": \"cultura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 0 -->\n" + //
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
                "<!-- 1 -->\n" + //
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
                "<!-- 2 -->\n" + //
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
                "<!-- 1 -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 60,\n" + //
                "  \"NOMBRE\": \"Historia y Cultura de Guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 3 -->\n" + //
                "<!realizar_solicitud: \"ELIMINAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_soldado_presidente\",\n" + //
                "  \"TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!--  -->\n" + //
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
                "<!-- 3 -->\n" + //
                "<!realizar_solicitud: \"ELIMINAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_cultura_guatemala\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!fin_solicitudes_realizada>";
    @Test
    public void testRequestSyntaxValidatorManagerTrivia() {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(solicitudTrivias);
        analizer.Anilisar();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser(), new DataBaseListTrivia(),new User("Bj", "", "", "", ""));
        requetSystaxValidator.checkRequests();
        boolean goodText= !analizer.isError() && analizer.getListRquest().size() == 5;
        //requetSystaxValidator.upDataBase(); 
        Assertions.assertTrue(goodText);
    }
    
}
