package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Array;
import model.code.operand.Operand;

public class AssignationArrayInstruction extends Instruction {

	private Array arrayRef;
	private Operand value;
	private Operand index;

	public AssignationArrayInstruction(short currentIndex, Operand value, Operand index,
			Array objectReference) {
		super(currentIndex);
		this.arrayRef = objectReference;
		this.value = value;
		this.arrayRef.addValue(value);
		this.index = index;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayAssignation(this);
	}

	public Operand getIndex() {
		return index;
	}

	public Array getArrayRef() {
		return arrayRef;
	}

	public Operand getValue() {
		return value;
	}
}
