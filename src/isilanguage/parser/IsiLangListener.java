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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLangParser}.
 */
public interface IsiLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(IsiLangParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(IsiLangParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(IsiLangParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(IsiLangParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(IsiLangParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(IsiLangParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdExpr(IsiLangParser.CmdExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdExpr(IsiLangParser.CmdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void enterCmdIf(IsiLangParser.CmdIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void exitCmdIf(IsiLangParser.CmdIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(IsiLangParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(IsiLangParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(IsiLangParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(IsiLangParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#attPara}.
	 * @param ctx the parse tree
	 */
	void enterAttPara(IsiLangParser.AttParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#attPara}.
	 * @param ctx the parse tree
	 */
	void exitAttPara(IsiLangParser.AttParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiLangParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(IsiLangParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(IsiLangParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(IsiLangParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(IsiLangParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#notExpr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(IsiLangParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#notExpr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(IsiLangParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolRel}.
	 * @param ctx the parse tree
	 */
	void enterBoolRel(IsiLangParser.BoolRelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolRel}.
	 * @param ctx the parse tree
	 */
	void exitBoolRel(IsiLangParser.BoolRelContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolNumRel}.
	 * @param ctx the parse tree
	 */
	void enterBoolNumRel(IsiLangParser.BoolNumRelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolNumRel}.
	 * @param ctx the parse tree
	 */
	void exitBoolNumRel(IsiLangParser.BoolNumRelContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolStringRel}.
	 * @param ctx the parse tree
	 */
	void enterBoolStringRel(IsiLangParser.BoolStringRelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolStringRel}.
	 * @param ctx the parse tree
	 */
	void exitBoolStringRel(IsiLangParser.BoolStringRelContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolBoolRel}.
	 * @param ctx the parse tree
	 */
	void enterBoolBoolRel(IsiLangParser.BoolBoolRelContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolBoolRel}.
	 * @param ctx the parse tree
	 */
	void exitBoolBoolRel(IsiLangParser.BoolBoolRelContext ctx);
}