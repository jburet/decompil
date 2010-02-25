package model.classes;

public class ConstantLong extends ConstantPoolValue {
	private long bytes;

	@Override
	public short getConstantCount() {
		return 2;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[LONG] value : ");
		sb.append(bytes);
		return sb.toString();
	}

	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
