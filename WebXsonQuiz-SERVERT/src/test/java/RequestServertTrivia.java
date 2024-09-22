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
import reports.UserRequestReport;

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
        Assertions.assertTrue(!analyzer.isError());
    }

    private String solicitudTrivias = "<?xml version=\"1.0\" ?>\n" + //
                "<!realizar_solicitudes>\n" + //
                "<!-- Trivia sobre Historia de México -->\n" + //
                "<!-- 0 -->\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_historia_mexico\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 30,\n" + //
                "  \"NOMBRE\": \"Historia de México\",\n" + //
                "  \"TEMA\": \"historia\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 1 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_independencia_mexico\",\n" + //
                "  \"TRIVIA\": \"$trivia_historia_mexico\",\n" + //
                "  \"CLASE\": \"CAMPO_TEXTO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿En qué año inició la guerra de Independencia de México?\",\n" + //
                "  \"RESPUESTA\": \"1810\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 2 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_revolucion_mexicana\",\n" + //
                "  \"TRIVIA\": \"$trivia_historia_mexico\",\n" + //
                "  \"CLASE\": \"RADIO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Quién fue el iniciador de la Revolución Mexicana?\",\n" + //
                "  \"OPCIONES\": \"Francisco I. Madero|Porfirio Díaz|Emiliano Zapata|Pancho Villa\",\n" + //
                "  \"RESPUESTA\": \"Francisco I. Madero\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 3 -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_historia_mexico\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
                "  \"NOMBRE\": \"Historia y Revolución de México\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 4 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_heroes_revolucion\",\n" + //
                "  \"TRIVIA\": \"$trivia_historia_mexico\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Seleccione a los líderes de la Revolución Mexicana:\",\n" + //
                "  \"OPCIONES\": \"Francisco I. Madero|Porfirio Díaz|Emiliano Zapata|Pancho Villa|Venustiano Carranza\",\n" + //
                "  \"RESPUESTA\": \"Francisco I. Madero|Emiliano Zapata|Pancho Villa|Venustiano Carranza\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- Trivia sobre Literatura Española -->\n" + //
                "<!-- 5 -->\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_literatura_espanola\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 40,\n" + //
                "  \"NOMBRE\": \"Literatura Española\",\n" + //
                "  \"TEMA\": \"literatura\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 6 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_autor_quijote\",\n" + //
                "  \"TRIVIA\": \"$trivia_literatura_espanola\",\n" + //
                "  \"CLASE\": \"CAMPO_TEXTO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Quién escribió 'Don Quijote de la Mancha'?\",\n" + //
                "  \"RESPUESTA\": \"Miguel de Cervantes\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 7 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_generacion_98\",\n" + //
                "  \"TRIVIA\": \"$trivia_literatura_espanola\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Seleccione a los autores pertenecientes a la Generación del 98:\",\n" + //
                "  \"OPCIONES\": \"Miguel de Unamuno|Antonio Machado|Pío Baroja|Federico García Lorca|Valle-Inclán\",\n" + //
                "  \"RESPUESTA\": \"Miguel de Unamuno|Antonio Machado|Pío Baroja|Valle-Inclán\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 8 -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_literatura_espanola\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 50,\n" + //
                "  \"NOMBRE\": \"Literatura Española Clásica y Moderna\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 9 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_obra_lorca\",\n" + //
                "  \"TRIVIA\": \"$trivia_literatura_espanola\",\n" + //
                "  \"CLASE\": \"RADIO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Cuál de estas obras pertenece a Federico García Lorca?\",\n" + //
                "  \"OPCIONES\": \"La Regenta|Bodas de Sangre|Don Juan Tenorio|La Celestina\",\n" + //
                "  \"RESPUESTA\": \"Bodas de Sangre\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "\n" + //
                "<!-- Trivia sobre Ciencia y Tecnología -->\n" + //
                "<!-- 10 -->\n" + //
                "<!realizar_solicitud: \"NUEVA_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_ciencia_tecnologia\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 35,\n" + //
                "  \"NOMBRE\": \"Ciencia y Tecnología\",\n" + //
                "  \"TEMA\": \"ciencia\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 11 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_teoria_relatividad\",\n" + //
                "  \"TRIVIA\": \"$trivia_ciencia_tecnologia\",\n" + //
                "  \"CLASE\": \"CAMPO_TEXTO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Quién propuso la Teoría de la Relatividad?\",\n" + //
                "  \"RESPUESTA\": \"Albert Einstein\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 12 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_elementos_tabla_periodica\",\n" + //
                "  \"TRIVIA\": \"$trivia_ciencia_tecnologia\",\n" + //
                "  \"CLASE\": \"RADIO\",\n" + //
                "  \"TEXTO_VISIBLE\": \"¿Cuántos elementos tiene la tabla periódica actual?\",\n" + //
                "  \"OPCIONES\": \"92|108|118|120\",\n" + //
                "  \"RESPUESTA\": \"118\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 13 -->\n" + //
                "<!realizar_solicitud: \"MODIFICAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_ciencia_tecnologia\",\n" + //
                "  \"TIEMPO_PREGUNTA\": 45,\n" + //
                "  \"NOMBRE\": \"Avances en Ciencia y Tecnología\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 14 -->\n" + //
                "<!realizar_solicitud: \"AGREGAR_COMPONENTE\" >\n" + //
                "{ \"PARAMETROS_COMPONENTE\":[{\n" + //
                "  \"ID\": \"$_inventos_siglo_xx\",\n" + //
                "  \"TRIVIA\": \"$trivia_ciencia_tecnologia\",\n" + //
                "  \"CLASE\": \"CHECKBOX\",\n" + //
                "  \"TEXTO_VISIBLE\": \"Seleccione los inventos del siglo XX:\",\n" + //
                "  \"OPCIONES\": \"Teléfono|Internet|Avión|Computadora personal|Imprenta\",\n" + //
                "  \"RESPUESTA\": \"Internet|Avión|Computadora personal\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!-- 15 -->\n" + //
                "<!realizar_solicitud: \"ELIMINAR_TRIVIA\" >\n" + //
                "{ \"PARAMETROS_TRIVIA\":[{\n" + //
                "  \"ID_TRIVIA\": \"$trivia_ciencia_tecnologia\"\n" + //
                "}\n" + //
                "]}\n" + //
                "<fin_solicitud_realizada!>\n" + //
                "<!fin_solicitudes_realizada>\n" + //
                "";
    @Test
    public void testRequestSyntaxValidatorManagerTrivia() {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(solicitudTrivias);
        analizer.Anilisar();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser(), new DataBaseListTrivia(),new User("Bj", "", "", "", ""));
        requetSystaxValidator.checkRequests();
        boolean goodText= !analizer.isError();
        requetSystaxValidator.upDataBase(); 
        Assertions.assertTrue(goodText);
    }


    @Test
    public void testReportSystemAccesTrivia() {
        AnalyzerManagerUser analizer = new AnalyzerManagerUser(solicitudTrivias);
        analizer.Anilisar();
        RequestSyntaxValidatorManagerUser requetSystaxValidator = new RequestSyntaxValidatorManagerUser(analizer,new DataBaseListUser(), new DataBaseListTrivia(),new User("Bj", "", "", "", ""));
        requetSystaxValidator.checkRequests();
        boolean goodText= !analizer.isError();
        requetSystaxValidator.upDataBase(); 
        String stringReport = (new UserRequestReport(requetSystaxValidator).reportString());
        Assertions.assertTrue(!stringReport.isEmpty() && goodText);
    }
    
}
