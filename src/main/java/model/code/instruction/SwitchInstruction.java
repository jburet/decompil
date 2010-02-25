package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;

public class SwitchInstruction extends Instruction {

	private Operand index;
	private int defaultIndex;
	private int[] match;
	private int[] jumpOffset;

	public SwitchInstruction(short currentIndex, Operand index,
			int defaultIndex, int[] match, int[] jumpOffset) {
		super(currentIndex);
		this.index = index;
		this.defaultIndex = defaultIndex;
		this.match = match;
		this.jumpOffset = jumpOffset;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSwitch(this);
	}

	public Operand getIndex() {
		return index;
	}

	public int getDefaultIndex() {
		return defaultIndex;
	}

	public int[] getMatch() {
		return match;
	}

	public int[] getJumpOffset() {
		return jumpOffset;
	}

}
