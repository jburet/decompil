package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.ArrayReference;
import model.code.operand.Operand;

public class ArrayAccessInstruction implements Operand {

	private ArrayReference arrayReference;

	private Operand index;

	public ArrayAccessInstruction(Operand index, ArrayReference arrayReference) {
		this.arrayReference = arrayReference;
		this.index = index;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayAccessInstruction(this);
	}

	public ArrayReference getArrayReference() {
		return arrayReference;
	}

	public Operand getIndex() {
		return index;
	}

}
