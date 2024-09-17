package reactions;

import java.io.File;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.User;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBase;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import fileManager.FileInput;

public class DataBaseListUser {
    private AnalyzerDataBase analyzer;
    private ArrayList<User> listUser = new ArrayList<>();

    public DataBaseListUser() {
        File archivoTxt = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
        String json = (new FileInput().cargarArchivoTexto(archivoTxt));
        this.analyzer = new AnalyzerDataBase(json);
        this.analyzer.Anilisar();
    }

    public ArrayList<RequestAnalyzer> getDataBaseUserToRequetsAnalyzer() {
        return this.analyzer.getListRquest();
    }

    public ArrayList<User> getListUsers() {
        if (!(this.listUser.size() > 0)) {
            for (RequestAnalyzer element : analyzer.getListRquest()) {
                User checkUser = this.requestAnalyzarToUser(element);
                this.listUser.add(checkUser);
            }
        } 
        return this.listUser;
    }

    public User requestAnalyzarToUser(RequestAnalyzer element){
        return (new ConverterToObject()).getRequestAnalyzerToUser(element);
    }
}
