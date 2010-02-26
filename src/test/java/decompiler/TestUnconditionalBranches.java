package decompiler;

import static org.junit.Assert.assertNotNull;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;
import model.classes.ClassFile;
import model.code.instruction.MethodInstruction;

import org.junit.Before;
import org.junit.Test;

import visitor.JavaResumeVisitor;

public class TestUnconditionalBranches {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;
	private JavaResumeVisitor jrv;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
		jrv = new JavaResumeVisitor();
	}

	@Test
	public void testBoucleFor() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleInfiniteWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleDoWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleForBreak() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}
}
