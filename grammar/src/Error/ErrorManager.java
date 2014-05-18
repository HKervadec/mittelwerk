package Error;

import java.io.PrintStream;

import Core.Mittelwerk;
import Manager.LineManager;

public class ErrorManager{
	private PrintStream o;
	
	private ErrorMessenger em;
	
	private int err_c; /* err_c for error_count */
	private int war_c; /* war_c for warning_count */
	
	public ErrorManager(){
		this(System.err);
	}
	
	public ErrorManager(PrintStream ps){
		this.o = ps;
		
		this.em = new ErrorMessenger();
		
		this.err_c = 0;
		this.war_c = 0;
	}
	
	public void printError(int id_err){
		printError(id_err, "");
	}
	
	public void printError(int id_err, String option){
		String result = "Error - " + em.getTitle(id_err) + "\n";
		result += Mittelwerk.l_m.toString() + "\n" ;
		result += "\t> " + em.getMess(id_err) + "\n";
		
		if(option != ""){
			result += "\t> " + option + "\n" ;
		}
		
		result += "\n";
		
		this.o.println(result);
		
		this.err_c++;
	}
	
	public void printWarning(int id_war, String option){
	
		this.war_c++;
	}
	
	
	public void finalPrint(){
		this.o.println(String.format("%d errors, %d warnings", this.err_c
															, this.war_c));
	}
}