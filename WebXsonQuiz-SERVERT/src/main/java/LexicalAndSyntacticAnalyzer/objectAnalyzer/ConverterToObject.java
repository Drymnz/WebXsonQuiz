package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class ConverterToObject {

    public User getRequestAnalyzerToUser(RequestAnalyzer element) {
        String id = "";
        String password= "";
        String name= "";
        String institution= "";
        String date= null;
        boolean[] list = { false, false, false, false };
        
        for (DataAnalyzer dataElement : element.getList()) {
            switch (dataElement.getType()) {
                case INSTITUTION:
                    institution = dataElement.getData();
                    list[0] = true;
                    break;
                case NAME:
                    name = dataElement.getData();
                    list[1] = true;
                    break;
                case PASSWORD:
                    password = dataElement.getData();
                    list[2] = true;
                    break;
                case USUARIO:
                    id = dataElement.getData();
                    list[3] = true;
                    break;
                case DATE:
                    id = dataElement.getData();
                    list[3] = true;
                    break;
                default:
                    break;
            }
        }

        if (isListTrue(list)) {
            date = (date==null)? getDate() : date; 
            return new User(id, password, name, institution,date);
        }else{
            return null;
        }
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
