package Ident;

public class IdentThruster extends Ident{
	private String s_value;
	
	public IdentThruster(String n, String v){
		super(n);
		
		this.s_value = v;
	}
	
	public String toString(){
		return super.toString() + " " + this.s_value;
	}
}