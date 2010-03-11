package jdecomp.core.visitor;

import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;

public interface OperandVisitor<R> {

	R visitObjectReference(ObjectReference objectReference);

	R visitArithmethicOperation(ArithmeticOperation arithmeticOperation);

	R visitArrayReference(Array arrayReference);

	R visitConditionalOperation(ConditionalOperation conditionalOperation);

	R visitConstant(Constant constant);

	R visitVariable(Variable variable);

	R visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction);

}
