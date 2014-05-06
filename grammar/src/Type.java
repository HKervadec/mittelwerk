public enum Type{
    ERROR ("ERROR"), 
	BOOL ("bool"),	
	INT ("int"), 
	THRUSTER ("THRUSTER"), 
	VOID ("void"), 
	DOUBLE ("double");
	
	private String text;
	
	Type(String t){
		this.text = t;
	}
	
	public String toString(){
		return this.text;
	}
}