package model.classes;

import java.util.Arrays;

public enum ClassAccessFlag {
	
	/**
	 * Declared public; may be accessed from outside its package.
	 */
	ACC_PUBLIC(0x1), 
	
	/**
	 * Declared final; no subclasses allowed.
	 */
	ACC_FINAL(0x10), 
	
	/**
	 * Treat superclass methods specially when invoked by the invokespecial instruction.
	 */
	ACC_SUPER(0x20), 
	
	/**
	 * Is an interface, not a class
	 */
	ACC_INTERFACE(0x200), 
	
	/**
	 * Declared abstract; must not be instantiated.
	 */
	ACC_ABSTRACT(0x400), 
	
	/**
	 * Declared synthetic; Not present in the source code.
	 */
	ACC_SYNTHETIC(0x1000), 
	
	/**
	 * Declared as an annotation type.
	 * If the ACC_ANNOTATION flag is set, the ACC_INTERFACE flag must be set as well.
	 */
	ACC_ANNOTATION(0x2000),
	
	/**
	 * Declared as an enum type.
	 */
	ACC_ENUM(0x4000);

	private int flag;

	public int getFlag() {
		return flag;
	}

	private ClassAccessFlag(int flag) {
		this.flag = flag;
	}

	public static ClassAccessFlag[] getAccessFlagDesc() {
		ClassAccessFlag[] res = ClassAccessFlag.values();
		Arrays.sort(res, new ClassAccessFlagComparator());
		return res;
	}
}
