package model.code.instruction;

import visitor.Visitor;

public class UnconditionalBranching extends Instruction {

	private short currentIndex;

	private short branchIndex;

	public UnconditionalBranching(short currentIndex, short branchIndex) {
		super(currentIndex);
		this.currentIndex = currentIndex;
		this.branchIndex = branchIndex;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUnconditionalBranching(this);
	}

	public short getCurrentIndex() {
		return currentIndex;
	}

	public short getBranchIndex() {
		return branchIndex;
	}

}
