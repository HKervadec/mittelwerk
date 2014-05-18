package Manager;

import Utils.Type;
import Ident.Ident;
import Ident.IdentVar;
import Core.Mittelwerk;
import Emitter.Instruction.I_Simple;

public class VariableManager{
	private String current_dcl;
	private Type current_type;
	
	public VariableManager(){
	}

	public void memorizeType(Type t){
		this.current_dcl = "\t" + t + " ";
		
		this.current_type = t;
	}
	
    /**
     * I should do some checks, later
     * 
     * @param n 
     */	
	public void newVar(String n){
		this.current_dcl += n;
		Mittelwerk.i_m.addLocal(n, this.buildIdVar(n));
	}
	
	public void newVar2(String n){
		this.current_dcl += ", ";
		this.newVar(n);
	}
	
	
	private Ident buildIdVar(String n){
		return new IdentVar(n, this.current_type);
	}
	
	public void commitDcl(){
		this.current_dcl += ";\n";
		Mittelwerk.e.add(new I_Simple(this.current_dcl));
	}
}