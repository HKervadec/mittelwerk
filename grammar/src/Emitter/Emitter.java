package Emitter;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

import Emitter.Instruction.*;	


/**
 * This class is just a big list of instructions.
 * They will be translated into c++ at the end of the parsing process, using
 * their convert() function.
 * 
 * Currently, it does not have a function to change the default output.
 */
public class Emitter{
	private String vesselName;
	private PrintWriter output;
	private ArrayList<Instruction> code;
	
	
    /**
     * Create the emitter. The default output is hardcoded.
     * 
	 * Initialize his instructions array, and add the instructions for 
	 * the header and the postStep function.
     */
	public Emitter(){
		this("result/Otto.cpp");
	}
	
	
	public Emitter(String path){
		this.code = new ArrayList<Instruction>();
	
		this.setOutput(path);
		
		this.code.add(new I_Header());
		this.code.add(new I_PostStep());
	}
	
	public void setOutput(String path){
		try{
			File file = new File(path);
			file.getParentFile().mkdirs();
			
			this.output = new PrintWriter(file, "UTF-8");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("test");
		}
	}
	
	
	public String getVesselName(){
		return this.vesselName;
	}
	public void setVesselName(String n){
		this.vesselName = n;
	}
	
	
	
	public void add(Instruction i){
		this.code.add(i);
	}
	
	/************************************************************/
	/*						Emit code							*/
	/************************************************************/
	
	public void emit(){
		for(Instruction inst : this.code){
			String line = inst.convert();
			this.output.print(line);
			// System.out.print(line);
		}
		
		this.output.close();
	}
}