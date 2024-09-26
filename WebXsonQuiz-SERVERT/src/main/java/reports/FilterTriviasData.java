package reports;

import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.Trivia;

import reactions.DataBaseListTrivia;

public class FilterTriviasData {
    private DataBaseListTrivia dataBase;

    public FilterTriviasData(DataBaseListTrivia dataBase) {
        this.dataBase = dataBase;
    }

    public ArrayList<Trivia> getListTriviasByIdUser(String idUser) {
        ArrayList<Trivia> listReturn = new ArrayList();
        for (Trivia element_trivia : this.dataBase.getListTrivias()) {
            if (idUser.equals(element_trivia.getIdUser())) {
                listReturn.add(element_trivia);
            }
        }
        return listReturn;
    }  
    
    public ArrayList<Trivia> getListTriviasAll() {
        return this.dataBase.getListTrivias();
    }
    
    public Trivia getTriviaId(String idTrivia){
        for (Trivia element_trivia : this.dataBase.getListTrivias()) {
            if (idTrivia.equals(element_trivia.getId())) {
               return element_trivia;
            }
        }
        return null;
    }
}
