/*primer seccion: codigo de usuario*/
package LexicalAndSyntacticAnalyzer.jflexandcupDataBaseQuizAttempt;

import java_cup.runtime.Symbol;

import reports.ReportErrorInterpreter;
import reports.ReportingConstants;
import reports.TypeIntreprete;

import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.analyzer.Token;

%%
/*segunda seccion: configuracion*/

%line
%column
%public
%class LexemaQuizAttempt
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymDataBaseQuizAttempt.EOF);
%eofval}

%{
    /*START-CODE*/
    private ArrayList<ReportErrorInterpreter> listError = new ArrayList();
  
    private void print(String token) {
        //System.out.println(" < " + yytext() + " > <Linea\"" + (yyline + 1) + "\">" + "<Columna\"" + (yycolumn+1) + "\">");
    }

    private void addError(){
        print("error");
        TypeIntreprete type = TypeIntreprete.LEXICON;
        Token toke = new Token(yyline + 1, yycolumn + 1, yytext());
        this.listError.add(new ReportErrorInterpreter(type, toke, ReportingConstants.ERROR_LEXEMA));
    }
    public ArrayList<ReportErrorInterpreter> getListError() {
        return this.listError;
    }

    /*FINAL-CODE*/
%}

DIGIT = [0-9]
DATE = "\""{DIGIT}{DIGIT}{DIGIT}{DIGIT}"-"{DIGIT}{DIGIT}"-"{DIGIT}{DIGIT}"\""
WHOLE = {DIGIT}+
DECIMAL = {WHOLE}[.]{WHOLE}
REAL_NUMEBERS = {DECIMAL}|{WHOLE}

STRING = \"([^\"\\]|\\.)*\"

espacio =[\n|\r|\t|\f|\b|\s| ]+

%%

/*tercer seccion: reglase lexicas*/
/*INGNORAR*/
{espacio}               {/* print(); */}
/*JSON*/
"{"                     {print("{"); return new Symbol(MySymDataBaseQuizAttempt.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymDataBaseQuizAttempt.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymDataBaseQuizAttempt.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymDataBaseQuizAttempt.BRACKETS_C ,yyline,yycolumn,yytext());}
//OBJ JSON
"\"user\""                {print("\"id\""); return new Symbol(MySymDataBaseQuizAttempt.ID_USER ,yyline,yycolumn,yytext());}
"\"quizId\""              {print("\"id\""); return new Symbol(MySymDataBaseQuizAttempt.ID_TRIVIA ,yyline,yycolumn,yytext());}
"\"responseTime\""        {print("\"id\""); return new Symbol(MySymDataBaseQuizAttempt.RESPONSE_TIME ,yyline,yycolumn,yytext());}
"\"score\""               {print("\"id\""); return new Symbol(MySymDataBaseQuizAttempt.SCORE ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
":"                     {print(":"); return new Symbol(MySymDataBaseQuizAttempt.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymDataBaseQuizAttempt.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS ARIMETICOS*/
"+"                     {print("+"); return new Symbol(MySymDataBaseQuizAttempt.SUMAR,yyline,yycolumn, (yytext()));}
"-"                     {print("-"); return new Symbol(MySymDataBaseQuizAttempt.RESTAR,yyline,yycolumn, (yytext()));}
"/"                     {print("/"); return new Symbol(MySymDataBaseQuizAttempt.DIVIDIR,yyline,yycolumn, (yytext()));}
"*"                     {print("*"); return new Symbol(MySymDataBaseQuizAttempt.MULTIPLICAR,yyline,yycolumn, (yytext()));}
"="                     {print("="); return new Symbol(MySymDataBaseQuizAttempt.EQUAL,yyline,yycolumn, (yytext()));}
":"                     {print(":"); return new Symbol(MySymDataBaseQuizAttempt.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymDataBaseQuizAttempt.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
"("                     {print("("); return new Symbol(MySymDataBaseQuizAttempt.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymDataBaseQuizAttempt.PARENTESIS_C,yyline,yycolumn,yytext());}
{DATE}                  {print("DATE"); return new Symbol(MySymDataBaseQuizAttempt.STRING_DATE,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymDataBaseQuizAttempt.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymDataBaseQuizAttempt.STRING ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
