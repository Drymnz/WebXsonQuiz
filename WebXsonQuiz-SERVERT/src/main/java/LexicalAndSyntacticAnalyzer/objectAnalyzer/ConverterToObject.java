package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import com.cunoc.webxsonquiz.data.servert.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class ConverterToObject {

    public User getRequestAnalyzerToUser(RequestAnalyzer element) {
        boolean[] list = { false, false, false, false };
        User newUser = createUserListDataAnalyzer(element.getList(),list);
        if (isListTrue(list)) {
            return newUser;
        }else{
            return null;
        }
    }

    public User createUserListDataAnalyzerLogin(ArrayList<DataAnalyzer> listDataAnalyzer ){
        boolean[] list = { false, false, false, false };
        return createUserListDataAnalyzer(listDataAnalyzer,list);
    }

    public User createUserListDataAnalyzer(ArrayList<DataAnalyzer> listDataAnalyzer, boolean[] list ){
        String id = "";
        String password= "";
        String name= "";
        String institution= "";
        String date= null;
        
        for (DataAnalyzer dataElement : listDataAnalyzer) {
            switch (dataElement.getType()) {
                case INSTITUTION:
                    institution = dataElement.getData().replace("\"", "");
                    list[0] = true;
                    break;
                case NAME:
                    name = dataElement.getData().replace("\"", "");
                    list[1] = true;
                    break;
                case PASSWORD:
                    password = dataElement.getData().replace("\"", "");
                    list[2] = true;
                    break;
                case USUARIO:
                    id = dataElement.getData().replace("\"", "");
                    list[3] = true;
                    break;
                case DATE:
                    date = dataElement.getData().replace("\"", "");
                    list[3] = true;
                    break;
                default:
                    break;
            }
        }

        date = (date==null)? getDate() : date; 
        return new User(id, password, name, institution,date);
    }

    public String getDate() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);

        return fechaFormateada;
    }

    private boolean isListTrue(boolean[] booleanos){
        for (boolean valor : booleanos) {
            if (!valor) {
                return false;
            }
        }
        return true;
    }
}
