package jdecomp.visualisation.view;

import jdecomp.core.model.code.instruction.MethodInstruction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.widgets.Graph;

public class MethodFlowFactory {

	public Graph constructGraph(MethodInstruction methodInstruction, Composite parent) {
		Graph graph = new Graph(parent, SWT.NONE);
		GraphVisitor graphVisitor = new GraphVisitor(graph);
		methodInstruction.accept(graphVisitor);
		return graph;
	}
}
