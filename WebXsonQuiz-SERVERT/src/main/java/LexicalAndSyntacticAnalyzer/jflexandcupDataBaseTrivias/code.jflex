/*primer seccion: codigo de usuario*/
package LexicalAndSyntacticAnalyzer.jflexandcupDataBaseTrivias;

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
%class LexemaDataBaseTrivias
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymLoginDataBaseTrivias.EOF);
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
"{"                     {print("{"); return new Symbol(MySymLoginDataBaseTrivias.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymLoginDataBaseTrivias.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymLoginDataBaseTrivias.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymLoginDataBaseTrivias.BRACKETS_C ,yyline,yycolumn,yytext());}
"("                     {print("("); return new Symbol(MySymLoginDataBaseTrivias.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymLoginDataBaseTrivias.PARENTESIS_C,yyline,yycolumn,yytext());}
//OBJ JSON
"\"id\""                {print("\"id\"");          return new Symbol(MySymLoginDataBaseTrivias.ID ,yyline,yycolumn,yytext());}
"\"name\""              {print("\"name\"");        return new Symbol(MySymLoginDataBaseTrivias.NAME ,yyline,yycolumn,yytext());}
"\"time\""              {print("\"time\"");        return new Symbol(MySymLoginDataBaseTrivias.TIME ,yyline,yycolumn,yytext());}
"\"theme\""             {print("\"theme\"");       return new Symbol(MySymLoginDataBaseTrivias.THEME ,yyline,yycolumn,yytext());}
"\"idUser\""            {print("\"idUser\"");      return new Symbol(MySymLoginDataBaseTrivias.ID_USER ,yyline,yycolumn,yytext());}
"\"date\""              {print("\"date\"");        return new Symbol(MySymLoginDataBaseTrivias.DATE ,yyline,yycolumn,yytext());}
"\"structure\""         {print("\"structure\"");   return new Symbol(MySymLoginDataBaseTrivias.STRUCTURE ,yyline,yycolumn,yytext());}
"\"idComponent\""       {print("\"idComponent\""); return new Symbol(MySymLoginDataBaseTrivias.ID_COMPONENT ,yyline,yycolumn,yytext());}
"\"type\""              {print("\"type\"");        return new Symbol(MySymLoginDataBaseTrivias.TYPE ,yyline,yycolumn,yytext());}
"\"index\""             {print("\"index\"");       return new Symbol(MySymLoginDataBaseTrivias.INDEX ,yyline,yycolumn,yytext());}
"\"text\""              {print("\"text\"");        return new Symbol(MySymLoginDataBaseTrivias.TEXT ,yyline,yycolumn,yytext());}
"\"options\""           {print("\"options\"");     return new Symbol(MySymLoginDataBaseTrivias.OPTIONS ,yyline,yycolumn,yytext());}
"\"row\""               {print("\"row\"");         return new Symbol(MySymLoginDataBaseTrivias.ROW ,yyline,yycolumn,yytext());}
"\"column\""            {print("\"column\"");      return new Symbol(MySymLoginDataBaseTrivias.COLUMN ,yyline,yycolumn,yytext());}
"\"result\""            {print("\"result\"");      return new Symbol(MySymLoginDataBaseTrivias.RESULT ,yyline,yycolumn,yytext());}
"\"idTrivia\""          {print("\"idTrivia\"");    return new Symbol(MySymLoginDataBaseTrivias.ID_TRIVIA ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
":"                     {print(":"); return new Symbol(MySymLoginDataBaseTrivias.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymLoginDataBaseTrivias.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
{DATE}                  {print("DATE"); return new Symbol(MySymLoginDataBaseTrivias.STRING_DATE,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymLoginDataBaseTrivias.STRING ,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymLoginDataBaseTrivias.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
([a-zA-Z0-9])+          {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
