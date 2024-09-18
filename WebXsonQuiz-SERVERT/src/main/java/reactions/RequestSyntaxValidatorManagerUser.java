package reactions;

import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.User;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;

public class RequestSyntaxValidatorManagerUser {
    private AnalyzerManagerUser analizer;
    private ArrayList<RequestAnalyzer> listErrorRequest = new ArrayList<>();
    private ArrayList<RequestAnalyzer> listAccept = new ArrayList<>();
    private DataBaseListUser dataBaseUser;


    public RequestSyntaxValidatorManagerUser(AnalyzerManagerUser analizer,DataBaseListUser dataBaseUser) {
        this.dataBaseUser = dataBaseUser;
        this.analizer = analizer;
    }

    public boolean isErrorRequest() {
        return this.listErrorRequest.size() > 0;
    }

    public ArrayList<RequestAnalyzer> getListErrorRequest() {
        return this.listErrorRequest;
    }

    public void checkRequests() {
        if (this.analizer != null) {
            for (RequestAnalyzer elemetRequest : this.analizer.getListRquest()) {
                switch (elemetRequest.getType()) {
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
                    default:
                        break;
                }
            }
        }
    }

    private boolean checkUserNew(RequestAnalyzer data) {
        User checkOne = (new ConverterToObject()).createUserListDataAnalyzerLogin(data.getList());
        if ((checkOne == null)) return false;
        User checkDataBase = this.getUserDataBaseId(checkOne.getId());
        if ((checkDataBase != null)) return false;
        this.dataBaseUser.getListUsers().add(checkOne);
        return true;
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
    
    public void upDataBase(){
        if (this.dataBaseUser.upDataBase()) {
            System.out.println("Actualizo");
        }else{
            System.out.println("No actualizo");
        }
    }
}
