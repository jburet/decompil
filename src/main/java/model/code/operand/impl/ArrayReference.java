package model.code.operand.impl;

import model.code.operand.Array;
import model.code.operand.Operand;
import model.code.operand.Variable;
import model.constant.Type;
import visitor.Visitor;

public class ArrayReference implements Array, Variable {

	private Operand operandReference;

	private String name;

	private Type type;

	public ArrayReference(Operand operandReference, String name, Type type) {
		this.operandReference = operandReference;
		this.name = name;
		this.type = type;
	}

	public ArrayReference(ConstantArrayReference operand, String name) {
		this.type = operand.getObjectType();
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayReference(this);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Operand getOperandReference() {
		return operandReference;
	}

	@Override
	public void addValue(Operand value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getObjectType() {
		return this.type;
	}

	@Override
	public Type getType() {
		return this.type;
	}

}
