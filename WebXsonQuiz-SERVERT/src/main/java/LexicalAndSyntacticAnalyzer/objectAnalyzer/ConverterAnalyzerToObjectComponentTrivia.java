package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import com.cunoc.webxsonquiz.data.servert.ClassComponent;
import com.cunoc.webxsonquiz.data.servert.ComponentTrivia;

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
                    index  = (int) Double.parseDouble(iterable_element.getData().replace("\"", ""));
                    break;
                case OPCIONES:
                    options = iterable_element.getData().replace("\"", "");
                    break;
                case FILAS:
                    row= (int) Double.parseDouble(iterable_element.getData().replace("\"", ""));
                    break;
                case COLUMNAS:
                    column = (int) Double.parseDouble(iterable_element.getData().replace("\"", ""));
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
            case "AREA_TEXT":
                return ClassComponent.AREA_TEXT;
            case "NULL_EMPTY":
                return ClassComponent.AREA_TEXT;
            case "CAMPO_TEXTO":
                return ClassComponent.CAMPO_TEXT;
            case "CAMPO_TEXT":
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
                return ClassComponent.AREA_TEXT;
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
