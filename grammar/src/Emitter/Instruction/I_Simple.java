package Emitter.Instruction;

public class I_Simple extends Instruction{
	private String inst;
	
	public I_Simple(String s){
		this.inst = s;
	}
	
	public String convert(){
		return this.inst;
	}
}