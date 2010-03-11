package jdecomp.plugin.java;

import jdecomp.core.model.code.operand.ArrayType;

public class ArrayTypeDecompiler {
	public static String getJavaType(ArrayType arrayType) {
		switch (arrayType.getArrayTypeDescriptor()) {
		case T_BOOLEAN:
			return "boolean";
		case T_BYTE:
			return "byte";
		case T_CHAR:
			return "char";
		case T_DOUBLE:
			return "double";
		case T_FLOAT:
			return "float";
		case T_INT:
			return "int";
		case T_LONG:
			return "long";
		case T_SHORT:
			return "short";
		case T_REF:
		default:
			return DescriptorDecompiler.getJavaType(arrayType.getClassDescriptor());
		}
	}
}
