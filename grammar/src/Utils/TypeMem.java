package Utils;

import java.util.Stack;

public class TypeMem{
	private Stack<Type> mem;
	
	public TypeMem(){
		this.mem = new Stack<Type>();
	}
	
	public void push(Type t){
		this.mem.push(t);
	}
	
	public Type pop(){
		return this.mem.pop();
	}
	
	public Type peek(){
		return this.mem.peek();
	}
	
	public void drop(){
		this.mem.pop();
	}
}