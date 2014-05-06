import java.util.ArrayList;

public class IdentFunction extends Ident{
	private Type return_type;
	private ArrayList<Type> params;
	private int param_size;
	
	public IdentFunction(String n, Type return_type){
		super(n);
		
		this.params = new ArrayList();
		this.return_type = return_type;
		this.param_size = 0;
	}
	
	public void addParamType(Type t){
		this.params.add(t);
		this.param_size++;
	}
	
	public String toString(){
		String result = this.return_type.toString();
		
		result += " " + super.toString() + "(";
		
		for(Type t : this.params){
			result += t + ", ";
		}
		
		return result + ")";
	}
}