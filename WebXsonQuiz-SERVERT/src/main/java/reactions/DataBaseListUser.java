package reactions;

import java.io.File;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.User;
import com.google.gson.Gson;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBase;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import fileManager.FileInput;
import fileManager.FileOutput;

public class DataBaseListUser {
    private AnalyzerDataBase analyzer = null;
    private ArrayList<User> listUser = new ArrayList<>();
    private File fileDataBase= null;

    public DataBaseListUser() {
        checkDataBase();
    }

    public DataBaseListUser(AnalyzerDataBase analyzer) {
        this.analyzer = analyzer;
    }

    private void checkDataBase(){
        if (this.analyzer == null) {
            this.fileDataBase = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
            String json = (new FileInput().cargarArchivoTexto(this.fileDataBase));
            this.analyzer = new AnalyzerDataBase(json);
            this.analyzer.Anilisar();
            for (RequestAnalyzer element : analyzer.getListRquest()) {
                User checkUser = this.requestAnalyzarToUser(element);
                this.listUser.add(checkUser);
            }
        }
    }

    public ArrayList<RequestAnalyzer> getDataBaseUserToRequetsAnalyzer() {
        return this.analyzer.getListRquest();
    }

    public ArrayList<User> getListUsers() {
        return this.listUser;
    }

    public User requestAnalyzarToUser(RequestAnalyzer element){
        return (new ConverterToObject()).getRequestAnalyzerToUser(element);
    }

    public boolean delUser(User delUser){
        for (int index = 0; index < getListUsers().size(); index++) {
            if (getListUsers().get(index).getId().equals(delUser.getId())){
                getListUsers().remove(index);
                return true;
            }
        }
        return false;
    }

    public boolean modifityUser(User modif,User dataBase){
        String id = modif.getId().isEmpty() ? dataBase.getId() : modif.getId();
        String password = modif.getPassword().isEmpty() ? dataBase.getPassword() : modif.getPassword();
        String name = modif.getName().isEmpty() ? dataBase.getName() : modif.getName();
        String institution = modif.getInstitution().isEmpty() ? dataBase.getInstitution() : modif.getInstitution();
        String date = modif.getDate().isEmpty() ? dataBase.getDate() : modif.getDate();
        dataBase.setId(id);
        dataBase.setPassword(password);
        dataBase.setName(name);
        dataBase.setInstitution(institution);
        dataBase.setDate(date);
        return true;
    }

    public boolean upDataBase(){
        if (this.fileDataBase == null) {
            this.checkDataBase();
        }
        Gson gson = new Gson();
        String json = gson.toJson(this.getListUsers());
        return (new FileOutput()).aguardarTexto(this.fileDataBase, json);
    }

}
