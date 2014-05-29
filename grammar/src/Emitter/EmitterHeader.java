package Emitter;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

import Emitter.Instruction.*;
import Core.Mittelwerk;
import Manager.StateManager;


/**
 * Generate the instructions for the vessel header
 */
public class EmitterHeader extends BasicEmitter{
    /**
     * Create the emitter. The default output is hardcoded.
     * 
	 * Initialize his instructions array, and add the instructions for 
	 * the header and the postStep function.
     */
	public EmitterHeader(){
		this("result/ShuttleA.h");
	}
	
	
	public EmitterHeader(String path){
		super();
		
		this.code = new ArrayList<Instruction>();
	
		this.setOutput(path);
		
		this.addInitialState();	
		this.addTmpVector();
		this.addPostStep();
	}
	

	private void addInitialState(){
		this.add(new I_InitialState());
	}
	
	private void addPostStep(){
		this.add(new I_Simple("void postStep(double simt, double simdt, double mjd);\n"));
	}
	
	private void addTmpVector(){
		this.add(new I_Simple("VECTOR3 tmp_vector;\n"));
	}
}