package Ident;

public class IdentState extends Ident{
	private int number;
	
	public IdentState(String n, int number){
		super(n);
		
		this.number = number;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public String toString(){
		return super.toString() + " " + this.number;
	}
}