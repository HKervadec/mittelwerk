package Manager;

import Core.Mittelwerk;
import Utils.Type;
import Ident.*;
import Emitter.Instruction.I_FunctionHeader;

public class FunctionManager{
	private Type current_type;
	private String current_name;
	
	private IdentFunction current_fct;
	
	public FunctionManager(){
		
	}
	
	
	public void setCurrentType(Type t){
		this.current_type = t;
	}
	
	public void setCurrentName(String n){
		this.current_name = n;
	}
	
	public void createFunction(){
		this.current_fct = new IdentFunction(this.current_name, this.current_type);
	}
	
	public void addParamType(Type t){
		this.current_fct.addParamType(t);
	}
	
	public void addParamName(String n){
		this.current_fct.addParamName(n);
	}
	
	public void commitFunction(){
		Mittelwerk.i_m.addGlobal(this.current_name, this.current_fct);
		Mittelwerk.e.add(new I_FunctionHeader(this.current_fct));
	}
}