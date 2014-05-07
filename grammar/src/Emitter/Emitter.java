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
		
	}
	
	public String getVesselName(){
		return this.vesselName;
	}
	public void setVesselName(String n){
		this.vesselName = n;
	}
	
	/************************************************************/
	/*						Emit code							*/
	/************************************************************/
	
	public void emit(){
		this.header();
		
		for(Instruction inst : this.code){
			String line = inst.convert();
			this.output.println(line);
			System.out.println(line);
		}
		
		this.output.close();
	}
	
	
	
	
	/************************************************************/
	/*						Hardcoded c++						*/
	/*					That's pretty shitty					*/
	/************************************************************/
	private void header(){
		String head = "#include \"" + this.vesselName + ".h\"";
		this.code.add(new InstructionSimple(head));
	}
}