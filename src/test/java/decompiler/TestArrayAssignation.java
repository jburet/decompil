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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import java.util.ArrayList;
import java.util.List;

import model.classes.ClassFile;
import model.code.instruction.AssignationArrayInstruction;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.Instruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.Array;
import model.code.operand.impl.ConstantArrayReference;

import org.junit.Before;
import org.junit.Test;

import visitor.JavaResumeVisitor;

public class TestArrayAssignation {
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
	public void testNewBooleanArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewBooleanConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(1);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewByteArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewByteConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewShortArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewShortConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewIntArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewIntConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewLongArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewLongConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[10]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[11]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewCharConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[12]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewCharConstantFromCharArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[13]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewFLoatArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[14]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewFloatConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[15]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewDoubleArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[16]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewDoubleConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[17]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[18]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewObjectConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[19]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewMultiDimPrimArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[20]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewMultiDimPrimConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[21]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewMultiDimPrimConstantArray2() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[22]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewMultiDimObjectArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[23]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Array);
	}

	@Test
	public void testNewMultiDimObjectConstantArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[24]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

	@Test
	public void testNewMultiDimObjectConstantArray2() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[25]);
		assertNotNull(mi);
		List<Instruction> ins = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		assertTrue(ins.get(0) instanceof AssignationArrayInstruction);
		AssignationArrayInstruction aai = (AssignationArrayInstruction) ins.get(0);
		assertTrue(ins.get(1) instanceof AssignationArrayInstruction);
		aai = (AssignationArrayInstruction) ins.get(1);
		assertTrue(ins.get(2) instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) ins.get(2);
		assertTrue(ai.getValue() instanceof ConstantArrayReference);
	}

}
