import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBase;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import com.cunoc.webxsonquiz.data.servert.User;

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

    private         ConverterToObject converter = new ConverterToObject();

    @Test
    public void testLoginFileToString() {
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));
        Assertions.assertTrue(json.equals("{\"id\":\"admin\",\"password\":\"admin\",\"name\":\"Benjamin de Jesus Perez Aguilar\",\"institution\":\"CUNOC\",\"date\":\""+converter.getDate()+"\"}"));
    }

    @Test
    public void testLoginFileToUser() {
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));

        AnalyzerDataBase analyzer = new AnalyzerDataBase(json);
        analyzer.Anilisar();
        User newUser = (new ConverterToObject()).getRequestAnalyzerToUser(analyzer.getListRquest().get(0));
        Assertions.assertTrue(newUser != null);
       // Assertions.assertTrue(newUser.toString().equals("ID:admin password:admin name:Benjamin de Jesus Perez Aguilar institution:CUNOC date:"+converter.getDate()));
    }

     @Test
    public void testSevedListUsersToFile() {
        List<User> listaUser = new ArrayList<>();

        listaUser.add(new User("admin", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate()));
        listaUser.add(new User("ADMIN", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "2001-04-2"));
        listaUser.add(new User("BJ_97", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "1997-07-28"));

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
        
        satisfactoryTest = listaUser.size() == 3;
        Assertions.assertTrue(satisfactoryTest);
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
