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

import static org.junit.Assert.assertTrue;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.AssignationArrayInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.visitor.JavaResumeVisitor;

import org.junit.Before;
import org.junit.Test;


public class TestArrayManipulation {
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
	public void testAssignValueOnBooleanArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnByteArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnShortArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnIntArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnLongArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnFloatArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnDoubleArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testAssignValueOnObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) mi.getInstructionsMap().firstEntry().getValue();

	}

	@Test
	public void testGetValueOnBooleanArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[10]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnByteArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[11]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnShortArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[12]);
		jrv.visitMethodInstruction(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnIntArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[13]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnLongArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[14]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[15]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnFloatArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[16]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnDoubleArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[17]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

	@Test
	public void testGetValueOnObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[18]);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		ri.getOperand();
	}

}
