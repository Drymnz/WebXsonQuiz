package LexicalAndSyntacticAnalyzer.jflexandcupManagerUser;

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
%class LexemaByMyParesUser
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymUser.EOF);
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
"<?"                    {print("<?" ); return new Symbol(MySymUser.OPEN_VERSION ,yyline,yycolumn,yytext());}
"?>"                    {print("?>" ); return new Symbol(MySymUser.CLOSE_VERSION ,yyline,yycolumn,yytext());}
">"                     {print(">" ); return new Symbol(MySymUser.CLOSE ,yyline,yycolumn,yytext());}
"<"                     {print("<" ); return new Symbol(MySymUser.OPEN ,yyline,yycolumn,yytext());}
"xson"                  {print("xson"); return new Symbol(MySymUser.XSON ,yyline,yycolumn,yytext());}
"version"               {print("version"); return new Symbol(MySymUser.VERSION ,yyline,yycolumn,yytext());}
/*SOLICITUD*/
"<!"                        {print("<!" ); return new Symbol(MySymUser.OPEN_REQUEST ,yyline,yycolumn,yytext());}
"!>"                        {print("!>" ); return new Symbol(MySymUser.CLOSE_REQUEST ,yyline,yycolumn,yytext());}
"realizar_solicitud"        {print("realizar_solicitud"); return new Symbol(MySymUser.MAKE_REQUEST ,yyline,yycolumn,yytext());}
"fin_solicitud_realizada"   {print("fin_solicitud_realizada"); return new Symbol(MySymUser.FINAL_REQUEST ,yyline,yycolumn,yytext());}
//TIPOS DE SOLICITUD
"\"USUARIO_NUEVO\""         {print("\"USUARIO_NUEVO\""); return new Symbol(MySymUser.NEW_USER ,yyline,yycolumn,yytext());}
"\"MODIFICAR_USUARIO\""     {print("\"MODIFICAR_USUARIO\""); return new Symbol(MySymUser.MODIFICAR_USUARIO  ,yyline,yycolumn,yytext());}
//DATA USSER
"\"USUARIO_ANTIGUO\""       {print("\"USUARIO_ANTIGUO\""); return new Symbol(MySymUser.USUARIO_ANTIGUO ,yyline,yycolumn,yytext());}
/*JSON*/
"{"                     {print("{"); return new Symbol(MySymUser.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymUser.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymUser.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymUser.BRACKETS_C ,yyline,yycolumn,yytext());}
//OBJ JSON
"\"DATOS_USUARIO\""     {print("\"DATOS_USUARIO\""); return new Symbol(MySymUser.USER_DATA ,yyline,yycolumn,yytext());}
//DATA USSER
"\"USUARIO\""       {print("\"USUARIO\""); return new Symbol(MySymUser.NAME_USER ,yyline,yycolumn,yytext());}
"\"PASSWORD\""      {print("\"PASSWORD\""); return new Symbol(MySymUser.PASS_USER ,yyline,yycolumn,yytext());}
"\"NOMBRE\""        {print("\"NOMBRE\""); return new Symbol(MySymUser.NAME_PERSONAL_USER ,yyline,yycolumn,yytext());}
"\“INSTITUCION\”"   {print("INSTITUCION"); return new Symbol(MySymUser.INSTITUCION ,yyline,yycolumn,yytext());}
"\FECHA_CREACION\”" {print("INSTITUCION"); return new Symbol(MySymUser.DATE ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
"+"                     {print("+"); return new Symbol(MySymUser.SUMAR,yyline,yycolumn, (yytext()));}
"-"                     {print("-"); return new Symbol(MySymUser.RESTAR,yyline,yycolumn, (yytext()));}
"/"                     {print("/"); return new Symbol(MySymUser.DIVIDIR,yyline,yycolumn, (yytext()));}
"*"                     {print("*"); return new Symbol(MySymUser.MULTIPLICAR,yyline,yycolumn, (yytext()));}
"="                     {print("="); return new Symbol(MySymUser.EQUAL,yyline,yycolumn, (yytext()));}
":"                     {print(":"); return new Symbol(MySymUser.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymUser.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
"("                     {print("("); return new Symbol(MySymUser.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymUser.PARENTESIS_C,yyline,yycolumn,yytext());}
{DATE}                  {print("DATE"); return new Symbol(MySymUser.STRING_DATE,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymUser.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymUser.STRING ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
