import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;

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
}
