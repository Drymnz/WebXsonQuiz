package LexicalAndSyntacticAnalyzer.analyzer;

public class Token {
    private int line;
    private int columna;
    private String lexeme;

    public Token(int line, int columna, String lexeme) {
        this.line = line;
        this.columna = columna;
        this.lexeme = lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumna() {
        return columna;
    }

    public String getLexeme() {
        return lexeme;
    }
    
    @Override
    public String toString() {
        return "-line:"+this.line+"-columna:"+this.columna+"-lexeme:"+this.lexeme;
    }
}
