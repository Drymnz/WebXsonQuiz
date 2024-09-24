package modelServert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;
import com.google.gson.Gson;

import fileManager.FileInput;
import fileManager.FileOutput;
import reactions.ConstantSystem;

public class ModelDatabaseFileManager {
    
    private File dataBaseUser = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
    private File dataBaseTrivia = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
    private File dataBaseQuiz = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);

        
    public void checkDataBaseIsCreated() {
        this.checkDataBaseUser();
        this.checkDataBaseTrivia();
        this.checkDataBaseQuizAttempt();
    }

    private void checkDataBaseQuizAttempt(){
        boolean create = false;
        if (this.dataBaseQuiz.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseQuiz));
            if (getDataBaseFile.isEmpty()) {//no hay datos
                create = !create;
            }
        } else {
            create = !create;
        }
        if (create) {
            List<QuizAttempt> listaQuizAttempt = new ArrayList<>();

            // Convertir la lista de objetos a JSON
            Gson gson = new Gson();
            String json = gson.toJson(listaQuizAttempt);

            if ((new FileOutput()).aguardarTexto(this.dataBaseQuiz, json)) {
                System.out.println("Exito");
            } else {
                System.out.println("fracaso");
            }
            System.out.println(this.dataBaseQuiz.getAbsolutePath());
        }
    }

    private void checkDataBaseTrivia(){
        boolean create = false;
        if (this.dataBaseTrivia.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseTrivia));
            if (getDataBaseFile.isEmpty()) {//no hay datos
                create = !create;
            }
        } else {
            create = !create;
        }
        if (create) {
            List<Trivia> listaTrivia = new ArrayList<>();

            // Convertir la lista de objetos a JSON
            Gson gson = new Gson();
            String json = gson.toJson(listaTrivia);

            if ((new FileOutput()).aguardarTexto(this.dataBaseTrivia, json)) {
                System.out.println("Exito");
            } else {
                System.out.println("fracaso");
            }
            System.out.println(this.dataBaseTrivia.getAbsolutePath());
        }
    }

    private void checkDataBaseUser(){
        boolean create = false;
        if (this.dataBaseUser.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseUser));
            if (getDataBaseFile.isEmpty()) {//no hay datos
                create = !create;
            }
        } else {
            create = !create;
        }
        if (create) {
            List<User> listaUser = new ArrayList<>();

            listaUser.add(new User("ADMIN", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "2001-04-2"));
            listaUser.add(new User("BJ_97", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "1997-07-28"));

            // Convertir la lista de objetos a JSON
            Gson gson = new Gson();
            String json = gson.toJson(listaUser);

            if ((new FileOutput()).aguardarTexto(this.dataBaseUser, json)) {
                System.out.println("Exito");
            } else {
                System.out.println("fracaso");
            }
            System.out.println(this.dataBaseUser.getAbsolutePath());
        }
    }
}
