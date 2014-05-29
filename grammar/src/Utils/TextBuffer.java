package Utils;

import Core.Mittelwerk;
import Emitter.Instruction.I_Simple;

public class TextBuffer{
	private String text;
	
	public TextBuffer(){
		this.reset();
	}
	
	
	public void reset(){
		this.text = "";
	}
	
	public void add(String t){
		this.text += t + " ";
	}
	

	public void commit(){
		// System.out.println(this.text);
		Mittelwerk.e.add(new I_Simple(this.text));
		
		this.reset();
	}
}