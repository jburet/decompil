package model.attribute;

public class ClassAttribute {
	private short innerClassInfoIndex;
	private short outerClassInfoIndex;
	private short innerNameIndex;
	private short innerClassAccessFlags;

	public short getInnerClassInfoIndex() {
		return innerClassInfoIndex;
	}

	public void setInnerClassInfoIndex(short innerClassInfoIndex) {
		this.innerClassInfoIndex = innerClassInfoIndex;
	}

	public short getOuterClassInfoIndex() {
		return outerClassInfoIndex;
	}

	public void setOuterClassInfoIndex(short outerClassInfoIndex) {
		this.outerClassInfoIndex = outerClassInfoIndex;
	}

	public short getInnerNameIndex() {
		return innerNameIndex;
	}

	public void setInnerNameIndex(short innerNameIndex) {
		this.innerNameIndex = innerNameIndex;
	}

	public short getInnerClassAccessFlags() {
		return innerClassAccessFlags;
	}

	public void setInnerClassAccessFlags(short innerClassAccessFlags) {
		this.innerClassAccessFlags = innerClassAccessFlags;
	}
}
