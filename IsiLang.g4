grammar IsiLang;

@header{
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
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _exprEscreva;
	private String _exprID;
	private String _exprContent;
	private String _exprIf;
	private String _exprEnquanto;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> comandoEnquanto;
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	public void verificaIDNumber(String id){
		verificaID(id);
		if (((IsiVariable) symbolTable.get(id)).getType() != 0){
			throw new IsiSemanticException("Variable "+id+" is not type NUMBER");
		}
	}
	
	public void verificaIDText(String id){
		verificaID(id);
		if (((IsiVariable) symbolTable.get(id)).getType() != 1){
			throw new IsiSemanticException("Variable "+id+" is not type TEXT");
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
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  } else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  } else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdLeitura  
 		|  cmdEscrita 
 		|  cmdExpr
 		|  cmdIf
 		|  cmdEnquanto 
		;
		
cmdLeitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdEscrita	: 'escreva' 
                 AP 
                 ( ID		{ verificaID(_input.LT(-1).getText()); }
                 | NUMBER   
                 | TEXT) 	{ _exprEscreva = _input.LT(-1).getText(); }
                 FP 
                 SC
               {
               	  CommandEscrita cmd = new CommandEscrita(_exprEscreva);
               	  stack.peek().add(cmd);
               }
			;
	
			
cmdExpr		:  ( ID   { verificaIDText(_input.LT(-1).getText());
                        _exprID = _input.LT(-1).getText(); } 
                 ATTR { _exprContent = ""; } 
                 string
                 
               | ID   { verificaIDNumber(_input.LT(-1).getText());
                        _exprID = _input.LT(-1).getText(); } 
                 ATTR { _exprContent = ""; } 
                 expr
               ) 
               
               SC
               { CommandExpr cmd = new CommandExpr(_exprID, _exprContent);
               	 stack.peek().add(cmd);
               }
			;
			
			
cmdIf		:  'se' AP
                    ID    { verificaIDNumber(_input.LT(-1).getText());
                    		_exprIf = _input.LT(-1).getText(); }
                    OPREL { _exprIf += _input.LT(-1).getText(); }
                    ( ID { verificaIDNumber(_input.LT(-1).getText()); }
                    | NUMBER) {_exprIf += _input.LT(-1).getText(); }
                    FP
                    
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                    	stack.push(curThread);
                   	}
                    (cmd)+ 
                    FCH 
                    
                    { listaTrue = stack.pop(); } 
                    
                   ('senao' 
                   	 ACH
                   	 {	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	
                   	{	listaFalse = stack.pop();
                   		CommandIf cmd = new CommandIf(_exprIf, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
            
			
cmdEnquanto	:  'enquanto' AP
                    	  ID    { verificaIDNumber(_input.LT(-1).getText());
                    	  		  _exprEnquanto = _input.LT(-1).getText(); }
                    	  OPREL { _exprEnquanto += _input.LT(-1).getText(); }
                    	  ( ID { verificaIDNumber(_input.LT(-1).getText()); }
                    	  | NUMBER) { _exprEnquanto += _input.LT(-1).getText(); }
                    	  FP
                    
                    	  ACH 
                   	  	  { curThread = new ArrayList<AbstractCommand>(); 
                    		  stack.push(curThread);
                   		  }
                    	  (cmd)+ 
                    
                    	  FCH 
                    	  {
                       		  comandoEnquanto = stack.pop();
                       		  CommandEnquanto cmd = new CommandEnquanto(_exprEnquanto, comandoEnquanto);
                   			  stack.peek().add(cmd);	
                    	  } 
            ;
            			
			
expr		: termo ( OP  { _exprContent += _input.LT(-1).getText(); }
	            	  termo )*
			;
			
termo		: ( ID { verificaIDNumber(_input.LT(-1).getText()); } 
              | NUMBER
              ) { _exprContent += _input.LT(-1).getText(); }
			;
			
string		: ( ID { verificaIDText(_input.LT(-1).getText()); } 
              | TEXT
              ) { _exprContent += _input.LT(-1).getText(); }
			;

	
AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : '='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
                 					
FCH  : '}'
     ;
     
ASP  : '"'
	 ;
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
TEXT	: ASP ([a-z] | [A-Z]) (PONT | ' ' | [a-z] | [A-Z] | [0-9])* ASP
		;
		
PONT	: '.' | ',' | ':' | '?' | '!' | '-' | '='
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;