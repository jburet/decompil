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
	public Object getDecodedBytes() {
		return getBytes();
	}
}
