
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package LexicalAndSyntacticAnalyzer.jflexandcup;

import java_cup.runtime.*;
import java.util.ArrayList;
import LexicalAndSyntacticAnalyzer.analyzer.Token;
import reports.*;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.*;
import LexicalAndSyntacticAnalyzer.dataAnalyzer.RequestAnalyzer;
import LexicalAndSyntacticAnalyzer.ListRequests;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class MyParserLoginUser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return MySymLoginUser.class;
}

  /** Default constructor. */
  @Deprecated
  public MyParserLoginUser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public MyParserLoginUser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public MyParserLoginUser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\031\000\002\002\004\000\002\004\003\000\002\004" +
    "\004\000\002\003\004\000\002\003\003\000\002\002\010" +
    "\000\002\002\007\000\002\005\005\000\002\007\005\000" +
    "\002\006\005\000\002\006\003\000\002\006\002\000\002" +
    "\011\004\000\002\011\003\000\002\011\002\000\002\010" +
    "\010\000\002\014\005\000\002\014\003\000\002\013\004" +
    "\000\002\013\004\000\002\013\003\000\002\013\002\000" +
    "\002\012\004\000\002\012\003\000\002\012\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\061\000\010\003\004\005\006\021\011\001\002\000" +
    "\006\005\006\021\011\001\002\000\010\002\ufffd\005\ufffd" +
    "\021\ufffd\001\002\000\004\007\056\001\002\000\010\002" +
    "\000\005\006\021\011\001\002\000\004\002\054\001\002" +
    "\000\004\024\012\001\002\000\004\011\013\001\002\000" +
    "\004\026\014\001\002\000\004\012\022\001\002\000\004" +
    "\013\017\001\002\000\010\002\ufffb\005\ufffb\021\ufffb\001" +
    "\002\000\004\022\020\001\002\000\004\023\021\001\002" +
    "\000\010\002\ufff9\005\ufff9\021\ufff9\001\002\000\010\003" +
    "\023\013\ufff6\017\025\001\002\000\004\013\ufff7\001\002" +
    "\000\004\013\ufffa\001\002\000\010\003\030\020\ufff3\027" +
    "\027\001\002\000\004\020\053\001\002\000\004\011\032" +
    "\001\002\000\004\020\ufff4\001\002\000\004\020\ufff5\001" +
    "\002\000\004\015\033\001\002\000\004\017\034\001\002" +
    "\000\014\003\037\020\uffec\025\uffec\030\041\031\035\001" +
    "\002\000\012\003\043\011\042\020\uffe9\025\uffe9\001\002" +
    "\000\006\020\047\025\046\001\002\000\006\020\uffed\025" +
    "\uffed\001\002\000\006\020\ufff0\025\ufff0\001\002\000\012" +
    "\003\043\011\042\020\uffe9\025\uffe9\001\002\000\004\014" +
    "\045\001\002\000\006\020\uffea\025\uffea\001\002\000\006" +
    "\020\uffef\025\uffef\001\002\000\006\020\uffeb\025\uffeb\001" +
    "\002\000\014\003\037\020\uffec\025\uffec\030\041\031\035" +
    "\001\002\000\004\016\050\001\002\000\004\020\ufff2\001" +
    "\002\000\006\020\ufff1\025\ufff1\001\002\000\006\020\uffee" +
    "\025\uffee\001\002\000\004\013\ufff8\001\002\000\004\002" +
    "\001\001\002\000\010\002\ufffe\005\ufffe\021\ufffe\001\002" +
    "\000\004\010\057\001\002\000\004\004\060\001\002\000" +
    "\004\014\061\001\002\000\004\006\062\001\002\000\010" +
    "\002\ufffc\005\ufffc\021\ufffc\001\002\000\010\002\uffff\005" +
    "\006\021\011\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\061\000\010\002\004\003\006\004\007\001\001\000" +
    "\006\002\004\003\062\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\002\054\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\005\014\001\001" +
    "\000\002\001\001\000\004\007\015\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\006\023\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\011\025\001\001\000\002\001\001\000\004" +
    "\010\030\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\013\037\014\035" +
    "\001\001\000\004\012\051\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\012\043\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\013\050\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\002\054\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$MyParserLoginUser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$MyParserLoginUser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$MyParserLoginUser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




