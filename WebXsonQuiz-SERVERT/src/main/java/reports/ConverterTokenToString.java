package reports;

import java.util.ArrayList;

public class ConverterTokenToString {
    
    private final String MESSAGE_TITLE = "Error de análisis encontrado.\n";
    
    public String converterTokenToString(ArrayList<ReportErrorInterpreter> listError){
        String returnSringConveerter = this.MESSAGE_TITLE;
        for (ReportErrorInterpreter iterable_element : listError) {
            returnSringConveerter += "\n"+iterable_element.toString();
        }
        returnSringConveerter +="\n";
        return returnSringConveerter;
    }
}
