package reactions;

import java.io.File;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.google.gson.Gson;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerDataBaseTrivia;
import fileManager.FileInput;
import fileManager.FileOutput;

public class DataBaseListTrivia {
    private AnalyzerDataBaseTrivia analyzer = null;
    private ArrayList<Trivia> listTrivias = new ArrayList<>();
    private File fileDataBase= null;

    public DataBaseListTrivia() {
        checkDataBase();
    }

    private void checkDataBase(){
        if (this.analyzer == null) {
            this.fileDataBase = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_TRIVIA);
            String json = (new FileInput().cargarArchivoTexto(this.fileDataBase));
            this.analyzer  = new AnalyzerDataBaseTrivia(json);
            analyzer.Anilisar();
            this.listTrivias = this.analyzer.getListTrivia();
        }
    }

    public ArrayList<Trivia> getListTrivias() {
        return listTrivias;
    }

    public boolean delTrivia(Trivia delTrivia){
        for (int index = 0; index < this.getListTrivias().size(); index++) {
            if (this.getListTrivias().get(index).getId().equals(delTrivia.getId())){
                this.getListTrivias().remove(index);
                return true;
            }
        }
        return false;
    }

     public boolean modifityUser(Trivia modif,Trivia dataBase){
        /* String id = modif.getId().isEmpty() ? dataBase.getId() : modif.getId();
        String password = modif.getPassword().isEmpty() ? dataBase.getPassword() : modif.getPassword();
        String name = modif.getName().isEmpty() ? dataBase.getName() : modif.getName();
        String institution = modif.getInstitution().isEmpty() ? dataBase.getInstitution() : modif.getInstitution();
        String date = modif.getDate().isEmpty() ? dataBase.getDate() : modif.getDate();
        dataBase.setId(id);
        dataBase.setPassword(password);
        dataBase.setName(name);
        dataBase.setInstitution(institution);
        dataBase.setDate(date); */
        return true;
    }

    public boolean upDataBase(){
        if (this.fileDataBase == null) {
            this.checkDataBase();
        }
        Gson gson = new Gson();
        String json = gson.toJson(this.getListTrivias());
        return (new FileOutput()).aguardarTexto(this.fileDataBase, json);
    }
}
