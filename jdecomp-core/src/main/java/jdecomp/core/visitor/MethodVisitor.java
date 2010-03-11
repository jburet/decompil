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

public interface MethodVisitor {

	public void visitMethodInstruction(MethodInstruction mi);

	public void visitStatementInstruction(StatementInstruction statementInstruction);

	public void visitConditionalBranching(ConditionalBrancheInstruction ifBlock);

	public void visitAssignation(AssignationInstruction assignationInstruction);

	public void visitReturn(ReturnInstruction returnInstruction);

	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction);

	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching);

	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction);

	/**
	 * @param switchInstruction
	 */
	public void visitSwitch(SwitchInstruction switchInstruction);

	/**
	 * @param staticMethodInvocationInstruction
	 */
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction);
}
