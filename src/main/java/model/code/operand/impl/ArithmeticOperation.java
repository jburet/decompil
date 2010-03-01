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

import model.code.operand.ArithmeticOperationType;
import model.code.operand.Operand;
import visitor.Visitor;

/**
 * @author jburet
 * 
 */
public class ArithmeticOperation implements Operand {

	private Operand op1;
	private Operand op2;
	private ArithmeticOperationType type;

	public ArithmeticOperation(Operand op2, Operand op1, ArithmeticOperationType type) {
		super();
		this.op1 = op1;
		this.op2 = op2;
		this.type = type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArithmethicOperation(this);
	}

	public Operand getOp1() {
		return op1;
	}

	public Operand getOp2() {
		return op2;
	}

	public ArithmeticOperationType getType() {
		return type;
	}

}
