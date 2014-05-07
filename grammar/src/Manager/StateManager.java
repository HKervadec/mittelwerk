package Manager;

import java.util.ArrayList;

import Ident.IdentState;
import Core.Mittelwerk;

public class StateManager{
	private int state_number;
	private ArrayList<String> state_names;
	
	public StateManager(){
		this.state_number = 0;
		this.state_names = new ArrayList<String>();
	}
	
	public void addState(String n){
		Mittelwerk.i_m.addGlobal(n, new IdentState(n, state_number++));
		this.state_names.add(n);
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
}