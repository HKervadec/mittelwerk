package Emitter;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;

import Emitter.Instruction.*;	


/**
 *  @file BasicEmitter.java
 *  @brief Abstract class for the emitter. Emitter & Emitter extends this class.
 *  Allow to share some code.
 *  
 *  This class is just a big list of instructions.
 *  They will be translated into c++ at the end of the parsing process, using
 *  their convert() function.
 */
public abstract class BasicEmitter{
	protected PrintWriter output;
	protected File file;
	
	protected ArrayList<Instruction> code;
	
	private int tab_count; /* Number of tab to put before an instruction. */
	
	public BasicEmitter(){
		this.tab_count = 0;
	}
	
	/**
	 *  @brief Set the output of the Emitter, and create the file if needed.
	 *  
	 *  @param [in] path The path to the file.
	 *  
	 *  @details It will also create the required folders if needed.
	 */
	public void setOutput(String path){
		try{
			this.file = new File(path);
			
			try{
				this.file.getParentFile().mkdirs();
			}catch(Exception e){}

			this.output = new PrintWriter(this.file, "UTF-8");
		}catch(Exception e){
			System.out.println("Errors while creating the following file: " + path);
			System.out.println(this.file);
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 *  @brief Add an instruction to his set.
	 *  
	 *  @param [in] i The instruction to adds
	 *  
	 *  @details It will add the instruction to his set, but also set the tab 
	 *  number of the instruction, according to the current tab_count.
	 */
	public void add(Instruction i){
		this.code.add(i);
		
		i.setTabValue(this.tab_count);
	}
	
	/**
	 *  @brief Increase the number of tabulation by one.
	 */
	public void incTab(){
		this.tab_count++;
	}
	
	/**
	 *  @brief Decrease the number of tabulation by one.
	 */
	public void decTab(){
		this.tab_count--;
	}
	
	/************************************************************/
	/*						Emit code							*/
	/************************************************************/
	/**
	 *  @brief Emit the code in the previously defined output.
	 *  
	 *  @details It will translate the instructions one by one into text
	 *  (c++), and print everything into the file.
	 */
	public void emit(){
		for(Instruction inst : this.code){
			String tab = inst.genTab();
			String line = inst.convert();
			this.output.print(tab + line);
			// System.out.print(line);
		}
		
		this.output.close();
		
		System.out.println(String.format("Code successfully emitted in %s", 
			this.file.getPath()));
	}
}