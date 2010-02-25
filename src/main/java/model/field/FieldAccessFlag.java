package model.field;

import java.util.Arrays;

public enum FieldAccessFlag {
	ACC_PUBLIC(0x1), ACC_PRIVATE(0x2), ACC_PROTECTED(0x4), ACC_STATIC(0x8), ACC_FINAL(
			0x10), ACC_VOLATILE(0x40), ACC_TRANSIENT(0x80), ACC_SYNTHETIC(
			0x1000), ACC_ENUM(0x4000);

	private int flag;

	public int getFlag() {
		return flag;
	}

	private FieldAccessFlag(int flag) {
		this.flag = flag;
	}

	public static FieldAccessFlag[] getAccessFlagDesc() {
		FieldAccessFlag[] res = FieldAccessFlag.values();
		Arrays.sort(res, new FieldAccessFlagComparator());
		return res;
	}
}
