/**
 * Copyright (C) 2010 Julien Buret <julien.buret@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package decompiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;
import model.classes.ClassFile;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.ObjectReference;
import model.code.operand.impl.Constant;
import model.code.operand.impl.SimpleVariable;

import org.junit.Before;
import org.junit.Test;

import utils.debug.ClassFilePrinter;
import visitor.JavaResumeVisitor;

public class TestPrimitiveAssignation {
	private ByteCodeReader bci;
	private ClassFilePrinter cfp;
	private OpCodeInterpreter cd;
	private JavaResumeVisitor jrv;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cfp = new ClassFilePrinter();
		cd = new OpCodeInterpreter();
		jrv = new JavaResumeVisitor();
	}

	@Test
	public void testConstantIntAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantLongAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("9999999999999999", c.getValue());
	}

	@Test
	public void testConstantShortAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantByteAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantBooleanAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testConstantFloatAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1.1", c.getValue());
	}

	@Test
	public void testConstantDoubleAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1.5", c.getValue());
	}

	@Test
	public void testConstantCharAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
		assertEquals("1", c.getValue());
	}

	@Test
	public void testVariableIntAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[10]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableLongAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[11]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableShortAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[12]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableByteAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[13]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableBooleanAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[14]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableFloatAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[15]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableDoubleAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[16]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVariableCharAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[17]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable v = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testReadIntInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[26]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		assertEquals("varint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[27]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("varint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}

	@Test
	public void testReadIntStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[28]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		assertEquals("staticint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/PrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[29]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("staticint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}
}
