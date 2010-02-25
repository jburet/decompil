package interpreter;

public interface ConstantType {
	public static final byte UTF8 = 1;
	public static final byte CLASS = 7;
	public static final byte FIELD_REF = 9;
	public static final byte METHOD_REF = 10;
	public static final byte INTERFACE_REF = 11;
	public static final byte STRING = 8;
	public static final byte INTEGER = 3;
	public static final byte FLOAT = 4;
	public static final byte LONG = 5;
	public static final byte DOUBLE = 6;
	public static final byte NAME_TYPE = 12;
	
}
