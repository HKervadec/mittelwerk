package Emitter;

import java.util.ArrayList;
import java.io.PrintWriter;

import Emitter.Instruction.*;	

public class Emitter{
	private String vesselName;
	private PrintWriter output;
	private ArrayList<Instruction> code;
	
	
	public Emitter(){
		this.code = new ArrayList<Instruction>();
	
		try{
			this.output = new PrintWriter("result/Otto.cpp", "UTF-8");
		}catch(Exception e){}
		
		this.code.add(new I_Header());
		this.code.add(new I_PostStep());
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
		// this.header();
		
		// this.postStep();
		
		for(Instruction inst : this.code){
			String line = inst.convert();
			this.output.print(line);
			// System.out.print(line);
		}
		
		this.output.close();
	}
	
	
	
	
	/************************************************************/
	/*						Hardcoded c++						*/
	/*					That's pretty shitty					*/
	/************************************************************/
	/* private void header(){
		String head = "#include \"" + this.vesselName + ".h\"";
		this.code.add(new I_Simple(head));
		this.code.add(new I_blank(2));
	} */
}