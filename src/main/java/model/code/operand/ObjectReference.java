package model.code.operand;

import visitor.Visitor;
import model.code.operand.impl.TypeDefinedByString;

public class ObjectReference implements Variable {

	public static ObjectReference NULL_REFERENCE = new ObjectReference(null, null, new TypeDefinedByString("null"));

	private Operand operandReference;

	private String name;

	private Type type;

	public ObjectReference(Operand operandReference, String name, Type type) {
		this.operandReference = operandReference;
		this.name = name;
		this.type = type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitObjectReference(this);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	public Operand getOperandReference() {
		return operandReference;
	}

}
