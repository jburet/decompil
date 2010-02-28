package model.code.operand;

import model.code.operand.impl.TypeDefinedByString;
import model.constant.Type;
import visitor.Visitor;

public class ObjectReference implements Variable {

	public static ObjectReference NULL_REFERENCE = new ObjectReference(null, null, null,
			new TypeDefinedByString("null"));

	/**
	 * Instance of object reference
	 */
	private Operand operandReference;

	/**
	 * Class defining the ref
	 */
	private String classReference;

	/**
	 * Name of variable
	 */
	private String name;

	private Type type;

	public static ObjectReference getNULL_REFERENCE() {
		return NULL_REFERENCE;
	}

	public ObjectReference(Operand operandReference, String classReference, String name, Type type) {
		this.operandReference = operandReference;
		this.classReference = classReference;
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

	public String getClassReference() {
		return classReference;
	}

}
