package reports;

import Lengua.LanguageConstants;
import LexicalAndSyntacticAnalyzer.ListRequests;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import reactions.RequestSyntaxValidatorManagerUser;

public class UserRequestReport {
    private RequestSyntaxValidatorManagerUser guide;

    public UserRequestReport(RequestSyntaxValidatorManagerUser guide) {
        this.guide = guide;
    }

    public String reportString() {
        if (this.guide == null || !(this.thereAreReports()))
            return null;
        String returnString = "<?xson version=”1.0” ?>";
        returnString += this.moreThanOneReports() ? "\n<!realizar_solicitudes>" : "";
        for (RequestAnalyzer element : this.guide.getListRequest()) {
            returnString += "\n"+this.changeVariants(element, true);
            returnString += "<!fin_envio_respuestas>";
        }
        for (RequestAnalyzer element : this.guide.getListErrorRequest()) {
            returnString += "\n"+this.changeVariants(element, false);
            returnString += "<!fin_envio_respuestas>";
        }
        returnString += this.moreThanOneReports() ? "\n<!envio_respuestas>" : "";
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
            returnString += firtDataString + returnStringData(data_element);
            firtData = true;
        }
        returnString += "}]}\n";
        return returnString;
    }

    private String returnStringData(DataAnalyzer data_element) {
        switch (data_element.getType()) {
            case DATE:
                return "\"FECHA_CREACION\":"+data_element.getData();
            case INSTITUTION:
                return "\"INSTITUCION\":"+data_element.getData();
            case NAME:
                return "\"NOMBRE\":"+data_element.getData();
            case NUEVO_PASSWORD:
                return "\"NUEVO_PASSWORD\":"+data_element.getData();
            case PASSWORD:
                return "\"PASSWORD\":"+data_element.getData();
            case USUARIO:
                return "\"USUARIO\":"+data_element.getData();
            case USUARIO_ANTIGUO:
                return "\"USUARIO_NUEVO\":"+data_element.getData();
            default:
                return "";
        }
    }

    private String sendResponse(ListRequests type, boolean acceptanceOrRrror) {
        String stringAcceptanceOrRrror = acceptanceOrRrror ? LanguageConstants.APPROVED_REQUEST
                : LanguageConstants.DENIED_REQUEST;
        switch (type) {
            case ELIMINAR_USUARIO:
                return "<!realizar_solicitud: \"ELIMINAR_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case LOGIN_USER:
                return "<!realizar_solicitud: \"LOGIN_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case MODIFICAR_USUARIO:
                return "<!realizar_solicitud: \"MODIFICAR_USUARIO\"" + stringAcceptanceOrRrror + "\"\">";
            case NEW_USER:
                return "<!realizar_solicitud: \"USUARIO_NUEVO\"" + stringAcceptanceOrRrror + "\"\">";
            default:
                return "";
        }
    }

    private boolean moreThanOneReports() {
        return this.guide.getListErrorRequest().size() > 1 || this.guide.getListRequest().size() > 1;
    }

    private boolean thereAreReports() {
        return this.guide.getListErrorRequest().size() > 0 || this.guide.getListRequest().size() > 0;
    }
}
