package model.field;

import model.attribute.Attribute;


public class FieldInfo {
	private short accessFlag;
	private short nameIndex;
	private short descriptorIndex;
	private short attributeCount;
	private Attribute[] attributes;

	public short getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(short accessFlag) {
		this.accessFlag = accessFlag;
	}

	public short getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(short nameIndex) {
		this.nameIndex = nameIndex;
	}

	public short getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(short descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	public short getAttributeCount() {
		return attributeCount;
	}

	public void setAttributeCount(short attributeCount) {
		this.attributeCount = attributeCount;
	}

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}
}
