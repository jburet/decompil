package model.code.instruction;

import model.code.operand.Type;

public abstract class MethodInvocation extends Instruction{

	public MethodInvocation(short currentIndex) {
		super(currentIndex);
	}
	
	public abstract Type getReturnType();

}
