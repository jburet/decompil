package jdecomp.plugin.java;

import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.visitor.OperandVisitor;

public class JavaOperandVisitor implements OperandVisitor<String> {

	@Override
	public String visitConditionalOperation(ConditionalOperation conditionalBlock) {
		StringBuffer sb = new StringBuffer();
		conditionalBlock.getOperand1().accept(this);
		sb.append(" " + conditionalBlock.getCo().getOp() + " ");
		conditionalBlock.getOperand2().accept(this);
		return sb.toString();
	}

	@Override
	public String visitConstant(Constant constant) {
		StringBuffer sb = new StringBuffer();
		sb.append(constant.getValue());
		return sb.toString();
	}

	@Override
	public String visitVariable(Variable variable) {
		StringBuffer sb = new StringBuffer();
		sb.append(variable.getName());
		return sb.toString();
	}

	@Override
	public String visitObjectReference(ObjectReference objectReference) {
		StringBuffer sb = new StringBuffer();
		sb.append(objectReference.getName());
		return sb.toString();
	}

	@Override
	public String visitArrayReference(Array arrayReference) {
		StringBuffer sb = new StringBuffer();
		if (arrayReference instanceof ArrayReference && ((ArrayReference) arrayReference).getName() != null) {
			sb.append(((ArrayReference) arrayReference).getName());
		} else if (arrayReference instanceof ConstantArrayReference) {
			// TODO Sinon on affiche les assignations stockes
			sb.append("new ");
			sb.append(ArrayTypeDecompiler.getJavaType(arrayReference.getType()));
			for (int i = 0; i < arrayReference.getDimension(); i++) {
				sb.append("[");
				sb.append(((ConstantArrayReference) arrayReference).getSize()[i]);
				sb.append("] ");
			}
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
		return sb.toString();
	}

	@Override
	public String visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {
		StringBuffer sb = new StringBuffer();
		arithmeticOperation.getOp1().accept(this);
		sb.append(arithmeticOperation.getType().getSign());
		arithmeticOperation.getOp2().accept(this);
		return sb.toString();
	}

	@Override
	public String visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		StringBuffer sb = new StringBuffer();
		arrayAccessInstruction.getArrayReference().accept(this);
		sb.append("[");
		arrayAccessInstruction.getIndex().accept(this);
		sb.append("]");
		return sb.toString();
	}

}
