package jdecomp.core.model.code.instruction;

import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.Visitor;

public class MonitorExitInstruction extends Instruction {

	private Operand semaphor;

	public MonitorExitInstruction(short currentIndex, Operand semaphor) {
		super(currentIndex);
		this.semaphor = semaphor;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	public Operand getSemaphor() {
		return semaphor;
	}

}
