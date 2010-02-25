package model.attribute;

public class ConstantValue extends Attribute {

	private short constantValueIndex;

	public ConstantValue() {

	}

	public ConstantValue(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getConstantValueIndex() {
		return constantValueIndex;
	}

	public void setConstantValueIndex(short constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}

}
