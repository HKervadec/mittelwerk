package Manager;

import Core.Mittelwerk;
import Utils.Type;
import Ident.*;
import Emitter.Instruction.I_FunctionHeader;

public class FunctionManager{
	private Type current_type;
	private String current_name;
	
	private Type tmp_t;
	
	private IdentFunction current_fct;
	
	public FunctionManager(){
		this.maxMin();
	}
	
	private void maxMin(){
		this.setCurrentType(Type.DOUBLE);
		this.setCurrentName("max");
		this.createFunction();
		this.addParamType(Type.DOUBLE);
		this.addParamType(Type.DOUBLE);
		Mittelwerk.i_m.addGlobal(this.current_name, this.current_fct);
		
		this.setCurrentType(Type.DOUBLE);
		this.setCurrentName("min");
		this.createFunction();
		this.addParamType(Type.DOUBLE);
		this.addParamType(Type.DOUBLE);
		Mittelwerk.i_m.addGlobal(this.current_name, this.current_fct);
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
		this.tmp_t = t;
	
		this.current_fct.addParamType(t);
	}
	
	public void addParamName(String n){
		this.current_fct.addParamName(n);
		
		Mittelwerk.i_m.addLocal(n, new IdentVar(n, this.tmp_t));
	}
	
	public void commitFunction(){
		Mittelwerk.i_m.addGlobal(this.current_name, this.current_fct);
		Mittelwerk.e.add(new I_FunctionHeader(this.current_fct));
	}
}