package jdecomp.visualisation.view;

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
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.visitor.Visitor;

import org.eclipse.swt.SWT;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

public class GraphVisitor implements Visitor {

	private Graph graph;

	public GraphVisitor(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {

	}

	@Override
	public void visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitArrayReference(Array arrayReference) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		new GraphNode(graph, SWT.NONE, "visitAssignation");
	}

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction ifBlock) {
		new GraphNode(graph, SWT.NONE, "visitConditionalBranching");
	}

	@Override
	public void visitConditionalOperation(ConditionalOperation conditionalBlock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitConstant(Constant constant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMethodInstruction(MethodInstruction mi) {
		for (Instruction ins : mi.getInstructionsMap().values()) {
			ins.accept(this);
		}
	}

	@Override
	public void visitObjectReference(ObjectReference objectReference) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitStatementInstruction(StatementInstruction statementInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitSwitch(SwitchInstruction switchInstruction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitVariable(Variable variable) {
		// TODO Auto-generated method stub

	}

}
