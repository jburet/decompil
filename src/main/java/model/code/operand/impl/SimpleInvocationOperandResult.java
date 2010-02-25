package model.code.operand.impl;

import visitor.Visitor;
import model.code.instruction.MethodInvocation;
import model.code.operand.Operand;
import model.code.operand.Type;

public class SimpleInvocationOperandResult extends InvocationOperandResult implements Operand {

	public SimpleInvocationOperandResult(MethodInvocation methodInvocation) {
		super(methodInvocation);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type getReturnType() {
		return methodInvocation.getReturnType();
	}


}
