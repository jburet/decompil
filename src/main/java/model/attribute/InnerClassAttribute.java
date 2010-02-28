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

package model.attribute;

public class InnerClassAttribute extends Attribute {

	private short numberOfClasses;

	private ClassAttribute[] classes;

	public InnerClassAttribute() {

	}

	public InnerClassAttribute(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(short numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

	public ClassAttribute[] getClasses() {
		return classes;
	}

	public void setClasses(ClassAttribute[] classes) {
		this.classes = classes;
	}
}
