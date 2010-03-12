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

package jdecomp.plugin.java;

import java.util.ArrayList;
import java.util.List;
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
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.visitor.MethodVisitor;

public class JavaCodeVisitor implements MethodVisitor {

	private List<JavaSourceInstruction> javaIns = new ArrayList<JavaSourceInstruction>();

	private JavaOperandVisitor javaOperandVisitor = new JavaOperandVisitor();

	public List<JavaSourceInstruction> getJavaIns() {
		return javaIns;
	}

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction conditionalBranching) {
		StringBuffer sb = new StringBuffer();
		sb.append("if(");
		sb.append(conditionalBranching.getCondition().accept(javaOperandVisitor));
		sb.append(") goto : " + conditionalBranching.getBranchIndex());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching) {
		StringBuffer sb = new StringBuffer();
		sb.append("goto : " + unconditionalBranching.getBranchIndex());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitStatementInstruction(StatementInstruction statementInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append(statementInstruction.getOpCode().name());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
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
		StringBuffer sb = new StringBuffer();
		sb.append(assignationInstruction.getVarName() + " = ");
		sb.append(assignationInstruction.getValue().accept(javaOperandVisitor));
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append("return ");
		if (returnInstruction.getOperand() != null) {
			sb.append(returnInstruction.getOperand().accept(javaOperandVisitor));
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append(instanceMethodInvocationInstruction.getIntance().accept(javaOperandVisitor));
		sb.append("." + instanceMethodInvocationInstruction.getMethodName() + "(");
		for (int i = instanceMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			sb.append(instanceMethodInvocationInstruction.getArgs()[i].accept(javaOperandVisitor));
			if (i > 0) {
				sb.append(", ");
			}
		}
		sb.append(")");
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seedecompiler.visitor.Visitor#visitSwitch(decompiler.instruction.
	 * SwitchInstruction)
	 */
	@Override
	public void visitSwitch(SwitchInstruction switchInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append("switch(");
		sb.append(switchInstruction.getIndex().accept(javaOperandVisitor));
		sb.append(") {");
		javaIns.add(new JavaSourceInstruction(sb.toString()));

		for (int i = 0; i < switchInstruction.getMatch().length; i++) {
			javaIns.add(new JavaSourceInstruction("case " + switchInstruction.getMatch()[i] + ": "));
			javaIns.add(new JavaSourceInstruction("goto : " + switchInstruction.getJumpOffset()[i] + ";"));
		}
		javaIns.add(new JavaSourceInstruction("default: "));
		javaIns.add(new JavaSourceInstruction("goto : " + switchInstruction.getDefaultIndex() + ";"));
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
		StringBuffer sb = new StringBuffer();
		sb.append(staticMethodInvocationInstruction.getClassName() + "."
				+ staticMethodInvocationInstruction.getMethodName() + "(");
		for (int i = staticMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			sb.append(staticMethodInvocationInstruction.getArgs()[i].accept(javaOperandVisitor));
			if (i > 0) {
				sb.append(", ");
			}
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		StringBuffer sb = new StringBuffer();
		if (!(assignationArrayInstruction.getArrayRef() instanceof ConstantArrayReference)) {
			sb.append(assignationArrayInstruction.getArrayRef().accept(javaOperandVisitor));
			sb.append("[");
			sb.append(assignationArrayInstruction.getIndex().accept(javaOperandVisitor));
			sb.append("] = ");
			sb.append(assignationArrayInstruction.getValue().accept(javaOperandVisitor));

			javaIns.add(new JavaSourceInstruction(sb.toString()));
		}
	}

}
