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
		AP=10, FP=11, SC=12, OP=13, OPAD=14, ATTR=15, VIR=16, ACH=17, FCH=18, 
		ASP=19, OPREL=20, ID=21, NUMBER=22, TEXT=23, PONT=24, WS=25;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, RULE_cmdExpr = 8, 
		RULE_cmdIf = 9, RULE_cmdEnquanto = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_string = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdLeitura", "cmdEscrita", 
		"cmdExpr", "cmdIf", "cmdEnquanto", "expr", "termo", "string"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
		"'se'", "'senao'", "'enquanto'", "'('", "')'", "';'", null, "'+'", "'='", 
		"','", "'{'", "'}'", "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
		"SC", "OP", "OPAD", "ATTR", "VIR", "ACH", "FCH", "ASP", "OPREL", "ID", 
		"NUMBER", "TEXT", "PONT", "WS"
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
		private String _exprIf;
		private String _exprEnquanto;
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
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaravar();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
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
			setState(39);
			tipo();
			setState(40);
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
			                    
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
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
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
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
			          
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				cmd();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdLeitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdEscrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdExpr();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdIf();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdEnquanto();
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
			setState(71);
			match(T__4);
			setState(72);
			match(AP);
			setState(73);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                     	  _varNaoAtribuidas.remove(new String(_readID));
			                          _varNaoUtilizadas.remove(new String(_readID));
			                        
			setState(75);
			match(FP);
			setState(76);
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
			setState(79);
			match(T__5);
			setState(80);
			match(AP);
			 _exprContent = ""; 
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(82);
				match(ID);
				 verificaID(_input.LT(-1).getText()); 
				                 			  _exprContent = _input.LT(-1).getText(); 
				                 			  _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case 2:
				{
				setState(84);
				expr();
				}
				break;
			case 3:
				{
				setState(85);
				string();
				}
				break;
			}
			setState(88);
			match(FP);
			setState(89);
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
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(92);
				match(ID);
				 verificaIDText(_input.LT(-1).getText());
				                        _exprID = _input.LT(-1).getText();
				                        _varNaoAtribuidas.remove(new String(_exprID));
				                        _varNaoUtilizadas.remove(new String(_exprID));
				setState(94);
				match(ATTR);
				 _exprContent = ""; 
				setState(96);
				string();
				}
				break;
			case 2:
				{
				setState(97);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText());
				                        _exprID = _input.LT(-1).getText();
				                        _varNaoAtribuidas.remove(new String(_exprID));
				                        _varNaoUtilizadas.remove(new String(_exprID));
				setState(99);
				match(ATTR);
				 _exprContent = ""; 
				setState(101);
				expr();
				}
				break;
			}
			setState(104);
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
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
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
			setState(107);
			match(T__6);
			setState(108);
			match(AP);
			setState(109);
			match(ID);
			 verificaIDNumber(_input.LT(-1).getText());
			                    		_exprIf = _input.LT(-1).getText();
			                    		_varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
			setState(111);
			match(OPREL);
			 _exprIf += _input.LT(-1).getText(); 
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(113);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText());
				                           _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case NUMBER:
				{
				setState(115);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprIf += _input.LT(-1).getText(); 
			setState(119);
			match(FP);
			setState(120);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                    	stack.push(curThread);
			                   	
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(122);
				cmd();
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			setState(127);
			match(FCH);
			 listaTrue = stack.pop(); 
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(129);
				match(T__7);
				setState(130);
				match(ACH);
					curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132);
					cmd();
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(137);
				match(FCH);
					listaFalse = stack.pop();
				                   		CommandIf cmd = new CommandIf(_exprIf, listaTrue, listaFalse);
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
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
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
			setState(142);
			match(T__8);
			setState(143);
			match(AP);
			setState(144);
			match(ID);
			 verificaIDNumber(_input.LT(-1).getText());
			                    	  		  _exprEnquanto = _input.LT(-1).getText(); 
			                    	  		  _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
			setState(146);
			match(OPREL);
			 _exprEnquanto += _input.LT(-1).getText(); 
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(148);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText()); 
				                    	         _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case NUMBER:
				{
				setState(150);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprEnquanto += _input.LT(-1).getText(); 
			setState(154);
			match(FP);
			setState(155);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>(); 
			                    		  stack.push(curThread);
			                   		  
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(157);
				cmd();
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << ID))) != 0) );
			setState(162);
			match(FCH);

			                       		  comandoEnquanto = stack.pop();
			                       		  CommandEnquanto cmd = new CommandEnquanto(_exprEnquanto, comandoEnquanto);
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
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			int _alt;
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				termo();
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(166);
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
						setState(168);
						termo();
						}
						} 
					}
					setState(173);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID || _la==NUMBER) {
					{
					{
					setState(174);
					termo();
					setState(175);
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
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
				match(AP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(185);
				expr();
				setState(186);
				match(FP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(188);
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
						setState(192);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
						case 1:
							{
							setState(190);
							termo();
							}
							break;
						case 2:
							{
							setState(191);
							expr();
							}
							break;
						}
						}
						} 
					}
					setState(198);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		enterRule(_localctx, 24, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(201);
				match(ID);
				 verificaIDNumber(_input.LT(-1).getText());
				                     _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case NUMBER:
				{
				setState(203);
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
		enterRule(_localctx, 26, RULE_string);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(208);
				match(ID);
				 verificaID(_input.LT(-1).getText()); 
				                     	  _varNaoUtilizadas.remove(new String(_input.LT(-1).getText())); 
				}
				break;
			case TEXT:
				{
				setState(210);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 _exprContent += _input.LT(-1).getText(); 
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(214);
					match(OPAD);
					 _exprContent += _input.LT(-1).getText(); 
					setState(216);
					string();
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00e1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\6\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5;\n\5\3\6\3\6\6\6?\n\6\r\6\16\6@\3\7\3\7"+
		"\3\7\3\7\3\7\5\7H\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tY\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\5\ni\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13w\n\13\3\13\3\13\3\13\3\13\3\13\6\13~\n\13\r\13\16\13\177\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\6\13\u0088\n\13\r\13\16\13\u0089\3\13\3\13"+
		"\3\13\5\13\u008f\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u009a\n"+
		"\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a1\n\f\r\f\16\f\u00a2\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\7\r\u00ac\n\r\f\r\16\r\u00af\13\r\3\r\3\r\3\r\3\r\7\r\u00b5"+
		"\n\r\f\r\16\r\u00b8\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c3"+
		"\n\r\7\r\u00c5\n\r\f\r\16\r\u00c8\13\r\5\r\u00ca\n\r\3\16\3\16\3\16\5"+
		"\16\u00cf\n\16\3\16\3\16\3\17\3\17\3\17\5\17\u00d6\n\17\3\17\3\17\3\17"+
		"\3\17\7\17\u00dc\n\17\f\17\16\17\u00df\13\17\3\17\2\2\20\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\2\3\3\2\17\20\2\u00eb\2\36\3\2\2\2\4%\3\2\2\2\6"+
		")\3\2\2\2\b:\3\2\2\2\n<\3\2\2\2\fG\3\2\2\2\16I\3\2\2\2\20Q\3\2\2\2\22"+
		"h\3\2\2\2\24m\3\2\2\2\26\u0090\3\2\2\2\30\u00c9\3\2\2\2\32\u00ce\3\2\2"+
		"\2\34\u00d5\3\2\2\2\36\37\7\3\2\2\37 \5\4\3\2 !\5\n\6\2!\"\7\4\2\2\"#"+
		"\b\2\1\2#\3\3\2\2\2$&\5\6\4\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2"+
		"\2(\5\3\2\2\2)*\5\b\5\2*+\7\27\2\2+\61\b\4\1\2,-\7\22\2\2-.\7\27\2\2."+
		"\60\b\4\1\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3"+
		"\2\2\2\63\61\3\2\2\2\64\65\7\16\2\2\65\7\3\2\2\2\66\67\7\5\2\2\67;\b\5"+
		"\1\289\7\6\2\29;\b\5\1\2:\66\3\2\2\2:8\3\2\2\2;\t\3\2\2\2<>\b\6\1\2=?"+
		"\5\f\7\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\13\3\2\2\2BH\5\16\b"+
		"\2CH\5\20\t\2DH\5\22\n\2EH\5\24\13\2FH\5\26\f\2GB\3\2\2\2GC\3\2\2\2GD"+
		"\3\2\2\2GE\3\2\2\2GF\3\2\2\2H\r\3\2\2\2IJ\7\7\2\2JK\7\f\2\2KL\7\27\2\2"+
		"LM\b\b\1\2MN\7\r\2\2NO\7\16\2\2OP\b\b\1\2P\17\3\2\2\2QR\7\b\2\2RS\7\f"+
		"\2\2SX\b\t\1\2TU\7\27\2\2UY\b\t\1\2VY\5\30\r\2WY\5\34\17\2XT\3\2\2\2X"+
		"V\3\2\2\2XW\3\2\2\2YZ\3\2\2\2Z[\7\r\2\2[\\\7\16\2\2\\]\b\t\1\2]\21\3\2"+
		"\2\2^_\7\27\2\2_`\b\n\1\2`a\7\21\2\2ab\b\n\1\2bi\5\34\17\2cd\7\27\2\2"+
		"de\b\n\1\2ef\7\21\2\2fg\b\n\1\2gi\5\30\r\2h^\3\2\2\2hc\3\2\2\2ij\3\2\2"+
		"\2jk\7\16\2\2kl\b\n\1\2l\23\3\2\2\2mn\7\t\2\2no\7\f\2\2op\7\27\2\2pq\b"+
		"\13\1\2qr\7\26\2\2rv\b\13\1\2st\7\27\2\2tw\b\13\1\2uw\7\30\2\2vs\3\2\2"+
		"\2vu\3\2\2\2wx\3\2\2\2xy\b\13\1\2yz\7\r\2\2z{\7\23\2\2{}\b\13\1\2|~\5"+
		"\f\7\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0082\7\24\2\2\u0082\u008e\b\13\1\2\u0083\u0084\7\n\2\2"+
		"\u0084\u0085\7\23\2\2\u0085\u0087\b\13\1\2\u0086\u0088\5\f\7\2\u0087\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008c\7\24\2\2\u008c\u008d\b\13\1\2\u008d\u008f\3"+
		"\2\2\2\u008e\u0083\3\2\2\2\u008e\u008f\3\2\2\2\u008f\25\3\2\2\2\u0090"+
		"\u0091\7\13\2\2\u0091\u0092\7\f\2\2\u0092\u0093\7\27\2\2\u0093\u0094\b"+
		"\f\1\2\u0094\u0095\7\26\2\2\u0095\u0099\b\f\1\2\u0096\u0097\7\27\2\2\u0097"+
		"\u009a\b\f\1\2\u0098\u009a\7\30\2\2\u0099\u0096\3\2\2\2\u0099\u0098\3"+
		"\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\f\1\2\u009c\u009d\7\r\2\2\u009d"+
		"\u009e\7\23\2\2\u009e\u00a0\b\f\1\2\u009f\u00a1\5\f\7\2\u00a0\u009f\3"+
		"\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a5\7\24\2\2\u00a5\u00a6\b\f\1\2\u00a6\27\3\2\2"+
		"\2\u00a7\u00ad\5\32\16\2\u00a8\u00a9\t\2\2\2\u00a9\u00aa\b\r\1\2\u00aa"+
		"\u00ac\5\32\16\2\u00ab\u00a8\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3"+
		"\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ca\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0"+
		"\u00b1\5\32\16\2\u00b1\u00b2\t\2\2\2\u00b2\u00b3\b\r\1\2\u00b3\u00b5\3"+
		"\2\2\2\u00b4\u00b0\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7\f"+
		"\2\2\u00ba\u00bb\b\r\1\2\u00bb\u00bc\5\30\r\2\u00bc\u00bd\7\r\2\2\u00bd"+
		"\u00c6\b\r\1\2\u00be\u00bf\t\2\2\2\u00bf\u00c2\b\r\1\2\u00c0\u00c3\5\32"+
		"\16\2\u00c1\u00c3\5\30\r\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c5\3\2\2\2\u00c4\u00be\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9"+
		"\u00a7\3\2\2\2\u00c9\u00b6\3\2\2\2\u00ca\31\3\2\2\2\u00cb\u00cc\7\27\2"+
		"\2\u00cc\u00cf\b\16\1\2\u00cd\u00cf\7\30\2\2\u00ce\u00cb\3\2\2\2\u00ce"+
		"\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\16\1\2\u00d1\33\3\2\2"+
		"\2\u00d2\u00d3\7\27\2\2\u00d3\u00d6\b\17\1\2\u00d4\u00d6\7\31\2\2\u00d5"+
		"\u00d2\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00dd\b\17"+
		"\1\2\u00d8\u00d9\7\20\2\2\u00d9\u00da\b\17\1\2\u00da\u00dc\5\34\17\2\u00db"+
		"\u00d8\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2"+
		"\2\2\u00de\35\3\2\2\2\u00df\u00dd\3\2\2\2\27\'\61:@GXhv\177\u0089\u008e"+
		"\u0099\u00a2\u00ad\u00b6\u00c2\u00c6\u00c9\u00ce\u00d5\u00dd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}