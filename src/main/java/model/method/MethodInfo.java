package model.method;

import model.attribute.Attribute;
import model.attribute.AttributeType;
import model.attribute.Code;
import model.classes.ClassFile;
import model.code.Descriptor;
import interpreter.utils.ClassFileUtils;
import interpreter.utils.DescriptorParser;

public class MethodInfo {
	private short accessFlags;
	private short nameIndex;
	private short descriptorIndex;
	private short attributesCount;
	private Attribute[] attributes;
	// Reference à ça classe
	private ClassFile referentClassFile;

	public MethodInfo(ClassFile referentClassFile) {
		super();
		this.referentClassFile = referentClassFile;
	}

	public short getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(short accessFlags) {
		this.accessFlags = accessFlags;
	}

	public short getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(short nameIndex) {
		this.nameIndex = nameIndex;
	}

	public short getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(short descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	public short getAttributesCount() {
		return attributesCount;
	}

	public void setAttributesCount(short attributesCount) {
		this.attributesCount = attributesCount;
	}

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}

	// Method utilitaire

	public static Code getCode(MethodInfo methodInfo) {
		for (Attribute att : methodInfo.attributes) {
			if (att.getAttributeType().equals(AttributeType.Code)) {
				return (Code) att;
			}
		}
		return null;
	}

	public String[] getArgType() {
		String descriptorDecoded = getReferentClassFile()
				.getDecodeMethodDescriptor(this);
		String listType = descriptorDecoded.substring(descriptorDecoded
				.indexOf('(') + 1, descriptorDecoded.indexOf(')'));
		return DescriptorParser.parseDecodedMethodDescriptor(listType);
	}

	public String[] getArgName() {
		String descriptorDecoded = getReferentClassFile()
				.getDecodeMethodDescriptor(this);
		String listType = descriptorDecoded.substring(descriptorDecoded
				.indexOf('(') + 1, descriptorDecoded.indexOf(')'));

		String[] res = DescriptorParser.parseDecodedMethodDescriptor(listType);
		for (int i = 0; i < res.length; i++) {
			res[i] = "args" + (i + 1);
		}
		return res;
	}

	public Descriptor getReturnType() {
		String descriptorDecoded = getReferentClassFile()
				.getDecodeMethodDescriptor(this);
		return ClassFileUtils.parseDescriptor(descriptorDecoded
				.substring(descriptorDecoded.lastIndexOf(')') + 1));
	}

	public ClassFile getReferentClassFile() {
		return referentClassFile;
	}
}
