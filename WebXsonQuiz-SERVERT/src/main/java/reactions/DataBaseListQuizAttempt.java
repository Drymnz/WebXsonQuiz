package reactions;

import java.io.File;
import java.util.ArrayList;
import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBaseQuizAttempt;
import fileManager.FileInput;
import fileManager.FileOutput;

public class DataBaseListQuizAttempt {

    private ArrayList<QuizAttempt> listQuizAttempt = new ArrayList<>();
    private File fileDataBase = null;
    private AnalyzerDataBaseQuizAttempt analyzer = null;

    public DataBaseListQuizAttempt() {
        checkDataBase();
    }

    private void checkDataBase() {
        if (this.analyzer == null) {
            this.fileDataBase = new File(ConstantSystem.SYSTEM_DIR,ConstantSystem.NAME_FILE_DATA_BASE_QUIZ_ATTERMPT);
            String json = (new FileInput().cargarArchivoTexto(this.fileDataBase));
            this.analyzer = new AnalyzerDataBaseQuizAttempt(json);
            this.analyzer.Analyze();
            this.listQuizAttempt.addAll(analyzer.getListQuizAttempt());
        } 
    }

    public ArrayList<QuizAttempt> getListQuizAttempt() {
        return this.listQuizAttempt;
    }

    public void addListQuizAttempt(QuizAttempt newQuizAttempt){
        this.listQuizAttempt.add(newQuizAttempt);
    }

    public boolean upDataBase() {
        if (this.fileDataBase == null) {
            this.checkDataBase();
        }
        Gson gson = new Gson();
        String json = gson.toJson(this.getListQuizAttempt());
        return (new FileOutput()).aguardarTexto(this.fileDataBase, json);
    }
}
