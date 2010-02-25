package model.attribute;

public class InnerClassAttribute extends Attribute {

	private short numberOfClasses;

	private ClassAttribute[] classes;

	public InnerClassAttribute() {

	}

	public InnerClassAttribute(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(short numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

	public ClassAttribute[] getClasses() {
		return classes;
	}

	public void setClasses(ClassAttribute[] classes) {
		this.classes = classes;
	}
}
