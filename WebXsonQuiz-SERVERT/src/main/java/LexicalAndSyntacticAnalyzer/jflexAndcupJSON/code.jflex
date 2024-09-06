/*primer seccion: codigo de usuario*/
package LexicalAndSyntacticAnalyzer.jflexAndcupJSON;

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
%class LexemaJSONUser
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymLoginDataBaseUser.EOF);
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

STRING = \"([^\"\\]|\\.)*\"

espacio =[\n|\r|\t|\f|\b|\s| ]+

%%

/*tercer seccion: reglase lexicas*/
/*INGNORAR*/
{espacio}               {/* print(); */}
/*JSON*/
"{"                     {print("{"); return new Symbol(MySymLoginDataBaseUser.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymLoginDataBaseUser.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymLoginDataBaseUser.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymLoginDataBaseUser.BRACKETS_C ,yyline,yycolumn,yytext());}
//OBJ JSON
"\"id\""                {print("\"id\""); return new Symbol(MySymLoginDataBaseUser.NAME_USER ,yyline,yycolumn,yytext());}
"\"password\""          {print("\"password\""); return new Symbol(MySymLoginDataBaseUser.PASS_USER ,yyline,yycolumn,yytext());}
"\"name\""              {print("\"name\""); return new Symbol(MySymLoginDataBaseUser.NAME_PERSONAL_USER ,yyline,yycolumn,yytext());}
"\"institution\""       {print("\"institution\""); return new Symbol(MySymLoginDataBaseUser.INSTITUCION ,yyline,yycolumn,yytext());}
"\"date\""              {print("\"date\""); return new Symbol(MySymLoginDataBaseUser.DATE ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
":"                     {print(":"); return new Symbol(MySymLoginDataBaseUser.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymLoginDataBaseUser.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
{DATE}                  {print("DATE"); return new Symbol(MySymLoginDataBaseUser.STRING_DATE,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymLoginDataBaseUser.STRING ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
