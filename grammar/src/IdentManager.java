import java.util.HashMap;

public class IdentManager{
	private HashMap<String, Ident> local;
	private HashMap<String, Ident> global;


	public IdentManager(){
		this.local = new HashMap<String, Ident>();
		this.global = new HashMap<String, Ident>();
	}
}