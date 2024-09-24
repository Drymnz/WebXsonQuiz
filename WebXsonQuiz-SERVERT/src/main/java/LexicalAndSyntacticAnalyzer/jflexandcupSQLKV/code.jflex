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

INSTITUCION = "\"""I""N""S""T""I""T""U""C""I""O""N""\""
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
"<!--" ~"-->"              {/*COMENTARIO*/}
{espacio}               {/* print(); */}
"<?"                    {print("<?" ); return new Symbol(MySymSQLKV.OPEN_VERSION ,yyline,yycolumn,yytext());}
"?>"                    {print("?>" ); return new Symbol(MySymSQLKV.CLOSE_VERSION ,yyline,yycolumn,yytext());}
">"                     {print(">" ); return new Symbol(MySymSQLKV.CLOSE ,yyline,yycolumn,yytext());}
"<"                     {print("<" ); return new Symbol(MySymSQLKV.OPEN ,yyline,yycolumn,yytext());}
"xson"                  {print("xson"); return new Symbol(MySymSQLKV.XSON ,yyline,yycolumn,yytext());}
"xml"                   {print("xml"); return new Symbol(MySymSQLKV.XML ,yyline,yycolumn,yytext());}
"version"               {print("version"); return new Symbol(MySymSQLKV.VERSION ,yyline,yycolumn,yytext());}
/*SOLICITUD*/
"<!"                        {print("<!" ); return new Symbol(MySymSQLKV.OPEN_REQUEST ,yyline,yycolumn,yytext());}
"!>"                        {print("!>" ); return new Symbol(MySymSQLKV.CLOSE_REQUEST ,yyline,yycolumn,yytext());}
"realizar_solicitudes"      {print("realizar_solicitudes"); return new Symbol(MySymSQLKV.MAKE_REQUESTS ,yyline,yycolumn,yytext());}
"fin_solicitudes_realizada" {print("fin_solicitudes_realizada"); return new Symbol(MySymSQLKV.FINAL_REQUESTS ,yyline,yycolumn,yytext());}
"realizar_solicitud"        {print("realizar_solicitud"); return new Symbol(MySymSQLKV.MAKE_REQUEST ,yyline,yycolumn,yytext());}
"fin_solicitud_realizada"   {print("fin_solicitud_realizada"); return new Symbol(MySymSQLKV.FINAL_REQUEST ,yyline,yycolumn,yytext());}
/*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*//*TRIVIAS*/
/*TRIVIAS*/
//TIPOS DE SOLICITUD 
"\"PARAMETROS_TRIVIA\""     {print("\"PARAMETROS_TRIVIA\""); return new Symbol(MySymSQLKV.PARAMETROS_TRIVIA ,yyline,yycolumn,yytext());}
"\"PARAMETROS_COMPONENTE\"" {print("\"PARAMETROS_COMPONENTE\""); return new Symbol(MySymSQLKV.PARAMETROS_COMPONENTE ,yyline,yycolumn,yytext());}
"\"NUEVA_TRIVIA\""          {print("\"NUEVA_TRIVIA\""); return new Symbol(MySymSQLKV.NEW_TRIVIA ,yyline,yycolumn,yytext());}
"\"AGREGAR_COMPONENTE\""    {print("\"AGREGAR_COMPONENTE\""); return new Symbol(MySymSQLKV.AGREGAR_COMPONENTE ,yyline,yycolumn,yytext());}
"\"MODIFICAR_TRIVIA\""      {print("\"MODIFICAR_TRIVIA\""); return new Symbol(MySymSQLKV.MODIFICAR_TRIVIA ,yyline,yycolumn,yytext());}
"\"MODIFICAR_COMPONENTE\""  {print("\"MODIFICAR_COMPONENTE\""); return new Symbol(MySymSQLKV.MODIFICAR_COMPONENTE ,yyline,yycolumn,yytext());}
"\"ELIMINAR_COMPONENTE\""   {print("\"ELIMINAR_COMPONENTE\""); return new Symbol(MySymSQLKV.ELIMINAR_COMPONENTE ,yyline,yycolumn,yytext());}
"\"ELIMINAR_TRIVIA\""       {print("\"ELIMINAR_TRIVIA\""); return new Symbol(MySymSQLKV.ELIMINAR_TRIVIA ,yyline,yycolumn,yytext());}
//DATOS DE TRIVIAS  
"\"ID\""                    {print("\"ID\""); return new Symbol(MySymSQLKV.ID ,yyline,yycolumn,yytext());}
"\"INDICE\""                {print("\"INDICE\""); return new Symbol(MySymSQLKV.INDICE ,yyline,yycolumn,yytext());}
"\"ID_TRIVIA\""             {print("\"ID_TRIVIA\""); return new Symbol(MySymSQLKV.ID_TRIVIA ,yyline,yycolumn,yytext());}
"\"TRIVIA\""                {print("\"TRIVIA\""); return new Symbol(MySymSQLKV.TRIVIA ,yyline,yycolumn,yytext());}
"\"CLASE\""                 {print("\"CLASE\""); return new Symbol(MySymSQLKV.CLASE ,yyline,yycolumn,yytext());}
"\"TEXTO_VISIBLE\""         {print("\"TEXTO_VISIBLE\""); return new Symbol(MySymSQLKV.TEXTO_VISIBLE ,yyline,yycolumn,yytext());}
"\"OPCIONES\""              {print("\"OPCIONES\""); return new Symbol(MySymSQLKV.OPCIONES ,yyline,yycolumn,yytext());}
"\"FILAS\""                 {print("\"FILAS\""); return new Symbol(MySymSQLKV.FILAS ,yyline,yycolumn,yytext());}
"\"COLUMNAS\""              {print("\"COLUMNAS\""); return new Symbol(MySymSQLKV.COLUMNAS ,yyline,yycolumn,yytext());}
"\"RESPUESTA\""             {print("\"RESPUESTA\""); return new Symbol(MySymSQLKV.RESPUESTA ,yyline,yycolumn,yytext());}
"\"TEMA\""                  {print("\"TEMA\""); return new Symbol(MySymSQLKV.TEMA ,yyline,yycolumn,yytext());}
"\"USUARIO_CREACION\""      {print("\"USUARIO_CREACION\""); return new Symbol(MySymSQLKV.USUARIO_CREACION ,yyline,yycolumn,yytext());}
"\"TIEMPO_PREGUNTA\""       {print("\"TIEMPO_PREGUNTA\""); return new Symbol(MySymSQLKV.TIEMPO_PREGUNTA ,yyline,yycolumn,yytext());}
//TIPOS DE SOLICITUD USUARIOAS//TIPOS DE SOLICITUD USUARIOAS//TIPOS DE SOLICITUD USUARIOAS//TIPOS DE SOLICITUD USUARIOAS//TIPOS DE SOLICITUD USUARIOAS
//TIPOS DE SOLICITUD USUARIOAS
"\"USUARIO_NUEVO\""         {print("\"USUARIO_NUEVO\""); return new Symbol(MySymSQLKV.NEW_USER ,yyline,yycolumn,yytext());}
"\"MODIFICAR_USUARIO\""     {print("\"MODIFICAR_USUARIO\""); return new Symbol(MySymSQLKV.MODIFICAR_USUARIO  ,yyline,yycolumn,yytext());}
"\"ELIMINAR_USUARIO\""      {print("\"ELIMINAR_USUARIO\""); return new Symbol(MySymSQLKV.ELIMINAR_USUARIO  ,yyline,yycolumn,yytext());}
//DATA USSER
"\"USUARIO_ANTIGUO\""       {print("\"USUARIO_ANTIGUO\""); return new Symbol(MySymSQLKV.USUARIO_ANTIGUO ,yyline,yycolumn,yytext());}
"\"NUEVO_PASSWORD\""        {print("\"NUEVO_PASSWORD\""); return new Symbol(MySymSQLKV.NUEVO_PASSWORD ,yyline,yycolumn,yytext());}
/*JSON*/
"{"                     {print("{"); return new Symbol(MySymSQLKV.KEYS_O ,yyline,yycolumn,yytext());}
"}"                     {print("}"); return new Symbol(MySymSQLKV.KEYS_C ,yyline,yycolumn,yytext());}
"["                     {print("["); return new Symbol(MySymSQLKV.BRACKETS_O ,yyline,yycolumn,yytext());}
"]"                     {print("]"); return new Symbol(MySymSQLKV.BRACKETS_C ,yyline,yycolumn,yytext());}
//OBJ JSON
"\"DATOS_USUARIO\""     {print("\"DATOS_USUARIO\""); return new Symbol(MySymSQLKV.USER_DATA ,yyline,yycolumn,yytext());}
//DATA USSER
"\"USUARIO\""       {print("\"USUARIO\""); return new Symbol(MySymSQLKV.NAME_USER ,yyline,yycolumn,yytext());}
"\"PASSWORD\""      {print("\"PASSWORD\""); return new Symbol(MySymSQLKV.PASS_USER ,yyline,yycolumn,yytext());}
"\"NOMBRE\""        {print("\"NOMBRE\""); return new Symbol(MySymSQLKV.NAME_PERSONAL_USER ,yyline,yycolumn,yytext());}
{INSTITUCION}       {print("INSTITUCION"); return new Symbol(MySymSQLKV.INSTITUCION ,yyline,yycolumn,yytext());}
"\FECHA_CREACION\”" {print("FECHA_CREACION"); return new Symbol(MySymSQLKV.DATE ,yyline,yycolumn,yytext());}
/*SIMBOLOS ARIMETICOS*/
"+"                     {print("+"); return new Symbol(MySymSQLKV.SUMAR,yyline,yycolumn, (yytext()));}
"-"                     {print("-"); return new Symbol(MySymSQLKV.RESTAR,yyline,yycolumn, (yytext()));}
"/"                     {print("/"); return new Symbol(MySymSQLKV.DIVIDIR,yyline,yycolumn, (yytext()));}
"*"                     {print("*"); return new Symbol(MySymSQLKV.MULTIPLICAR,yyline,yycolumn, (yytext()));}
"="                     {print("="); return new Symbol(MySymSQLKV.EQUAL,yyline,yycolumn, (yytext()));}
":"                     {print(":"); return new Symbol(MySymSQLKV.COLNO,yyline,yycolumn, (yytext()));}
","                     {print(","); return new Symbol(MySymSQLKV.COMA,yyline,yycolumn, (yytext()));}
/*SIMBOLOS DE AGRUPACION*/
"("                     {print("("); return new Symbol(MySymSQLKV.PARENTESIS_A,yyline,yycolumn,yytext());}
")"                     {print(")"); return new Symbol(MySymSQLKV.PARENTESIS_C,yyline,yycolumn,yytext());}
{DATE}                  {print("DATE"); return new Symbol(MySymSQLKV.STRING_DATE,yyline,yycolumn,yytext());}
{REAL_NUMEBERS}         {print("REAL_NUMEBERS"); return new Symbol(MySymSQLKV.REAL_NUMEBERS ,yyline,yycolumn,yytext());}
{STRING}                {print("STRING"); return new Symbol(MySymSQLKV.STRING ,yyline,yycolumn,yytext());}
/*ERROR LEXICO*/
.                       {
                        //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
                        }
