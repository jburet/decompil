package model.classes;

public class ConstantInteger extends ConstantPoolValue {
	private int bytes;

	@Override
	public short getConstantCount() {
		return 1;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
