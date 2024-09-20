package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import com.cunoc.webxsonquiz.data.servert.ClassComponent;
import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;
import com.cunoc.webxsonquiz.data.servert.Trivia;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class ConverterAnalyzerToObjectComponentTrivia {

    public ComponentTrivia newComponent(RequestAnalyzer element) {
        String idComponent = "";
        String trivia = "";
        ClassComponent type = ClassComponent.NULL_EMPTY;
        int index = 0;
        String text = "";
        String options = "";
        int row = 0;
        int column = 0;
        String result = "";
        for (DataAnalyzer iterable_element : element.getList()) {
            switch (iterable_element.getType()) {
                case ID:
                    idComponent = iterable_element.getData().replace("\"", "");
                    break;
                case TRIVIA:
                    trivia = iterable_element.getData().replace("\"", "");
                    break;
                case CLASE:
                    type = this.selectClassComponent(iterable_element.getData().replace("\"", ""));
                    break;
                case TEXTO_VISIBLE:
                    text = iterable_element.getData().replace("\"", "");
                    break;
                case RESPUESTA:
                    result = iterable_element.getData().replace("\"", "");
                    break;
                case INDICE:
                    index = Integer.valueOf(iterable_element.getData().replace("\"", ""));
                    break;
                case OPCIONES:
                    options = iterable_element.getData().replace("\"", "");
                    break;
                case FILAS:
                    row = Integer.valueOf(iterable_element.getData().replace("\"", ""));
                    break;
                case COLUMNAS:
                    column = Integer.valueOf(iterable_element.getData().replace("\"", ""));
                    break;
                default:
                    break;
            }
        }
        return new ComponentTrivia(idComponent, type, index, text, options, row, column, result, trivia);
    }

    private ClassComponent selectClassComponent(String clas) {
        switch (clas) {
            case "AREA_TEXTO":
                return ClassComponent.AREA_TEXT;
            case "CAMPO_TEXTO":
                return ClassComponent.CAMPO_TEXT;
            case "CHECKBOX":
                return ClassComponent.CHECKBOX;
            case "COMBO":
                return ClassComponent.COMBO;
            case "FICHERO":
                return ClassComponent.FICHERO;
            case "RADIO":
                return ClassComponent.RADIO;
            default:
                return null;
        }
    }

    public ComponentTrivia minimumConditions(RequestAnalyzer element){
        ComponentTrivia returnComponentTrivia = this.newComponent(element);
        if(returnComponentTrivia.getIdComponent().isEmpty()) return null;
        if(returnComponentTrivia.getIdTrivia().isEmpty()) return null;
        if(returnComponentTrivia.getType() == ClassComponent.NULL_EMPTY) return null;
        if(returnComponentTrivia.getText().isEmpty()) return null;
        if(returnComponentTrivia.getResult().isEmpty()) return null;
        return returnComponentTrivia;
    }
}
