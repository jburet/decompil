package model.attribute;

public class RuntimeParameterAnnotations {
	private short typeConstantIndex;
	private short numElement;
	private ElementValuePair[] elementValuePairs;

	public short getTypeConstantIndex() {
		return typeConstantIndex;
	}

	public void setTypeConstantIndex(short typeConstantIndex) {
		this.typeConstantIndex = typeConstantIndex;
	}

	public short getNumElement() {
		return numElement;
	}

	public void setNumElement(short numElement) {
		this.numElement = numElement;
	}

	public ElementValuePair[] getElementValuePairs() {
		return elementValuePairs;
	}

	public void setElementValuePairs(ElementValuePair[] elementValuePairs) {
		this.elementValuePairs = elementValuePairs;
	}

}
