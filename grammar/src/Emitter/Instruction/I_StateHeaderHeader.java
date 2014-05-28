package Emitter.Instruction;

import Core.Mittelwerk;

public class I_StateHeaderHeader extends Instruction{
	private String name;
	
	public I_StateHeaderHeader(String n){
		this.name = n;
	}
	

	public String convert(){
		String r = "";
		r += String.format("void %s", name);
		
		r += "(double simt, double simdt, double mjd);\n";
		
		return r;
	}	
}