/**
 *
 */
package jdecomp.plugin.complexity;

import static org.junit.Assert.assertNotNull;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jburet
 * 
 */
public class TestCyclomaticComplexity {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;
	private MethodComplexityVisitor mcv;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
		mcv = new MethodComplexityVisitor();
	}

	@Test
	public void testIntegration() {
		ClassFile cf = bci
				.readClassFile("../jdecomp-core/target/classes/jdecomp/core/generator/impl/ClassGenerator.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		mcv.visitMethodInstruction(mi);
		System.out.println("Nodes : " + mcv.getNodes());
		System.out.println("Edges : " + mcv.getEdges());
		System.out.println("v(G) : " + (mcv.getEdges() - mcv.getNodes() + 1));
	}
}
