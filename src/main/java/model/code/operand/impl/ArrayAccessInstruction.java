package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.Array;
import model.code.operand.Operand;

public class ArrayAccessInstruction implements Operand {

	private Array arrayReference;

	private Operand index;

	public ArrayAccessInstruction(Operand index, Array arrayReference) {
		this.arrayReference = arrayReference;
		this.index = index;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayAccessInstruction(this);
	}

	public Array getArrayReference() {
		return arrayReference;
	}

	public Operand getIndex() {
		return index;
	}

}
