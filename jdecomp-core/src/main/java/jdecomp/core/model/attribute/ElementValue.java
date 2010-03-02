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

public class ElementValue {

	private byte tag;
	private short constantValueIndex;

	// Enuum const value
	private short typeNameIndex;
	private short constantNameIndex;
	// fin enum const value
	private short classInfoIndex;
	private RuntimeParameterAnnotations annotationValue;
	// array value
	private short numValues;
	private ElementValue[] elementValue;

	// fin array value
	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public short getConstantValueIndex() {
		return constantValueIndex;
	}

	public void setConstantValueIndex(short constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}

	public short getTypeNameIndex() {
		return typeNameIndex;
	}

	public void setTypeNameIndex(short typeNameIndex) {
		this.typeNameIndex = typeNameIndex;
	}

	public short getConstantNameIndex() {
		return constantNameIndex;
	}

	public void setConstantNameIndex(short constantNameIndex) {
		this.constantNameIndex = constantNameIndex;
	}

	public short getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(short classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}

	public RuntimeParameterAnnotations getAnnotationValue() {
		return annotationValue;
	}

	public void setAnnotationValue(RuntimeParameterAnnotations annotationValue) {
		this.annotationValue = annotationValue;
	}

	public short getNumValues() {
		return numValues;
	}

	public void setNumValues(short numValues) {
		this.numValues = numValues;
	}

	public ElementValue[] getElementValue() {
		return elementValue;
	}

	public void setElementValue(ElementValue[] elementValue) {
		this.elementValue = elementValue;
	}
}
