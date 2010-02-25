package decompiler;

import static org.junit.Assert.*;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.impl.Constant;
import model.code.operand.impl.SimpleVariable;

import org.junit.Before;
import org.junit.Test;

import utils.debug.ClassFilePrinter;

public class TestPrimitiveAssignation {
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
	public void testConstantIntAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantLongAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("9999999999999999", c.getValue());
	}

	@Test
	public void testConstantShortAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantByteAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantBooleanAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantFloatAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1.1", c.getValue());
	}

	@Test
	public void testConstantDoubleAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1.5", c.getValue());
	}

	@Test
	public void testConstantCharAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testVariableIntAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("int", v.getType());
	}

	@Test
	public void testVariableLongAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("long", v.getType());
	}

	@Test
	public void testVariableShortAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("short", v.getType());
	}

	@Test
	public void testVariableByteAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("byte", v.getType());
	}

	@Test
	public void testVariableBooleanAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("boolean", v.getType());
	}

	@Test
	public void testVariableFloatAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("float", v.getType());
	}

	@Test
	public void testVariableDoubleAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("double", v.getType());
	}

	@Test
	public void testVariableCharAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
		assertEquals("char", v.getType());
	}
}
