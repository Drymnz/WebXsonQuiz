/*primer seccion: codigo de usuario*/
package jflexandcup;

import java_cup.runtime.Symbol;

import reports.ReportErrorInterpreter;
import reports.ReportingConstants;
import reports.TypeIntreprete;

import java.util.ArrayList;

import analyzer.Token;

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
  
    private void print() {
        //System.out.println("\n<" + yytext() + "><Linea\"" + (yyline + 1) + "\">" + "<Columna\"" + (yycolumn+1) + "\">");
    }

    private void addError(){
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
REAL_NUMEBERS = {DECIMAL} | {WHOLE}

espacio =[\n|\r|\t|\f|\b|\s| ]+

%%

/*tercer seccion: reglase lexicas*/
/*INGNORAR*/
{espacio}           {print();}
/*SIMBOLOS ARIMETICOS*/
/* "+"                 {
                   print(); return new Symbol(MySymLoginUser.SUMAR,yyline,yycolumn, (yytext()));
                    } */
{REAL_NUMEBERS}     {
                   print(); return new Symbol(MySymLoginUser.REAL_NUMEBERS ,yyline,yycolumn,yytext());                    
                    }
/*ERROR LEXICO*/
.                   {
                    //MANEJAR EL ERROR LEXICO
                        print();
                        addError();
                    }
