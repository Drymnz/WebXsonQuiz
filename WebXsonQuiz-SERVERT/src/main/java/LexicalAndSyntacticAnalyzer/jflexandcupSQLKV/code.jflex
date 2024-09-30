package LexicalAndSyntacticAnalyzer.jflexandcupSQLKV;

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
%class LexemaMySQLKV
%unicode
%cup
%eofval{
  return new java_cup.runtime.Symbol(MySymSQLKV.EOF);
%eofval}

%{
    /*START-CODE*/
    private ArrayList<ReportErrorInterpreter> listError = new ArrayList();
  
    private void print(String token) {
            //System.out.println(token+" < " + yytext() + " > <Linea\"" + (yyline + 1) + "\">" + "<Columna\"" + (yycolumn+1) + "\">");
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

INSTITUCION = "\"""I""N""S""T""I""T""U""C""I""O""N""\""
DIGIT = [0-9]
WHOLE = {DIGIT}+
DECIMAL = {WHOLE}[.]{WHOLE}
REAL_NUMEBERS = {DECIMAL}|{WHOLE}

///FIRST, SECOND, THIRD, FOURTH
STRING_FIRS = \"([^\"\\]|\\.)*\"
STRING_THIRD = '([^']|\\.)*'
STRING_FOURTH = ’([^’]|\\.)*’ 
STRING = ([\"]|[\’])([^\"]|[^\’]|[^\\]|\\.)*([\"]|[\’])

espacio =[\n|\r|\t|\f|\b|\s|" "| ]+

ID_TRIVIA = [_\-\$][_\-\$0-9a-zA-Z]*[\s]*

%%

/*tercer seccion: reglase lexicas*/

//ID DE LA TRIVIA
{espacio}               {/* print(); */}
">"                     {print(">" ); return new Symbol(MySymSQLKV.GREATER_THAN ,yyline,yycolumn,yytext());}
"<"                     {print("<" ); return new Symbol(MySymSQLKV.LESS_THAN ,yyline,yycolumn,yytext());}
">="                     {print(">=" ); return new Symbol(MySymSQLKV.GREATER_THAN_OR_EQUAL ,yyline,yycolumn,yytext());}
"<="                     {print("<=" ); return new Symbol(MySymSQLKV.LESS_THAN_OR_EQUAL ,yyline,yycolumn,yytext());}
/*PALABRAS CLAVES*/
"SELECCIONAR"           {print("SELECCIONAR" ); return new Symbol(MySymSQLKV.SELECCIONAR ,yyline,yycolumn,yytext());}
"REPORTE"               {print("REPORTE" ); return new Symbol(MySymSQLKV.REPORTE ,yyline,yycolumn,yytext());}
"FILTRAR"               {print("FILTRAR" ); return new Symbol(MySymSQLKV.FILTRAR ,yyline,yycolumn,yytext());}
"POR"                   {print("POR" ); return new Symbol(MySymSQLKV.POR ,yyline,yycolumn,yytext());}
//ID DEL USUARIO
"USUARIO"               {print("USUARIO" ); return new Symbol(MySymSQLKV.USUARIO ,yyline,yycolumn,yytext());}
"TEMA"                      {print("\"TEMA\""); return new Symbol(MySymSQLKV.TEMA ,yyline,yycolumn,yytext());}
"NOMBRE"                    {print("\"NOMBRE\""); return new Symbol(MySymSQLKV.NOMBRE ,yyline,yycolumn,yytext());}
"USUARIO_CREACION"          {print("\"USUARIO_CREACION\""); return new Symbol(MySymSQLKV.USUARIO_CREACION ,yyline,yycolumn,yytext());}
//DATOS DE TRIVIAS NUMERICO
//TIEMPO DE RESPUESTA DEL USUARIO
"TIEMPO"                    {print("\"TIEMPO\""); return new Symbol(MySymSQLKV.TIEMPO ,yyline,yycolumn,yytext());}
"SCORE"                     {print("\"SCORE\""); return new Symbol(MySymSQLKV.SCORE ,yyline,yycolumn,yytext());}
"TIEMPO_PREGUNTA"           {print("\"TIEMPO_PREGUNTA\""); return new Symbol(MySymSQLKV.TIEMPO_PREGUNTA ,yyline,yycolumn,yytext());}
//DATA USSER
{INSTITUCION}       {print("INSTITUCION"); return new Symbol(MySymSQLKV.INSTITUCION ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
"AND"                    {print("AND"); return new Symbol(MySymSQLKV.AND,yyline,yycolumn, (yytext()));}
"OR"                     {print("OR"); return new Symbol(MySymSQLKV.OR,yyline,yycolumn, (yytext()));}
"NOT"                    {print("NOT"); return new Symbol(MySymSQLKV.NOT,yyline,yycolumn, (yytext()));}
"+"                     {print("+"); return new Symbol(MySymSQLKV.SUMAR,yyline,yycolumn, (yytext()));}
"-"                     {print("-"); return new Symbol(MySymSQLKV.RESTAR,yyline,yycolumn, (yytext()));}
"/"                     {print("/"); return new Symbol(MySymSQLKV.DIVIDIR,yyline,yycolumn, (yytext()));}
"*"                     {print("*"); return new Symbol(MySymSQLKV.MULTIPLICAR,yyline,yycolumn, (yytext()));}
"="                     {print("="); return new Symbol(MySymSQLKV.EQUAL,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymSQLKV.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
"("                     {print("("); return new Symbol(MySymSQLKV.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymSQLKV.PARENTESIS_C,yyline,yycolumn,yytext());}
{ID_TRIVIA}             {print("ID_TRIVIA"); return new Symbol(MySymSQLKV.ID_TRIVIA ,yyline,yycolumn,yytext());}
{STRING_THIRD}          {print("STRING_THIRD"); return new Symbol(MySymSQLKV.STRING ,yyline,yycolumn,yytext());}
{STRING_FIRS}           {print("STRING_FIRS"); return new Symbol(MySymSQLKV.STRING ,yyline,yycolumn,yytext());}
{STRING_FOURTH}         {print("STRING_FOURTH"); return new Symbol(MySymSQLKV.STRING ,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymSQLKV.STRING ,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymSQLKV.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
([a-zA-Z0-9])+          {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }                        
