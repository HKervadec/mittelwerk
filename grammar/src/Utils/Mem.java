package Utils;

import java.util.Stack;

public class Mem<E>{
	private Stack<E> mem;
	
	public Mem(){
		this.mem = new Stack<E>();
	}
	
	public void push(E e){
		this.mem.push(e);
	}
	
	public E pop(){
		return this.mem.pop();
	}
	
	public E peek(){
		return this.mem.peek();
	}
	
	public void drop(){
		this.mem.pop();
	}
}