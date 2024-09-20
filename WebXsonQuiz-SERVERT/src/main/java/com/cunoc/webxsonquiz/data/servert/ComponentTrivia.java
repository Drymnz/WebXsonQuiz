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
    
    public ComponentTrivia(String idComponent, ClassComponent type, int index, String text, String options, int row,
            int column, String result) {
        this.idComponent = idComponent;
        this.type = type;
        this.index = index;
        this.text = text;
        this.options = options;
        this.row = row;
        this.column = column;
        this.result = result;
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
    
}
