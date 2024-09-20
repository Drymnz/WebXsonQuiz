package com.cunoc.webxsonquiz.data.servert;

import java.io.Serializable;

public class ComponentTrivia implements Serializable {

    private static final long serialVersionUID = 2L;

    private String idComponent;
    private ClassComponent type ;
    private int index;
    private String text;
    private String options;
    private int row;
    private int column;
    private String result;
    private String idTrivia;
    
    public ComponentTrivia(String idComponent, ClassComponent type, int index, String text, String options, int row,
            int column, String result,String idTrivia) {
        this.idComponent = idComponent;
        this.type = type;
        this.index = index;
        this.text = text;
        this.options = options;
        this.row = row;
        this.column = column;
        this.result = result;
        this.idTrivia = idTrivia;
    }

    public String getIdComponent() {
        return idComponent;
    }

    public ClassComponent getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public String getOptions() {
        return options;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getResult() {
        return result;
    }

    public String getIdTrivia() {
        return idTrivia;
    }
    
}
