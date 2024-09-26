package reports;

import Lengua.LanguageConstants;
import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import reactions.RequestSyntaxValidatorManagerUser;

public class UserRequestReport {
    private RequestSyntaxValidatorManagerUser guide;
    private final String MAKE_REQUESTS = "<!envio_respuestas>";
    private final String END_REQUESTS_COMPLETED = "<!fin_envio_respuestas>";
    private final String MAKE_REQUEST = "<!envio_respuesta:";
    private final String END_REQUEST_COMPLETED = " <!fin_envio_respuesta>";


    public UserRequestReport(RequestSyntaxValidatorManagerUser guide) {
        this.guide = guide;
    }

    public String reportString() {
        if (this.guide == null || !(this.thereAreReports()))
            return null;
        String returnString = "<?xson version=”1.0” ?>";
        returnString += this.moreThanOneReports() ? "\n"+this.MAKE_REQUESTS : "";
        for (RequestAnalyzer element : this.guide.getListRequest()) {
            returnString += "\n" + this.changeVariants(element, true);
            returnString += "\n"+this.END_REQUESTS_COMPLETED ;
        }
        for (RequestAnalyzer element : this.guide.getListErrorRequest()) {
            returnString += "\n" + this.changeVariants(element, false);
            returnString += "\n"+this.END_REQUESTS_COMPLETED ;
        }
        returnString += this.moreThanOneReports() ? "\n"+this.END_REQUEST_COMPLETED : "";
        return returnString;
    }

    private String changeVariants(RequestAnalyzer element, boolean acceptanceOrRrror) {
        if (element == null)
            return "";
        String returnString = "";
        returnString += this.sendResponse(element.getType(), acceptanceOrRrror);
        returnString += "\n{ \"DATOS_USUARIO\":[{";
        boolean firtData = false;
        for (DataAnalyzer data_element : element.getList()) {
            String firtDataString = firtData ? "," : "";
            returnString += "\n"+firtDataString + returnStringData(data_element);
            firtData = true;
        }
        returnString += "}]}\n";
        return returnString;
    }

    private String returnStringData(DataAnalyzer data_element) {
        switch (data_element.getType()) {
            case DATE:
                return "\"FECHA_CREACION\":" + data_element.getData();
            case INSTITUTION:
                return "\"INSTITUCION\":" + data_element.getData();
            case NAME:
                return "\"NOMBRE\":" + data_element.getData();
            case NUEVO_PASSWORD:
                return "\"NUEVO_PASSWORD\":" + data_element.getData();
            case PASSWORD:
                return "\"PASSWORD\":" + data_element.getData();
            case USUARIO:
                return "\"USUARIO\":" + data_element.getData();
            case USUARIO_ANTIGUO:
                return "\"USUARIO_ANTIGUO\":" + data_element.getData();
            case ID_TRIVIA:
                return "\"ID_TRIVIA\":" + data_element.getData();
            case TIEMPO_PREGUNTA:
                return "\"TIEMPO_PREGUNTA\":" + data_element.getData();
            case USUARIO_CREACION:
                return "\"USUARIO_CREACION\":" + data_element.getData();
            case TEMA:
                return "\"TEMA\":" + data_element.getData();
            case ID:
                return "\"ID\":" + data_element.getData();
            case TRIVIA:
                return "\"TRIVIA\":" + data_element.getData();
            case CLASE:
                return "\"CLASE\":" + data_element.getData();
            case TEXTO_VISIBLE:
                return "\"TEXTO_VISIBLE\":" + data_element.getData();
            case RESPUESTA:
                return "\"RESPUESTA\":" + data_element.getData();
            case INDICE:
                return "\"INDICE\":" + data_element.getData();
            case OPCIONES:
                return "\"OPCIONES\":" + data_element.getData();
            case COLUMNAS:
                return "\"COLUMNAS\":" + data_element.getData();
            case FILAS:
                return "\"FILAS\":" + data_element.getData();
            default:
                return "\"UNKNOWN\":" + data_element.getData();
        }
    }

    private String sendResponse(ListRequests type, boolean acceptanceOrRrror) {
        String stringAcceptanceOrRrror = acceptanceOrRrror ? LanguageConstants.APPROVED_REQUEST
                : LanguageConstants.DENIED_REQUEST;
        switch (type) {
            case ELIMINAR_USUARIO:
                return this.MAKE_REQUEST+ "\"ELIMINAR_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case LOGIN_USER:
                return this.MAKE_REQUEST+ "\"LOGIN_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case MODIFICAR_USUARIO:
                return this.MAKE_REQUEST+ "\"MODIFICAR_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case NEW_USER:
                return this.MAKE_REQUEST+ "\"USUARIO_NUEVO\"" + stringAcceptanceOrRrror + "\"\">";
            case NEW_TRIVIA:
                return this.MAKE_REQUEST+ "\"NUEVA_TRIVIA\"" + stringAcceptanceOrRrror + "\"\">";
            case MODIFICAR_TRIVIA:
                return this.MAKE_REQUEST+ "\"MODIFICAR_TRIVIA\"" + stringAcceptanceOrRrror + "\"\">";
            case ELIMINAR_TRIVIA:
                return this.MAKE_REQUEST+ "\"ELIMINAR_TRIVIA\"" + stringAcceptanceOrRrror + "\"\">";
            case AGREGAR_COMPONENTE:
                return this.MAKE_REQUEST+ "\"AGREGAR_COMPONENTE\"" + stringAcceptanceOrRrror + "\"\">";
            case MODIFICAR_COMPONENTE:
                return this.MAKE_REQUEST+ "\"MODIFICAR_COMPONENTE\"" + stringAcceptanceOrRrror + "\"\">";
            case ELIMINAR_COMPONENTE:
                return this.MAKE_REQUEST+ "\"ELIMINAR_COMPONENTE\"" + stringAcceptanceOrRrror + "\"\">";
            default:
                return this.MAKE_REQUEST+ "\"UNKNOWN\"" + stringAcceptanceOrRrror + "\"\">";
        }
    }

    private boolean moreThanOneReports() {
        return this.guide.getListErrorRequest().size() > 1 || this.guide.getListRequest().size() > 1;
    }

    private boolean thereAreReports() {
        return this.guide.getListErrorRequest().size() > 0 || this.guide.getListRequest().size() > 0;
    }
}
