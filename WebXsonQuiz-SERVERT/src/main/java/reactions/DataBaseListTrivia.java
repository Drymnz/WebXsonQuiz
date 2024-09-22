package reactions;

import java.io.File;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.ClassComponent;
import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;
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

     public boolean modifityTrivia(Trivia modif,Trivia dataBase){
        String id     = modif.getId().isEmpty() ? dataBase.getId() : modif.getId();
        String name   = modif.getName().isEmpty() ? dataBase.getName() : modif.getName();
        String theme  = modif.getTheme().isEmpty() ? dataBase.getTheme() :modif.getTheme();
        String idUser = modif.getIdUser().isEmpty() ? dataBase.getIdUser() : modif.getIdUser();
        String date   = modif.getDate().isEmpty() ? dataBase.getDate() : modif.getDate();
        double time = modif.getTime()== dataBase.getTime()? dataBase.getTime(): dataBase.getTime();

        dataBase.setId(id);
        dataBase.setName(name);
        dataBase.setTheme(theme);
        dataBase.setIdUser(idUser);
        dataBase.setDate(date);
        dataBase.setTime(time);

        return true;
    }

    public boolean modifityComponentTrivia(ComponentTrivia modif,ComponentTrivia dataBase){
        String idComponent = modif.getIdComponent().isEmpty()? dataBase.getIdComponent(): modif.getIdComponent();
        ClassComponent type = modif.getType() == dataBase.getType()? dataBase.getType():modif.getType() ;
        int index = modif.getIndex() == dataBase.getIndex()? dataBase.getIndex(): modif.getIndex();
        String text = modif.getText().isEmpty()? dataBase.getText(): modif.getText();
        String options = modif.getOptions().isEmpty()? dataBase.getOptions(): modif.getOptions();
        int row = modif.getRow() == dataBase.getRow()? dataBase.getRow(): modif.getRow();
        int column = modif.getColumn() == dataBase.getColumn()? dataBase.getColumn(): modif.getColumn();
        String result = modif.getResult().isEmpty()? dataBase.getResult(): modif.getResult();
        String idTrivia = modif.getIdTrivia().isEmpty()? dataBase.getIdTrivia() : modif.getIdTrivia();
        dataBase.setIdComponent(idComponent);
        dataBase.setType(type);
        dataBase.setIndex(index);
        dataBase.setText(text);
        dataBase.setOptions(options);
        dataBase.setRow(row);
        dataBase.setColumn(column);
        dataBase.setResult(result);
        dataBase.setIdTrivia(idTrivia);
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
