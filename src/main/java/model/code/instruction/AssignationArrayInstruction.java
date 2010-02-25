package model.code.instruction;

import visitor.Visitor;
import model.code.operand.ArrayReference;
import model.code.operand.Operand;

public class AssignationArrayInstruction extends Instruction {

	private ArrayReference arrayRef;
	private Operand value;
	private Operand index;

	public AssignationArrayInstruction(short currentIndex, Operand value, Operand index,
			ArrayReference objectReference) {
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

	public ArrayReference getArrayRef() {
		return arrayRef;
	}

	public Operand getValue() {
		return value;
	}
}
