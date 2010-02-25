package model.attribute;

public class RuntimeAnnotations extends Attribute {

	private short numAnnotation;

	private RuntimeParameterAnnotations[] annotation;

	public RuntimeAnnotations() {

	}

	public RuntimeAnnotations(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumAnnotation() {
		return numAnnotation;
	}

	public void setNumAnnotation(short numAnnotation) {
		this.numAnnotation = numAnnotation;
	}

	public RuntimeParameterAnnotations[] getAnnotation() {
		return annotation;
	}

	public void setAnnotation(RuntimeParameterAnnotations[] annotation) {
		this.annotation = annotation;
	}
}
