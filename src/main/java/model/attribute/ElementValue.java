package model.attribute;

public class ElementValue {

	private byte tag;
	private short constantValueIndex;

	// Enuum const value
	private short typeNameIndex;
	private short constantNameIndex;
	// fin enum const value
	private short classInfoIndex;
	private RuntimeParameterAnnotations annotationValue;
	// array value
	private short numValues;
	private ElementValue[] elementValue;

	// fin array value
	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public short getConstantValueIndex() {
		return constantValueIndex;
	}

	public void setConstantValueIndex(short constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}

	public short getTypeNameIndex() {
		return typeNameIndex;
	}

	public void setTypeNameIndex(short typeNameIndex) {
		this.typeNameIndex = typeNameIndex;
	}

	public short getConstantNameIndex() {
		return constantNameIndex;
	}

	public void setConstantNameIndex(short constantNameIndex) {
		this.constantNameIndex = constantNameIndex;
	}

	public short getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(short classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}

	public RuntimeParameterAnnotations getAnnotationValue() {
		return annotationValue;
	}

	public void setAnnotationValue(RuntimeParameterAnnotations annotationValue) {
		this.annotationValue = annotationValue;
	}

	public short getNumValues() {
		return numValues;
	}

	public void setNumValues(short numValues) {
		this.numValues = numValues;
	}

	public ElementValue[] getElementValue() {
		return elementValue;
	}

	public void setElementValue(ElementValue[] elementValue) {
		this.elementValue = elementValue;
	}
}
