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

package jdecomp.core.model.code.instruction;

import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.visitor.Visitor;

public class AssignationArrayInstruction extends Instruction {

	private Operand arrayRef;
	private Operand value;
	private Operand index;

	public AssignationArrayInstruction(short currentIndex, Operand value, Operand index, Operand objectReference) {
		super(currentIndex);
		this.arrayRef = objectReference;
		this.value = value;
		if (arrayRef instanceof ConstantArrayReference) {
			((ConstantArrayReference) this.arrayRef).addValue(index, value);
		}
		this.index = index;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayAssignation(this);
	}

	public Operand getIndex() {
		return index;
	}

	public Operand getArrayRef() {
		return arrayRef;
	}

	public Operand getValue() {
		return value;
	}
}
