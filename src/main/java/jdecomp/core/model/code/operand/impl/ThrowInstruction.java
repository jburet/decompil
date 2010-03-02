package jdecomp.core.model.code.operand.impl;

import jdecomp.core.model.code.instruction.Instruction;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.Visitor;

public class ThrowInstruction extends Instruction {

	public ThrowInstruction(short currentIndex, Operand operand) {
		super(currentIndex);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

}
