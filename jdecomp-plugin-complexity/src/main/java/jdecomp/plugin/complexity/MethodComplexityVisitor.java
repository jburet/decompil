/**
 *
 */
package jdecomp.plugin.complexity;

import java.util.Map.Entry;

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
import jdecomp.core.visitor.MethodVisitor;

/**
 * @author jburet
 * 
 */
public class MethodComplexityVisitor implements MethodVisitor {

	private int nodes;
	private int edges;
	private boolean noConnection = false;

	public MethodComplexityVisitor() {
		nodes = 1;
		edges = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitArrayAssignation(jdecomp.core.model
	 * .code.instruction.AssignationArrayInstruction)
	 */
	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitAssignation(jdecomp.core.model.code
	 * .instruction.AssignationInstruction)
	 */
	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitConditionalBranching(jdecomp.core.model
	 * .code.instruction.ConditionalBrancheInstruction)
	 */
	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction ifBlock) {
		edges++;
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitInstanceMethodInvocation(jdecomp.core
	 * .model.code.instruction.InstanceMethodInvocationInstruction)
	 */
	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitMethodInstruction(jdecomp.core.model
	 * .code.instruction.MethodInstruction)
	 */
	@Override
	public void visitMethodInstruction(MethodInstruction methodInstruction) {
		for (Entry<Short, Instruction> ins : methodInstruction.getInstructionsMap().entrySet()) {
			if (!noConnection) {
				edges++;
			} else {
				noConnection = false;
			}
			ins.getValue().accept(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitReturn(jdecomp.core.model.code.instruction
	 * .ReturnInstruction)
	 */
	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitStatementInstruction(jdecomp.core.model
	 * .code.instruction.StatementInstruction)
	 */
	@Override
	public void visitStatementInstruction(StatementInstruction statementInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitStaticMethodInvocation(jdecomp.core
	 * .model.code.instruction.StaticMethodInvocationInstruction)
	 */
	@Override
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitSwitch(jdecomp.core.model.code.instruction
	 * .SwitchInstruction)
	 */
	@Override
	public void visitSwitch(SwitchInstruction switchInstruction) {
		nodes++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jdecomp.core.visitor.Visitor#visitUnconditionalBranching(jdecomp.core
	 * .model.code.instruction.UnconditionalBranching)
	 */
	@Override
	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching) {
		noConnection = true;
		edges++;
		nodes++;
	}

	public int getNodes() {
		return nodes;
	}

	public int getEdges() {
		return edges;
	}

}
