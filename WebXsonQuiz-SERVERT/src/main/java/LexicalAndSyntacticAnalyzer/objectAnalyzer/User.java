package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String password;
    private String name;
    private String institution;
    private String date;


    public User(String id, String password, String name, String institution,String date)  {
        this.id = id;
        this.password = password;
        this.name = name;
        this.institution = institution;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "ID:" + id +
                " password:" + password +
                " name:" + name +
                " institution:" + institution+
                " date:" + date
                ;
    }

    public String getDate() {
        return date;
    }

}
