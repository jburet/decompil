package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;

public class ReturnInstruction extends Instruction {

	private Operand operand;
	
	public ReturnInstruction(short currentIndex) {
		super(currentIndex);
	}
	
	public ReturnInstruction(short currentIndex, Operand operand) {
		super(currentIndex);
		this.operand = operand;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitReturn(this);
	}

	public Operand getOperand() {
		return operand;
	}

}
