import org.junit.jupiter.api.Test;

import java_cup.parser;
import jflexandcup.LexemaUser;
import jflexandcup.MyParserLoginUser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class FirstJUnitTest {

    private String textosdepureva = 
    "<!realizar_solicitud: \"USUARIO_NUEVO\" > \n" + //
    "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619\", \"PASSWORD\": \"12345678\", \"NOMBRE\": \"JUAN PEREZ\",“INSTITUCION” :\"CUNOC\" }] }\n" + //
    "<fin_solicitud_realizada!>\n" ;
    @Test
    public void testOne(){
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
    
    public FirstJUnitTest() {
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
