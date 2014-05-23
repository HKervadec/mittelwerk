package Manager;

import java.util.HashMap;
import java.util.ArrayList;

import Ident.*;
import Utils.Type;
import Core.Mittelwerk;
import Error.ErrorManager;


/**
 * The IdentManager allow to remember the names of functions, variables, etc.
 * We can access them via their name, and it'll return an identifier, containing
 * every usefull information
 * 
 * It has two separate hashmap, a global one, and a local one. Basically, the
 * local one is cleaned at the beginning of every function/state definition.
 *
 * See the Ident documentation for more informations about it.
 */
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
	
    /**
	 * Private fonction used to save an indentifier into a specific localtion.
	 * If the ident already exist, it won't be saved
	 *
	 *	NEED TO IMPLEMENTED ERRORS
	 *
	 * @param dest The destination : local or global.
     * @param name The name of the identifier, the one we will use later to 
	 * access it.
     * @param id The identifier itself.
     * @return boolean True if the param has been successfully added, false 
	 * otherwise
	 * @see addLocal
	 * @see addGlobal
     */
	private boolean add(HashMap<String, Ident> dest, String name, Ident id){
		if(this.exist(name)){
			Mittelwerk.err_m.printError(2, name);
			
			return false;
		}
		
		dest.put(name, id);
		
		return true;
	}
	
    /**
     * Add an id into the local hashmap. Return false if the identifier 
	 * already exist.
     * 
     * @param name name of the Ident
     * @param id The ident itself
     * @return boolean True is sucess, false otherwise
     */
	public boolean addLocal(String name, Ident id){
		return this.add(this.local, name, id);
	}
	
    /**
     * Add an id into the global hashmap. Return false if the identifier 
	 * already exist.
     * 
     * @param name name of the Ident
     * @param id The ident itself
     * @return boolean True is sucess, false otherwise
     */
	public boolean addGlobal(String name, Ident id){
		return this.add(this.global, name, id);
	}
	
	
	
    /**
     * Clear the local hashmap.
	 *
     * It should be done at the beginning of a function/state. 
     */
	public void clearLocal(){
		// System.out.println(this);
	
		this.local = new HashMap<String, Ident>();
	}
	
	
    /**
     * Return an ident, by looking into the specified source.
     *
	 * If not found, an error (id: 3) will be called with the error manager.
	 *
	 * @param source Local or global
     * @param name Name of the ident
     * @return Ident The Ident, null if not found.
     */
	private Ident getIdent(HashMap<String, Ident> source, String name){
		Ident result = source.get(name);
		
		if(result == null){
			Mittelwerk.err_m.printError(3, name);
		}
		
		return result;
	}
	
	
	
    /**
     * Return a local Ident, searching with the name.
     * 
     * @param name Name of the Ident
     * @return Ident The ident, null if not found.
	 * @see getIdent
     */
	public Ident getLocalIdent(String name){
		return this.getIdent(this.local, name);
	}
	
	/**
     * Return a global Ident, searching with the name.
     * 
     * @param name Name of the Ident
     * @return Ident The ident, null if not found.
	 * @see getIdent
     */
	public Ident getGlobalIdent(String name){
		return this.getIdent(this.global, name);
	}
	
	
	
    /**
     * Test if an ident exist, looking by name into the local and global hashmap.
     * 
     * @param name The ident name
     * @return boolean True if found in local and/or global, false otherwise.
     */
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