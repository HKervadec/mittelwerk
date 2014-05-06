package Manager;

import java.util.HashMap;
import Ident.*;
import Utils.Type;

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
		if(!dest.containsKey(name)){
			dest.put(name, id);
			
			return true;
		}
		
		return false;
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
		return source.get(name);
	}
	
	public Ident getLocalIdent(String name){
		return this.getIdent(this.local, name);
	}
	
	public Ident getGLobalIdent(String name){
		return this.getIdent(this.global, name);
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
	
	public void saveLocalVar(Type t){
		Ident id = this.buildIdVar(t);
		
		this.addLocal(this.tmpName, id);
	}
	
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
	
	private Ident buildIdVar(Type t){
		return new IdentVar(this.tmpName, t);
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