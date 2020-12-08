package isilanguage.ast;

import java.util.ArrayList;

public class CommandEnquanto extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> comando;
	
	public CommandEnquanto(String condition, ArrayList<AbstractCommand> comando) {
		this.condition = condition;
		this.comando   = comando;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: comando) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", comando=" + comando
				+ "]";
	}
	
	

}
