package model.code.operand;

public enum ConditionalOperator {

	EQ("=="), NE("!="), LT("<"), LE("<="), GT(">"), GE(">=");

	private String op;

	private ConditionalOperator(String op) {
		this.op = op;
	}

	public String getOp() {
		return op;
	}
}
