package isilanguage.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import isilanguage.parser.IsiLangParser;
import isilanguage.parser.IsiLangLexer;
import isilanguage.exceptions.IsiSemanticException;;

/* Classe responsável por criar a interação com o usuário
 * instanciando o parser e apontando para o arquivo fonte: input.isi
 */

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			//leio o arquivo "input.isi" e isso é a entrada para o Analisador Léxico
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			//crio um "fluxo de tokens" para passar para o Parser
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			//crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			parser.generateCode();
			
			parser.variaveisUtilizadasNaoAtribuidas();
			
			parser.variaveisNaoUtilizadas();
		}
		catch (IsiSemanticException ex){
			System.err.println("Semantic error: " + ex.getMessage());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
