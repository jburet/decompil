package model.classes;

public class ConstantNameType extends ConstantPoolInfo {
	private short nameIndex;
	private short descritptorIndex;
	
	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(short nameIndex) {
		this.nameIndex = nameIndex;
	}

	public short getDescritptorIndex() {
		return descritptorIndex;
	}

	public void setDescritptorIndex(short descritptorIndex) {
		this.descritptorIndex = descritptorIndex;
	}

}
