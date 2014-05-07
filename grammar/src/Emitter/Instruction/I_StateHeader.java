package Emitter.Instruction;

import Core.Mittelwerk;

public class I_StateHeader extends Instruction{
	private String name;
	
	public I_StateHeader(String n){
		this.name = n;
	}
	
	public String convert(){
		String r = "";
		r += String.format("void %s::%s", Mittelwerk.e.getVesselName(), name);
		
		r += "(double simt, double simdt, double mjd){\n";
		
		return r;
	}	
}