package model.classes;

public abstract class ConstantPoolInfo {
	private byte tag;
	
	public abstract short getConstantCount();

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}
}
