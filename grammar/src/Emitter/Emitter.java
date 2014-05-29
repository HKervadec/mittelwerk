package Emitter;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

import Emitter.Instruction.*;	

public class Emitter extends BasicEmitter{
	private EmitterHeader header;

	private String vesselName;
	
	
    /**
     * Create the emitter. The default output is hardcoded.
     * 
	 * Initialize his instructions array, and add the instructions for 
	 * the header and the postStep function.
     */
	public Emitter(){
		this("result/Otto.cpp", "result/header.h");
	}
	
	public Emitter(String path){
		this(path, "result/ShuttleA.h");
	}
	
	public Emitter(String path, String path_header){
		super();
		
		this.header = new EmitterHeader(path_header);
		
		this.code = new ArrayList<Instruction>();
	
		this.setOutput(path);
		
		this.code.add(new I_Header());
		this.code.add(new I_PostStep());
	}
	
	
	public String getVesselName(){
		return this.vesselName;
	}
	public void setVesselName(String n){
		this.vesselName = n;
	}
	
	
	/**
	 *  @brief Add an instruction (as in the BasicEmitter), but also add 
	 *  instructions to the header if needed.
	 */
	public void add(Instruction i){
		super.add(i);
		
		if(i instanceof I_FunctionHeader){
			this.header.add(((I_FunctionHeader) i).genHeader());
		}else if(i instanceof I_StateHeader){	
			this.header.add(((I_StateHeader) i).genHeader());
		}
	}
	
	/**
	 *  @brief Basic emitting process, and launch the emitting process for the
	 *  header.
	 */
	public void emit(){
		this.header.emit();
	
		super.emit();
	}
}