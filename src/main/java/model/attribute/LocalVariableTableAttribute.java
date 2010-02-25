package model.attribute;

public class LocalVariableTableAttribute extends Attribute {

	private short numberOfLocalVariable;

	private LocalVariable[] localVariables;

	public LocalVariableTableAttribute(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumberOfLocalVariable() {
		return numberOfLocalVariable;
	}

	public void setNumberOfLocalVariable(short numberOfLocalVariable) {
		this.numberOfLocalVariable = numberOfLocalVariable;
	}

	public LocalVariable[] getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(LocalVariable[] localVariables) {
		this.localVariables = localVariables;
	}
}
