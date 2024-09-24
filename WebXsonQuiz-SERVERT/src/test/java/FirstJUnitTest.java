import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerLogin;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.jflexandcup.LexemaUser;
import LexicalAndSyntacticAnalyzer.jflexandcup.MyParserLoginUser;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import com.cunoc.webxsonquiz.data.servert.User;

import fileManager.FileOutput;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class FirstJUnitTest {

    private String textosdepureva = "<!realizar_solicitud: \"USUARIO_NUEVO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619\", \"PASSWORD\": \"12345678\", \"NOMBRE\": \"JUAN PEREZ\",“INSTITUCION” :\"CUNOC\" }] }\n"
            + //
            "<fin_solicitud_realizada!>\n";

    @Test
    public void testOne() {
        Reader reader = new StringReader(textosdepureva);
        LexemaUser lexeman = new LexemaUser(reader);
        MyParserLoginUser parse = new MyParserLoginUser(lexeman);
        try {
            parse.parse();
        } catch (Exception e) {
            System.out.println(e);
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void testAnalyzerUser() {
        AnalyzerLogin analyzer = new AnalyzerLogin(textosdepureva);
        analyzer.Analyze();
        boolean satisfactoryTest  = false;
        for (RequestAnalyzer iterable_element : analyzer.getListRquest()) {
            satisfactoryTest = iterable_element.getType() == ListRequests.NEW_USER && iterable_element.getList().size() == 4;
        }
        Assertions.assertTrue(!analyzer.isError() && satisfactoryTest);
    }

    @Test
    public void testCreateUser() {
        AnalyzerLogin analyzer = new AnalyzerLogin(textosdepureva);
        analyzer.Analyze();
        boolean satisfactoryTest  = false;
        ConverterToObject converter = new ConverterToObject();
        User newUser = (converter).getRequestAnalyzerToUser(analyzer.getListRquest().get(0));
        //satisfactoryTest = newUser.toString().equals("ID:juanito619 password:12345678 name:JUAN PEREZ institution:CUNOC date: 2024-09-14");
        satisfactoryTest = newUser != null;
        Assertions.assertTrue(!analyzer.isError() && satisfactoryTest);
    }

    @Test
    public void testSevedUser() {
        ConverterToObject converter = new ConverterToObject();
        User userSaved = new User("admin", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate());
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        Gson gson = new Gson();
        String json = gson.toJson(userSaved);
        Assertions.assertTrue((new FileOutput()).aguardarTexto(archivoTxt, json));
    }

    @Test
    public void testSevedUsers() {
        ConverterToObject converter = new ConverterToObject();
        User userSaved = new User("admin", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", converter.getDate());
        String directorioActual = System.getProperty("user.dir");
        File archivoTxt = new File(directorioActual, "DataBaseUser.json");
        Gson gson = new Gson();
        String json = gson.toJson(userSaved);
        Assertions.assertTrue((new FileOutput()).aguardarTexto(archivoTxt, json));
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
