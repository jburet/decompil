package jdecomp.core.model.code.instruction;

import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.MethodVisitor;

public class MonitorEnterInstruction extends Instruction {

	private Operand semaphor;

	public MonitorEnterInstruction(short currentIndex, Operand semaphor) {
		super(currentIndex);
		this.semaphor = semaphor;
	}

	@Override
	public void accept(MethodVisitor visitor) {
		// TODO Auto-generated method stub

	}

	public Operand getSemaphor() {
		return semaphor;
	}

}
