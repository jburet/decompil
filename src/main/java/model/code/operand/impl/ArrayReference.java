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

	private int dimension;

	public ArrayReference(Operand operandReference, String name, Type type, int dimension) {
		this.operandReference = operandReference;
		this.name = name;
		this.type = type;
		this.dimension = dimension;
	}

	public ArrayReference(ConstantArrayReference operand, String name) {
		this.type = operand.getObjectType();
		this.name = name;
		this.dimension = operand.getDimension();
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
	public void addValue(Operand index, Operand value) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.code.operand.Array#getDimension()
	 */
	@Override
	public int getDimension() {
		return this.dimension;
	}

}
