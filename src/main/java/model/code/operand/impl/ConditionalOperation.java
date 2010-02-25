package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.ConditionalOperator;
import model.code.operand.Operand;

public class ConditionalOperation implements Operand {

	private Operand operand1;
	private Operand operand2;
	private ConditionalOperator co;

	public ConditionalOperation(Operand operand1, Operand operand2,
			ConditionalOperator co) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.co = co;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitConditionalOperation(this);
	}

	public Operand getOperand1() {
		return operand1;
	}

	public void setOperand1(Operand operand1) {
		this.operand1 = operand1;
	}

	public Operand getOperand2() {
		return operand2;
	}

	public void setOperand2(Operand operand2) {
		this.operand2 = operand2;
	}

	public ConditionalOperator getCo() {
		return co;
	}

	public void setCo(ConditionalOperator co) {
		this.co = co;
	}
}
