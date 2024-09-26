import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;
import reactions.DataBaseListQuizAttempt;
import reports.Filter;

public class ReportingSQLKVLanguageTests {
    private String firstTestText = "SELECCIONAR REPORTE $trivia1 FILTRAR POR USUARIO = \"juanito769\"";

    @Test
    public void firstTestText() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(firstTestText);
        analyzer.Analyze();
        Assertions.assertTrue(!analyzer.isError());
    }

    private String secondTestText = "SELECCIONAR REPORTE $trivia1 FILTRAR POR TIEMPO < 100";

    @Test
    public void secondTestText() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(secondTestText);
        analyzer.Analyze();
        Assertions.assertTrue(!analyzer.isError());
    }

    private String thirdTestText = "SELECCIONAR REPORTE FILTRAR POR USUARIO = \"juanito45\" AND USUARIO = 'jorge23'";

    @Test
    public void thirdTestText() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(thirdTestText);
        analyzer.Analyze();
        Assertions.assertTrue(!analyzer.isError());
    }

    private String firstTestTexLoad = "SELECCIONAR REPORTE $trivia FILTRAR POR USUARIO = \"BJ\"";

    @Test
    public void firstTestTexLoadObject() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(firstTestTexLoad);
        analyzer.Analyze();
        ArrayList<QuizAttempt> listFilter = (new Filter(new DataBaseListQuizAttempt(), analyzer)).filterList();
        Assertions.assertTrue(!analyzer.isError() && !listFilter.isEmpty());
    }

    private String secondTestTextLoad = "SELECCIONAR REPORTE $trivia FILTRAR POR TIEMPO < 100";

    @Test
    public void secondTestTextLoad() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(secondTestTextLoad);
        analyzer.Analyze();
        ArrayList<QuizAttempt> listFilter = (new Filter(new DataBaseListQuizAttempt(), analyzer)).filterList();
        Assertions.assertTrue(!analyzer.isError() && !listFilter.isEmpty());
    }

    private String thirdTestTextLoad = "SELECCIONAR REPORTE FILTRAR POR USUARIO = \"BJ\" AND USUARIO = 'MN'";

    @Test
    public void thirdTestTextLoad() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(thirdTestTextLoad);
        analyzer.Analyze();

        ArrayList<QuizAttempt> listFilter = (new Filter(new DataBaseListQuizAttempt(), analyzer)).filterList();
        Assertions.assertTrue(!analyzer.isError() && !listFilter.isEmpty());
    }
}
