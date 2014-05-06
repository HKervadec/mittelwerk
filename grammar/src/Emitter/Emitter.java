package Emitter;

// import java.util.ArrayList;
import java.io.PrintWriter;

public class Emitter{
	private String vesselName;
	private PrintWriter output;
	
	
	public Emitter(){
		try{
			this.output = new PrintWriter("Otto.cpp", "UTF-8");
		}catch(Exception e){}
	}
	
	public String getVesselName(){
		return this.vesselName;
	}
	public void setVesselName(String n){
		this.vesselName = n;
	}
}