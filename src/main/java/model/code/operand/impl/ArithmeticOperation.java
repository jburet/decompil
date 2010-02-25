package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.ArithmeticOperationType;
import model.code.operand.Operand;

/**
 * @author jburet
 * 
 */
public class ArithmeticOperation implements Operand {

	private Operand op1;
	private Operand op2;
	private ArithmeticOperationType type;

	public ArithmeticOperation(Operand op1, Operand op2,
			ArithmeticOperationType type) {
		super();
		this.op1 = op1;
		this.op2 = op2;
		this.type = type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArithmethicOperation(this);
	}

	public Operand getOp1() {
		return op1;
	}

	public Operand getOp2() {
		return op2;
	}

	public ArithmeticOperationType getType() {
		return type;
	}

}
