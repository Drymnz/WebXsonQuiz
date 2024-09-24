package LexicalAndSyntacticAnalyzer.objectAnalyzer;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;

import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;

public class ConverterAnalyzerToObjetQuizAttempt {
    
    public QuizAttempt newQuizAttempt(RequestAnalyzer element){
        if (element == null) return null;
        String user = "";
        String quizId = "";
        int responseTime = 0;
        int score = 0;
        for (DataAnalyzer iterable_element : element.getList()) {
            switch (iterable_element.getType()) {
                case ID_USER:
                    user = iterable_element.getData().replace("\"", "");
                    break;
                case ID_TRIVIA:
                    quizId = iterable_element.getData().replace("\"", "");
                    break;
                case RESPONSE_TIME:
                    responseTime  = (int) Double.parseDouble(iterable_element.getData().replace("\"", ""));
                    break;
                case SCORE:
                    score  = (int) Double.parseDouble(iterable_element.getData().replace("\"", ""));
                    break;
                default:
                    break;
            }
        }
        return new QuizAttempt(user, quizId, responseTime, score);
    }
}
