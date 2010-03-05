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
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.ConditionalOperation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
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

	private StringBuffer tmpInstruction = new StringBuffer();

	Map<Short, GraphNode> graphNodes = new HashMap<Short, GraphNode>();
	Map<Short, Set<Short>> graphConnection = new HashMap<Short, Set<Short>>();

	public FlowGraphVisitor(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void visitArithmethicOperation(ArithmeticOperation arithmeticOperation) {
		arithmeticOperation.getOp1().accept(this);
		tmpInstruction.append(arithmeticOperation.getType().getSign());
		arithmeticOperation.getOp2().accept(this);
	}

	@Override
	public void visitArrayAccessInstruction(ArrayAccessInstruction arrayAccessInstruction) {
		arrayAccessInstruction.getArrayReference().accept(this);
		tmpInstruction.append("[");
		arrayAccessInstruction.getIndex().accept(this);
		tmpInstruction.append("]");
	}

	@Override
	public void visitArrayAssignation(AssignationArrayInstruction assignationArrayInstruction) {

		assignationArrayInstruction.getArrayRef().accept(this);
		tmpInstruction.append("[");
		assignationArrayInstruction.getIndex().accept(this);
		tmpInstruction.append("] = ");
		assignationArrayInstruction.getValue().accept(this);
		tmpInstruction.append(";");

		createNode(currentPos, assignationArrayInstruction);
	}

	@Override
	public void visitArrayReference(Array arrayReference) {
		if (arrayReference instanceof ArrayReference && ((ArrayReference) arrayReference).getName() != null) {
			tmpInstruction.append(((ArrayReference) arrayReference).getName());
		} else if (arrayReference instanceof ConstantArrayReference) {
			// TODO Sinon on affiche les assignations stockes
			tmpInstruction.append("new ");
			tmpInstruction.append(arrayReference.getObjectType());
			tmpInstruction.append("[] ");
			if (((ConstantArrayReference) arrayReference).getValues() != null) {
				tmpInstruction.append("{");
				// FIXME On doit utilise la taille de l'array reference et non
				// pas
				// la taille de la liste
				for (int i = 0; i < ((ConstantArrayReference) arrayReference).getValues().size(); i++) {
					if (((ConstantArrayReference) arrayReference).getValues().get(i) != null) {
						((ConstantArrayReference) arrayReference).getValues().get(i).accept(this);
					} else {
						// FIXME (null ou la valeur par defaut si type primitif
						tmpInstruction.append("null ");
					}
					if (((ConstantArrayReference) arrayReference).getValues().size() - i > 1) {
						tmpInstruction.append(", ");
					}
				}
				tmpInstruction.append("}");
			}
		}
	}

	@Override
	public void visitAssignation(AssignationInstruction assignationInstruction) {
		tmpInstruction.append(assignationInstruction.getVarName() + " = ");
		assignationInstruction.getValue().accept(this);
		tmpInstruction.append(";");
		createNode(currentPos, assignationInstruction);
	}

	@Override
	public void visitConditionalBranching(ConditionalBrancheInstruction conditionalBranching) {
		tmpInstruction.append("if(");
		conditionalBranching.getCondition().accept(this);
		tmpInstruction.append(") goto : " + conditionalBranching.getBranchIndex());
		createNode(currentPos, conditionalBranching);
		// Ajout d'une autre connection
		addConnection(currentPos, (short) conditionalBranching.getBranchIndex());
	}

	@Override
	public void visitConditionalOperation(ConditionalOperation conditionalBlock) {
		conditionalBlock.getOperand1().accept(this);
		tmpInstruction.append(" " + conditionalBlock.getCo().getOp() + " ");
		conditionalBlock.getOperand2().accept(this);
	}

	@Override
	public void visitConstant(Constant constant) {
		tmpInstruction.append(constant.getValue());
	}

	@Override
	public void visitInstanceMethodInvocation(InstanceMethodInvocationInstruction instanceMethodInvocationInstruction) {
		instanceMethodInvocationInstruction.getIntance().accept(this);
		tmpInstruction.append("." + instanceMethodInvocationInstruction.getMethodName() + "(");
		for (int i = instanceMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			instanceMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				tmpInstruction.append(", ");
			}
		}
		tmpInstruction.append(");");
		createNode(currentPos, instanceMethodInvocationInstruction);
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
	public void visitObjectReference(ObjectReference objectReference) {
		tmpInstruction.append(objectReference.getName());
	}

	@Override
	public void visitReturn(ReturnInstruction returnInstruction) {
		tmpInstruction.append("return ");
		if (returnInstruction.getOperand() != null) {
			returnInstruction.getOperand().accept(this);
		}
		tmpInstruction.append(";");
		createNode(currentPos, returnInstruction);
		noConnection = true;
	}

	@Override
	public void visitStatementInstruction(StatementInstruction statementInstruction) {
		tmpInstruction.append(statementInstruction.getOpCode().name());
		createNode(currentPos, statementInstruction);

	}

	@Override
	public void visitStaticMethodInvocation(StaticMethodInvocationInstruction staticMethodInvocationInstruction) {
		tmpInstruction.append(staticMethodInvocationInstruction.getClassName() + "."
				+ staticMethodInvocationInstruction.getMethodName() + "(");
		for (int i = staticMethodInvocationInstruction.getArgs().length - 1; i >= 0; i--) {
			staticMethodInvocationInstruction.getArgs()[i].accept(this);
			if (i > 0) {
				tmpInstruction.append(", ");
			}
		}
		tmpInstruction.append(");");
		createNode(currentPos, staticMethodInvocationInstruction);

	}

	@Override
	public void visitSwitch(SwitchInstruction switchInstruction) {
		tmpInstruction.append("switch(");
		switchInstruction.getIndex().accept(this);
		tmpInstruction.append(") {");
		for (int i = 0; i < switchInstruction.getMatch().length; i++) {
			tmpInstruction.append("case " + switchInstruction.getMatch()[i] + ": ");
			tmpInstruction.append("goto : " + switchInstruction.getJumpOffset()[i] + ";");
		}
		tmpInstruction.append("default: ");
		tmpInstruction.append("goto : " + switchInstruction.getDefaultIndex() + ";");
		createNode(currentPos, switchInstruction);
	}

	@Override
	public void visitUnconditionalBranching(UnconditionalBranching unconditionalBranching) {
		tmpInstruction.append("goto : " + unconditionalBranching.getBranchIndex());
		createNode(currentPos, unconditionalBranching);
		noConnection = true;
		addConnection(currentPos, (short) unconditionalBranching.getBranchIndex());
	}

	@Override
	public void visitVariable(Variable variable) {
		tmpInstruction.append(variable.getName());
	}

	private void createNode(short currentPos, Instruction ins) {
		graphNodes.put(currentPos, new GraphNode(graph, SWT.NONE, ins.getCurrentIndex() + " : "
				+ tmpInstruction.toString()));
		tmpInstruction = new StringBuffer();
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
