package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class ConverterAnalyzerToObjectTrivia {
    private User user = null;
    
    public ConverterAnalyzerToObjectTrivia(User user) {
        this.user = user;
    }

    public Trivia newTrivia(RequestAnalyzer element) {
        if (element == null)
            return null;
        String id = "";
        String name = "";
        double time = 0;
        String theme = "";
        String idUser = "";
        String date = "";
        for (DataAnalyzer iterable_element : element.getList()) {
            switch (iterable_element.getType()) {
                case ID_TRIVIA:
                    id = iterable_element.getData().replace("\"", "");
                    break;
                case TIEMPO_PREGUNTA:
                    time = Double.parseDouble(iterable_element.getData());
                    break;
                case NAME:
                    name = iterable_element.getData().replace("\"", "");
                    break;
                case TEMA:
                    theme = iterable_element.getData().replace("\"", "");
                    break;
                case USUARIO_CREACION:
                    idUser = iterable_element.getData().replace("\"", "");
                    break;
                case DATE:
                    date = iterable_element.getData().replace("\"", "");
                    break;
                default:
                    break;
            }
        }
        date = date.isEmpty()? getDate(): date;
        idUser = idUser.isEmpty()? (this.user != null)? this.user.getId(): "" : idUser;
        return new Trivia(id, name, time, theme, idUser,date);
    }

    public Trivia minimumConditions(RequestAnalyzer element){
        Trivia returTrivia = this.newTrivia(element);
        if(returTrivia.getId().isEmpty()) return null;
        if(returTrivia.getName().isEmpty()) return null;
        if(returTrivia.getTime()<=0) return null;
        if(returTrivia.getTheme().isEmpty()) return null;
        if(returTrivia.getIdUser().isEmpty()) return null;
        if(returTrivia.getDate().isEmpty()) return null;
        return returTrivia;
    }

    public String getDate() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);
        return fechaFormateada;
    }

}
