package com.cunoc.webxsonquiz.data.servert;

import java.io.Serializable;

public class Trivia  implements Serializable{

    private static final long serialVersionUID = 3L;

    private String id;
    private String name;
    private double time;
    private String theme;
    private String idUser;

    
    public Trivia(String id, String name, double time, String theme, String idUser) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.theme = theme;
        this.idUser = idUser;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public double getTime() {
        return time;
    }


    public String getTheme() {
        return theme;
    }


    public String getIdUser() {
        return idUser;
    }

}
