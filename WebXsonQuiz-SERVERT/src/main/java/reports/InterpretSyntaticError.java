package reports;

import LexicalAndSyntacticAnalyzer.jflexAndcupJSON.MyParserLoginDataBaseUser;
import LexicalAndSyntacticAnalyzer.jflexAndcupJSON.MySymLoginDataBaseUser;
import LexicalAndSyntacticAnalyzer.jflexandcup.MyParserLoginUser;
import LexicalAndSyntacticAnalyzer.jflexandcup.MySymLoginUser;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseQuizAttempt.MyParserDataQuizAttempt;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseQuizAttempt.MySymDataBaseQuizAttempt;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseTrivias.MyParserDataBaseTrivias;
import LexicalAndSyntacticAnalyzer.jflexandcupDataBaseTrivias.MySymLoginDataBaseTrivias;
import LexicalAndSyntacticAnalyzer.jflexandcupManagerUser.MyParseUser;
import LexicalAndSyntacticAnalyzer.jflexandcupManagerUser.MySymUser;
import LexicalAndSyntacticAnalyzer.jflexandcupSQLKV.MyParseSQLKV;
import LexicalAndSyntacticAnalyzer.jflexandcupSQLKV.MySymSQLKV;
import java.util.Stack;
import java_cup.runtime.Symbol;
import java_cup.runtime.lr_parser;

public class InterpretSyntaticError {

    private final String ERROR_IF_THERE_IS_NO_TOKEN_TO_PARSE = "No hay nada que analizar en token";
    private final String THE_NEXT_TOKEN_WAS_NOT_EXPECTED_CHECK_THE_DOCUMENTATION = "Token inesperado. Revisa la gramática y la documentación.";
    private final String INITIAL_SUGGESTION = "\n- Sugerencia: El error ocurrió después del token. => \"";
    private final String FINAL_SUGGESTION = " \" Confirma si el token es correcto y está bien ubicado.";

    private Stack stack;// la pila

    public InterpretSyntaticError(Stack stack) {
        this.stack = stack;
    }

    public String descriptionParser(lr_parser context) {
        if (this.stack != null && this.stack.get(this.stack.size() - 1) != null && this.stack.get(this.stack.size() - 1) instanceof Symbol) {
            Symbol analizer = (Symbol) this.stack.get(this.stack.size() - 1);
            if (analizer.sym == 0) {
                return this.ERROR_IF_THERE_IS_NO_TOKEN_TO_PARSE;
            } else {
                if (analizer.sym == 1) {
                    int index = (this.stack.size() - 2 > 0) ? this.stack.size() - 2 : this.stack.size() - 1;
                    analizer = (Symbol) this.stack.get(index);
                }
                return accordingToItsSymParserLogin(analizer, context);
            }
        }
        return "";
    }

    private String accordingToItsSymParserLogin(Symbol analizer, lr_parser context) {
        String lastToken = lastToken(analizer, context);
        if (lastToken.equals("EOF") || lastToken.equals("error")) {
            return this.ERROR_IF_THERE_IS_NO_TOKEN_TO_PARSE;
        } else {
            return this.THE_NEXT_TOKEN_WAS_NOT_EXPECTED_CHECK_THE_DOCUMENTATION + this.INITIAL_SUGGESTION + lastToken + this.FINAL_SUGGESTION;
        }
    }
    
    private String lastToken(Symbol analizer, lr_parser context){
        if (context instanceof MyParserLoginUser) {
            return MySymLoginUser.terminalNames[analizer.sym];
        }
        if (context instanceof MyParserDataQuizAttempt) {
            return MySymDataBaseQuizAttempt.terminalNames[analizer.sym];
        }
        if (context instanceof MyParserDataBaseTrivias) {
            return MySymLoginDataBaseTrivias.terminalNames[analizer.sym];
        }
        if (context instanceof MyParserLoginDataBaseUser) {
            return MySymLoginDataBaseUser.terminalNames[analizer.sym];
        }
        if (context instanceof MyParseUser) {
            return MySymUser.terminalNames[analizer.sym];
        }
        if (context instanceof MyParseSQLKV) {
            return MySymSQLKV.terminalNames[analizer.sym];
        }else 
        {
            return "EOF";
        }
    }
}
