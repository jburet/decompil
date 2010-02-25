package decompiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.impl.Constant;

import org.junit.Before;
import org.junit.Test;

import utils.debug.ClassFilePrinter;

public class TestReferenceAssignation {
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
	public void testConstantReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testVarReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testVarArrayReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testVarBiDimArrayReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testVarGenericsReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testEnumReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().get((short) 1);
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

}
