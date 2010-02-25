package model.code.instruction;

import model.constant.Type;

public abstract class MethodInvocation extends Instruction{

	public MethodInvocation(short currentIndex) {
		super(currentIndex);
	}
	
	public abstract Type getReturnType();

}
