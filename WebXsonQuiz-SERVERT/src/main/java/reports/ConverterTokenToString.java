package reports;

import java.util.ArrayList;

public class ConverterTokenToString {
    public String converterTokenToString(ArrayList<ReportErrorInterpreter> listError){
        String returnSringConveerter = "";
        for (ReportErrorInterpreter iterable_element : listError) {
            returnSringConveerter += "\n"+iterable_element.toString();
        }
        return returnSringConveerter;
    }
}
