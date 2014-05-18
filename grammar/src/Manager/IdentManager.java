package Manager;

import java.util.HashMap;
import java.util.ArrayList;

import Ident.*;
import Utils.Type;
import Core.Mittelwerk;
import Error.ErrorManager;

public class IdentManager{
	private HashMap<String, Ident> local;
	private HashMap<String, Ident> global;
	
	private String tmpName;
	


	public IdentManager(){
		this.local = new HashMap<String, Ident>();
		this.global = new HashMap<String, Ident>();
	}
	
	
	/************************************************************/
	/*						Manage hashmap						*/
	/************************************************************/
	private boolean add(HashMap<String, Ident> dest, String name, Ident id){
		if(this.exist(name)){
			Mittelwerk.err_m.printError(2, name);
			
			return false;
		}
		
		dest.put(name, id);
		
		return true;
	}
	
	public boolean addLocal(String name, Ident id){
		return this.add(this.local, name, id);
	}
	
	public boolean addGlobal(String name, Ident id){
		return this.add(this.global, name, id);
	}
	
	
	public void clearLocal(){
		// System.out.println(this);
	
		this.local = new HashMap<String, Ident>();
	}
	
	
	private Ident getIdent(HashMap<String, Ident> source, String name){
		Ident result = source.get(name);
		
		if(result == null){
			Mittelwerk.err_m.printError(3, name);
		}
		
		return result;
	}
	
	public Ident getLocalIdent(String name){
		return this.getIdent(this.local, name);
	}
	
	public Ident getGLobalIdent(String name){
		return this.getIdent(this.global, name);
	}
	
	public boolean exist(String name){
		return this.local.get(name) != null ||
				this.global.get(name) != null;
	}
	
	
	/************************************************************/
	/*						Manage ident						*/
	/************************************************************/
	public void memorizeName(String name){
		this.tmpName = name;
	}
	
	public void saveLocal(Type t, String value){
		Ident id = this.buildId(t, value);
		
		this.addLocal(this.tmpName, id);
	}
	
	// public void saveLocalVar(Type t){
		// Ident id = this.buildIdVar(t);
		
		// this.addLocal(this.tmpName, id);
	// }
	
	public void saveGlobal(Type t, String value){
		Ident id = this.buildId(t, value);
		
		this.addGlobal(this.tmpName, id);
	}
	
	private Ident buildId(Type t, String value){
		switch(t){
		case THRUSTER:
			return new IdentThruster(this.tmpName, value);
		default:
			return null;
		}
	}
	


	
	
	
	/************************************************************/
	/*						Utilities							*/
	/************************************************************/
	public String toString(){
		String result = "Global:\n";
		
		for(Ident id : this.global.values()){
			result += "\t" + id + "\n";
		}
		
		result += "\nLocal:\n";
		
		for(Ident id : this.local.values()){
			result += "\t" + id + "\n";
		}
		
		return result + "\n";
	}
}