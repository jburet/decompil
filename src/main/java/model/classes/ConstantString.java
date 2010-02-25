package model.classes;

public class ConstantString extends ConstantPoolInfo {
	private short stringIndex;

	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getStringIndex() {
		return stringIndex;
	}

	public void setStringIndex(short stringIndex) {
		this.stringIndex = stringIndex;
	}
}
