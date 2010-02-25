package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;

public class AssignationInstruction extends Instruction {

	private String varName;
	private Operand value;

	public AssignationInstruction(short currentIndex, String varName, Operand value) {
		super(currentIndex);
		this.value = value;
		this.varName = varName;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitAssignation(this);
	}

	public String getVarName() {
		return varName;
	}

	public Operand getValue() {
		return value;
	}

}
