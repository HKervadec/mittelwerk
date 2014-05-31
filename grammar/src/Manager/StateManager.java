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



/**
 *  @file StateManager.java
 *  @brief The state manager mission is to add new states, setting their id,
 *  local var and keep track of the initial state.
 *  
 *  It will also do some verifications, about the use of the tokens START, FINAL
 *  and GOTOGOTO. 
 */
public class StateManager{
	private int state_number;
	private ArrayList<String> state_names;
	
	private int initial_state;
	private boolean state_initialized;
	
	public StateManager(){
		this.state_number = 0;
		this.state_names = new ArrayList<String>();
		
		this.initial_state = 0;
		this.state_initialized = false;
	}
	
	
	/**
	 *  @brief Add a state to the compiler
	 *  
	 *  @param [in] n The state name
	 *  
	 *  @details This function will:
	 *  	- Set the state id
	 *  	- Increment the state number
	 *  	- Add the variables t, dt, and mjd to the local ident of the state.
	 *  	- Add a state header instruction to the emitter.
	 */
	public void addState(String n){
		Mittelwerk.i_m.addGlobal(n, new IdentState(n, state_number++));
		this.state_names.add(n);
		
		Mittelwerk.i_m.addLocal("t", new IdentVar("simt", Type.DOUBLE));
		Mittelwerk.i_m.addLocal("dt", new IdentVar("simdt", Type.DOUBLE));
		Mittelwerk.i_m.addLocal("mjd", new IdentVar("mjd", Type.DOUBLE));
		
		
		Mittelwerk.e.add(new I_StateHeader(n));
	}
	
	/**
	 *  @brief Return the total of states.
	 *  
	 *  @return The amount of states.
	 */
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
	
	
	/**
	 *  @brief Add the gotogoto instruction to the emitter
	 *  
	 *  @param [in] name The goal state
	 *  
	 *  @todo Check the gotogoto privilege.
	 */
	public void gotogoto(String name){
		Mittelwerk.e.add(new I_Goto(name));
	}
	
	
	/**
	 *  @brief Set the initial state to the last state id used.
	 *  
	 *  @details Print an error if an initial state has already be defined.
	 */
	public void setInitialState(){
		if(this.state_initialized){
			/* Initial State already initialized */
			Mittelwerk.err_m.printError(4);
		}
		
		this.initial_state = this.state_number - 1;
		this.state_initialized = true;
	}
	
	/**
	 *  @brief Return the id of the initial state
	 *  
	 *  @return The id of the initial state. If it has not been initialized, it
	 *  will return 0, which is the default initial state.
	 */
	public int getInitialState(){
		return this.initial_state;
	}
}