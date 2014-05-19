package Emitter.Instruction;

import Core.Mittelwerk;

public class I_Header extends Instruction{
	
	public I_Header(){
	}
	
	public String convert(){
		return String.format("#include \"%s.h\"\n\n", 
			Mittelwerk.e.getVesselName());
	}
}