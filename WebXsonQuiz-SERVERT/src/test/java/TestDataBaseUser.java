import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBase;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.User;
import fileManager.FileInput;
import fileManager.FileOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TestDataBaseUser {

    private String textosdepureva = "<!realizar_solicitud: \"USUARIO_NUEVO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619\", \"PASSWORD\": \"12345678\", \"NOMBRE\": \"JUAN PEREZ\",“INSTITUCION” :\"CUNOC\" }] }\n"
            + //
            "<fin_solicitud_realizada!>\n";

    @Test
    public void testLoginFileToString() {
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));
        Assertions.assertTrue(json.equals("{\"id\":\"admin\",\"password\":\"admin\",\"name\":\"Benjamin de Jesus Perez Aguilar\",\"institution\":\"CUNOC\",\"date\":\"2024-09-05\"}"));
    }

    @Test
    public void testLoginFileToUser() {
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));

        AnalyzerDataBase analyzer = new AnalyzerDataBase(json);
        analyzer.Anilisar();
        boolean satisfactoryTest  = false;
        User newUser = (new ConverterToObject()).getRequestAnalyzerToUser(analyzer.getListRquest().get(0));
        Assertions.assertTrue(newUser.toString().equals("ID:\"admin\" password:\"admin\" name:\"Benjamin de Jesus Perez Aguilar\" institution:\"CUNOC\" date:2024-09-05"));
    }

     @Test
    public void testSevedUsers() {
        ConverterToObject converter = new ConverterToObject();
        List<User> listaUser = new ArrayList<>();

        listaUser.add(new User("admin", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate()));
        listaUser.add(new User("ADMIN", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate()));
        listaUser.add(new User("SR", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate()));

        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUsers.json");

        // Convertir la lista de objetos a JSON
        Gson gson = new Gson();
        String json = gson.toJson(listaUser);

        Assertions.assertTrue((new FileOutput()).aguardarTexto(archivoTxt, json));
    }

    @Test
    public void testLoginFileToUsers() {
        List<User> listaUser = new ArrayList<>();
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUsers.json");
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));

        AnalyzerDataBase analyzer = new AnalyzerDataBase(json);
        analyzer.Anilisar();
        boolean satisfactoryTest  = false;
        for (RequestAnalyzer element : analyzer.getListRquest()) {
            listaUser.add((new ConverterToObject()).getRequestAnalyzerToUser(element));
        }
        Assertions.assertTrue(listaUser.size() == 2);
    }
   
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

}
