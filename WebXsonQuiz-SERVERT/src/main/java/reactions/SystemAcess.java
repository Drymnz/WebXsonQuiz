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
        this.userClient = searchByID(new DataBaseListUser().getDataBaseUserToRequetsAnalyzer(), UserWantToAccess.getId());
        if (this.userClient!= null & UserWantToAccess!=null) {
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
        analyzer.Anilisar();
        ConverterToObject converter = new ConverterToObject();
        return (converter).createUserListDataAnalyzerLogin(analyzer.getListRquest().get(0).getList());
    }

    private User searchByID( ArrayList<RequestAnalyzer> list , String id){
        if(list.size()==0)return null;
        for (RequestAnalyzer element : list) {
            User checkUser = (new ConverterToObject()).getRequestAnalyzerToUser(element);
            if(checkUser.getId().equals(id) && element.getType() == ListRequests.LOGIN_USER ) return checkUser;
        }
        return null;
    }

}
