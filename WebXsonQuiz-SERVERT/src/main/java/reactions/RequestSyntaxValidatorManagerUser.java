package reactions;

import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.User;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerManagerUser;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class RequestSyntaxValidatorManagerUser {
    private AnalyzerManagerUser analizer;
    private ArrayList<RequestAnalyzer> listErrorRequest;
    private ArrayList<RequestAnalyzer> listAccept;
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
                    this.addListCheck(elemetRequest,false);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean checkUserModify(RequestAnalyzer data) {
        User check = this.dataBaseUser.requestAnalyzarToUser(data);
        if (!(check != null)) return false;
        User checkDataBase = this.getUserDataBaseId(check.getId());
        if (checkDataBase != null) {
            return true;
        }
        return false;
    }

    private void addListCheck(RequestAnalyzer data,boolean ckeck){
        if (ckeck) {
            this.listAccept.add(data);
        } else {
            this.listErrorRequest.add(data);
        }
    }

    private boolean checkUserDel(RequestAnalyzer data) {
        User check = this.dataBaseUser.requestAnalyzarToUser(data);
        if (!(check != null)) return false;
        User checkDataBase = this.getUserDataBaseId(check.getId());
        if (checkDataBase != null) {
            return true;
        }
        return false;
    }

    private User getUserDataBaseId(String id){
        for (User check : this.dataBaseUser.getListUsers()) {
            if(check.getId().equals(id)) return check;
        }
        return null;
    }
}
