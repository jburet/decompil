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

import java.util.ArrayList;
import java.util.List;

import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ArrayType;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.OperandVisitor;

/**
 * 
 * @author jburet
 * 
 *         Represente un tableau... Si le type de tableau est d'object.
 *         objectType est obligatoire et contient le type du tableau
 */
public class ConstantArrayReference implements Array {

	private ArrayType type;

	private Operand[] size;

	private List<Operand> values;

	private int dimension;

	public ConstantArrayReference(ArrayType arrayType, Operand... size) {
		this.type = arrayType;
		this.dimension = size.length;
		this.size = size;
	}

	@Override
	public <T> T accept(OperandVisitor<T> visitor) {
		return visitor.visitArrayReference(this);
	}

	public ArrayType getType() {
		return type;
	}

	public Operand[] getSize() {
		return size;
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
