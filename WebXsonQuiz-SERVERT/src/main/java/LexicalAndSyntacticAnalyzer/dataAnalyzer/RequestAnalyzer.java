package LexicalAndSyntacticAnalyzer.dataAnalyzer;

import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.ListRequests;

public class RequestAnalyzer {
    private ArrayList<DataAnalyzer> list;
    private ListRequests type;

    public RequestAnalyzer(ArrayList<DataAnalyzer> list, ListRequests type) {
        this.list = list;
        this.type = type;
    }

    public ArrayList<DataAnalyzer> getList() {
        return list;
    }

    public ListRequests getType() {
        return type;
    }
    
}
