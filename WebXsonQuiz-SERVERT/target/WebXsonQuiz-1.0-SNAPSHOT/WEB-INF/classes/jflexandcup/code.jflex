/*primer seccion: codigo de usuario*/
package com.example.mycalculator.JflexYCup.Code;

import java_cup.runtime.Symbol;


import java.util.ArrayList;

%%
/*segunda seccion: configuracion*/
%class Lexema
%line
%column
%cup
%public
%unicode

%{
/*CODE*/
    private ArrayList<ReportErrorInterpreter> listError = new ArrayList();
    public static final String ERROR_LEXEMA = "El caracter no existe dentro del albesadario";

  
    private void print() {
        //System.out.println("\n<" + yytext() + "><Linea\"" + (yyline + 1) + "\">" + "<Columna\"" + (yycolumn+1) + "\">");
    }
    private void addError(){
        TypeIntreprete type = TypeIntreprete.LEXICON;
        Token toke = new Token(yyline + 1, yycolumn + 1, yytext());
        this.listError.add(new ReportErrorInterpreter(type, toke, ERROR_LEXEMA));
    }
    public ArrayList<ReportErrorInterpreter> getListError() {
        return this.listError;
    }
/*CODE*/
%}

DIGIT = [0-9]
WHOLE = {DIGIT}+
DECIMAL = {WHOLE}[.]{WHOLE}
REAL_NUMEBERS = {DECIMAL} | {WHOLE}

espacio =[\n|\r|\t|\f|\b|\s| ]+

%%

/*tercer seccion: reglase lexicas*/
/*INGNORAR*/
{espacio}           {print();}
/*SIMBOLOS ARIMETICOS*/
"+"                 {
                   print(); return new Symbol(sym.SUMAR,yyline,yycolumn, (yytext()));
                    }
"-"                 {
                   print(); return new Symbol(sym.RESTAR,yyline,yycolumn, (yytext()));
                    }
"/"                 {
                   print(); return new Symbol(sym.DIVIDIR,yyline,yycolumn, (yytext()));
                    }
"*"                 {
                   print(); return new Symbol(sym.MULTIPLICAR,yyline,yycolumn, (yytext()));
                    }
/*SIMBOLOS DE AGRUPACION*/
"("                 {
                   print(); return new Symbol(sym.PARENTESIS_A,yyline,yycolumn,yytext());
                    }
")"                 {
                   print(); return new Symbol(sym.PARENTESIS_C,yyline,yycolumn,yytext());
                    }
{REAL_NUMEBERS}     {
                   print(); return new Symbol( sym.REAL_NUMEBERS ,yyline,yycolumn,yytext());                    
                    }

/*ERROR LEXICO*/
.                   {
                    //MANEJAR EL ERROR LEXICO
                        print();
                        addError();
                    }
