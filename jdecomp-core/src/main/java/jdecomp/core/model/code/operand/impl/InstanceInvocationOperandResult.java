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

import jdecomp.core.model.code.instruction.InstanceMethodInvocationInstruction;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.OperandVisitor;

public class InstanceInvocationOperandResult implements Operand {

	private InstanceMethodInvocationInstruction instanceMethodInvocationInstruction;

	public InstanceInvocationOperandResult(InstanceMethodInvocationInstruction methodInvocation) {
		this.instanceMethodInvocationInstruction = methodInvocation;
	}

	@Override
	public <T> T accept(OperandVisitor<T> visitor) {
		return visitor.visitInstanceInvocationOperandResult(this);
	}

	public InstanceMethodInvocationInstruction getInstanceMethodInvocationInstruction() {
		return instanceMethodInvocationInstruction;
	}

}
