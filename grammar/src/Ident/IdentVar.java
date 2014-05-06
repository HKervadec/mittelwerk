package Ident;

import Utils.Type;

public class IdentVar extends Ident{
	private Type t;
	
	public IdentVar(String n, Type t){
		super(n);
		
		this.t = t;
	}
	
	public String toString(){
		return this.t + " " + super.toString();
	}
}