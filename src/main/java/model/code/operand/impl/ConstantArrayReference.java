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

package model.code.operand.impl;

import java.util.ArrayList;
import java.util.List;

import model.code.operand.Array;
import model.code.operand.ArrayType;
import model.code.operand.Operand;
import model.constant.Type;
import visitor.Visitor;

/**
 * 
 * @author jburet
 * 
 *         Represente un tableau... Si le type de tableau est d'object.
 *         objectType est obligatoire et contient le type du tableau
 */
public class ConstantArrayReference implements Array {

	private final ArrayType arrayType;

	private Type objectType;

	private final Operand[] size;

	private List<Operand> values;

	private int dimension;

	public ConstantArrayReference(ArrayType arrayType, Operand... size) {
		this.arrayType = arrayType;
		this.dimension = size.length;
		this.size = size;
	}

	public ConstantArrayReference(ArrayType arrayType, Type objectType, Operand... size) {
		this.arrayType = arrayType;
		this.dimension = size.length;
		this.size = size;
		this.objectType = objectType;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayReference(this);
	}

	public ArrayType getArrayType() {
		return arrayType;
	}

	public Operand[] getSize() {
		return size;
	}

	public Type getObjectType() {
		return objectType;
	}

	public List<Operand> getValues() {
		return this.values;
	}

	public void addValue(Operand index, Operand obj) {
		if (this.values == null) {
			this.values = new ArrayList<Operand>();
		}
		if (index instanceof Constant) {
			this.values.add(Integer.parseInt(((Constant) index).getValue()), obj);
		} else {
			this.values.add(obj);
		}
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
