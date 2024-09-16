package LexicalAndSyntacticAnalyzer.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.jflexandcupManagerUser.LexemaByMyParesUser;
import LexicalAndSyntacticAnalyzer.jflexandcupManagerUser.MyParseUser;
import reports.ReportErrorInterpreter;

public class AnalyzerManagerUser {
    private LexemaByMyParesUser lexeman;
    private MyParseUser parse;

    public AnalyzerManagerUser(String text) {
        Reader reader = new StringReader(text);
        this.lexeman = new LexemaByMyParesUser(reader);
        this.parse = new MyParseUser(this.lexeman);
    }

    public void Anilisar() {
        try {
            this.parse.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Returnar el listado de Rquest
    public ArrayList<RequestAnalyzer> getListRquest(){
      return this.parse.getListRquest();
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
}
