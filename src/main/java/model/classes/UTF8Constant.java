package model.classes;

import java.io.UnsupportedEncodingException;

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
	
	public String getDecodedByte(){
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[UTF-8] length : ");
		sb.append(length);
		sb.append(" bytes : ");
		sb.append(bytes);
		sb.append("decoded : ");
		sb.append(getDecodedByte());
		return sb.toString();
	}
}
