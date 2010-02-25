package model.attribute;

public class Exception extends Attribute {

	private short numberOfException;

	private short[] constantExceptionIndex;

	public Exception() {

	}

	public Exception(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumberOfException() {
		return numberOfException;
	}

	public void setNumberOfException(short numberOfException) {
		this.numberOfException = numberOfException;
	}

	public short[] getConstantExceptionIndex() {
		return constantExceptionIndex;
	}

	public void setConstantExceptionIndex(short[] constantExceptionIndex) {
		this.constantExceptionIndex = constantExceptionIndex;
	}
}
