package Emitter.Instruction;

public class InstructionSimple extends Instruction{
	private String inst;
	
	public InstructionSimple(String s){
		this.inst = s;
	}
	
	public String convert(){
		return this.inst;
	}
}