package reports;

import java.util.ArrayList;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;

import LexicalAndSyntacticAnalyzer.analyzer.AnalyzerSQLKV;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.DataAnalyzer;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.OperationSQLKV;
import reactions.DataBaseListQuizAttempt;

public class Filter {
    private DataBaseListQuizAttempt dataBaseQA;
    private AnalyzerSQLKV analyzer;
    private ArrayList<QuizAttempt> listFilterReturn = new ArrayList();

    public Filter(DataBaseListQuizAttempt dataBaseQA, AnalyzerSQLKV analyzer) {
        this.dataBaseQA = dataBaseQA;
        this.analyzer = analyzer;
    }

    public ArrayList<QuizAttempt> filterList() {
        this.listTrivias();
        this.filterOperationSQLKV();
        return this.listFilterReturn;
    }

    private void filterOperationSQLKV() {
        for (OperationSQLKV operationSQLKV : this.analyzer.getListOperationSQLKV()) {
            switch (operationSQLKV.getType()) {
                /// data string
                case AND:
                    this.addAnd(operationSQLKV);
                    break;
                case OR:
                    this.addOr(operationSQLKV);
                    break;
                case NOT:
                    break;
                /// data number
                case GREATER_THAN:
                    this.addGreaterThan(operationSQLKV);
                    break;
                case LESS_THAN:
                    this.addLessThan(operationSQLKV);
                    break;
                case GREATER_THAN_OR_EQUAL:
                    break;
                case LESS_THAN_OR_EQUAL:
                    break;
                case EQUAL:
                    break;
                default:
                    break;
            }
        }
    }

    private void addGreaterThan(OperationSQLKV operationSQLKV) {
        if (operationSQLKV.getRight() == null) {
            deletettemBecauseItDoesNotComplyTypeString(operationSQLKV.getLeft());
        } else {
            // ListTypeData ringhtType = null;
        }
    }

    private void addLessThan(OperationSQLKV operationSQLKV) {
        if (operationSQLKV.getLeft() != null && operationSQLKV.getRight() != null) {
            Double dataRinght = this.converterDataToDouble(operationSQLKV.getRight());
            Double dataLeft = this.converterDataToDouble(operationSQLKV.getLeft());
            for (int i = 0; i < this.listFilterReturn.size(); i++) {
                QuizAttempt element = this.listFilterReturn.get(i);
                if (dataRinght == -1.0) {
                    if (!(dataLeft < element.getResponseTime())) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                } else if (dataRinght == -3.0) {
                    if (!(dataLeft < element.getScore())) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                } else if (dataLeft == -1.0) {
                    if (!(element.getResponseTime() < dataRinght)) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                } else if (dataLeft == -3.0) {
                    if (!(element.getScore() < dataRinght)) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                }
            }

        }
    }

    private Double converterDataToDouble(DataAnalyzer elemnt) {
        switch (elemnt.getType()) {
            case INDICE:
                return Double.parseDouble(elemnt.getData().replace("\"", ""));
            case RESPONSE_TIME:
                return -1.0;
            case TIEMPO_PREGUNTA:
                return -2.0;
            case SCORE:
                return -3.0;
            default:
                return 0.0;
        }
    }

    private void addAnd(OperationSQLKV operationSQLKV) {
        if (operationSQLKV.getRight() == null) {
            deletettemBecauseItDoesNotComplyTypeString(operationSQLKV.getLeft());
        } else {
            String right = returnarStringData(operationSQLKV.getLeft());
            String left = returnarStringData(operationSQLKV.getRight());
            for (int i = 0; i < this.listFilterReturn.size(); i++) {
                if (!this.listFilterReturn.get(i).getUser().equals(right)) {
                    if (!this.listFilterReturn.get(i).getUser().equals(left)) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                }
            }
        }
    }
    private void addOr(OperationSQLKV operationSQLKV) {
        if (operationSQLKV.getRight() == null) {
            deletettemBecauseItDoesNotComplyTypeString(operationSQLKV.getLeft());
        } else {
            String right = returnarStringData(operationSQLKV.getLeft());
            String left = returnarStringData(operationSQLKV.getRight());
            for (int i = 0; i < this.listFilterReturn.size(); i++) {
                if (!this.listFilterReturn.get(i).getUser().equals(right)) {
                    if (!this.listFilterReturn.get(i).getUser().equals(left)) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                }
            }
        }
    }

    private String returnarStringData(DataAnalyzer elemnt) {
        switch (elemnt.getType()) {
            case ID_USER:// id del usuario
                return elemnt.getData().replace("\"", "").replace("\'", "");
            case TEMA:// tema de la trivia
                return elemnt.getData().replace("\"", "").replace("\'", "");
            case NAME:/// nombre de la trivia
                return elemnt.getData().replace("\"", "").replace("\'", "");
            case USUARIO_CREACION: /// creador de la trivia
                return elemnt.getData().replace("\"", "").replace("\'", "");
            case INSTITUTION:/// institucion del usuario
                return elemnt.getData().replace("\"", "").replace("\'", "");
            default:
                return "";
        }
    }

    private void deletettemBecauseItDoesNotComplyTypeString(DataAnalyzer elemnt) {
        for (int i = 0; i < this.listFilterReturn.size(); i++) {
            String dataFilter = "";
            switch (elemnt.getType()) {
                case ID_USER:// id del usuario
                    dataFilter = elemnt.getData().replace("\"", "").replace("\'", "");
                    if (!dataFilter.equals(this.listFilterReturn.get(i).getUser())) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                    break;
                case TEMA:// tema de la trivia
                    break;
                case NAME:/// nombre de la trivia
                    dataFilter = elemnt.getData().replace("\"", "").replace("\'", "");
                    if (!dataFilter.equals(this.listFilterReturn.get(i).getQuizId())) {
                        this.listFilterReturn.remove(i);
                        i --;
                    }
                    break;
                case USUARIO_CREACION: /// creador de la trivia
                    break;
                case INSTITUTION:/// institucion del usuario
                    break;
                default:
                    break;
            }
        }
    }

    private void listTrivias() {
        if (analyzer.getListNameTrivias().size() > 0) {
            filterNameTrivias();
        } else {
            this.listFilterReturn = this.dataBaseQA.getListQuizAttempt();
        }
    }

    private void filterNameTrivias() {
        for (String string_element : this.analyzer.getListNameTrivias()) {
            string_element = string_element.replace(" ", "");
            for (QuizAttempt quizAttempt : this.dataBaseQA.getListQuizAttempt()) {
                if (quizAttempt.getQuizId().equals(string_element)) {
                    this.listFilterReturn.add(quizAttempt);
                }
            }
        }
    }

}
