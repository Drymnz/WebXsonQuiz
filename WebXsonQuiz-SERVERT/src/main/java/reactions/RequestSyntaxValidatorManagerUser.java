package reactions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;
import com.password4j.Password;

import Lengua.LanguageConstants;
import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectComponentTrivia;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterAnalyzerToObjectTrivia;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import reports.ReportErrorInterpreter;

public class RequestSyntaxValidatorManagerUser {
    private AnalyzerManagerUser analizer;
    private ArrayList<RequestAnalyzer> listErrorRequest = new ArrayList<>();
    private ArrayList<RequestAnalyzer> listAccept = new ArrayList<>();
    private DataBaseListUser dataBaseUser;
    private DataBaseListTrivia dataBaseTrivia;
    private User user = null;

    public RequestSyntaxValidatorManagerUser(DataBaseListUser dataBaseUser,DataBaseListTrivia dataBaseTrivia,User user) {
        this.dataBaseUser = dataBaseUser;
        this.dataBaseTrivia = dataBaseTrivia;
        this.user = user;
    }

    public RequestSyntaxValidatorManagerUser(AnalyzerManagerUser analizer,DataBaseListUser dataBaseUser,DataBaseListTrivia dataBaseTrivia,User user) {
        this.dataBaseUser = dataBaseUser;
        this.dataBaseTrivia = dataBaseTrivia;
        this.analizer = analizer;
        this.user = user;
    }

    public boolean isErrorRequest() {
        return this.listErrorRequest.size() > 0;
    }

    public ArrayList<RequestAnalyzer> getListRequest() {
        return this.listAccept;
    }

    public ArrayList<RequestAnalyzer> getListErrorRequest() {
        return this.listErrorRequest;
    }

    public boolean isErroLexicoOrSyntanc(){
        return this.analizer.isError();
    }

    public ArrayList<ReportErrorInterpreter> getListError(){
        return this.analizer.getListError();
    }

