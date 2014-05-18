package Manager;

import Ident.*;
import Core.Mittelwerk;
import Emitter.Instruction.I_Simple;

public class ExpressionManager{
	public String current_expression;
	
	public ExpressionManager(){
		this.init();
	}
	
	public void init(){
		this.current_expression = "";
	}
	
	public void addStr(String s){
		this.current_expression += s + " ";
	}
	
	public void addInt(int i){
		this.addStr("" + i);
	}
	
	public void addDouble(double d){
		this.addStr("" + d);
	}
	
	
	public void addIdent(String n){
		Ident id = Mittelwerk.i_m.getLocalIdent(n);
		
		if(id != null){
			this.addStr(id.getName()); // why?
		}else{
			id = Mittelwerk.i_m.getGLobalIdent(n);
			
			if(id instanceof IdentThruster){
				this.addStr(((IdentThruster) id).getValue());
			}
		}
	}
	
	public void commit(){
		System.out.println(this.current_expression);
		Mittelwerk.e.add(new I_Simple(this.current_expression));
	}
}