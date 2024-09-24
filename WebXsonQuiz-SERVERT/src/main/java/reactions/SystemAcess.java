package reactions;

import LexicalAndSyntacticAnalyzer.ListRequests;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerLogin;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import com.cunoc.webxsonquiz.data.servert.User;

public class SystemAcess {
    private String textAnalyzer;
    private User userClient;

    public SystemAcess(String textAnalyzer) {
        this.textAnalyzer = textAnalyzer;
    }
    
    public User loginSystem(){
        if (isAcceder()) {
            return this.userClient;
        }else{
            return null;
        }
    }
    
    public boolean isAcceder(){
        User UserWantToAccess = userWantToAccess();
        if (UserWantToAccess == null) return false;
        this.userClient = searchByID(new DataBaseListUser().getDataBaseUserToRequetsAnalyzer(), UserWantToAccess.getId());
        if (this.userClient!= null ) {
            return isEquetUser(this.userClient,UserWantToAccess );
        } else {
            return false;
        }
    }

    private boolean isEquetUser(User one, User two){
        return one.getId().equals(two.getId()) && one.getPassword().equals(two.getPassword());
    }

    private User userWantToAccess(){
        AnalyzerLogin analyzer = new AnalyzerLogin(this.textAnalyzer);
        analyzer.Analyze();
        RequestAnalyzer userLogin = analyzer.getListRquest().get(0);
        if (!(userLogin.getType() == ListRequests.LOGIN_USER)) return null;
        ConverterToObject converter = new ConverterToObject();
        return (converter).createUserListDataAnalyzerLogin(userLogin.getList());
    }

    private User searchByID( ArrayList<RequestAnalyzer> list , String id){
        if(list.size()==0)return null;
        for (RequestAnalyzer element : list) {
            User checkUser = (new ConverterToObject()).getRequestAnalyzerToUser(element);
            if(checkUser.getId().equals(id)) return checkUser;
        }
        return null;
    }

}
