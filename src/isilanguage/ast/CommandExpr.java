package isilanguage.ast;

import isilanguage.datastructures.IsiVariable;

public class CommandExpr extends AbstractCommand{

	private String id;
	private String expr;
	
	public CommandExpr(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return id + " = "+expr+";";
	}
	@Override
	public String toString() {
		return "CommandExpr [id=" + id + ", expr=" + expr + "]";
	}
	
	

}
