package LexicalAndSyntacticAnalyzer.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.jflexandcupSQLKV.LexemaMySQLKV;
import LexicalAndSyntacticAnalyzer.jflexandcupSQLKV.MyParseSQLKV;
import reports.ReportErrorInterpreter;

public class AnalyzerSQLKV {
     private LexemaMySQLKV lexeman;
    private MyParseSQLKV parse;

    public AnalyzerSQLKV(String text) {
        Reader reader = new StringReader(text);
        this.lexeman = new LexemaMySQLKV(reader);
        this.parse = new MyParseSQLKV(this.lexeman);
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
