package jdecomp.core.visitor;

import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;

public class JavaResumeOperandVisitor implements OperandVisitor<Object> {

	@Override
	public Object visitConditionalOperation(ConditionalOperation conditionalBlock) {
		conditionalBlock.getOperand1().accept(this);
		System.out.print(" " + conditionalBlock.getCo().getOp() + " ");
		conditionalBlock.getOperand2().accept(this);
		return null;
	}

	@Override
	public Object visitConstant(Constant constant) {
		System.out.print(constant.getValue());
		return null;
	}

	@Override
	public Object visitVariable(Variable variable) {
		System.out.print(variable.getName());
		return null;
	}

	@Override
	public Object visitObjectReference(ObjectReference objectReference) {
		System.out.print(objectReference.getName());
		return null;
	}

	@Override
	public Object visitArrayReference(Array arrayReference) {
		if (arrayReference instanceof ArrayReference && ((ArrayReference) arrayReference).getName() != null) {
			System.out.print(((ArrayReference) arrayReference).getName());
		} else if (arrayReference instanceof ConstantArrayReference) {
			// TODO Sinon on affiche les assignations stockes
			System.out.print("new ");
			System.out.print(arrayReference.getType());
			System.out.print("[] ");
			if (((ConstantArrayReference) arrayReference).getValues() != null) {
				System.out.print("{");
				// FIXME On doit utilise la taille de l'array reference et non
				// pas
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
		return null;
	}

	@Override
	public Object visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {
		arithmeticOperation.getOp1().accept(this);
		System.out.print(arithmeticOperation.getType().getSign());
		arithmeticOperation.getOp2().accept(this);
		return null;
	}

	@Override
	public Object visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		arrayAccessInstruction.getArrayReference().accept(this);
		System.out.print("[");
		arrayAccessInstruction.getIndex().accept(this);
		System.out.print("]");
		return null;
	}

}
