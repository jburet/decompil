package model.code.operand.impl;

import model.code.instruction.Instruction;
import model.code.operand.Operand;
import visitor.Visitor;

public class ThrowInstruction extends Instruction {

	public ThrowInstruction(short currentIndex, Operand operand) {
		super(currentIndex);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}
