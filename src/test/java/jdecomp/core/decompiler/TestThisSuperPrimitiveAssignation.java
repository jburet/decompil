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

package jdecomp.core.decompiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.AssignationInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.impl.SimpleVariable;
import jdecomp.core.utils.debug.ClassFilePrinter;
import jdecomp.core.visitor.JavaResumeVisitor;

import org.junit.Before;
import org.junit.Test;


public class TestThisSuperPrimitiveAssignation {
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
	public void testReadIntSuperInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		ObjectReference or = (ObjectReference) ai.getValue();
		assertNotNull(or.getOperandReference());
		assertEquals("varint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntSuperInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("varint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}

	@Test
	public void testReadIntSuperStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		assertEquals("staticint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntSuperStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("staticint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}

	@Test
	public void testReadIntInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		assertEquals("varint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntInstanceField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("varint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}

	@Test
	public void testReadIntStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof ObjectReference);
		assertEquals("staticint", ((ObjectReference) ai.getValue()).getName());
	}

	@Test
	public void testWriteIntStaticField() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/SuperPrimitiveAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertEquals("staticint", ai.getVarName());
		assertTrue(ai.getValue() instanceof SimpleVariable);
	}
}
