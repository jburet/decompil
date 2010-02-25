/**
 *
 */
package model.code;

import model.constant.DescriptorType;

/**
 * @author jburet
 * 
 */
public class Descriptor {

	private final DescriptorType descriptorType;
	private String className;
	private int arrayLevel = 0;

	public Descriptor(DescriptorType descriptorType) {
		this.descriptorType = descriptorType;
	}

	public Descriptor(DescriptorType descriptorType, String classname) {
		this(descriptorType);
		this.className = classname;
	}

	public Descriptor(DescriptorType descriptorType, int arrayLevel) {
		this(descriptorType);
		this.arrayLevel = arrayLevel;
	}

	public Descriptor(DescriptorType descriptorType, String classname, int arrayLevel) {
		this(descriptorType, classname);
		this.arrayLevel = arrayLevel;
	}

	public DescriptorType getDescriptorType() {
		return descriptorType;
	}

	public String getClassName() {
		return className;
	}

	public int getArrayLevel() {
		return arrayLevel;
	}

}
