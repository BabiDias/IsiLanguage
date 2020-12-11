// Generated from IsiLang.g4 by ANTLR 4.7.1
package isilanguage.parser;

	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.exceptions.IsiSemanticException;
	import isilanguage.ast.IsiProgram;
	import isilanguage.ast.AbstractCommand;
	import isilanguage.ast.CommandLeitura;
	import isilanguage.ast.CommandEscrita;
	import isilanguage.ast.CommandExpr;
	import isilanguage.ast.CommandIf;
	import isilanguage.ast.CommandEnquanto;
	import isilanguage.ast.CommandPara;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, AP=15, FP=16, SC=17, 
		OP=18, OPAD=19, ATTR=20, VIR=21, ACH=22, FCH=23, ASP=24, OPREL=25, OPBOOL=26, 
		NOT=27, ID=28, NUMBER=29, TEXT=30, PONT=31, WS=32;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, RULE_cmdExpr = 8, 
		RULE_cmdIf = 9, RULE_cmdEnquanto = 10, RULE_cmdPara = 11, RULE_attPara = 12, 
		RULE_expr = 13, RULE_termo = 14, RULE_string = 15, RULE_boolExpr = 16, 
		RULE_notExpr = 17, RULE_boolRel = 18, RULE_boolNumRel = 19, RULE_boolStringRel = 20, 
		RULE_boolBoolRel = 21;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdLeitura", "cmdEscrita", 
		"cmdExpr", "cmdIf", "cmdEnquanto", "cmdPara", "attPara", "expr", "termo", 
		"string", "boolExpr", "notExpr", "boolRel", "boolNumRel", "boolStringRel", 
		"boolBoolRel"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'boolean'", 
		"'leia'", "'escreva'", "'se'", "'senao'", "'enquanto'", "'para'", "'passo'", 
		"'true'", "'false'", "'('", "')'", "';'", null, "'+'", "'='", "','", "'{'", 
		"'}'", "'\"'", null, null, "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "AP", "FP", "SC", "OP", "OPAD", "ATTR", "VIR", "ACH", 
		"FCH", "ASP", "OPREL", "OPBOOL", "NOT", "ID", "NUMBER", "TEXT", "PONT", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private int _tipo;
		private String _varName;
		private String _varValue;
		private ArrayList<String> _varNaoAtribuidas = new ArrayList<String>();
		private ArrayList<String> _varNaoUtilizadas = new ArrayList<String>();
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _exprID;
		private String _exprContent;
		private String _exprBool = "";
		private String _passoVal;
		private String _forInit;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> comandoEnquanto;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Variable '"+id+"' was not declared.");
			}
		}
		
		public void verificaIDNumber(String id){
			verificaID(id);
			if (((IsiVariable) symbolTable.get(id)).getType() != 0){
				throw new IsiSemanticException("Variable '"+id+"' is not type NUMBER.");
			}
		}
		
		public void verificaIDText(String id){
			verificaID(id);
			if (((IsiVariable) symbolTable.get(id)).getType() != 1){
				throw new IsiSemanticException("Variable '"+id+"' is not type TEXT.");
			}
		}
		
		public void verificaIDBool(String id) {
			verificaID(id);
			if (((IsiVariable) symbolTable.get(id)).getType() != 2){
				throw new IsiSemanticException("Variable '"+id+"' is not type BOOL.");
			}
		}
		
		public void variaveisNaoUtilizadas(){
			for (String var: _varNaoAtribuidas){
				System.out.println("Warning: Variable '" +var+ "' declared but never used.");
			}
		}
		
		public void variaveisUtilizadasNaoAtribuidas(){
			Boolean flag = false;
			for (String i: _varNaoAtribuidas){
				for (String j: _varNaoUtilizadas){
					if (i.equals(j)){
						flag = true;
					}
				}
				if (!flag){
					throw new IsiSemanticException("Variable '"+i+"' has no value.");
				}
				flag = false;
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__0);
			setState(45);
			decl();
			setState(46);
			bloco();
			setState(47);
			match(T__1);
			  program.setVarTable(symbolTable);
			           	  program.setComandos(stack.pop());
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				declaravar();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			tipo();
			setState(56);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  _varNaoAtribuidas.add(_varName);
				                  _varNaoUtilizadas.add(_varName);
				                  symbol = new IsiVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  } else{
				                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                  }
			                    
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(58);
				match(VIR);
				setState(59);
				match(ID);

					                  _varName = _input.LT(-1).getText();
					                  _varValue = null;
					                  _varNaoAtribuidas.add(_varName);
					                  _varNaoUtilizadas.add(_varName);
					                  symbol = new IsiVariable(_varName, _tipo, _varValue);
					                  if (!symbolTable.exists(_varName)){
					                     symbolTable.add(symbol);	
					                  } else{
					                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
					                  }
				                    
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(T__4);
				 _tipo = IsiVariable.BOOL;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77);
				cmd();
				}
				}
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdExprContext cmdExpr() {
			return getRuleContext(CmdExprContext.class,0);
		}
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdParaContext cmdPara() {
			return getRuleContext(CmdParaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				cmdLeitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				cmdEscrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				cmdExpr();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				cmdIf();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
				cmdEnquanto();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(87);
				cmdPara();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__5);
			setState(91);
			match(AP);
			setState(92);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  _varNaoAtribuidas.remove(new String(_readID));
			                          _varNaoUtilizadas.remove(new String(_readID));
			                        
			setState(94);
			match(FP);
			setState(95);
			match(SC);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
			              	stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__6);
			setState(99);
			match(AP);
			 _exprContent = ""; 
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(101);
				match(ID);
				 verificaID(_input.LT(-1).getText()); 
				                 			  _exprContent = _input.LT(-1).getText(); 
				                 			  _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case 2:
				{
				setState(103);
				expr();
				}
				break;
			case 3:
				{
				setState(104);
				string();
				}
				break;
			}
			setState(107);
			match(FP);
			setState(108);
			match(SC);

			               	  CommandEscrita cmd = new CommandEscrita(_exprContent);
			               	  stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdExprContext extends ParserRuleContext {
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CmdExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdExpr(this);
		}
	}

	public final CmdExprContext cmdExpr() throws RecognitionException {
		CmdExprContext _localctx = new CmdExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(111);
				match(ID);
				 verificaIDBool(_input.LT(-1).getText());
				                        _exprID = _input.LT(-1).getText();
				                        _varNaoAtribuidas.remove(new String(_exprID));
				                        _varNaoUtilizadas.remove(new String(_exprID));
				setState(113);
				match(ATTR);
				 _exprContent = ""; _exprBool = "";
				setState(115);
				boolExpr();
				 _exprContent = _exprBool;
				}
				break;
			case 2:
				{
				setState(118);
				match(ID);
				 verificaIDText(_input.LT(-1).getText());
				                        _exprID = _input.LT(-1).getText();
				                        _varNaoAtribuidas.remove(new String(_exprID));
				                        _varNaoUtilizadas.remove(new String(_exprID));
				setState(120);
				match(ATTR);
				 _exprContent = ""; 
				setState(122);
				string();
				}
				break;
			case 3:
				{
				setState(123);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText());
				                        _exprID = _input.LT(-1).getText();
				                        _varNaoAtribuidas.remove(new String(_exprID));
				                        _varNaoUtilizadas.remove(new String(_exprID));
				setState(125);
				match(ATTR);
				 _exprContent = ""; 
				setState(127);
				expr();
				}
				break;
			}
			setState(130);
			match(SC);
			 CommandExpr cmd = new CommandExpr(_exprID, _exprContent);
			               	 stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdIf(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 _exprBool = ""; 
			setState(134);
			match(T__7);
			setState(135);
			match(AP);
			setState(136);
			boolExpr();
			setState(137);
			match(FP);
			setState(138);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                	stack.push(curThread);
			               	
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				cmd();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(145);
			match(FCH);
			 listaTrue = stack.pop(); 
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(147);
				match(T__8);
				setState(148);
				match(ACH);
					curThread = new ArrayList<AbstractCommand>();
				               	 	stack.push(curThread);
				               	 
				{
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(150);
					cmd();
					}
					}
					setState(153); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(155);
				match(FCH);
					listaFalse = stack.pop();
				               		CommandIf cmd = new CommandIf(_exprBool, listaTrue, listaFalse);
				               		stack.peek().add(cmd);
				               	
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 _exprBool = ""; 
			setState(161);
			match(T__9);
			setState(162);
			match(AP);
			setState(163);
			boolExpr();
			setState(164);
			match(FP);
			setState(165);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                    		  stack.push(curThread);
			                   		  
			setState(168); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(167);
				cmd();
				}
				}
				setState(170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(172);
			match(FCH);

			                       		  comandoEnquanto = stack.pop();
			                       		  CommandEnquanto cmd = new CommandEnquanto(_exprBool, comandoEnquanto);
			                   			  stack.peek().add(cmd);	
			                    	  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public AttParaContext attPara() {
			return getRuleContext(AttParaContext.class,0);
		}
		public List<TerminalNode> SC() { return getTokens(IsiLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(IsiLangParser.SC, i);
		}
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdPara(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 _exprBool = ""; 
			setState(176);
			match(T__10);
			setState(177);
			match(AP);
			setState(178);
			attPara();
			setState(179);
			match(SC);
			setState(180);
			boolExpr();
			setState(181);
			match(SC);
			setState(182);
			match(T__11);
			setState(183);
			match(NUMBER);

								_passoVal = _input.LT(-1).getText();
							
			setState(185);
			match(FP);
			setState(186);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                    		  stack.push(curThread);
			                   		  
			setState(189); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(188);
				cmd();
				}
				}
				setState(191); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(193);
			match(FCH);

			                       		  comandoEnquanto = stack.pop();
			                       		  CommandPara cmd = new CommandPara(_exprID, _forInit, _exprBool, _passoVal, comandoEnquanto);
			                   			  stack.peek().add(cmd);	
			                    	  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttParaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AttParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterAttPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitAttPara(this);
		}
	}

	public final AttParaContext attPara() throws RecognitionException {
		AttParaContext _localctx = new AttParaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_attPara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(ID);

								_exprID = _input.LT(-1).getText();
								_varNaoAtribuidas.remove(_exprID);
								_varNaoUtilizadas.remove(_exprID);
			                
			setState(198);
			match(ATTR);
			 _exprContent = ""; 
			setState(200);
			expr();
			 _forInit = _exprContent; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OPAD() { return getTokens(IsiLangParser.OPAD); }
		public TerminalNode OPAD(int i) {
			return getToken(IsiLangParser.OPAD, i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			int _alt;
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				termo();
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(204);
						_la = _input.LA(1);
						if ( !(_la==OP || _la==OPAD) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						 _exprContent += _input.LT(-1).getText(); 
						setState(206);
						termo();
						}
						} 
					}
					setState(211);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID || _la==NUMBER) {
					{
					{
					setState(212);
					termo();
					setState(213);
					_la = _input.LA(1);
					if ( !(_la==OP || _la==OPAD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					 _exprContent += _input.LT(-1).getText(); 
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221);
				match(AP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(223);
				expr();
				setState(224);
				match(FP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(226);
						_la = _input.LA(1);
						if ( !(_la==OP || _la==OPAD) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						 _exprContent += _input.LT(-1).getText(); 
						setState(230);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
						case 1:
							{
							setState(228);
							termo();
							}
							break;
						case 2:
							{
							setState(229);
							expr();
							}
							break;
						}
						}
						} 
					}
					setState(236);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(239);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText());
				                     _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case NUMBER:
				{
				setState(241);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprContent += _input.LT(-1).getText(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public List<TerminalNode> OPAD() { return getTokens(IsiLangParser.OPAD); }
		public TerminalNode OPAD(int i) {
			return getToken(IsiLangParser.OPAD, i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_string);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(246);
				match(ID);
				 verificaID(_input.LT(-1).getText()); 
				                     	  _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case TEXT:
				{
				setState(248);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprContent += _input.LT(-1).getText(); 
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(252);
					match(OPAD);
					 _exprContent += _input.LT(-1).getText(); 
					setState(254);
					string();
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolExprContext extends ParserRuleContext {
		public BoolRelContext boolRel() {
			return getRuleContext(BoolRelContext.class,0);
		}
		public NotExprContext notExpr() {
			return getRuleContext(NotExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public List<TerminalNode> OPBOOL() { return getTokens(IsiLangParser.OPBOOL); }
		public TerminalNode OPBOOL(int i) {
			return getToken(IsiLangParser.OPBOOL, i);
		}
		public List<BoolExprContext> boolExpr() {
			return getRuleContexts(BoolExprContext.class);
		}
		public BoolExprContext boolExpr(int i) {
			return getRuleContext(BoolExprContext.class,i);
		}
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolExpr(this);
		}
	}

	public final BoolExprContext boolExpr() throws RecognitionException {
		BoolExprContext _localctx = new BoolExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(260);
				boolRel();
				}
				break;
			case 2:
				{
				setState(261);
				notExpr();
				}
				break;
			case 3:
				{
				setState(262);
				match(ID);
				 
										verificaIDBool(_input.LT(-1).getText()); 
										_exprBool += _input.LT(-1).getText();
				                  
				}
				break;
			}
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(266);
					match(OPBOOL);
					 _exprBool += _input.LT(-1).getText(); 
					setState(268);
					boolExpr();
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotExprContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(IsiLangParser.NOT, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public NotExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitNotExpr(this);
		}
	}

	public final NotExprContext notExpr() throws RecognitionException {
		NotExprContext _localctx = new NotExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_notExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(NOT);
			 _exprBool += "!"; 
			setState(276);
			match(AP);
			 _exprBool += "("; 
			setState(278);
			boolExpr();
			setState(279);
			match(FP);
			 _exprBool += ")"; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolRelContext extends ParserRuleContext {
		public BoolNumRelContext boolNumRel() {
			return getRuleContext(BoolNumRelContext.class,0);
		}
		public BoolStringRelContext boolStringRel() {
			return getRuleContext(BoolStringRelContext.class,0);
		}
		public BoolBoolRelContext boolBoolRel() {
			return getRuleContext(BoolBoolRelContext.class,0);
		}
		public BoolRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolRel(this);
		}
	}

	public final BoolRelContext boolRel() throws RecognitionException {
		BoolRelContext _localctx = new BoolRelContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_boolRel);
		int _la;
		try {
			setState(287);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				 _exprBool += _input.LT(-1).getText(); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				boolNumRel();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				boolStringRel();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(286);
				boolBoolRel();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolNumRelContext extends ParserRuleContext {
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public BoolNumRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolNumRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolNumRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolNumRel(this);
		}
	}

	public final BoolNumRelContext boolNumRel() throws RecognitionException {
		BoolNumRelContext _localctx = new BoolNumRelContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_boolNumRel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(289);
				match(ID);
				 
									verificaIDNumber(_input.LT(-1).getText());
								  	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
								
				}
				break;
			case NUMBER:
				{
				setState(291);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprBool += _input.LT(-1).getText(); 
			setState(295);
			match(OPREL);
			 _exprBool += _input.LT(-1).getText(); 
			setState(300);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(297);
				match(ID);
				 
						    		verificaIDNumber(_input.LT(-1).getText());
						           	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
						     	
				}
				break;
			case NUMBER:
				{
				setState(299);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprBool += _input.LT(-1).getText(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolStringRelContext extends ParserRuleContext {
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public BoolStringRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolStringRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolStringRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolStringRel(this);
		}
	}

	public final BoolStringRelContext boolStringRel() throws RecognitionException {
		BoolStringRelContext _localctx = new BoolStringRelContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_boolStringRel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(304);
				match(ID);
				 
									verificaIDText(_input.LT(-1).getText());
								  	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
								
				}
				break;
			case TEXT:
				{
				setState(306);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprBool += _input.LT(-1).getText(); 
			setState(310);
			match(OPREL);
			 _exprBool += _input.LT(-1).getText(); 
			setState(315);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(312);
				match(ID);
				 
						    		verificaIDText(_input.LT(-1).getText());
						           	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
						     	
				}
				break;
			case TEXT:
				{
				setState(314);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprBool += _input.LT(-1).getText(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolBoolRelContext extends ParserRuleContext {
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public BoolBoolRelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolBoolRel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolBoolRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolBoolRel(this);
		}
	}

	public final BoolBoolRelContext boolBoolRel() throws RecognitionException {
		BoolBoolRelContext _localctx = new BoolBoolRelContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_boolBoolRel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(319);
				match(ID);
				 
									verificaIDBool(_input.LT(-1).getText());
								  	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
								
				}
				break;
			case T__12:
			case T__13:
				{
				setState(321);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprBool += _input.LT(-1).getText(); 
			setState(325);
			match(OPREL);
			 _exprBool += _input.LT(-1).getText(); 
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(327);
				match(ID);
				 
						    		verificaIDBool(_input.LT(-1).getText());
						           	_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
						     	
				}
				break;
			case T__12:
			case T__13:
				{
				setState(329);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprBool += _input.LT(-1).getText(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u0151\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\6\3\66\n\3\r\3\16\3\67\3\4\3\4\3\4\3\4\3\4\3\4\7\4@\n\4\f\4\16"+
		"\4C\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5M\n\5\3\6\3\6\6\6Q\n\6\r\6"+
		"\16\6R\3\7\3\7\3\7\3\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0083\n\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0090\n\13\r\13"+
		"\16\13\u0091\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u009a\n\13\r\13\16\13"+
		"\u009b\3\13\3\13\3\13\5\13\u00a1\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\6\f\u00ab\n\f\r\f\16\f\u00ac\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00c0\n\r\r\r\16\r\u00c1\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00d2\n\17"+
		"\f\17\16\17\u00d5\13\17\3\17\3\17\3\17\3\17\7\17\u00db\n\17\f\17\16\17"+
		"\u00de\13\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00e9\n"+
		"\17\7\17\u00eb\n\17\f\17\16\17\u00ee\13\17\5\17\u00f0\n\17\3\20\3\20\3"+
		"\20\5\20\u00f5\n\20\3\20\3\20\3\21\3\21\3\21\5\21\u00fc\n\21\3\21\3\21"+
		"\3\21\3\21\7\21\u0102\n\21\f\21\16\21\u0105\13\21\3\22\3\22\3\22\3\22"+
		"\5\22\u010b\n\22\3\22\3\22\3\22\7\22\u0110\n\22\f\22\16\22\u0113\13\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u0122\n\24\3\25\3\25\3\25\5\25\u0127\n\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\5\25\u012f\n\25\3\25\3\25\3\26\3\26\3\26\5\26\u0136\n\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u013e\n\26\3\26\3\26\3\27\3\27\3\27\5\27\u0145"+
		"\n\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u014d\n\27\3\27\3\27\3\27\2\2"+
		"\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\4\3\2\24\25\3\2\17"+
		"\20\2\u0161\2.\3\2\2\2\4\65\3\2\2\2\69\3\2\2\2\bL\3\2\2\2\nN\3\2\2\2\f"+
		"Z\3\2\2\2\16\\\3\2\2\2\20d\3\2\2\2\22\u0082\3\2\2\2\24\u0087\3\2\2\2\26"+
		"\u00a2\3\2\2\2\30\u00b1\3\2\2\2\32\u00c6\3\2\2\2\34\u00ef\3\2\2\2\36\u00f4"+
		"\3\2\2\2 \u00fb\3\2\2\2\"\u010a\3\2\2\2$\u0114\3\2\2\2&\u0121\3\2\2\2"+
		"(\u0126\3\2\2\2*\u0135\3\2\2\2,\u0144\3\2\2\2./\7\3\2\2/\60\5\4\3\2\60"+
		"\61\5\n\6\2\61\62\7\4\2\2\62\63\b\2\1\2\63\3\3\2\2\2\64\66\5\6\4\2\65"+
		"\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\5\3\2\2\29:\5\b\5"+
		"\2:;\7\36\2\2;A\b\4\1\2<=\7\27\2\2=>\7\36\2\2>@\b\4\1\2?<\3\2\2\2@C\3"+
		"\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\23\2\2E\7\3\2\2\2F"+
		"G\7\5\2\2GM\b\5\1\2HI\7\6\2\2IM\b\5\1\2JK\7\7\2\2KM\b\5\1\2LF\3\2\2\2"+
		"LH\3\2\2\2LJ\3\2\2\2M\t\3\2\2\2NP\b\6\1\2OQ\5\f\7\2PO\3\2\2\2QR\3\2\2"+
		"\2RP\3\2\2\2RS\3\2\2\2S\13\3\2\2\2T[\5\16\b\2U[\5\20\t\2V[\5\22\n\2W["+
		"\5\24\13\2X[\5\26\f\2Y[\5\30\r\2ZT\3\2\2\2ZU\3\2\2\2ZV\3\2\2\2ZW\3\2\2"+
		"\2ZX\3\2\2\2ZY\3\2\2\2[\r\3\2\2\2\\]\7\b\2\2]^\7\21\2\2^_\7\36\2\2_`\b"+
		"\b\1\2`a\7\22\2\2ab\7\23\2\2bc\b\b\1\2c\17\3\2\2\2de\7\t\2\2ef\7\21\2"+
		"\2fk\b\t\1\2gh\7\36\2\2hl\b\t\1\2il\5\34\17\2jl\5 \21\2kg\3\2\2\2ki\3"+
		"\2\2\2kj\3\2\2\2lm\3\2\2\2mn\7\22\2\2no\7\23\2\2op\b\t\1\2p\21\3\2\2\2"+
		"qr\7\36\2\2rs\b\n\1\2st\7\26\2\2tu\b\n\1\2uv\5\"\22\2vw\b\n\1\2w\u0083"+
		"\3\2\2\2xy\7\36\2\2yz\b\n\1\2z{\7\26\2\2{|\b\n\1\2|\u0083\5 \21\2}~\7"+
		"\36\2\2~\177\b\n\1\2\177\u0080\7\26\2\2\u0080\u0081\b\n\1\2\u0081\u0083"+
		"\5\34\17\2\u0082q\3\2\2\2\u0082x\3\2\2\2\u0082}\3\2\2\2\u0083\u0084\3"+
		"\2\2\2\u0084\u0085\7\23\2\2\u0085\u0086\b\n\1\2\u0086\23\3\2\2\2\u0087"+
		"\u0088\b\13\1\2\u0088\u0089\7\n\2\2\u0089\u008a\7\21\2\2\u008a\u008b\5"+
		"\"\22\2\u008b\u008c\7\22\2\2\u008c\u008d\7\30\2\2\u008d\u008f\b\13\1\2"+
		"\u008e\u0090\5\f\7\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\7\31\2\2"+
		"\u0094\u00a0\b\13\1\2\u0095\u0096\7\13\2\2\u0096\u0097\7\30\2\2\u0097"+
		"\u0099\b\13\1\2\u0098\u009a\5\f\7\2\u0099\u0098\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009e\7\31\2\2\u009e\u009f\b\13\1\2\u009f\u00a1\3\2\2\2\u00a0\u0095\3"+
		"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\25\3\2\2\2\u00a2\u00a3\b\f\1\2\u00a3"+
		"\u00a4\7\f\2\2\u00a4\u00a5\7\21\2\2\u00a5\u00a6\5\"\22\2\u00a6\u00a7\7"+
		"\22\2\2\u00a7\u00a8\7\30\2\2\u00a8\u00aa\b\f\1\2\u00a9\u00ab\5\f\7\2\u00aa"+
		"\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\31\2\2\u00af\u00b0\b\f\1\2\u00b0"+
		"\27\3\2\2\2\u00b1\u00b2\b\r\1\2\u00b2\u00b3\7\r\2\2\u00b3\u00b4\7\21\2"+
		"\2\u00b4\u00b5\5\32\16\2\u00b5\u00b6\7\23\2\2\u00b6\u00b7\5\"\22\2\u00b7"+
		"\u00b8\7\23\2\2\u00b8\u00b9\7\16\2\2\u00b9\u00ba\7\37\2\2\u00ba\u00bb"+
		"\b\r\1\2\u00bb\u00bc\7\22\2\2\u00bc\u00bd\7\30\2\2\u00bd\u00bf\b\r\1\2"+
		"\u00be\u00c0\5\f\7\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\7\31\2\2"+
		"\u00c4\u00c5\b\r\1\2\u00c5\31\3\2\2\2\u00c6\u00c7\7\36\2\2\u00c7\u00c8"+
		"\b\16\1\2\u00c8\u00c9\7\26\2\2\u00c9\u00ca\b\16\1\2\u00ca\u00cb\5\34\17"+
		"\2\u00cb\u00cc\b\16\1\2\u00cc\33\3\2\2\2\u00cd\u00d3\5\36\20\2\u00ce\u00cf"+
		"\t\2\2\2\u00cf\u00d0\b\17\1\2\u00d0\u00d2\5\36\20\2\u00d1\u00ce\3\2\2"+
		"\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00f0"+
		"\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5\36\20\2\u00d7\u00d8\t\2\2\2"+
		"\u00d8\u00d9\b\17\1\2\u00d9\u00db\3\2\2\2\u00da\u00d6\3\2\2\2\u00db\u00de"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00df\u00e0\7\21\2\2\u00e0\u00e1\b\17\1\2\u00e1\u00e2\5"+
		"\34\17\2\u00e2\u00e3\7\22\2\2\u00e3\u00ec\b\17\1\2\u00e4\u00e5\t\2\2\2"+
		"\u00e5\u00e8\b\17\1\2\u00e6\u00e9\5\36\20\2\u00e7\u00e9\5\34\17\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e4\3\2"+
		"\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00cd\3\2\2\2\u00ef\u00dc\3\2"+
		"\2\2\u00f0\35\3\2\2\2\u00f1\u00f2\7\36\2\2\u00f2\u00f5\b\20\1\2\u00f3"+
		"\u00f5\7\37\2\2\u00f4\u00f1\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3"+
		"\2\2\2\u00f6\u00f7\b\20\1\2\u00f7\37\3\2\2\2\u00f8\u00f9\7\36\2\2\u00f9"+
		"\u00fc\b\21\1\2\u00fa\u00fc\7 \2\2\u00fb\u00f8\3\2\2\2\u00fb\u00fa\3\2"+
		"\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0103\b\21\1\2\u00fe\u00ff\7\25\2\2\u00ff"+
		"\u0100\b\21\1\2\u0100\u0102\5 \21\2\u0101\u00fe\3\2\2\2\u0102\u0105\3"+
		"\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104!\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0106\u010b\5&\24\2\u0107\u010b\5$\23\2\u0108\u0109\7\36\2\2"+
		"\u0109\u010b\b\22\1\2\u010a\u0106\3\2\2\2\u010a\u0107\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010b\u0111\3\2\2\2\u010c\u010d\7\34\2\2\u010d\u010e\b\22\1\2"+
		"\u010e\u0110\5\"\22\2\u010f\u010c\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112#\3\2\2\2\u0113\u0111\3\2\2\2\u0114"+
		"\u0115\7\35\2\2\u0115\u0116\b\23\1\2\u0116\u0117\7\21\2\2\u0117\u0118"+
		"\b\23\1\2\u0118\u0119\5\"\22\2\u0119\u011a\7\22\2\2\u011a\u011b\b\23\1"+
		"\2\u011b%\3\2\2\2\u011c\u011d\t\3\2\2\u011d\u0122\b\24\1\2\u011e\u0122"+
		"\5(\25\2\u011f\u0122\5*\26\2\u0120\u0122\5,\27\2\u0121\u011c\3\2\2\2\u0121"+
		"\u011e\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122\'\3\2\2\2"+
		"\u0123\u0124\7\36\2\2\u0124\u0127\b\25\1\2\u0125\u0127\7\37\2\2\u0126"+
		"\u0123\3\2\2\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\b\25"+
		"\1\2\u0129\u012a\7\33\2\2\u012a\u012e\b\25\1\2\u012b\u012c\7\36\2\2\u012c"+
		"\u012f\b\25\1\2\u012d\u012f\7\37\2\2\u012e\u012b\3\2\2\2\u012e\u012d\3"+
		"\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\25\1\2\u0131)\3\2\2\2\u0132\u0133"+
		"\7\36\2\2\u0133\u0136\b\26\1\2\u0134\u0136\7 \2\2\u0135\u0132\3\2\2\2"+
		"\u0135\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b\26\1\2\u0138\u0139"+
		"\7\33\2\2\u0139\u013d\b\26\1\2\u013a\u013b\7\36\2\2\u013b\u013e\b\26\1"+
		"\2\u013c\u013e\7 \2\2\u013d\u013a\3\2\2\2\u013d\u013c\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u0140\b\26\1\2\u0140+\3\2\2\2\u0141\u0142\7\36\2\2\u0142"+
		"\u0145\b\27\1\2\u0143\u0145\t\3\2\2\u0144\u0141\3\2\2\2\u0144\u0143\3"+
		"\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b\27\1\2\u0147\u0148\7\33\2\2\u0148"+
		"\u014c\b\27\1\2\u0149\u014a\7\36\2\2\u014a\u014d\b\27\1\2\u014b\u014d"+
		"\t\3\2\2\u014c\u0149\3\2\2\2\u014c\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\u014f\b\27\1\2\u014f-\3\2\2\2\37\67ALRZk\u0082\u0091\u009b\u00a0\u00ac"+
		"\u00c1\u00d3\u00dc\u00e8\u00ec\u00ef\u00f4\u00fb\u0103\u010a\u0111\u0121"+
		"\u0126\u012e\u0135\u013d\u0144\u014c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}