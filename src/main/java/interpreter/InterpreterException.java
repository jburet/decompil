package interpreter;

public class InterpreterException extends RuntimeException{
	public InterpreterException(Throwable t){
		super(t);
	}
	
	public InterpreterException(ErrorInterpretor ei, Throwable t){
		super(ei.toString(), t);
	}
	
	public InterpreterException(ErrorInterpretor ei){
		super(ei.toString());
	}
}
