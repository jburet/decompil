package jdecomp.visualisation;

import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.visualisation.view.MethodFlowFactory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

public class View extends ViewPart {
	public static final String ID = "jdecomp.visualisation.view";
	private MethodFlowFactory mff = new MethodFlowFactory();
	private ByteCodeReader bci = new ByteCodeReader();
	private OpCodeInterpreter cd = new OpCodeInterpreter();

	private int layout = 1;

	@Override
	public void createPartControl(Composite parent) {
		// Graph will hold all other objects
		ClassFile cf = bci.readClassFile("d:/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		Graph graph = mff.constructGraph(mi, parent);
		// Now a few nodes

		// Lets have a directed connection
		// new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, node1,
		// node2);
		// Lets have a dotted graph connection
		// new GraphConnection(graph, ZestStyles.CONNECTIONS_DOT, node2, node3);
		// Standard connection
		// new GraphConnection(graph, SWT.NONE, node3, node1);
		// Change line color and line width
		// GraphConnection graphConnection = new GraphConnection(graph,
		// SWT.NONE, node1, node4);
		// graphConnection.changeLineColor(parent.getDisplay().getSystemColor(SWT.COLOR_GREEN));
		// Also set a text
		// graphConnection.setText("This is a text");
		// graphConnection.setHighlightColor(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
		// graphConnection.setLineWidth(3);
		// graphConnection.addListener(SWT.SELECTED, new Listener() {

		// @Override
		// public void handleEvent(Event event) {
		// System.out.println("Selected");
		// }

		// });
		graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
	}
}