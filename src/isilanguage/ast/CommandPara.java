package isilanguage.ast;

import java.util.ArrayList;

public class CommandPara extends AbstractCommand {
	 
		private String condition;
		private ArrayList<AbstractCommand> comando;
		private String varLoop;
		private String valInicial;
		private String passo;
		
		public CommandPara(String varLoop, String valInicial, String condition, String passo, ArrayList<AbstractCommand> comando) {
			this.condition = condition;
			this.comando   = comando;
			this.varLoop = varLoop;
			this.valInicial = valInicial;
			this.passo = passo;
		}
		
		@Override
		public String generateJavaCode() {
			// TODO Auto-generated method stub
			StringBuilder str = new StringBuilder();
			str.append("for ("+varLoop+"=" + valInicial+"; "+condition+"; " + varLoop+ "+=" + passo + ") {\n");
			for (AbstractCommand cmd: comando) {
				str.append(cmd.generateJavaCode());
				str.append("\n");
			}
			str.append("}");
			
			return str.toString();
		}
		@Override
		public String toString() {
			return "CommandPara [condition=" + condition + ", comando=" + comando + "]";
		}
		
		

	}