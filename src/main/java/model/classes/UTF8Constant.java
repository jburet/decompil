package model.classes;

public class UTF8Constant extends ConstantPoolInfo {
	private short length;
	private byte[] bytes;

	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
