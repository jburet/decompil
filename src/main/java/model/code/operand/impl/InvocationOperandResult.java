package model.code.operand.impl;

import model.code.instruction.MethodInvocation;
import model.code.operand.Operand;
import model.code.operand.Type;

public abstract class InvocationOperandResult implements Operand {

	protected MethodInvocation methodInvocation;
	
	public InvocationOperandResult(MethodInvocation methodInvocation){
		this.methodInvocation = methodInvocation;
	}
	
	public abstract Type getReturnType();

}
