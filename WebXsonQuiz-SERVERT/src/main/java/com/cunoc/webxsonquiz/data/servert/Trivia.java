package com.cunoc.webxsonquiz.data.servert;

import java.io.Serializable;
import java.util.ArrayList;

public class Trivia  implements Serializable{

    private static final long serialVersionUID = 3L;

    private String id;
    private String name;
    private double time;
    private String theme;
    private String idUser;
    private String date;
    private ArrayList<ComponentTrivia> structure = new ArrayList<>();

    
    public Trivia(String id, String name, double time, String theme, String idUser,String date) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.theme = theme;
        this.idUser = idUser;
        this.date = date;
    }

    public void addComponent(ComponentTrivia component){
        this.structure.add(component);
    }

    public ArrayList<ComponentTrivia> getListComponet() {
        return structure;
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

    public String getDate() {
        return date;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ComponentTrivia> getStructure() {
        return structure;
    }

    public void setStructure(ArrayList<ComponentTrivia> structure) {
        this.structure = structure;
    }

}
