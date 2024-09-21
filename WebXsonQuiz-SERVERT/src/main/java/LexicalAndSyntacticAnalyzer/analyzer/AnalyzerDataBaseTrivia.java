package LexicalAndSyntacticAnalyzer.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.Trivia;

import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseTrivias.LexemaDataBaseTrivias;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseTrivias.MyParserDataBaseTrivias;
import reports.ReportErrorInterpreter;

public class AnalyzerDataBaseTrivia {
    private LexemaDataBaseTrivias lexeman;
    private MyParserDataBaseTrivias parse;

    public AnalyzerDataBaseTrivia(String text) {
        Reader reader = new StringReader(text);
        this.lexeman = new LexemaDataBaseTrivias(reader);
        this.parse = new MyParserDataBaseTrivias(this.lexeman);
    }

    public void Anilisar() {
        try {
            this.parse.parse();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
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

    /* //Returnar el listado de Rquest
    public ArrayList<RequestAnalyzer> getListRquest(){
      return this.parse.getListRquest();
    } */

    //Returnar el listado trivia
    public ArrayList<Trivia> getListTrivia(){
        return this.parse.getListTrivias();
    }
}
