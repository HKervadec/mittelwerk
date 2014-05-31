package Error;

import java.io.PrintStream;

import Core.Mittelwerk;
import Manager.LineManager;


/**
 *  @file ErrorManager.java
 *  @brief The error manager is a tool to print easily errors and warning, and 
 *  to print their total at the end.
 *  
 *  The messages errors are defined in the ErrorMessenger, and it use the 
 *  LineManager to improve the messages.
 *  @see Error.ErrorMessenger
 *  @see Manager.LineManager
 */
public class ErrorManager{
	private PrintStream o; /* Useful to change easily the error stream. */
	
	private ErrorMessenger em;
	
	private int err_c; /* err_c for error_count */
	private int war_c; /* war_c for warning_count */
	
	/**
	 *  @brief Will create a basic ErrorManager, with System.err as error stream.
	 */
	public ErrorManager(){
		this(System.err);
	}
	
	/**
	 *  @brief Create a new ErrorManager, with the specified PrintStream
	 *  
	 *  @param [in] ps The future output for all errors and warnings.
	 */
	public ErrorManager(PrintStream ps){
		this.o = ps;
		
		this.em = new ErrorMessenger();
		
		this.err_c = 0;
		this.war_c = 0;
	}
	
	
	/**
	 *  @brief Print an error, with no optional arguments.
	 *  
	 *  @param [in] id_err The error id

	 *  @details It will just call himself with an empty string argument.
	 */
	public void printError(int id_err){
		printError(id_err, "");
	}
	
	
	/**
	 *  @brief Main print error function.
	 *  
	 *  @param [in] id_err The error id
	 *  @param [in] option The optional parameter, which is unknown to the 
	 *  ErrorMessenger. It can be a name, a value, etc.
	 *  
	 *  @details The error printing process is pretty simple.
	 *  	Error - <Error_title>
	 *  		> <the_current_line>
	 *  		> <Error_message>
	 *  		[> <The_optional_message>]
	 */
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
	
	
	/**
	 *  @brief Print a warning. Does not work at the moment, it will just 
	 *  increment the warning total.
	 *  
	 *  @param [in] id_war The warning id
	 *  @param [in] option Optional text that can improve the error message.
	 */
	public void printWarning(int id_war, String option){
	
		this.war_c++;
	}
	
	
	/**
	 *  @brief Print the error and warning totals.
	 */
	public void finalPrint(){
		this.o.println(String.format("%d errors, %d warnings", this.err_c
															, this.war_c));
	}
}