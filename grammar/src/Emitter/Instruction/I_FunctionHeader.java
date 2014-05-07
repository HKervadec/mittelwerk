package Emitter.Instruction;

import java.util.ArrayList;

import Ident.IdentFunction;
import Utils.Type;
import Core.Mittelwerk;

public class I_FunctionHeader extends Instruction{
	private IdentFunction f;
	
	public I_FunctionHeader(IdentFunction f){
		this.f = f;
	}
	
	public String convert(){
		ArrayList<Type> p_t = this.f.getParamsType();
		ArrayList<String> p_n = this.f.getParamsName();
		int p_s = p_n.size();
		String v_n = Mittelwerk.e.getVesselName();
		
		String result = this.f.getType().toString() + " ";
		result += String.format("%s::%s", v_n, this.f.getName());
		
		result += "(";
		for(int i = 0 ; i < p_s ; i++){
			if(i >= 1){
				result += ", ";
			}
			
			result += String.format("%s %s", p_t.get(i), p_n.get(i));
		}
		result += "){\n";
		
		return result;
	}
}