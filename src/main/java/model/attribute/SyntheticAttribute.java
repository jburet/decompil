package model.attribute;

public class SyntheticAttribute extends Attribute {

	public SyntheticAttribute() {

	}

	public SyntheticAttribute(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}
}
