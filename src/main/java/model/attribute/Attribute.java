package model.attribute;

public class Attribute {
	private short nameIndex;
	private String typeName;
	private int length;
	private AttributeType attributeType;
	private byte[] uninterpretedByte;

	public short getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(short nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public byte[] getUninterpretedByte() {
		return uninterpretedByte;
	}

	public void setUninterpretedByte(byte[] uninterpretedByte) {
		this.uninterpretedByte = uninterpretedByte;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