private ArrayList<ReportErrorInterpreter> listError = new ArrayList();
private ArrayList<DataAnalyzer> dataStack = new ArrayList();
private ArrayList<RequestAnalyzer> listRquest = new ArrayList();


	  public MyParserLoginUser(LexemaUser lexer) {
        super(lexer);
    }

    //erorr
    public void syntax_error(Symbol cur_token) {
        String nameTerminal = symbl_name_from_id(this.cur_token.sym);
        int numberTerminal = this.cur_token.sym;
        ///codigo para el objeto
        TypeIntreprete type = TypeIntreprete.SYNTACTIC;
        int line = cur_token.left+1;
        int columna = cur_token.right +1;
        String lexema = (this.cur_token.value!=null)? this.cur_token.value.toString() : "Token no existe";
        Token token =  new Token(line, columna, lexema);
        this.listError.add(new ReportErrorInterpreter(type, token, ReportingConstants.ERROR_SICTATICO));
    }

    //Returnar el listado de errores
    public ArrayList<ReportErrorInterpreter> getListError() {
        return this.listError;
    }

    //Returnar el listado de Rquest
    public ArrayList<RequestAnalyzer> getListRquest(){
      return this.listRquest;
    }

    /**
     * ***END CODE*******
     */


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$MyParserLoginUser$actions {
  private final MyParserLoginUser parser;

  /** Constructor */
  CUP$MyParserLoginUser$actions(MyParserLoginUser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$MyParserLoginUser$do_action_part00000000(
    int                        CUP$MyParserLoginUser$act_num,
    java_cup.runtime.lr_parser CUP$MyParserLoginUser$parser,
    java.util.Stack            CUP$MyParserLoginUser$stack,
    int                        CUP$MyParserLoginUser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$MyParserLoginUser$result;

      /* select the action based on the action number */
      switch (CUP$MyParserLoginUser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= inicio EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)).value;
		RESULT = start_val;
              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$MyParserLoginUser$parser.done_parsing();
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // inicio ::= bucle_inicio 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("inicio",2, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // inicio ::= error bucle_inicio 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("inicio",2, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // bucle_inicio ::= bucle_inicio inicio_xson 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("bucle_inicio",1, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // bucle_inicio ::= inicio_xson 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("bucle_inicio",1, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // inicio_xson ::= OPEN_VERSION XSON VERSION EQUAL STRING CLOSE_VERSION 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("inicio_xson",0, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-5)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // inicio_xson ::= OPEN_REQUEST MAKE_REQUEST COLNO solicitud cerrar_solicitud 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("inicio_xson",0, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-4)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // solicitud ::= LOGIN_USER CLOSE json 
            {
              Object RESULT =null;
		
    listRquest.add(new RequestAnalyzer(dataStack, ListRequests.LOGIN_USER));
    dataStack = new ArrayList();
  
              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("solicitud",3, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-2)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // cerrar_solicitud ::= OPEN FINAL_REQUEST CLOSE_REQUEST 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("cerrar_solicitud",5, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-2)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // json ::= KEYS_O obj_json KEYS_C 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("json",4, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-2)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // json ::= error 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("json",4, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // json ::= 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("json",4, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // obj_json ::= USER_DATA solicitud_usuario 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("obj_json",7, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // obj_json ::= error 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("obj_json",7, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // obj_json ::= 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("obj_json",7, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // solicitud_usuario ::= COLNO BRACKETS_O KEYS_O datas_user KEYS_C BRACKETS_C 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("solicitud_usuario",6, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-5)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // datas_user ::= datas_user COMA data_user 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("datas_user",10, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-2)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // datas_user ::= data_user 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("datas_user",10, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // data_user ::= NAME_USER data 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$MyParserLoginUser$stack.peek()).value;
		if(a!=null)dataStack.add(new DataAnalyzer(new Token(cur_token.left+1,cur_token.right +1,a.toString()),ListTypeData.USUARIO,a.toString()));
              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data_user",9, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // data_user ::= PASS_USER data 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$MyParserLoginUser$stack.peek()).value;
		if(a!=null)dataStack.add(new DataAnalyzer(new Token(cur_token.left+1,cur_token.right +1,a.toString()),ListTypeData.PASSWORD,a.toString()));
              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data_user",9, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // data_user ::= error 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data_user",9, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // data_user ::= 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data_user",9, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // data ::= COLNO STRING 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$MyParserLoginUser$stack.peek()).value;
		RESULT = a;
              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data",8, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.elementAt(CUP$MyParserLoginUser$top-1)), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // data ::= error 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data",8, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // data ::= 
            {
              Object RESULT =null;

              CUP$MyParserLoginUser$result = parser.getSymbolFactory().newSymbol("data",8, ((java_cup.runtime.Symbol)CUP$MyParserLoginUser$stack.peek()), RESULT);
            }
          return CUP$MyParserLoginUser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$MyParserLoginUser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$MyParserLoginUser$do_action(
    int                        CUP$MyParserLoginUser$act_num,
    java_cup.runtime.lr_parser CUP$MyParserLoginUser$parser,
    java.util.Stack            CUP$MyParserLoginUser$stack,
    int                        CUP$MyParserLoginUser$top)
    throws java.lang.Exception
    {
              return CUP$MyParserLoginUser$do_action_part00000000(
                               CUP$MyParserLoginUser$act_num,
                               CUP$MyParserLoginUser$parser,
                               CUP$MyParserLoginUser$stack,
                               CUP$MyParserLoginUser$top);
    }
}

}
