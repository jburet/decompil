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

package visitor;

import model.code.instruction.AssignationArrayInstruction;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.ConditionalBrancheInstruction;
import model.code.instruction.InstanceMethodInvocationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.instruction.ReturnInstruction;
import model.code.instruction.StatementInstruction;
import model.code.instruction.StaticMethodInvocationInstruction;
import model.code.instruction.SwitchInstruction;
import model.code.instruction.UnconditionalBranching;
import model.code.operand.Array;
import model.code.operand.ObjectReference;
import model.code.operand.Variable;
import model.code.operand.impl.ArithmeticOperation;
import model.code.operand.impl.ArrayAccessInstruction;
import model.code.operand.impl.ConditionalOperation;
import model.code.operand.impl.Constant;

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
