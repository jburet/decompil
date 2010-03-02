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

public class Code extends Attribute {

	private short maxStack;
	private short maxLocals;
	private int codeLength;
	private byte[] code;
	private short exceptionTableLength;
	private ExceptionTable exceptionTable;
	private short attributeCount;
	private Attribute[] attributes;

	public Code() {

	}

	public Code(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getMaxStack() {
		return maxStack;
	}

	public void setMaxStack(short maxStack) {
		this.maxStack = maxStack;
	}

	public short getMaxLocals() {
		return maxLocals;
	}

	public void setMaxLocals(short maxLocals) {
		this.maxLocals = maxLocals;
	}

	public int getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	public byte[] getCode() {
		return code;
	}

	public void setCode(byte[] code) {
		this.code = code;
	}

	public short getExceptionTableLength() {
		return exceptionTableLength;
	}

	public void setExceptionTableLength(short exceptionTableLength) {
		this.exceptionTableLength = exceptionTableLength;
	}

	public ExceptionTable getExceptionTable() {
		return exceptionTable;
	}

	public void setExceptionTable(ExceptionTable exceptionTable) {
		this.exceptionTable = exceptionTable;
	}

	public short getAttributeCount() {
		return attributeCount;
	}

	public void setAttributeCount(short attributeCount) {
		this.attributeCount = attributeCount;
	}

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}
}
