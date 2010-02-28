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

package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;

public class AssignationInstruction extends Instruction {

	private String varName;
	private Operand value;

	public AssignationInstruction(short currentIndex, String varName, Operand value) {
		super(currentIndex);
		this.value = value;
		this.varName = varName;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitAssignation(this);
	}

	public String getVarName() {
		return varName;
	}

	public Operand getValue() {
		return value;
	}

}
