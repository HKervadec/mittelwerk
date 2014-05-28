package Manager;

import java.io.FileReader;
import java.io.BufferedReader;

import Core.Mittelwerk;
import Error.ErrorManager;


/**
 * Small class to keep track of the content of the line currently parsed.
 * Got a pretty print (toString), and a function to just retrieve the current
 * line.
 * Very usefull for debugging purposes.
 */
public class LineManager{
    private String fileName;
    private String current_line;
    private int line_number;
    private BufferedReader file;
    
    
    /**
     * Return the current line without any modification
     * @see toString
     * @return String
     */
    public String getLine(){
        return this.current_line;
    }

    
    public LineManager(){

    }
    
    /**
     * Open the file in parameter, and raise an error if the file is not found.
     * If sucess, read the first line.
     * @param fileName 
     */
    public void setFile(String fileName){
        try{
            this.file = new BufferedReader(new FileReader(fileName));
        }catch(Exception e){
            Mittelwerk.err_m.printError(0, fileName);
        }
        
        this.fileName = fileName;
        this.nextLine();
    }
    
    
    /**
     * Read the next line of the file, and increment the line count.
     * Raise an error if the end of file is found.
     */
    public void nextLine(){
        try{
            this.current_line = this.file.readLine();
        }catch(Exception e){
            Mittelwerk.err_m.printError(1, this.fileName);
        }
        
		/* if(this.current_line == null){
            Mittelwerk.err_m.printError(1, this.fileName);
        } */
        
        this.line_number++;
    }
    
    /**
     * Return the lineNumber and the line contents, separated by a '-'
     * @return String
     */
    public String toString(){
        return this.line_number + " - " + this.current_line;
    }
}