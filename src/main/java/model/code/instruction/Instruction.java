package model.code.instruction;

import visitor.Visitor;

public abstract class Instruction {

	protected short currentIndex;

	public Instruction(short currentIndex) {
		this.currentIndex = currentIndex;
	}

	public short getCurrentIndex() {
		return currentIndex;
	}

	public abstract void accept(Visitor visitor);

}
