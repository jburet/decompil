package jdecomp.visualisation.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;

public class FlowGraphVisitor implements Visitor {

	private Graph graph;

	private short currentPos = -1;

	private boolean noConnection = false;

	Map<Short, GraphNode> graphNodes = new HashMap<Short, GraphNode>();
	Map<Short, Set<Short>> graphConnection = new HashMap<Short, Set<Short>>();

	public FlowGraphVisitor(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void visitArithmethicOperation(ArithmeticOperation arg0) {

	}

	@Override
	public void visitArrayAccessInstruction(ArrayAccessInstruction arg0) {

	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction arg0) {
		createNode(currentPos, arg0);
	}

	@Override
	public void visitArrayReference(Array arg0) {

	}

	@Override
	public void visitAssignation(AssignationInstruction arg0) {
		createNode(currentPos, arg0);

	}

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction arg0) {
		createNode(currentPos, arg0);
		// Ajout d'une autre connection
		addConnection(currentPos, (short) arg0.getBranchIndex());
	}

	@Override
	public void visitConditionalOperation(ConditionalOperation arg0) {

	}

	@Override
	public void visitConstant(Constant arg0) {

	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction arg0) {
		createNode(currentPos, arg0);

	}

	@Override
	public void visitMethodInstruction(MethodInstruction methodInstruction) {
		// On creer tous les nodes
		for (Entry<Short, Instruction> ins : methodInstruction.getInstructionsMap().entrySet()) {
			if (currentPos != -1) {
				// Connection par defaut
				if (!noConnection) {
					addConnection(currentPos, ins.getKey());
				} else {
					noConnection = false;
				}
			}
			currentPos = ins.getKey();
			ins.getValue().accept(this);
		}
		// A la fin on cree les connections
		for (Entry<Short, Set<Short>> connections : graphConnection.entrySet()) {
			short currentIdx = connections.getKey();
			for (short destIdx : connections.getValue()) {
				new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, graphNodes.get(currentIdx), graphNodes
						.get(getRealIndx(destIdx)));
			}
		}
	}

	private short getRealIndx(short destIdx) {
		TreeSet<Short> key = new TreeSet<Short>(graphNodes.keySet());
		if (destIdx > key.last()) {
			return key.last();
		} else if (key.contains(destIdx)) {
			return destIdx;
		} else {
			return key.higher(destIdx);
		}
	}

	@Override
	public void visitObjectReference(ObjectReference arg0) {

	}

	@Override
	public void visitReturn(ReturnInstruction arg0) {
		createNode(currentPos, arg0);
		noConnection = true;
	}

	@Override
	public void visitStatementInstruction(StatementInstruction arg0) {
		createNode(currentPos, arg0);

	}

	@Override
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction arg0) {
		createNode(currentPos, arg0);

	}

	@Override
	public void visitSwitch(SwitchInstruction arg0) {
		createNode(currentPos, arg0);

	}

	@Override
	public void visitUnconditionalBranching(UnconditionalBranching arg0) {
		createNode(currentPos, arg0);
		noConnection = true;
		addConnection(currentPos, (short) arg0.getBranchIndex());
	}

	@Override
	public void visitVariable(Variable arg0) {

	}

	private void createNode(short currentPos, Instruction ins) {
		graphNodes.put(currentPos, new GraphNode(graph, SWT.NONE, ins.getCurrentIndex() + " : "
				+ ins.getClass().getSimpleName()));
	}

	private void addConnection(short precPos, Short currentPos) {
		Set<Short> dest = graphConnection.get(precPos);
		if (dest == null) {
			dest = new HashSet<Short>();
			graphConnection.put(precPos, dest);
		}
		dest.add(currentPos);
	}

}
