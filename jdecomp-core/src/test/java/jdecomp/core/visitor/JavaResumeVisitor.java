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

import java.util.Map;

import jdecomp.core.model.code.instruction.AssignationArrayInstruction;
import jdecomp.core.model.code.instruction.AssignationInstruction;
import jdecomp.core.model.code.instruction.ConditionalBrancheInstruction;
import jdecomp.core.model.code.instruction.InstanceMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.Instruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.model.code.instruction.StatementInstruction;
import jdecomp.core.model.code.instruction.StaticMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.SwitchInstruction;
import jdecomp.core.model.code.instruction.UnconditionalBranching;
import jdecomp.core.model.code.operand.impl.ArrayReference;

public class JavaResumeVisitor implements MethodVisitor {

	private JavaResumeOperandVisitor javaResumeOperandVisitor = new JavaResumeOperandVisitor();

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction conditionalBranching) {
		System.out.print("if(");
		conditionalBranching.getCondition().accept(javaResumeOperandVisitor);
		System.out.println(") goto : " + conditionalBranching.getBranchIndex());
	}

	@Override
	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching) {
		System.out.println("goto : " + unconditionalBranching.getBranchIndex());
	}

	@Override
	public void visitStatementInstruction(StatementInstruction statementInstruction) {
		System.out.println(statementInstruction.getOpCode().name());
	}

	@Override
	public void visitMethodInstruction(MethodInstruction mi) {
		Map<Short, Instruction> instructionMap = mi.getInstructionsMap();
		for (short index : instructionMap.keySet()) {
			instructionMap.get(index).accept(this);
		}
	}

	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		System.out.print(assignationInstruction.getVarName() + " = ");
		assignationInstruction.getValue().accept(javaResumeOperandVisitor);
		System.out.println(";");
	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		if (assignationArrayInstruction.getArrayRef() instanceof ArrayReference
				&& ((ArrayReference) assignationArrayInstruction.getArrayRef()).getName() != null) {
			assignationArrayInstruction.getArrayRef().accept(javaResumeOperandVisitor);
			System.out.print("[");
			assignationArrayInstruction.getIndex().accept(javaResumeOperandVisitor);
			System.out.print("] = ");
			assignationArrayInstruction.getValue().accept(javaResumeOperandVisitor);
			System.out.println(";");
		} else {
			// TODO On stocke les assignations dans la reference du tableau
		}
	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		System.out.print("return ");
		if (returnInstruction.getOperand() != null) {
			returnInstruction.getOperand().accept(javaResumeOperandVisitor);
		}
		System.out.println(";");
	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		instanceMethodInvocationInstruction.getIntance().accept(javaResumeOperandVisitor);
		System.out.print("." + instanceMethodInvocationInstruction.getMethodName() + "(");
		for (int i = instanceMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			instanceMethodInvocationInstruction.getArgs()[i].accept(javaResumeOperandVisitor);
			if (i > 0) {
				System.out.print(", ");
			}
		}
		System.out.println(");");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seedecompiler.visitor.Visitor#visitSwitch(decompiler.instruction.
	 * SwitchInstruction)
	 */
	@Override
	public void visitSwitch(SwitchInstruction switchInstruction) {
		System.out.print("switch(");
		switchInstruction.getIndex().accept(javaResumeOperandVisitor);
		System.out.println(") {");
		for (int i = 0; i < switchInstruction.getMatch().length; i++) {
			System.out.println("case " + switchInstruction.getMatch()[i] + ": ");
			System.out.println("goto : " + switchInstruction.getJumpOffset()[i] + ";");
		}
		System.out.println("default: ");
		System.out.println("goto : " + switchInstruction.getDefaultIndex() + ";");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * decompiler.visitor.Visitor#visitStaticMethodInvocation(decompiler.instruction
	 * .StaticMethodInvocationInstruction)
	 */
	@Override
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction) {
		System.out.print(staticMethodInvocationInstruction.getClassName() + "."
				+ staticMethodInvocationInstruction.getMethodName() + "(");
		for (int i = staticMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			staticMethodInvocationInstruction.getArgs()[i].accept(javaResumeOperandVisitor);
			if (i > 0) {
				System.out.print(", ");
			}
		}
		System.out.println(");");
	}

}
