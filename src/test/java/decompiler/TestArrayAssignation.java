package decompiler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.ArrayReference;
import model.code.operand.impl.InvocationOperandResult;

import org.junit.Before;
import org.junit.Test;


public class TestArrayAssignation {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
	}

	@Test
	public void testNewBooleanArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewBooleanConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewByteArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewByteConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewShortArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewShortConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewIntArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewIntConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewLongArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewLongConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[10]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[11]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewCharConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[12]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewCharConstantFromCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[13]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewFLoatArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[14]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewFLoatConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[15]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewDoubleArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[16]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewDoubleConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[17]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[18]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewObjectConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[19]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimPrimArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[20]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimPrimConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[21]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimPrimConstantArray2() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[22]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[23]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimObjectConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[24]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

	@Test
	public void testNewMultiDimObjectConstantArray2() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[25]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ArrayReference);
	}

}
