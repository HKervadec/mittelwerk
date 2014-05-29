package Utils;

import java.util.Stack;

/**
 *  @file Mem.java
 *  @brief Memory generic class
 *  
 *  @details A memory is just a stack. It is used to save some informations 
 *  globally in the parsing process, for example ident, values, etc.
 *  
 *  We could have used a stack directly, but whatever.
 */
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
	
	/**
	 *  @brief Pop the stack without returning the value.
	 */
	public void drop(){
		this.mem.pop();
	}
}