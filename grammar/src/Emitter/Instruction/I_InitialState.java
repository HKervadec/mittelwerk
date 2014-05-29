package Emitter.Instruction;

import Core.Mittelwerk;
import Manager.StateManager;

/**
 *  @file I_InitialState.java
 *  @brief Instruction to initialize the current state to the right value.
 *  Retrieve the initial value saved by the StateManager.
 */
public class I_InitialState extends Instruction{
	public String convert(){
		int initial_state = Mittelwerk.s_m.getInitialState();
		return String.format("int m_state = %d;\n", initial_state);
	}
}