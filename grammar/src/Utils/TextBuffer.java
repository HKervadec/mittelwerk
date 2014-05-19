package Utils;

import Core.Mittelwerk;
import Emitter.Instruction.I_Simple;

public class TextBuffer{
	private String text;
	private int tabulation;
	private final static String tab = "\t";
	
	public TextBuffer(){
		this.tabulation = 0;
		
		this.reset();
	}
	
	
	public void reset(){
		this.text = "";
		
		for(int i = 0 ; i < this.tabulation ; i++){
			this.text += TextBuffer.tab;
		}
	}
	
	public void add(String t){
		this.text += t + " ";
	}
	
	public void incTab(){
		this.tabulation++;
	}
	
	public void decTab(){
		this.tabulation--;
	}
	
	public void commit(){
		this.text += "\n";
		
		System.out.println(this.text);
		Mittelwerk.e.add(new I_Simple(this.text));
		
		this.reset();
	}
}