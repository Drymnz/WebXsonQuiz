import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBaseQuizAttempt;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjetQuizAttempt;
import fileManager.FileOutput;
import reactions.ConstantSystem;
import reactions.DataBaseListQuizAttempt;

public class TriviaQuizAttemptTests {

    @Test
    public void TestAttemptListingSaveTest() {
        QuizAttempt newQuizAttempt = new QuizAttempt("BJ", "$trivia", 10, 100);
        File fileDataBase = new File(ConstantSystem.SYSTEM_DIR,ConstantSystem.NAME_FILE_DATA_BASE_QUIZ_ATTERMPT);
        ArrayList<QuizAttempt> listQuizAttempt = new ArrayList<>();
        listQuizAttempt.add(newQuizAttempt);
        Gson gson = new Gson();
        String json = gson.toJson(listQuizAttempt);
        Assertions.assertTrue((new FileOutput()).aguardarTexto(fileDataBase, json));
    }

    private String testStringTest = "[{\"user\":\"BJ\",\"quizId\":\"$trivia\",\"responseTime\":10,\"score\":100}]";

    @Test
    public void analyzerTest() {
        AnalyzerDataBaseQuizAttempt analyzer = new AnalyzerDataBaseQuizAttempt(testStringTest);
        analyzer.Anilisar();
        Assertions.assertTrue(!analyzer.isError());
    }

    @Test
    public void ConverterAnalyzerToObjectTest() {
        QuizAttempt newQuizAttempt = null;
        AnalyzerDataBaseQuizAttempt analyzer = new AnalyzerDataBaseQuizAttempt(testStringTest);
        analyzer.Anilisar();
        newQuizAttempt = (analyzer.getListQuizAttempt().get(0));
        Assertions.assertTrue(newQuizAttempt!=null);
    }

    @Test
    public void TestUpData() {
        QuizAttempt newQuizAttempt = new QuizAttempt("BJ", "$trivia", 10, 100);
        DataBaseListQuizAttempt dataBaseQA = new DataBaseListQuizAttempt();
        dataBaseQA.addListQuizAttempt(newQuizAttempt);
        Assertions.assertTrue(dataBaseQA.upDataBase());
    }


}
