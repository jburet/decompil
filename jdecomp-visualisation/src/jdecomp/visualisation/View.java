package jdecomp.visualisation;

import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.visualisation.visitor.FlowGraphVisitor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class View extends ViewPart {
	public static final String ID = "jdecomp.visualisation.view";

	private Graph graph;
	private FlowGraphVisitor visitor;
	private ByteCodeReader bci = new ByteCodeReader();
	private OpCodeInterpreter cd = new OpCodeInterpreter();

	@Override
	public void createPartControl(Composite parent) {
		ClassFile cf = bci
				.readClassFile("/Users/jburet/Documents/workspace/jdecomp-app/jdecomp-core/target/classes/jdecomp/core/generator/impl/ClassGenerator.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		graph = new Graph(parent, SWT.NONE);
		visitor = new FlowGraphVisitor(graph);
		visitor.visitMethodInstruction(mi);
		LayoutAlgorithm layout = setLayout();
		graph.setLayoutAlgorithm(layout, true);
		graph.applyLayout();
	}

	private LayoutAlgorithm setLayout() {
		LayoutAlgorithm layout;
		// layout = new
		// SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		layout = new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// We will filter on the nodes
		return layout;

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
	}

}
