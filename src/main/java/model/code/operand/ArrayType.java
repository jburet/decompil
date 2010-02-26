package model.code.operand;

import model.constant.Type;

public enum ArrayType implements Type {

	T_BOOLEAN((short) 4),
	T_CHAR((short) 5),
	T_FLOAT((short) 6),
	T_DOUBLE((short) 7),
	T_BYTE((short) 8),
	T_SHORT((short) 9),
	T_INT((short) 10),
	T_LONG((short) 11),
	T_REF((short) 0);

	private short code;

	private ArrayType(short code) {
		this.code = code;
	}

	public short getCode() {
		return this.code;
	}

	public static ArrayType getByCode(short code) {
		for (ArrayType at : values()) {
			if (at.getCode() == code) {
				return at;
			}
		}
		return T_REF;
	}

	@Override
	public boolean isArray() {
		return true;
	}
}
