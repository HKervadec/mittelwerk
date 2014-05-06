import java.util.HashMap;

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
	private boolean add(HashMap dest, String name, Ident id){
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