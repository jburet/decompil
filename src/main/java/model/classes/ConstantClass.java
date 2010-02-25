package model.classes;

public class ConstantClass extends ConstantPoolInfo{
	private short nameConstantIndex;
	
	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getNameConstantIndex() {
		return nameConstantIndex;
	}

	public void setNameConstantIndex(short nameConstantIndex) {
		this.nameConstantIndex = nameConstantIndex;
	}
	
}
