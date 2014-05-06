package Manager;

import Ident.IdentState;
import Core.Mittelwerk;

public class StateManager{
	private int state_number;
	
	public StateManager(){
		this.state_number = 0;
	}
	
	public void addState(String n){
		Mittelwerk.i_m.addGlobal(n, new IdentState(n, state_number++));
	}
}