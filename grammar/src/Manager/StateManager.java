package Manager;

import java.util.ArrayList;

import Ident.IdentState;
import Ident.IdentVar;
import Ident.Ident;
import Core.Mittelwerk;
import Emitter.Instruction.I_StateHeader;
import Emitter.Instruction.I_Goto;
import Emitter.Emitter;
import Utils.Type;

public class StateManager{
	private int state_number;
	private ArrayList<String> state_names;
	
	private int initial_state = 0;
	private boolean state_initialized = false;
	
	public StateManager(){
		this.state_number = 0;
		this.state_names = new ArrayList<String>();
	}
	
	public void addState(String n){
		Mittelwerk.i_m.addGlobal(n, new IdentState(n, state_number++));
		this.state_names.add(n);
		
		Mittelwerk.i_m.addLocal("t", new IdentVar("simt", Type.DOUBLE));
		Mittelwerk.i_m.addLocal("dt", new IdentVar("simdt", Type.DOUBLE));
		Mittelwerk.i_m.addLocal("mjd", new IdentVar("mjd", Type.DOUBLE));
		
		
		Mittelwerk.e.add(new I_StateHeader(n));
	}
	
	public int getStateNumber(){
		return this.state_number;
	}
	

	/**
	* @return return a list containing the states names,
	* sorted by their number.
	*/
	public ArrayList<String> getStateNames(){
		return this.state_names;
	}
	
	
	public void gotogoto(String name){
		Mittelwerk.e.add(new I_Goto(name));
	}
	
	public void setInitialState(){
		if(this.state_initialized){
			/* Error Manager already initialized */
			Mittelwerk.err_m.printError(4);
		}
		
		this.initial_state = this.state_number - 1;
		this.state_initialized = true;
	}
	
	public int getInitialState(){
		return this.initial_state;
	}
}