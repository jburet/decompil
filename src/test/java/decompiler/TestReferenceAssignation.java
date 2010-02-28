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
import model.classes.ClassFile;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.operand.impl.Constant;
import model.code.operand.impl.SimpleVariable;

import org.junit.Before;
import org.junit.Test;

import utils.debug.ClassFilePrinter;
import visitor.JavaResumeVisitor;

public class TestReferenceAssignation {
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
	public void testConstantReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof Constant);
		Constant c = (Constant) ai.getValue();
	}

	@Test
	public void testVarReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable c = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVarArrayReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable c = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVarBiDimArrayReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable c = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testVarGenericsReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable c = (SimpleVariable) ai.getValue();
	}

	@Test
	public void testEnumReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ReferenceAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof SimpleVariable);
		SimpleVariable c = (SimpleVariable) ai.getValue();
	}

}
