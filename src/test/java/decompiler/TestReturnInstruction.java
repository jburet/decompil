package decompiler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.MethodInstruction;
import model.code.instruction.ReturnInstruction;

import org.junit.Before;
import org.junit.Test;


import utils.debug.ClassFilePrinter;

public class TestReturnInstruction {
	private ByteCodeReader bci;
	private ClassFilePrinter cfp;
	private OpCodeInterpreter cd;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cfp = new ClassFilePrinter();
		cd = new OpCodeInterpreter();
	}

	@Test
	public void testReturnVoid() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNull(ri.getOperand());
	}
	
	@Test
	public void testReturnBoolean() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnByte() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnShort() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnInt() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnLong() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnChar() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnFloat() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
	
	@Test
	public void testReturnDouble() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get((short) mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
}
