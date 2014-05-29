package Manager;

import java.util.ArrayList;

import Core.Mittelwerk;
import Emitter.Emitter;
import Emitter.Instruction.I_Simple;
import Ident.IdentThruster;


public class ApiGet{
	private ArrayList<String> destList;
	private String getter;
	private ArrayList<String> argList;
	
	
	public ApiGet(){
		this.reset();
	}
	
	public void reset(){
		this.destList = new ArrayList<String>();
		this.argList = new ArrayList<String>();
	}
	
	
	public void addDest(String id){
		this.checkLocal(id);
		
		this.destList.add(id);
	}
	
	public void setGetter(String name){
		this.getter = name;
	}
	
	public void addArg(String str){
		this.argList.add(str);
	}
	
	
	
	private void checkLocal(String id){
		if(Mittelwerk.i_m.getLocalIdent(id) == null){
			Mittelwerk.err_m.printError(5, id);
		}
	}
	
	public void commit(){
		String result = "";
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
	
	private void getAltitude(){
		/* oapiGetAltitude(GetHandle(), &current_altitude) */
		if(destList.size() > 1){
			Mittelwerk.err_m.printError(6, this.getter);
		}
		
		if(argList.size() > 0){
			Mittelwerk.err_m.printError(7, this.getter);
		}
	
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
	
	private void getThrusterLevel(){
		/* tl0 = GetThrusterLevel(th_hover[0]); */
		if(destList.size() > 1){
			Mittelwerk.err_m.printError(6, this.getter);
		}
	
		if(argList.size() > 1){
			Mittelwerk.err_m.printError(7, this.getter);
		}
		
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
	
	private void getSpeed(){
		/* oapiGetShipAirspeedVector(GetHandle(), &tmp_vector);
		double x = tmp_vector.x;
		double y = tmp_vector.y;
		double z = tmp_vector.z; */
		if(destList.size() > 3){
			Mittelwerk.err_m.printError(6, this.getter);
		}
	
		if(argList.size() > 0){
			Mittelwerk.err_m.printError(7, this.getter);
		}
		
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