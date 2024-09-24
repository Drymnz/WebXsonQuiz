package LexicalAndSyntacticAnalyzer.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseQuizAttempt.LexemaQuizAttempt;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseQuizAttempt.MyParserDataQuizAttempt;
import reports.ReportErrorInterpreter;

public class AnalyzerDataBaseQuizAttempt {
    private LexemaQuizAttempt lexeman;
    private MyParserDataQuizAttempt parse;

    public AnalyzerDataBaseQuizAttempt(String text) {
        Reader reader = new StringReader(text);
        this.lexeman = new LexemaQuizAttempt(reader);
        this.parse = new MyParserDataQuizAttempt(this.lexeman);
    }

    public void Anilisar() {
        try {
            this.parse.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<QuizAttempt> getListQuizAttempt(){
        return this.parse.getListQuizAttempt();
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
