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

package jdecomp.core.model.code.operand.impl;

import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.constant.Type;
import jdecomp.core.visitor.MethodVisitor;

public class ArrayReference implements Array, Variable {

	private Operand operandReference;

	private String name;

	private Type type;

	private int dimension;

	public ArrayReference(Operand operandReference, String name, Type type, int dimension) {
		this.operandReference = operandReference;
		this.name = name;
		this.type = type;
		this.dimension = dimension;
	}

	public ArrayReference(ConstantArrayReference operand, String name) {
		this.type = operand.getObjectType();
		this.name = name;
		this.dimension = operand.getDimension();
	}

	@Override
	public void accept(MethodVisitor visitor) {
		visitor.visitArrayReference(this);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Operand getOperandReference() {
		return operandReference;
	}

	@Override
	public void addValue(Operand index, Operand value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getObjectType() {
		return this.type;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.code.operand.Array#getDimension()
	 */
	@Override
	public int getDimension() {
		return this.dimension;
	}

}
