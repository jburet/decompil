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
import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.visitor.MethodVisitor;

public class JavaCodeVisitor implements MethodVisitor {

	private List<JavaSourceInstruction> javaIns = new ArrayList<JavaSourceInstruction>();

	public List<JavaSourceInstruction> getJavaIns() {
		return javaIns;
	}

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction conditionalBranching) {
		StringBuffer sb = new StringBuffer();
		sb.append("if(");
		conditionalBranching.getCondition().accept(this);
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
	public void visitConditionalOperation(ConditionalOperation conditionalBlock) {
		StringBuffer sb = new StringBuffer();
		conditionalBlock.getOperand1().accept(this);
		sb.append(" " + conditionalBlock.getCo().getOp() + " ");
		conditionalBlock.getOperand2().accept(this);
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitConstant(Constant constant) {
		StringBuffer sb = new StringBuffer();
		sb.append(constant.getValue());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitVariable(Variable variable) {
		StringBuffer sb = new StringBuffer();
		sb.append(variable.getName());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitObjectReference(ObjectReference objectReference) {
		StringBuffer sb = new StringBuffer();
		sb.append(objectReference.getName());
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append(assignationInstruction.getVarName() + " = ");
		assignationInstruction.getValue().accept(this);
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		StringBuffer sb = new StringBuffer();
		if (assignationArrayInstruction.getArrayRef() instanceof ArrayReference
				&& ((ArrayReference) assignationArrayInstruction.getArrayRef()).getName() != null) {
			assignationArrayInstruction.getArrayRef().accept(this);
			sb.append("[");
			assignationArrayInstruction.getIndex().accept(this);
			sb.append("] = ");
			assignationArrayInstruction.getValue().accept(this);
		} else {
			// TODO On stocke les assignations dans la reference du tableau
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		StringBuffer sb = new StringBuffer();
		sb.append("return ");
		if (returnInstruction.getOperand() != null) {
			returnInstruction.getOperand().accept(this);
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		StringBuffer sb = new StringBuffer();
		instanceMethodInvocationInstruction.getIntance().accept(this);
		sb.append("." + instanceMethodInvocationInstruction.getMethodName() + "(");
		for (int i = instanceMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			instanceMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				sb.append(", ");
			}
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitArrayReference(Array arrayReference) {
		StringBuffer sb = new StringBuffer();
		if (arrayReference instanceof ArrayReference && ((ArrayReference) arrayReference).getName() != null) {
			sb.append(((ArrayReference) arrayReference).getName());
		} else if (arrayReference instanceof ConstantArrayReference) {
			// TODO Sinon on affiche les assignations stockes
			sb.append("new ");
			sb.append(arrayReference.getObjectType());
			sb.append("[] ");
			if (((ConstantArrayReference) arrayReference).getValues() != null) {
				sb.append("{");
				// FIXME On doit utilise la taille de l'array reference et non
				// pas
				// la taille de la liste
				for (int i = 0; i < ((ConstantArrayReference) arrayReference).getValues().size(); i++) {
					if (((ConstantArrayReference) arrayReference).getValues().get(i) != null) {
						((ConstantArrayReference) arrayReference).getValues().get(i).accept(this);
					} else {
						// FIXME (null ou la valeur par defaut si type primitif
						sb.append("null ");
					}
					if (((ConstantArrayReference) arrayReference).getValues().size() - i > 1) {
						sb.append(", ");
					}
				}
				sb.append("}");
			}
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {
		StringBuffer sb = new StringBuffer();
		arithmeticOperation.getOp1().accept(this);
		sb.append(arithmeticOperation.getType().getSign());
		arithmeticOperation.getOp2().accept(this);
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

	@Override
	public void visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		StringBuffer sb = new StringBuffer();
		arrayAccessInstruction.getArrayReference().accept(this);
		sb.append("[");
		arrayAccessInstruction.getIndex().accept(this);
		sb.append("]");
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
		switchInstruction.getIndex().accept(this);
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
			staticMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				sb.append(", ");
			}
		}
		javaIns.add(new JavaSourceInstruction(sb.toString()));
	}

}
