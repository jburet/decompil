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
import model.code.operand.impl.InvocationOperandResult;
import model.code.operand.impl.SimpleVariable;

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
