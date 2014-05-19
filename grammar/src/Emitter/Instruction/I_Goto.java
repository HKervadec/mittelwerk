package Emitter.Instruction;

import Core.Mittelwerk;
import Manager.IdentManager;
import Ident.Ident;
import Ident.IdentState;

public class I_Goto extends Instruction{
	private String dest;
	
	
	public I_Goto(String dest){
		this.dest = dest;
	}
	
	public String convert(){
		Ident dest = Mittelwerk.i_m.getGlobalIdent(this.dest);
		
		return "m_state = " + ((IdentState)dest).getNumber() + ";\n";
	}
}	