package jdecomp.plugin.java;

import jdecomp.core.model.code.Descriptor;

public class DescriptorDecompiler {

	public static String getJavaType(Descriptor returnType) {
		StringBuffer type = new StringBuffer();
		switch (returnType.getDescriptorType()) {
		case BOOLEAN:
			type.append("boolean");
			break;
		case BYTE:
			type.append("byte");
			break;
		case CHAR:
			type.append("char");
			break;
		case DOUBLE:
			type.append("double");
			break;
		case FLOAT:
			type.append("float");
			break;
		case INT:
			type.append("int");
			break;
		case LONG:
			type.append("long");
			break;
		case SHORT:
			type.append("short");
			break;
		case VOID:
			type.append("void");
			break;
		case CLASS:
			type.append(returnType.getClassName());
			break;
		}
		// array
		if (returnType.isArray()) {
			for (int i = 0; i < returnType.getArrayLevel(); i++) {
				type.append("[]");
			}
		}
		return type.toString();
	}
}
