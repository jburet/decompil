package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;
import model.code.operand.Type;

public class InstanceMethodInvocationInstruction extends MethodInvocation {

	private Operand intance;
	private String methodName;
	private Operand[] args;
	private Type returnType;

	public InstanceMethodInvocationInstruction(short currentIndex,
			Operand instance, String methodName, Type returnType, Operand... args) {
		super(currentIndex);
		this.intance = instance;
		this.methodName = methodName;
		this.args = args;
		this.returnType = returnType;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitInstanceMethodInvocation(this);
	}
	
	@Override
	public Type getReturnType() {
		return this.returnType;
	}

	public Operand getIntance() {
		return intance;
	}

	public void setIntance(Operand intance) {
		this.intance = intance;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Operand[] getArgs() {
		return args;
	}

	public void setArgs(Operand[] args) {
		this.args = args;
	}

	
}
