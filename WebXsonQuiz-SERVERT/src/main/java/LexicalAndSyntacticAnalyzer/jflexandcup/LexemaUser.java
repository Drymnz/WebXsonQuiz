// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: code.jflex

/*primer seccion: codigo de usuario*/
package LexicalAndSyntacticAnalyzer.jflexandcup;

import java_cup.runtime.Symbol;

import reports.ReportErrorInterpreter;
import reports.ReportingConstants;
import reports.TypeIntreprete;

import java.util.ArrayList;

import LexicalAndSyntacticAnalyzer.analyzer.Token;


@SuppressWarnings("fallthrough")
public class LexemaUser implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\25\u0100\1\u0200\11\u0100\1\u0300\17\u0100\1\u0400\247\u0100"+
    "\10\u0500\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\10\0\2\1\4\2\22\0\1\1\1\3\1\4\5\0"+
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\12\15\1\16\1\0\1\17\1\20\1\21\1\22\1\0"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\2\0\1\34\1\35\1\36\1\37\1\40\1\0"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\3\0\1\47"+
    "\1\50\1\51\1\0\1\52\1\0\1\53\1\0\1\54"+
    "\1\55\1\56\1\57\2\0\1\60\2\0\1\61\1\0"+
    "\1\62\1\63\2\0\1\64\1\65\1\66\1\67\1\70"+
    "\1\0\1\71\1\0\1\72\1\73\1\1\1\74\7\0"+
    "\1\2\32\0\1\1\u01df\0\1\1\177\0\13\1\21\0"+
    "\1\75\1\76\12\0\2\2\5\0\1\1\57\0\1\1"+
    "\240\0\1\1\377\0\u0100\77";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1536];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\1\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\2\1\1\17\1\20\4\1\1\21\1\22\1\1\1\23"+
    "\1\0\1\24\10\0\1\25\1\26\1\27\14\0\1\12"+
    "\20\0\1\30\40\0\1\31\4\0\1\32\12\0\1\33"+
    "\10\0\1\34\15\0\1\35\15\0\1\36\6\0\1\37"+
    "\1\40\1\41\1\42\7\0\1\43\4\0\1\44";

  private static int [] zzUnpackAction() {
    int [] result = new int[181];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\100\0\200\0\300\0\u0100\0\100\0\100\0\100"+
    "\0\100\0\100\0\100\0\100\0\u0140\0\100\0\u0180\0\100"+
    "\0\100\0\u01c0\0\u0200\0\100\0\100\0\u0240\0\u0280\0\u02c0"+
    "\0\u0300\0\100\0\100\0\u0340\0\100\0\u0380\0\100\0\u03c0"+
    "\0\u0400\0\u0440\0\u0480\0\u04c0\0\u0500\0\u0540\0\u0580\0\100"+
    "\0\100\0\100\0\u05c0\0\u0600\0\u0640\0\u0680\0\u06c0\0\u0700"+
    "\0\u0740\0\u0780\0\u07c0\0\u0800\0\u0840\0\u0880\0\u0580\0\u08c0"+
    "\0\u0900\0\u0940\0\u0980\0\u09c0\0\u0a00\0\u0a40\0\u0a80\0\u0ac0"+
    "\0\u0b00\0\u0b40\0\u0b80\0\u0bc0\0\u0c00\0\u0c40\0\u0c80\0\100"+
    "\0\u0cc0\0\u0d00\0\u0d40\0\u0d80\0\u0dc0\0\u0e00\0\u0e40\0\u0e80"+
    "\0\u0ec0\0\u0f00\0\u0f40\0\u0f80\0\u0fc0\0\u1000\0\u1040\0\u1080"+
    "\0\u10c0\0\u1100\0\u1140\0\u1180\0\u11c0\0\u1200\0\u1240\0\u1280"+
    "\0\u12c0\0\u1300\0\u1340\0\u1380\0\u13c0\0\u1400\0\u1440\0\u1480"+
    "\0\100\0\u14c0\0\u1500\0\u1540\0\u1580\0\100\0\u15c0\0\u1600"+
    "\0\u1640\0\u1680\0\u16c0\0\u1700\0\u1740\0\u1780\0\u17c0\0\u1800"+
    "\0\100\0\u1840\0\u1880\0\u18c0\0\u1900\0\u1940\0\u1980\0\u19c0"+
    "\0\u1a00\0\100\0\u1a40\0\u1a80\0\u1ac0\0\u1b00\0\u1b40\0\u1b80"+
    "\0\u1bc0\0\u1c00\0\u1c40\0\u1c80\0\u1cc0\0\u1d00\0\u1d40\0\100"+
    "\0\u1d80\0\u1dc0\0\u1e00\0\u1e40\0\u1e80\0\u1ec0\0\u1f00\0\u1f40"+
    "\0\u1f80\0\u1fc0\0\u2000\0\u2040\0\u2080\0\100\0\u20c0\0\u2100"+
    "\0\u2140\0\u2180\0\u21c0\0\u2200\0\100\0\100\0\100\0\100"+
    "\0\u2240\0\u2280\0\u22c0\0\u2300\0\u2340\0\u2380\0\u23c0\0\100"+
    "\0\u2400\0\u2440\0\u2480\0\u24c0\0\100";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[181];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\2\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\5\2\1\23\16\2\1\24\1\2\1\25"+
    "\5\2\1\26\4\2\1\27\3\2\1\30\1\31\1\2"+
    "\1\32\1\33\1\34\1\2\102\0\2\3\116\0\1\35"+
    "\56\0\4\36\1\37\10\36\1\40\10\36\1\41\5\36"+
    "\1\42\1\36\1\43\1\36\1\44\3\36\1\45\3\36"+
    "\1\46\27\36\13\0\1\47\1\0\1\15\65\0\1\50"+
    "\16\0\1\51\76\0\1\52\105\0\1\53\130\0\1\54"+
    "\75\0\1\55\77\0\1\56\106\0\1\57\45\0\1\60"+
    "\44\0\4\36\1\37\43\36\1\46\33\36\1\37\10\36"+
    "\1\61\32\36\1\46\33\36\1\37\16\36\1\62\24\36"+
    "\1\46\33\36\1\37\32\36\1\63\10\36\1\46\33\36"+
    "\1\37\32\36\1\64\10\36\1\46\33\36\1\37\16\36"+
    "\1\65\24\36\1\46\33\36\1\37\35\36\1\66\5\36"+
    "\1\46\31\36\1\0\74\36\16\0\1\67\107\0\1\70"+
    "\134\0\1\71\70\0\1\72\110\0\1\73\76\0\1\74"+
    "\52\0\1\75\41\0\4\36\1\37\10\36\1\76\32\36"+
    "\1\46\33\36\1\37\36\36\1\77\4\36\1\46\33\36"+
    "\1\37\24\36\1\100\16\36\1\46\33\36\1\37\30\36"+
    "\1\101\12\36\1\46\33\36\1\37\35\36\1\102\5\36"+
    "\1\46\33\36\1\37\37\36\1\103\3\36\1\46\27\36"+
    "\32\0\1\104\117\0\1\105\106\0\1\106\103\0\1\107"+
    "\74\0\1\110\57\0\1\111\35\0\4\36\1\37\10\36"+
    "\1\112\32\36\1\46\33\36\1\37\32\36\1\113\10\36"+
    "\1\46\33\36\1\37\26\36\1\114\14\36\1\46\33\36"+
    "\1\37\17\36\1\115\23\36\1\46\33\36\1\37\35\36"+
    "\1\116\5\36\1\46\33\36\1\37\16\36\1\117\24\36"+
    "\1\46\27\36\23\0\1\120\141\0\1\121\72\0\1\122"+
    "\77\0\1\123\62\0\1\124\34\0\4\36\1\37\5\36"+
    "\1\125\35\36\1\46\33\36\1\37\35\36\1\126\5\36"+
    "\1\46\33\36\1\37\31\36\1\127\11\36\1\46\33\36"+
    "\1\37\34\36\1\130\6\36\1\46\33\36\1\37\41\36"+
    "\1\131\1\36\1\46\33\36\1\37\34\36\1\132\6\36"+
    "\1\46\27\36\52\0\1\133\110\0\1\134\106\0\1\135"+
    "\70\0\1\136\47\0\1\137\44\0\4\36\1\37\10\36"+
    "\1\140\32\36\1\46\33\36\1\37\43\36\1\46\1\36"+
    "\1\141\31\36\1\37\43\36\1\46\1\36\1\142\31\36"+
    "\1\37\22\36\1\143\20\36\1\46\33\36\1\37\32\36"+
    "\1\144\10\36\1\46\33\36\1\37\26\36\1\145\14\36"+
    "\1\46\27\36\25\0\1\146\133\0\1\147\71\0\1\150"+
    "\106\0\1\151\60\0\1\152\34\0\4\36\1\37\10\36"+
    "\1\153\32\36\1\46\33\36\1\37\37\36\1\154\3\36"+
    "\1\46\33\36\1\37\37\36\1\155\3\36\1\46\33\36"+
    "\1\156\43\36\1\46\33\36\1\37\34\36\1\157\6\36"+
    "\1\46\33\36\1\37\32\36\1\160\10\36\1\46\27\36"+
    "\41\0\1\161\116\0\1\162\103\0\1\163\57\0\1\164"+
    "\33\0\4\36\1\37\5\36\1\165\35\36\1\46\33\36"+
    "\1\37\35\36\1\166\5\36\1\46\33\36\1\37\35\36"+
    "\1\167\5\36\1\46\33\36\1\37\21\36\1\170\21\36"+
    "\1\46\33\36\1\171\43\36\1\46\1\36\1\172\25\36"+
    "\27\0\1\173\124\0\1\174\75\0\1\175\52\0\1\176"+
    "\52\0\4\36\1\37\10\36\1\177\32\36\1\46\33\36"+
    "\1\37\37\36\1\200\3\36\1\46\33\36\1\37\37\36"+
    "\1\201\3\36\1\46\33\36\1\202\43\36\1\46\33\36"+
    "\1\37\31\36\1\203\11\36\1\46\27\36\23\0\1\204"+
    "\134\0\1\205\104\0\1\206\45\0\1\207\44\0\4\36"+
    "\1\37\10\36\1\210\32\36\1\46\33\36\1\37\16\36"+
    "\1\211\24\36\1\46\33\36\1\37\16\36\1\212\24\36"+
    "\1\46\33\36\1\37\37\36\1\213\3\36\1\46\27\36"+
    "\25\0\1\214\140\0\1\215\74\0\1\216\53\0\1\217"+
    "\40\0\4\36\1\220\43\36\1\46\33\36\1\37\34\36"+
    "\1\221\6\36\1\46\33\36\1\37\34\36\1\222\6\36"+
    "\1\46\33\36\1\37\22\36\1\223\20\36\1\46\27\36"+
    "\33\0\1\224\133\0\1\225\71\0\1\226\54\0\1\227"+
    "\41\0\4\36\1\37\26\36\1\230\14\36\1\46\33\36"+
    "\1\37\26\36\1\231\14\36\1\46\33\36\1\37\40\36"+
    "\1\232\2\36\1\46\27\36\37\0\1\233\115\0\1\234"+
    "\102\0\1\235\115\0\1\236\1\0\4\36\1\37\32\36"+
    "\1\237\10\36\1\46\33\36\1\37\32\36\1\240\10\36"+
    "\1\46\33\36\1\37\32\36\1\241\10\36\1\46\27\36"+
    "\36\0\1\242\113\0\1\243\101\0\1\244\23\0\4\36"+
    "\1\245\43\36\1\46\33\36\1\246\43\36\1\46\33\36"+
    "\1\247\43\36\1\46\27\36\76\0\1\250\65\0\1\251"+
    "\73\0\1\252\75\0\1\253\107\0\1\254\64\0\1\255"+
    "\113\0\1\256\71\0\1\257\73\0\1\260\102\0\1\261"+
    "\111\0\1\262\60\0\1\263\101\0\1\264\75\0\1\265"+
    "\24\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[9472];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\7\11\1\1\1\11\1\1\2\11"+
    "\2\1\2\11\4\1\2\11\1\1\1\11\1\0\1\11"+
    "\10\0\3\11\14\0\1\1\20\0\1\11\40\0\1\11"+
    "\4\0\1\11\12\0\1\11\10\0\1\11\15\0\1\11"+
    "\15\0\1\11\6\0\4\11\7\0\1\11\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[181];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexemaUser(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {   return new java_cup.runtime.Symbol(MySymLoginUser.EOF);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { //MANEJAR EL ERROR LEXICO
                        print("ERROR");
                        addError();
            }
          // fall through
          case 37: break;
          case 2:
            { /* print(); */
            }
          // fall through
          case 38: break;
          case 3:
            { print("("); return new Symbol(MySymLoginUser.PARENTESIS_A,yyline,yycolumn,yytext());
            }
          // fall through
          case 39: break;
          case 4:
            { print(")"); return new Symbol(MySymLoginUser.PARENTESIS_C,yyline,yycolumn,yytext());
            }
          // fall through
          case 40: break;
          case 5:
            { print("*"); return new Symbol(MySymLoginUser.MULTIPLICAR,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 41: break;
          case 6:
            { print("+"); return new Symbol(MySymLoginUser.SUMAR,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 42: break;
          case 7:
            { print(","); return new Symbol(MySymLoginUser.COMA,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 43: break;
          case 8:
            { print("-"); return new Symbol(MySymLoginUser.RESTAR,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 44: break;
          case 9:
            { print("/"); return new Symbol(MySymLoginUser.DIVIDIR,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 45: break;
          case 10:
            { print("REAL_NUMEBERS"); return new Symbol(MySymLoginUser.REAL_NUMEBERS ,yyline,yycolumn,yytext());
            }
          // fall through
          case 46: break;
          case 11:
            { print(":"); return new Symbol(MySymLoginUser.COLNO,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 47: break;
          case 12:
            { print("<" ); return new Symbol(MySymLoginUser.OPEN ,yyline,yycolumn,yytext());
            }
          // fall through
          case 48: break;
          case 13:
            { print("="); return new Symbol(MySymLoginUser.EQUAL,yyline,yycolumn, (yytext()));
            }
          // fall through
          case 49: break;
          case 14:
            { print(">" ); return new Symbol(MySymLoginUser.CLOSE ,yyline,yycolumn,yytext());
            }
          // fall through
          case 50: break;
          case 15:
            { print("["); return new Symbol(MySymLoginUser.BRACKETS_O ,yyline,yycolumn,yytext());
            }
          // fall through
          case 51: break;
          case 16:
            { print("]"); return new Symbol(MySymLoginUser.BRACKETS_C ,yyline,yycolumn,yytext());
            }
          // fall through
          case 52: break;
          case 17:
            { print("{"); return new Symbol(MySymLoginUser.KEYS_O ,yyline,yycolumn,yytext());
            }
          // fall through
          case 53: break;
          case 18:
            { print("}"); return new Symbol(MySymLoginUser.KEYS_C ,yyline,yycolumn,yytext());
            }
          // fall through
          case 54: break;
          case 19:
            { print("!>" ); return new Symbol(MySymLoginUser.CLOSE_REQUEST ,yyline,yycolumn,yytext());
            }
          // fall through
          case 55: break;
          case 20:
            { print("STRING"); return new Symbol(MySymLoginUser.STRING ,yyline,yycolumn,yytext());
            }
          // fall through
          case 56: break;
          case 21:
            { print("<!" ); return new Symbol(MySymLoginUser.OPEN_REQUEST ,yyline,yycolumn,yytext());
            }
          // fall through
          case 57: break;
          case 22:
            { print("<?" ); return new Symbol(MySymLoginUser.OPEN_VERSION ,yyline,yycolumn,yytext());
            }
          // fall through
          case 58: break;
          case 23:
            { print("?>" ); return new Symbol(MySymLoginUser.CLOSE_VERSION ,yyline,yycolumn,yytext());
            }
          // fall through
          case 59: break;
          case 24:
            { print("xson"); return new Symbol(MySymLoginUser.XSON ,yyline,yycolumn,yytext());
            }
          // fall through
          case 60: break;
          case 25:
            { print("version"); return new Symbol(MySymLoginUser.VERSION ,yyline,yycolumn,yytext());
            }
          // fall through
          case 61: break;
          case 26:
            { print("\"NOMBRE\""); return new Symbol(MySymLoginUser.NAME_PERSONAL_USER ,yyline,yycolumn,yytext());
            }
          // fall through
          case 62: break;
          case 27:
            { print("\"USUARIO\""); return new Symbol(MySymLoginUser.NAME_USER ,yyline,yycolumn,yytext());
            }
          // fall through
          case 63: break;
          case 28:
            { print("\"PASSWORD\""); return new Symbol(MySymLoginUser.PASS_USER ,yyline,yycolumn,yytext());
            }
          // fall through
          case 64: break;
          case 29:
            { print("DATE"); return new Symbol(MySymLoginUser.STRING_DATE,yyline,yycolumn,yytext());
            }
          // fall through
          case 65: break;
          case 30:
            { print("INSTITUCION"); return new Symbol(MySymLoginUser.INSTITUCION ,yyline,yycolumn,yytext());
            }
          // fall through
          case 66: break;
          case 31:
            { print("\"DATOS_USUARIO\""); return new Symbol(MySymLoginUser.USER_DATA ,yyline,yycolumn,yytext());
            }
          // fall through
          case 67: break;
          case 32:
            { print("\"LOGIN_USUARIO\""); return new Symbol(MySymLoginUser.LOGIN_USER ,yyline,yycolumn,yytext());
            }
          // fall through
          case 68: break;
          case 33:
            { print("\"USUARIO_NUEVO\""); return new Symbol(MySymLoginUser.NEW_USER ,yyline,yycolumn,yytext());
            }
          // fall through
          case 69: break;
          case 34:
            { print("INSTITUCION"); return new Symbol(MySymLoginUser.DATE ,yyline,yycolumn,yytext());
            }
          // fall through
          case 70: break;
          case 35:
            { print("realizar_solicitud"); return new Symbol(MySymLoginUser.MAKE_REQUEST ,yyline,yycolumn,yytext());
            }
          // fall through
          case 71: break;
          case 36:
            { print("fin_solicitud_realizada"); return new Symbol(MySymLoginUser.FINAL_REQUEST ,yyline,yycolumn,yytext());
            }
          // fall through
          case 72: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
