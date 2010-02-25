package model.code.operand.impl;

import visitor.Visitor;
import model.code.instruction.MethodInvocation;
import model.code.operand.ArrayReference;
import model.code.operand.Operand;
import model.constant.Type;

public class ArrayRefInvocationOperandResult extends InvocationOperandResult implements Operand, ArrayReference {

	public ArrayRefInvocationOperandResult(MethodInvocation methodInvocation) {
		super(methodInvocation);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type getReturnType() {
		return methodInvocation.getReturnType();
	}

	// FIXME ou pas... Dans le cas d'une variable on ne conserve pas les
	// valeurs.
	@Override
	public void addValue(Operand value) {
		// TODO Auto-generated method stub
		
	}

	
}
