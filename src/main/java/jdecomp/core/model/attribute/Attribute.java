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

package jdecomp.core.model.attribute;

public class Attribute {
	private short nameIndex;
	private String typeName;
	private int length;
	private AttributeType attributeType;
	private byte[] uninterpretedByte;

	public short getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(short nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public byte[] getUninterpretedByte() {
		return uninterpretedByte;
	}

	public void setUninterpretedByte(byte[] uninterpretedByte) {
		this.uninterpretedByte = uninterpretedByte;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
