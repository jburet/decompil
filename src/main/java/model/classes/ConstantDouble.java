package model.classes;

public class ConstantDouble extends ConstantPoolValue {
	private double bytes;

	@Override
	public short getConstantCount() {
		return 2;
	}

	public double getBytes() {
		return bytes;
	}

	public void setBytes(double bytes) {
		this.bytes = bytes;
	}
	
	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
