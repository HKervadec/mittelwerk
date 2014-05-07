package Emitter.Instruction;

import java.util.ArrayList;

import Core.Mittelwerk;

public class I_PostStep extends Instruction{
	
	public I_PostStep(){
	}
	
	public String convert(){
		String r = "";
		
		String name = Mittelwerk.e.getVesselName();
		int s_number = Mittelwerk.s_m.getStateNumber();
		ArrayList<String> s_names = Mittelwerk.s_m.getStateNames();
		
		
		r += String.format("typedef void(%s:: *StatePt)(double, double, double);\n\n"
			, name);
			
		r += String.format("void %s::postStep(double simt, double simdt, double mjd){\n"
			, name);
		
		r += "\tStatePt state_fct[] = { ";
		r += String.format("&%s::%s, \n", name, s_names.get(0));
		for(int i = 1 ; i < (s_number - 1) ; i++){
			r += String.format("\t\t&%s::%s,\n", name, s_names.get(i));
		}
		r += String.format("\t\t&%s::%s };\n", name, s_names.get(s_number - 1));
		
		r += "\t(this->*state_fct[m_state])(simt, simdt, mjd);\n";
		r += "}\n\n";
	
		return r;
	}
}