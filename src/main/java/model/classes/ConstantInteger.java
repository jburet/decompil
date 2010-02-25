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
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[INTEGER] value : ");
		sb.append(bytes);
		return sb.toString();
	}

	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
