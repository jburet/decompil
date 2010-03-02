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

package jdecomp.core.visitor;

import jdecomp.core.model.code.instruction.AssignationArrayInstruction;
import jdecomp.core.model.code.instruction.AssignationInstruction;
import jdecomp.core.model.code.instruction.ConditionalBrancheInstruction;
import jdecomp.core.model.code.instruction.InstanceMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.model.code.instruction.StatementInstruction;
import jdecomp.core.model.code.instruction.StaticMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.SwitchInstruction;
import jdecomp.core.model.code.instruction.UnconditionalBranching;
import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;

public interface Visitor {

	public void visitMethodInstruction(MethodInstruction mi);

	public void visitStatementInstruction(StatementInstruction statementInstruction);

	public void visitConditionalBranching(ConditionalBrancheInstruction ifBlock);

	public void visitVariable(Variable variable);

	public void visitConditionalOperation(ConditionalOperation conditionalBlock);

	public void visitConstant(Constant constant);

	public void visitAssignation(AssignationInstruction assignationInstruction);

	public void visitReturn(ReturnInstruction returnInstruction);

	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction);

	public void visitObjectReference(ObjectReference objectReference);

	public void visitArrayReference(Array arrayReference);

	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction);

	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching);

	public void visitArithmethicOperation(ArithmeticOperation arithmeticOperation);

	public void visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction);

	/**
	 * @param switchInstruction
	 */
	public void visitSwitch(SwitchInstruction switchInstruction);

	/**
	 * @param staticMethodInvocationInstruction
	 */
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction);
}
