/**
 *
 */
package model.constant;


/**
 * @author jburet
 * 
 */
public enum DescriptorType implements Type {

	BYTE('B'),
	CHAR('C'),
	DOUBLE('D'),
	FLOAT('F'),
	INT('I'),
	LONG('J'),
	SHORT('S'),
	BOOLEAN('B'),
	VOID('V'),
	CLASS('L');

	private char vmType;

	private DescriptorType(char vmType) {
		this.vmType = vmType;
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

}
