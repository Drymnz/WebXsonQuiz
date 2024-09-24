import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;

public class ReportingLanguageTests {
    private String firstTestText = "SELECCIONAR REPORTE $trivia1 FILTRAR POR USUARIO = \"juanito769\"";
    @Test
    public void testReportSystemAccesUser() {
        AnalyzerSQLKV analyzer = new AnalyzerSQLKV(firstTestText);
        analyzer.Analyze();
        Assertions.assertTrue(true);
    }
}
