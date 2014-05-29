package Ident;

/**
 *  @file IdentThruster.java
 *  @brief Ident for a thruster. It will just save the alias defined by the 
 *  user.
 */
public class IdentThruster extends Ident{
	private String s_value;
	
	public IdentThruster(String n, String v){
		super(n);
		
		this.s_value = v;
	}
	
	public String toString(){
		return super.toString() + " " + this.s_value;
	}
	
	/**
	 *  @return The right text to access the thruster.
	 */
	public String getValue(){
		return this.s_value;
	}
}