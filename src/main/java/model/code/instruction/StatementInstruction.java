package model.code.instruction;

import visitor.Visitor;
import model.code.OpCodes;

public class StatementInstruction extends Instruction{

	private OpCodes opCode;

	public StatementInstruction(short currentIndex, OpCodes opc) {
		super(currentIndex);
		this.opCode = opc;
	}
	
	public OpCodes getOpCode() {
		return opCode;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitStatementInstruction(this);
	}

}
