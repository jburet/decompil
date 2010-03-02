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
import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.visitor.Visitor;


public class JavaResumeVisitor implements Visitor {

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction conditionalBranching) {
		System.out.print("if(");
		conditionalBranching.getCondition().accept(this);
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
	public void visitConditionalOperation(ConditionalOperation conditionalBlock) {
		conditionalBlock.getOperand1().accept(this);
		System.out.print(" " + conditionalBlock.getCo().getOp() + " ");
		conditionalBlock.getOperand2().accept(this);
	}

	@Override
	public void visitConstant(Constant constant) {
		System.out.print(constant.getValue());
	}

	@Override
	public void visitVariable(Variable variable) {
		System.out.print(variable.getName());
	}

	@Override
	public void visitObjectReference(ObjectReference objectReference) {
		System.out.print(objectReference.getName());
	}

	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		System.out.print(assignationInstruction.getVarName() + " = ");
		assignationInstruction.getValue().accept(this);
		System.out.println(";");
	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		if (assignationArrayInstruction.getArrayRef() instanceof ArrayReference
				&& ((ArrayReference) assignationArrayInstruction.getArrayRef()).getName() != null) {
			assignationArrayInstruction.getArrayRef().accept(this);
			System.out.print("[");
			assignationArrayInstruction.getIndex().accept(this);
			System.out.print("] = ");
			assignationArrayInstruction.getValue().accept(this);
			System.out.println(";");
		} else {
			// TODO On stocke les assignations dans la reference du tableau
		}
	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		System.out.print("return ");
		if (returnInstruction.getOperand() != null) {
			returnInstruction.getOperand().accept(this);
		}
		System.out.println(";");
	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		instanceMethodInvocationInstruction.getIntance().accept(this);
		System.out.print("." + instanceMethodInvocationInstruction.getMethodName() + "(");
		for (int i = instanceMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			instanceMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				System.out.print(", ");
			}
		}
		System.out.println(");");
	}

	@Override
	public void visitArrayReference(Array arrayReference) {
		if (arrayReference instanceof ArrayReference && ((ArrayReference) arrayReference).getName() != null) {
			System.out.print(((ArrayReference) arrayReference).getName());
		} else if (arrayReference instanceof ConstantArrayReference) {
			// TODO Sinon on affiche les assignations stockes
			System.out.print("new ");
			System.out.print(arrayReference.getObjectType());
			System.out.print("[] ");
			System.out.print("{");
			// FIXME On doit utilise la taille de l'array reference et non pas
			// la taille de la liste
			for (int i = 0; i < ((ConstantArrayReference) arrayReference).getValues().size(); i++) {
				if (((ConstantArrayReference) arrayReference).getValues().get(i) != null) {
					((ConstantArrayReference) arrayReference).getValues().get(i).accept(this);
				} else {
					// FIXME (null ou la valeur par defaut si type primitif
					System.out.print("null ");
				}
				if (((ConstantArrayReference) arrayReference).getValues().size() - i > 1) {
					System.out.print(", ");
				}
			}
			System.out.print("}");
		}
	}

	@Override
	public void visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {
		arithmeticOperation.getOp1().accept(this);
		System.out.print(arithmeticOperation.getType().getSign());
		arithmeticOperation.getOp2().accept(this);
	}

	@Override
	public void visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		arrayAccessInstruction.getArrayReference().accept(this);
		System.out.print("[");
		arrayAccessInstruction.getIndex().accept(this);
		System.out.print("]");
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
		switchInstruction.getIndex().accept(this);
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
			staticMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				System.out.print(", ");
			}
		}
		System.out.println(");");
	}

}
