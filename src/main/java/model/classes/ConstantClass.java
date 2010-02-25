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
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[CLASS] NameConstantIndex : ");
		sb.append(nameConstantIndex);
		return sb.toString();
	}


}
