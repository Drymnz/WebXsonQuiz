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
import reactions.DataBaseListUser;
import reactions.RequestSyntaxValidatorManagerUser;

public class ModelDatabaseFileManager {

    private final String STORED_FILE_PATH = "Ruta del archivo almacenado => ";

    private File dataBaseUser = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
    private File dataBaseTrivia = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_TRIVIA);
    private File dataBaseQuiz = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_QUIZ_ATTERMPT);

    public void checkDataBaseIsCreated() {
        this.checkDataBaseUser();
        this.checkDataBaseTrivia();
        this.checkDataBaseQuizAttempt();
    }

    private void checkDataBaseQuizAttempt() {
        boolean create = false;
        if (this.dataBaseQuiz.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseQuiz));
            if (getDataBaseFile.isEmpty()) {// no hay datos
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
            System.out.println(this.STORED_FILE_PATH + this.dataBaseQuiz.getAbsolutePath());
        }
    }

    private void checkDataBaseTrivia() {
        boolean create = false;
        if (this.dataBaseTrivia.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseTrivia));
            if (getDataBaseFile.isEmpty()) {// no hay datos
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
            System.out.println(this.STORED_FILE_PATH + this.dataBaseTrivia.getAbsolutePath());
        }
    }

    private void checkDataBaseUser() {
        boolean create = false;
        if (this.dataBaseUser.exists()) {// existe
            String getDataBaseFile = (new FileInput().cargarArchivoTexto(this.dataBaseUser));
            if (getDataBaseFile.isEmpty()) {// no hay datos
                create = !create;
            }
        } else {
            create = !create;
        }
        if (create) {
            ArrayList<User> listaUser = new ArrayList<>();

            String passwordUserFirts = RequestSyntaxValidatorManagerUser.encriptar("admin");

            User userFirts = new User("admin", passwordUserFirts, "Benjamin de Jesus Perez Aguilar", "CUNOC", "2001-04-2");
            User usersegunda = new User("BJ_97", passwordUserFirts, "Benjamin de Jesus Perez Aguilar", "CUNOC", "2001-04-2");

            listaUser.add(userFirts);
            listaUser.add(usersegunda);
            
            DataBaseListUser dataBase = new DataBaseListUser();
            dataBase.getListUsers().addAll(listaUser);

            if (dataBase.upDataBase()) {
                System.out.println("Exito");
            } else {
                System.out.println("fracaso");
            }
            System.out.println(this.STORED_FILE_PATH + this.dataBaseUser.getAbsolutePath());
        }
    }

}
