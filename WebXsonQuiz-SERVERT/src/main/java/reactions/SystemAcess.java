package reactions;

import LexicalAndSyntacticAnalyzer.ListRequests;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerLogin;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.ConverterToObject;
import reports.ConverterTokenToString;

import com.cunoc.webxsonquiz.data.servert.User;
import com.password4j.Password;

public class SystemAcess {
    private String textAnalyzer;
    private User userClient;
    private String errorToken;

    public SystemAcess(String textAnalyzer) {
        this.textAnalyzer = textAnalyzer;
    }

    public User loginSystem() {
        if (isAcceder()) {
            return this.userClient;
        } else {
            return null;
        }
    }

    public boolean isAcceder() {
        User UserWantToAccess = userWantToAccess();
        if (UserWantToAccess == null)
            return false;
        this.userClient = searchByID(new DataBaseListUser().getDataBaseUserToRequetsAnalyzer(),
                UserWantToAccess.getId());
        if (this.userClient != null) {
            return isEquetUser(this.userClient, UserWantToAccess);
        } else {
            return false;
        }
    }

    private boolean isEquetUser(User userClient, User UserWantToAccess) {
        boolean equalsId = userClient.getId().equals(UserWantToAccess.getId());
        if(!equalsId) return false;
        String password = UserWantToAccess.getPassword();
        String hash = userClient.getPassword();
        byte[] passwordBytes = hash.getBytes(StandardCharsets.UTF_8);
        String utf8Password = new String(passwordBytes, StandardCharsets.UTF_8);
        boolean passwordBoolean = Password.check(password,utf8Password ).withArgon2();
        return equalsId && passwordBoolean;
    }

    private User userWantToAccess() {
        AnalyzerLogin analyzer = new AnalyzerLogin(this.textAnalyzer);
        analyzer.Analyze();
        if (analyzer.isError()) {
            this.errorToken = (new ConverterTokenToString()).converterTokenToString(analyzer.getListError());
            return null;
        }
        if (analyzer.getListRquest().size() < 1)
            return null;
        RequestAnalyzer userLogin = analyzer.getListRquest().get(0);
        if (!(userLogin.getType() == ListRequests.LOGIN_USER))
            return null;
        ConverterToObject converter = new ConverterToObject();
        return (converter).createUserListDataAnalyzerLogin(userLogin.getList());
    }

    private User searchByID(ArrayList<RequestAnalyzer> list, String id) {
        if (list.size() == 0)
            return null;
        for (RequestAnalyzer element : list) {
            User checkUser = (new ConverterToObject()).getRequestAnalyzerToUser(element);
            if (checkUser.getId().equals(id))
                return checkUser;
        }
        return null;
    }

    public String getErrorToken() {
        return errorToken;
    }

}
