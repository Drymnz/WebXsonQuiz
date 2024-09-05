package LexicalAndSyntacticAnalyzer.objectAnalyzer;

public class User {

    private String id;
    private String password;
    private String name;
    private String institution;

    public User(String id, String password, String name, String institution) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.institution = institution;
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
                " institution:" + institution;
    }

}
