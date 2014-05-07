package Emitter.Instruction;

/**
 * A simple instruction producing only blank lines.
 */
public class I_blank extends Instruction{
	private int nol;
	
    /**
     * @param n The number of blank lines to produce
     */	
	public I_blank(int n){
		this.nol = n;
	}
	
	public String convert(){
		String result = "";
		for(int i = 0 ; i < this.nol ; i++){
			result += "\n";
		}
		
		return result;
	}
}