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

    @Override
    public String toString() {
        return "Type :"+this.type.toString() + getDataString ();
    }
    
    private String getDataString (){
        String listData = "";
        for (DataAnalyzer elemet : this.list) {
            listData = listData+ "\n" +elemet.toString();
        }
        return listData;
    }
}
