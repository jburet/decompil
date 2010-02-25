/**
 *
 */
package model.constant;

import model.code.operand.Type;

/**
 * @author jburet
 * 
 */
public enum DescriptorType implements Type {

	BYTE('B', "byte"),
	CHAR('C', "char"),
	DOUBLE('D', "double"),
	FLOAT('F', "float"),
	INT('I', "int"),
	LONG('J', "long"),
	SHORT('S', "short"),
	BOOLEAN('B', "boolean"),
	VOID('V', "void"),
	CLASS('L', "class");

	private char vmType;
	private String javaType;

	private DescriptorType(char vmType, String javaType) {
		this.vmType = vmType;
		this.javaType = javaType;
	}

	public static DescriptorType getDescriptorFromVmtype(char vmType) {
		for (DescriptorType dt : values()) {
			if (dt.vmType == vmType) {
				return dt;
			}
		}
		return null;
	}

	public char getVmType() {
		return vmType;
	}

	public String getJavaType() {
		return javaType;
	}

}
