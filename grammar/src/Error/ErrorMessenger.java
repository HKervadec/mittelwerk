package Error;

import java.util.ArrayList;

public class ErrorMessenger{
	private ArrayList<String> title, mess;
	
	public ErrorMessenger(){
		this.title = new ArrayList<String>();
		this.mess = new ArrayList<String>();
		
		this.addErrors();
	}
	
	public String getTitle(int id){
		return this.title.get(id);
	}
	
	public String getMess(int id){
		return this.mess.get(id);
	}
	
	
	private void addErrors(){
		/* this.addError(123456, "", ""); */
		
		this.addError(0, "File not found", "Could not open the following file:");
		this.addError(1, "EOF", "Unexpected End Of File found.");
		this.addError(2, "Ident already exist", "Identifier already in use:");
		this.addError(3, "Ident does not exist", "Identifier unknown");
		this.addError(4, "START already declared", "An initial state has already be declared. It will override the previous initial state.");
		this.addError(5, "Invalid destination", "The destination is not correct (it is a global ident or does not exist.");
		this.addError(6, "Too much destination", "The getter does not need that much destinations.");
		this.addError(7, "Too much arguments", "The getter does not need that much arguments.");
		this.addError(8, "Unknown getter", "The getter does not exist.");
	}
	
	private void addError(int id, String t, String m){
		this.title.add(id, t);
		this.mess.add(id, m);
	}
	
	
}