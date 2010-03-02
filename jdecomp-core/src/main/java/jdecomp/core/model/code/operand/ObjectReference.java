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

package jdecomp.core.model.code.operand;

import jdecomp.core.model.code.operand.impl.TypeDefinedByString;
import jdecomp.core.model.constant.Type;
import jdecomp.core.visitor.Visitor;

public class ObjectReference implements Variable {

	public static ObjectReference NULL_REFERENCE = new ObjectReference(null, null, null,
			new TypeDefinedByString("null"));

	/**
	 * Instance of object reference
	 */
	private Operand operandReference;

	/**
	 * Class defining the ref
	 */
	private String classReference;

	/**
	 * Name of variable
	 */
	private String name;

	private Type type;

	public static ObjectReference getNULL_REFERENCE() {
		return NULL_REFERENCE;
	}

	public ObjectReference(Operand operandReference, String classReference, String name, Type type) {
		this.operandReference = operandReference;
		this.classReference = classReference;
		this.name = name;
		this.type = type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitObjectReference(this);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	public Operand getOperandReference() {
		return operandReference;
	}

	public String getClassReference() {
		return classReference;
	}

}