    //Check Solicitud
    public void checkRequests() {
        if (this.analizer != null) {
            for (RequestAnalyzer elemetRequest : this.analizer.getListRquest()) {
                switch (elemetRequest.getType()) {
                        //user
                    case ELIMINAR_USUARIO:
                        this.addListCheck(elemetRequest,checkUserDel(elemetRequest));
                        break;
                    case LOGIN_USER:
                        this.addListCheck(elemetRequest,false);
                        break;
                    case MODIFICAR_USUARIO:
                        this.addListCheck(elemetRequest,checkUserModify(elemetRequest));
                        break;
                    case NEW_USER:
                        this.addListCheck(elemetRequest,checkUserNew(elemetRequest));
                        break;
                        //componenet
                    case AGREGAR_COMPONENTE:
                        this.addListCheck(elemetRequest,addComponent(elemetRequest));
                        break;
                    case ELIMINAR_COMPONENTE:
                        this.addListCheck(elemetRequest,checkDelComponentTrivia(elemetRequest));
                        break;
                    case MODIFICAR_COMPONENTE:
                        this.addListCheck(elemetRequest,checkComponentTriviaModify(elemetRequest));
                        break;
                        //trivia
                    case ELIMINAR_TRIVIA:
                        this.addListCheck(elemetRequest,checkDelTrivia(elemetRequest));
                        break;
                    case MODIFICAR_TRIVIA:
                        this.addListCheck(elemetRequest,checkTriviaModify(elemetRequest));
                        break;
                    case NEW_TRIVIA:
                        this.addListCheck(elemetRequest,checkNewTrivia(elemetRequest));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private boolean checkComponentTriviaModify(RequestAnalyzer data) {
        ComponentTrivia checkOne = (new ConverterAnalyzerToObjectComponentTrivia().newComponent(data));
        if ((checkOne == null)) return false;
        Trivia checkTrivia = this.getTriviaDataBaseId(checkOne.getIdTrivia());
        if ((checkTrivia == null)) return false;
        ComponentTrivia checkDataBase = null;
        for (ComponentTrivia element : checkTrivia.getListComponet()) {
            if (element.getIdComponent().equals(checkOne.getIdComponent())) {
                checkDataBase = element;
                break;
            }
        }
        if ((checkDataBase == null)) return false;
        return this.dataBaseTrivia.modifityComponentTrivia(checkOne, checkDataBase);
    }

    private boolean checkTriviaModify(RequestAnalyzer data) {
        Trivia checkOne = (new ConverterAnalyzerToObjectTrivia(this.user).newTrivia(data));
        if ((checkOne == null)) return false;
        Trivia checkDataBase = this.getTriviaDataBaseId(checkOne.getId());
        return this.dataBaseTrivia.modifityTrivia(checkOne, checkDataBase);
    }

    private boolean checkDelComponentTrivia(RequestAnalyzer data) {
        ComponentTrivia checkOne = (new ConverterAnalyzerToObjectComponentTrivia().newComponent(data));
        if ((checkOne == null)) return false;
        Trivia checkDataBase = this.getTriviaDataBaseId(checkOne.getIdTrivia());
        if ((checkDataBase == null)) return false;
        for (int index = 0; index < checkDataBase.getListComponet().size(); index++) {
            if (checkDataBase.getListComponet().get(index).getIdComponent().equals(checkOne.getIdComponent())) {
                checkDataBase.getListComponet().remove(index);
                return true;
            }
        }
        return false;
    }

    private boolean checkDelTrivia(RequestAnalyzer data) {
        Trivia checkOne = (new ConverterAnalyzerToObjectTrivia(this.user).newTrivia(data));
        if ((checkOne == null)) return false;
        Trivia checkDataBase = this.getTriviaDataBaseId(checkOne.getId());
        if (checkDataBase == null) return false;
        return  this.dataBaseTrivia.delTrivia(checkDataBase);
    }

    private boolean addComponent(RequestAnalyzer data) {
        ComponentTrivia checkOne = (new ConverterAnalyzerToObjectComponentTrivia().newComponent(data));
        if ((checkOne == null)) return false;
        Trivia checkDataBase = this.getTriviaDataBaseId(checkOne.getIdTrivia());
        if ((checkDataBase == null)) return false;
        int counter = 0;
        for (ComponentTrivia element : checkDataBase.getListComponet()) {
            if (element.getIdComponent().equals(checkOne.getIdComponent())) {
                return false;
            }
            counter++;
        }
        checkOne.setIndex(counter);
        checkDataBase.addComponent(checkOne);
        return true;
    }

    private boolean checkNewTrivia(RequestAnalyzer data) {
        Trivia checkOne = (new ConverterAnalyzerToObjectTrivia(this.user).minimumConditions(data));
        if ((checkOne == null)) return false;
        Trivia checkDataBase = this.getTriviaDataBaseId(checkOne.getId());
        if ((checkDataBase != null)) return false;
        this.dataBaseTrivia.getListTrivias().add(checkOne);
        return true;
    }

    public boolean checkNewTrivia(Trivia newTrivia){
        Trivia checkDataBase = this.getTriviaDataBaseId(newTrivia.getId());
        if ((checkDataBase != null)) return false;
        this.dataBaseTrivia.getListTrivias().add(newTrivia);
        return true;
    }

    private boolean checkUserNew(RequestAnalyzer data) {
        User checkOne = (new ConverterToObject()).minimumRequirementsNewUsario(data.getList());
        if ((checkOne == null)) return false;
        User checkDataBase = this.getUserDataBaseId(checkOne.getId());
        if ((checkDataBase != null)) return false;
        checkOne = encriptarPassword(checkOne);
        this.dataBaseUser.getListUsers().add(checkOne);
        return true;
    }

    private User encriptarPassword(User encriptarUser){
        encriptarUser.setPassword(encriptar(encriptarUser.getPassword()));
        return encriptarUser;
    }

    public static String encriptar(String password){
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        String utf8Password = new String(passwordBytes, StandardCharsets.UTF_8);
        return Password.hash(utf8Password).withArgon2().getResult();
    }

    private boolean checkUserModify(RequestAnalyzer data) {
        User checkOne = (new ConverterToObject()).getModifyOneUser(data);
        if ((checkOne == null)) return false;
        User checkDataBase = this.getUserDataBaseId(checkOne.getId());
        if ((checkDataBase == null)) return false;
        User checkTwo = (new ConverterToObject()).getModifyTwoUser(data);
        if ((checkTwo == null)) return false;
        return this.dataBaseUser.modifityUser(checkTwo, checkDataBase);
    }

    private void addListCheck(RequestAnalyzer data,boolean ckeck){
        if (ckeck) {
            this.listAccept.add(data);
        } else {
            this.listErrorRequest.add(data);
        }
    }

    private boolean checkUserDel(RequestAnalyzer data) {
        User check = (new ConverterToObject()).getDelUser(data);
        if ((check == null)) return false;
        User checkDataBase = this.getUserDataBaseId(check.getId());
        if (checkDataBase != null) {
            return dataBaseUser.delUser(checkDataBase);
        }
        return false;
    }

    private User getUserDataBaseId(String id){
        for (User check : this.dataBaseUser.getListUsers()) {
            if(check.getId().equals(id)) return check;
        }
        return null;
    }

    private Trivia getTriviaDataBaseId(String id){
        for (Trivia check : this.dataBaseTrivia.getListTrivias()) {
            if(check.getId().equals(id)) return check;
        }
        return null;
    }
    
    public void upDataBase(){
        if (this.dataBaseUser.upDataBase()) {
            System.out.println(LanguageConstants.DATABASE_UPDATA_USER);
        }else{
            System.out.println(LanguageConstants.NOT_DATABASE_UPDATA_USER);
        }
        if (this.dataBaseTrivia.upDataBase()) {
            System.out.println(LanguageConstants.DATABASE_UPDATA_TRIVIA);
        } else {
            System.out.println(LanguageConstants.DATABASE_UPDATA_TRIVIA);
        }
    }
}
