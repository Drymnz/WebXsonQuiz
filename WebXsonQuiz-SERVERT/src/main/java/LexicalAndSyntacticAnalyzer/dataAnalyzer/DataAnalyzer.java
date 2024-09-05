package LexicalAndSyntacticAnalyzer.dataAnalyzer;

import LexicalAndSyntacticAnalyzer.analyzer.Token;

public class DataAnalyzer {
    private Token token;
    private ListTypeData type;
    private String data;
    
    public DataAnalyzer(Token token, ListTypeData type, String data) {
        this.token = token;
        this.type = type;
        this.data = data;
    }

    public Token getToken() {
        return token;
    }

    public ListTypeData getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return  "token"+token.toString()+
        "type:"+type.toString()+
        "data:"+data;
    }
}
