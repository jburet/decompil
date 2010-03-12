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

import jdecomp.core.model.code.operand.ConditionalOperator;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.OperandVisitor;

public class ConditionalOperation implements Operand {

	private Operand operand1;
	private Operand operand2;
	private ConditionalOperator co;

	public ConditionalOperation(Operand operand1, Operand operand2, ConditionalOperator co) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.co = co;
	}

	@Override
	public <T> T accept(OperandVisitor<T> visitor) {
		return visitor.visitConditionalOperation(this);
	}

	public Operand getOperand1() {
		return operand1;
	}

	public void setOperand1(Operand operand1) {
		this.operand1 = operand1;
	}

	public Operand getOperand2() {
		return operand2;
	}

	public void setOperand2(Operand operand2) {
		this.operand2 = operand2;
	}

	public ConditionalOperator getCo() {
		return co;
	}

	public void setCo(ConditionalOperator co) {
		this.co = co;
	}
}
