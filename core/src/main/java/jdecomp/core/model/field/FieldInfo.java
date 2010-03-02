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

package jdecomp.core.model.field;

import jdecomp.core.model.attribute.Attribute;


public class FieldInfo {
	private short accessFlag;
	private short nameIndex;
	private short descriptorIndex;
	private short attributeCount;
	private Attribute[] attributes;

	public short getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(short accessFlag) {
		this.accessFlag = accessFlag;
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
