package Ident;

import java.util.ArrayList;
import Utils.Type;

public class IdentFunction extends Ident{
	private Type return_type;
	private ArrayList<Type> params_t;
	private ArrayList<String> params_n;
	private int param_size;
	
	public IdentFunction(String n, Type return_type){
		super(n);
		
		this.params_t = new ArrayList<Type>();
		this.params_n = new ArrayList<String>();
		this.return_type = return_type;
		this.param_size = 0;
	}
	
	public void addParamType(Type t){
		this.params_t.add(t);
		this.param_size++;
	}
	
	public void addParamName(String n){
		this.params_n.add(n);
	}
	
	
	/************************************************************/
	/*							Getters							*/
	/************************************************************/
	public Type getType(){
		return this.return_type;
	}
	
	public ArrayList<Type> getParamsType(){
		return this.params_t;
	}
	
	public ArrayList<String> getParamsName(){
		return this.params_n;
	}
	
	
	
	
	public String toString(){
		String result = this.return_type.toString();
		
		result += " " + super.toString() + "(";
		
		for(Type t : this.params_t){
			result += t + ", ";
		}
		
		return result + ")";
	}
}