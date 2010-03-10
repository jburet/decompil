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

package jdecomp.core.model.classes;

import jdecomp.core.model.attribute.Attribute;
import jdecomp.core.model.field.FieldInfo;
import jdecomp.core.model.method.MethodInfo;
import jdecomp.core.visitor.ClassVisitor;

public class ClassFile {

	private int magic;
	private short minorVersion;
	private short majorVersion;
	private short constantPoolCount;
	private ConstantPoolInfo[] constantPool;
	private short accessFlags;
	private short thisClass;
	private short superClass;
	private short interfacesCount;
	private short[] interfacesIndex;
	private short fieldsCount;
	private FieldInfo[] fields;
	private short methodsCount;
	private MethodInfo methods[];
	private short attributesCount;
	private Attribute[] attributes;

	// attribute_info attributes[attributes_count];
	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public short getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(short minorVersion) {
		this.minorVersion = minorVersion;
	}

	public short getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(short majorVersion) {
		this.majorVersion = majorVersion;
	}

	public short getConstantPoolCount() {
		return constantPoolCount;
	}

	public void setConstantPoolCount(short constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}

	public short getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(short accessFlags) {
		this.accessFlags = accessFlags;
	}

	public short getThisClass() {
		return thisClass;
	}

	public void setThisClass(short thisClass) {
		this.thisClass = thisClass;
	}

	public short getSuperClass() {
		return superClass;
	}

	public void setSuperClass(short superClass) {
		this.superClass = superClass;
	}

	public ConstantPoolInfo[] getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(ConstantPoolInfo[] constantPool) {
		this.constantPool = constantPool;
	}

	public short getInterfacesCount() {
		return interfacesCount;
	}

	public void setInterfacesCount(short interfacesCount) {
		this.interfacesCount = interfacesCount;
	}

	public short getFieldsCount() {
		return fieldsCount;
	}

	public void setFieldsCount(short fieldsCount) {
		this.fieldsCount = fieldsCount;
	}

	public short getMethodsCount() {
		return methodsCount;
	}

	public void setMethodsCount(short methodsCount) {
		this.methodsCount = methodsCount;
	}

	public short getAttributesCount() {
		return attributesCount;
	}

	public void setAttributesCount(short attributesCount) {
		this.attributesCount = attributesCount;
	}

	public short[] getInterfacesIndex() {
		return interfacesIndex;
	}

	public void setInterfacesIndex(short[] interfacesIndex) {
		this.interfacesIndex = interfacesIndex;
	}

	public FieldInfo[] getFields() {
		return fields;
	}

	public void setFields(FieldInfo[] fields) {
		this.fields = fields;
	}

	public MethodInfo[] getMethods() {
		return methods;
	}

	public void setMethods(MethodInfo[] methods) {
		this.methods = methods;
	}

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}

	public void accept(ClassVisitor visitor) {
		visitor.visitClassFile(this);
	}

}
