package LexicalAndSyntacticAnalyzer.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.jflexandcup.LexemaUser;
import LexicalAndSyntacticAnalyzer.jflexandcup.MyParserLoginUser;
import reports.ReportErrorInterpreter;

/**
 *
 * @author drymnz
 */
public class AnalyzerLogin {
    private LexemaUser lexeman;
    private MyParserLoginUser parse;

    public AnalyzerLogin(String text) {
        Reader reader = new StringReader(text);
        this.lexeman = new LexemaUser(reader);
        this.parse = new MyParserLoginUser(this.lexeman);
    }

    public void Analyze() {
        try {
            this.parse.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //return si hay errores
    public boolean isError(){
        return (this.lexeman.getListError().size() > 0 || this.parse.getListError().size() > 0);
    }

    //Returnar el listado de errores
    public ArrayList<ReportErrorInterpreter> getListError() {
        ArrayList<ReportErrorInterpreter> returnListErro = new ArrayList<>();
        returnListErro.addAll(this.lexeman.getListError());
        returnListErro.addAll(this.parse.getListError());
        return returnListErro;
    }

    //Returnar el listado de Rquest
    public ArrayList<RequestAnalyzer> getListRquest(){
      return this.parse.getListRquest();
    }
}
