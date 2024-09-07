import org.junit.jupiter.api.Test;

import reactions.SystemAcess;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerLogin;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.User;


import org.junit.jupiter.api.Assertions;

/**
 *
 * @author drymnz
 */
public class TestRequest {

    private String textosdepureva = "<!realizar_solicitud: \"LOGIN_USUARIO\" > \n" + //
            "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"juanito619\", \"PASSWORD\": \"12345678\" } ]} <fin_solicitud_realizada!>\n"
            + //
            "";

    private String textLoginBJ = "<!realizar_solicitud: \"LOGIN_USUARIO\" > \n" + //
                "{ \"DATOS_USUARIO\":[{ \"USUARIO\": \"BJ_97\", \"PASSWORD\": \"admin\" } ]} <fin_solicitud_realizada!>";

    @Test
    public void testSystemAcces() {
        User checkLogin = new User("juanito619", "12345678", "", "", "");
        AnalyzerLogin analyzer = new AnalyzerLogin(textosdepureva);
        analyzer.Anilisar();
        boolean satisfactoryTest = false;
        ConverterToObject converter = new ConverterToObject();
        User newUser = (converter).createUserListDataAnalyzerLogin(analyzer.getListRquest().get(0).getList());
        satisfactoryTest = checkLogin.getId().equals(newUser.getId())
                && checkLogin.getPassword().equals(newUser.getPassword());
        Assertions.assertTrue(!analyzer.isError() && satisfactoryTest);
    }

    @Test
    public void testSystemAccesUser() {
        SystemAcess userSy = new SystemAcess(textLoginBJ);
        boolean getIn = (!textLoginBJ.isEmpty()) && (userSy.isAcceder());
        Assertions.assertTrue(getIn);
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
