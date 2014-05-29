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
public class EmitterHeader{
	private PrintWriter output;
	private File file;
	private ArrayList<Instruction> code;
	
	
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
		this.code = new ArrayList<Instruction>();
	
		this.setOutput(path);
	}
	
	public void setOutput(String path){
		try{
			this.file = new File(path);
			
			try{
				this.file.getParentFile().mkdirs();
			}catch(Exception e){}

			this.output = new PrintWriter(this.file, "UTF-8");
		}catch(Exception e){
			System.out.println("Errors while creating the following file: " + path);
			System.out.println(this.file);
			System.out.println(e.getMessage());
		}
	}

	public void add(Instruction i){
		this.code.add(i);
	}
	
	/************************************************************/
	/*						Emit code							*/
	/************************************************************/
	
	public void emit(){
		this.addInitialState();
		this.addPostStep();
		this.addTmpVector();
	
		for(Instruction inst : this.code){
			String line = inst.convert();
			this.output.print(line);
			// System.out.print(line);
		}
		
		this.output.close();
	}
	
	private void addInitialState(){
		int initial_state = Mittelwerk.s_m.getInitialState();
		String text = String.format("int m_state = %d;\n", initial_state);
		
		this.add(new I_Simple(text));
	}
	
	private void addPostStep(){
		this.add(new I_Simple("void postStep(double simt, double simdt, double mjd);\n"));
	}
	
	private void addTmpVector(){
		this.add(new I_Simple("VECTOR3 tmp_vector;\n"));
	}
}