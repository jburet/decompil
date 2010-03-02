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

import jdecomp.core.model.code.OpCodes;
import jdecomp.core.model.code.operand.ConditionalOperator;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.visitor.Visitor;

/**
 * 
 * @author jburet Block If
 * 
 */
public class ConditionalBrancheInstruction extends Instruction {

	/**
	 * Opcode correspondant a la generation du block
	 */
	private OpCodes opc;

	private int currentIndex;

	private int branchIndex;

	private ConditionalOperation condition;

	public ConditionalBrancheInstruction(ConditionalOperator co, int currentIndex, int branchIndex, Operand operand1,
			Operand operand2) {
		super(currentIndex);
		this.condition = new ConditionalOperation(operand1, operand2, co);
		this.currentIndex = currentIndex;
		this.branchIndex = branchIndex;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitConditionalBranching(this);
	}

	public OpCodes getOpc() {
		return opc;
	}

	public ConditionalOperation getCondition() {
		return condition;
	}

	@Override
	public int getCurrentIndex() {
		return currentIndex;
	}

	public int getBranchIndex() {
		return branchIndex;
	}

}
