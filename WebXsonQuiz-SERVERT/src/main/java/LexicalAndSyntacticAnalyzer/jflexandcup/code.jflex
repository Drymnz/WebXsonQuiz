/*primer seccion: codigo de usuario*/
package LexicalAndSyntacticAnalyzer.jflexandcup;

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
%class LexemaUser
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymLoginUser.EOF);
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
WHOLE = {DIGIT}+
DECIMAL = {WHOLE}[.]{WHOLE}
REAL_NUMEBERS = {DECIMAL}|{WHOLE}

DATE = "\""{DIGIT}{DIGIT}{DIGIT}{DIGIT}"-"{DIGIT}{DIGIT}"-"{DIGIT}{DIGIT}"\""

STRING = \"([^\"\\]|\\.)*\"

espacio =[\n|\r|\t|\f|\b|\s| ]+

%%

/*tercer seccion: reglase lexicas*/
/*INGNORAR*/
{espacio}               {/* print(); */}
"<?"                    {print("<?" ); return new Symbol(MySymLoginUser.OPEN_VERSION ,yyline,yycolumn,yytext());}
"?>"                    {print("?>" ); return new Symbol(MySymLoginUser.CLOSE_VERSION ,yyline,yycolumn,yytext());}
">"                     {print(">" ); return new Symbol(MySymLoginUser.CLOSE ,yyline,yycolumn,yytext());}
"<"                     {print("<" ); return new Symbol(MySymLoginUser.OPEN ,yyline,yycolumn,yytext());}
"xson"                  {print("xson"); return new Symbol(MySymLoginUser.XSON ,yyline,yycolumn,yytext());}
"version"               {print("version"); return new Symbol(MySymLoginUser.VERSION ,yyline,yycolumn,yytext());}
/*SOLICITUD*/
"<!"                        {print("<!" ); return new Symbol(MySymLoginUser.OPEN_REQUEST ,yyline,yycolumn,yytext());}
"!>"                        {print("!>" ); return new Symbol(MySymLoginUser.CLOSE_REQUEST ,yyline,yycolumn,yytext());}
"realizar_solicitud"        {print("realizar_solicitud"); return new Symbol(MySymLoginUser.MAKE_REQUEST ,yyline,yycolumn,yytext());}
"fin_solicitud_realizada"   {print("fin_solicitud_realizada"); return new Symbol(MySymLoginUser.FINAL_REQUEST ,yyline,yycolumn,yytext());}
//TIPOS DE SOLICITUD
"\"USUARIO_NUEVO\""     {print("\"USUARIO_NUEVO\""); return new Symbol(MySymLoginUser.NEW_USER ,yyline,yycolumn,yytext());}
"\"LOGIN_USUARIO\""     {print("\"LOGIN_USUARIO\""); return new Symbol(MySymLoginUser.LOGIN_USER ,yyline,yycolumn,yytext());}
/*JSON*/
"{"                     {print("{"); return new Symbol(MySymLoginUser.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymLoginUser.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymLoginUser.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymLoginUser.BRACKETS_C ,yyline,yycolumn,yytext());}
//OBJ JSON
"\"DATOS_USUARIO\""     {print("\"DATOS_USUARIO\""); return new Symbol(MySymLoginUser.USER_DATA ,yyline,yycolumn,yytext());}
//DATA USSER
"\"USUARIO\""       {print("\"USUARIO\""); return new Symbol(MySymLoginUser.NAME_USER ,yyline,yycolumn,yytext());}
"\"PASSWORD\""      {print("\"PASSWORD\""); return new Symbol(MySymLoginUser.PASS_USER ,yyline,yycolumn,yytext());}
"\"NOMBRE\""        {print("\"NOMBRE\""); return new Symbol(MySymLoginUser.NAME_PERSONAL_USER ,yyline,yycolumn,yytext());}
"\“INSTITUCION\”"   {print("INSTITUCION"); return new Symbol(MySymLoginUser.INSTITUCION ,yyline,yycolumn,yytext());}
"\FECHA_CREACION\”" {print("INSTITUCION"); return new Symbol(MySymLoginUser.DATE ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
"+"                     {print("+"); return new Symbol(MySymLoginUser.SUMAR,yyline,yycolumn, (yytext()));}
"-"                     {print("-"); return new Symbol(MySymLoginUser.RESTAR,yyline,yycolumn, (yytext()));}
"/"                     {print("/"); return new Symbol(MySymLoginUser.DIVIDIR,yyline,yycolumn, (yytext()));}
"*"                     {print("*"); return new Symbol(MySymLoginUser.MULTIPLICAR,yyline,yycolumn, (yytext()));}
"="                     {print("="); return new Symbol(MySymLoginUser.EQUAL,yyline,yycolumn, (yytext()));}
":"                     {print(":"); return new Symbol(MySymLoginUser.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymLoginUser.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
"("                     {print("("); return new Symbol(MySymLoginUser.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymLoginUser.PARENTESIS_C,yyline,yycolumn,yytext());}
{DATE}                  {print("DATE"); return new Symbol(MySymLoginUser.STRING_DATE,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymLoginUser.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymLoginUser.STRING ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
