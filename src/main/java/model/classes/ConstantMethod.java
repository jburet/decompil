package model.classes;

public class ConstantMethod extends ConstantPoolInfo {
	private short classIndex;
	private short nameTypeIndex;
	
	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(short classIndex) {
		this.classIndex = classIndex;
	}

	public short getNameTypeIndex() {
		return nameTypeIndex;
	}

	public void setNameTypeIndex(short nameTypeIndex) {
		this.nameTypeIndex = nameTypeIndex;
	}
}
