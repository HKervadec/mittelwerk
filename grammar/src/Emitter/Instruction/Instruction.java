package Emitter.Instruction;

public abstract class Instruction{
	private int tab_value;
	private final static String tab = "\t";
	
	public abstract String convert();
	
	public void setTabValue(int v){
		this.tab_value = v;
	}
	
	public String genTab(){
		String result = "";
		
		for(int i = 0 ; i < this.tab_value ; i++){
			result += this.tab;
		}
		
		return result;
	}
}