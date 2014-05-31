package Manager;

import java.util.ArrayList;

import Core.Mittelwerk;
import Emitter.Emitter;
import Emitter.Instruction.I_Simple;
import Ident.IdentThruster;

/**
 *  @file ApiGet.java
 *  @brief Class managing the ApiGet functions.
 *  @details An ApiGet function is a function defined in the orbiter api.
 *  Often, is it complex to use, or at least, to complex for our goal.
 *  
 *  Almost getter has a different behaviour, making it more difficult to 
 *  implement. Thus, we have a function per getter.
 *  
 *  The process of generating the code is simple:
 *  	- Save the informations destinations.
 *  	- Save the getter used.
 *  	- Save the optional arguments.
 *  	- And generate the fitting c++ code.
 */
public class ApiGet{
	private ArrayList<String> destList;
	private String getter;
	private ArrayList<String> argList;
	
	
	public ApiGet(){
		this.reset();
	}
	
	/**
	 *  @brief Empty the destList and the argList
	 *  
	 *  @details In facts, it just recreate them. 
	 *  
	 *  I'M A JAVA PROGRAMMER AND I DON'T KNOW WHAT I'M DOING
	 */
	public void reset(){
		this.destList = new ArrayList<String>();
		this.argList = new ArrayList<String>();
	}
	
	
	/**
	 *  @brief Add a destination to the list.
	 *  
	 *  @param [in] id The destination
	 *  
	 *  @details Also call the checkIdent function.
	 */
	public void addDest(String id){
		this.checkIdent(id);
		
		this.destList.add(id);
	}
	
	/**
	 *  @brief Set the getter that will be used.
	 *  
	 *  @param [in] name The getter name
	 */
	public void setGetter(String name){
		this.getter = name;
	}
	
	/**
	 *  @brief Add an argument to the list.
	 *  
	 *  @param [in] str The argument.
	 *  
	 *  @details Does not any verification
	 */
	public void addArg(String str){
		this.argList.add(str);
	}
	
	
	/**
	 *  @brief Check if an ident is a valid destination. Print an error (or 
	 *  more) if something suspicious is found.
	 *  
	 *  @param [in] id The id to be checked.
	 *  
	 *  @details Currently, just check is it a local destination.
	 */
	private void checkIdent(String id){
		if(Mittelwerk.i_m.getLocalIdent(id) == null){
			Mittelwerk.err_m.printError(5, id);
		}
	}
	
	/**
	 *  @brief Generate the c++ and add it to the Emitter
	 *  
	 *  
	 *  @details It is in fact a big switch which call the right function.
	 *  Print an error if the getter is unknown.
	 */
	public void commit(){
		switch(this.getter){
		case "oapiGetAltitude":
			this.getAltitude();
			break;
		case "GetThrusterLevel":
			this.getThrusterLevel();
			break;
		case "oapiGetShipAirspeedVector":
			this.getSpeed();
			break;
		default:
			Mittelwerk.err_m.printError(8, this.getter);
			break;
		}
	}
	
	/**
	 *  @brief A function who check the size of both destinations and arguments
	 *  and print errors if needed.
	 *  
	 *  @param [in] destGoal The expected size of the destinations
	 *  @param [in] argGoal  The expected size of the arguments
	 *  
	 *  @details It will compare the size of the destList to the goal provided,
	 *  and will print error (with the ErrorManager) if it differ from it.
	 *  Same with argList.	 
	 */
	private void checkSizes(int destGoal, int argGoal){
		int destSize = this.destList.size();
		
		if(destSize > destGoal){
			Mittelwerk.err_m.printError(6, this.getter);
		}else if(destSize < destGoal){
			Mittelwerk.err_m.printError(9, this.getter);		
		}
		
		
		int argSize = this.argList.size();
		
		if(argSize > argGoal){
			Mittelwerk.err_m.printError(7, this.getter);
		}else if(argGoal < argSize){
			Mittelwerk.err_m.printError(10, this.getter);		
		}
	}
	
	
	/**
	 *  @brief Generate the code to get the vessel altitude.
	 */
	private void getAltitude(){
		/* oapiGetAltitude(GetHandle(), &current_altitude) */
		this.checkSizes(1, 0);
	
		String result = this.getter;
		
		result += "(";
		result += "GetHandle()";
		result += ",";
		result += "&" + this.destList.get(0);
		result += ")";
		result += ";";
		result += "\n";
		
		Mittelwerk.e.add(new I_Simple(result));
	}
	
	/**
	 *  @brief Generate the code to get the level of a thruster.
	 */
	private void getThrusterLevel(){
		/* tl0 = GetThrusterLevel(th_hover[0]); */
		this.checkSizes(1, 1);
		
		String result = this.destList.get(0);
		result += " = ";
		result += this.getter;
		result += "(";
		
		result += ((IdentThruster) Mittelwerk.i_m.getGlobalIdent(this.argList.get(0))).getValue();
		
		result += ")";
		result += ";";
		result += "\n";
		
		Mittelwerk.e.add(new I_Simple(result));
	}
	
	/**
	 *  @brief Generate the code to get the vessel speed.
	 */
	private void getSpeed(){
		/* oapiGetShipAirspeedVector(GetHandle(), &tmp_vector);
		double x = tmp_vector.x;
		double y = tmp_vector.y;
		double z = tmp_vector.z; */
		this.checkSizes(3, 0);
		
		String result = this.getter;
		result += "(GetHandle(), &tmp_vector);\n";
		Mittelwerk.e.add(new I_Simple(result));
		
		result = String.format("%s = tmp_vector.x;\n", this.destList.get(0));
		Mittelwerk.e.add(new I_Simple(result));
		
		result = String.format("%s = tmp_vector.y;\n", this.destList.get(1));
		Mittelwerk.e.add(new I_Simple(result));
		
		result = String.format("%s = tmp_vector.z;\n", this.destList.get(2));
		Mittelwerk.e.add(new I_Simple(result));
	}
}