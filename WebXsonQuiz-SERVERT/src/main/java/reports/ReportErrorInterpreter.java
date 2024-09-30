package reports;

import LexicalAndSyntacticAnalyzer.analyzer.Token;

public class ReportErrorInterpreter {
    private TypeIntreprete type;
    private Token toke;
    private String description;

  public ReportErrorInterpreter(TypeIntreprete type, Token toke, String description) {
      this.type = type;
      this.toke = toke;
      this.description = description;
  }

  public TypeIntreprete getType() {
      return type;
  }

  public Token getToke() {
      return toke;
  }

  public String getDescription() {
      return description;
  }
    
    @Override
    public String toString() {
        return "- Tipo de error:"+type.toString()+"\n - Token afectado: "+toke.toString()+"\n - Descripción: "+description.toString();
    }
}
