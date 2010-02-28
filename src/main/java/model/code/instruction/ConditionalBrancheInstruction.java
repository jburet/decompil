package model.code.instruction;

import model.code.OpCodes;
import model.code.operand.ConditionalOperator;
import model.code.operand.Operand;
import model.code.operand.impl.ConditionalOperation;
import visitor.Visitor;

/**
 * 
 * @author jburet Block If
 * 
 */
public class ConditionalBrancheInstruction extends Instruction {

	/**
	 * Opcode correspondant a la generation du block
	 */
	private OpCodes opc;

	private short currentIndex;

	private short branchIndex;

	private ConditionalOperation condition;

	public ConditionalBrancheInstruction(ConditionalOperator co, short currentIndex, short branchIndex,
			Operand operand1, Operand operand2) {
		super(currentIndex);
		this.condition = new ConditionalOperation(operand1, operand2, co);
		this.currentIndex = currentIndex;
		this.branchIndex = branchIndex;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitConditionalBranching(this);
	}

	public OpCodes getOpc() {
		return opc;
	}

	public ConditionalOperation getCondition() {
		return condition;
	}

	@Override
	public short getCurrentIndex() {
		return currentIndex;
	}

	public short getBranchIndex() {
		return branchIndex;
	}

}
