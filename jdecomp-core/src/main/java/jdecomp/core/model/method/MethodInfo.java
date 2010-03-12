/**
 * Copyright (C) 2010 Julien Buret <julien.buret@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jdecomp.core.model.method;

import jdecomp.core.interpreter.utils.ClassFileUtils;
import jdecomp.core.interpreter.utils.DescriptorParser;
import jdecomp.core.model.attribute.Attribute;
import jdecomp.core.model.attribute.AttributeType;
import jdecomp.core.model.attribute.Code;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.Descriptor;

public class MethodInfo {
	private short accessFlags;
	private short nameIndex;
	private short descriptorIndex;
	private short attributesCount;
	private Attribute[] attributes;
	// reference to classe
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

	public Descriptor[] getArgType() {
		String descriptorDecoded = this.getDecodeMethodDescriptor();
		String listType = descriptorDecoded.substring(descriptorDecoded.indexOf('(') + 1, descriptorDecoded
				.indexOf(')'));
		return DescriptorParser.parseDecodedMethodDescriptor(listType);
	}

	public String[] getArgName() {
		String descriptorDecoded = this.getDecodeMethodDescriptor();
		String listType = descriptorDecoded.substring(descriptorDecoded.indexOf('(') + 1, descriptorDecoded
				.indexOf(')'));
		// FIXME Use debug information if present
		String[] res = new String[DescriptorParser.parseDecodedMethodDescriptor(listType).length];
		for (int i = 0; i < res.length; i++) {
			res[i] = "args" + (i + 1);
		}
		return res;
	}

	public Descriptor getReturnType() {
		String descriptorDecoded = this.getDecodeMethodDescriptor();
		return ClassFileUtils.parseDescriptor(descriptorDecoded.substring(descriptorDecoded.lastIndexOf(')') + 1));
	}

	public ClassFile getReferentClassFile() {
		return referentClassFile;
	}

	public String getDecodeMethodDescriptor() {
		return ClassFileUtils.decodeUTF(getReferentClassFile(), this.getDescriptorIndex());
	}
}
