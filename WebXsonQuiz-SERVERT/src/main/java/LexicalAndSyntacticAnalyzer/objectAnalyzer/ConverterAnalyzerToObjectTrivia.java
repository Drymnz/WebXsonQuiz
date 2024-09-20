package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import java.util.ArrayList;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;

public class ConverterAnalyzerToObjectTrivia {
    private AnalyzerManagerUser trivias;
    private ArrayList<Trivia> listTrivia = new ArrayList();

    public ConverterAnalyzerToObjectTrivia(AnalyzerManagerUser trivias) {
        this.trivias = trivias;
    }

    
}
