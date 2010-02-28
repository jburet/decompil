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

public class LocalVariableTableAttribute extends Attribute {

	private short numberOfLocalVariable;

	private LocalVariable[] localVariables;

	public LocalVariableTableAttribute(Attribute att) {
		this.setNameIndex(att.getNameIndex());
		this.setLength(att.getLength());
		this.setTypeName(att.getTypeName());
		this.setAttributeType(att.getAttributeType());
	}

	public short getNumberOfLocalVariable() {
		return numberOfLocalVariable;
	}

	public void setNumberOfLocalVariable(short numberOfLocalVariable) {
		this.numberOfLocalVariable = numberOfLocalVariable;
	}

	public LocalVariable[] getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(LocalVariable[] localVariables) {
		this.localVariables = localVariables;
	}
}
