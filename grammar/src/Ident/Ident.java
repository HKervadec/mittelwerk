package Ident;

public abstract class Ident{
	private String name;
	
	public Ident(String n){
		this.name = n;
	}
	
	public String toString(){
		return this.name;
	}
}