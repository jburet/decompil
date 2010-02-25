package model.code.instruction;

import visitor.Visitor;

public class MethodInstruction extends BlockInstruction{

	public MethodInstruction(BlockInstruction precBlock,short startIndex, short endIndex) {
		super(precBlock, startIndex, endIndex);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitMethodInstruction(this);
	}

}
