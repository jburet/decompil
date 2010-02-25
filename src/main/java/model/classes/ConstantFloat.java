package model.classes;

public class ConstantFloat extends ConstantPoolValue {
	private float bytes;

	@Override
	public short getConstantCount() {
		return 1;
	}

	public float getBytes() {
		return bytes;
	}

	public void setBytes(float bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[FLOAT] value : ");
		sb.append(bytes);
		return sb.toString();
	}

	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
