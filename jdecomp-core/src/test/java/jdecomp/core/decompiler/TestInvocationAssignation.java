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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.AssignationInstruction;
import jdecomp.core.model.code.instruction.ConditionalBrancheInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.MethodInvocation;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.InvocationOperandResult;
import jdecomp.core.utils.debug.ClassFilePrinter;


import org.junit.Before;
import org.junit.Test;



public class TestInvocationAssignation {
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
	public void testNewClassReferenceAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/InvocationResultAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		// FIXME Comment tester
		fail();
	}

	@Test
	public void testInstanceInvocationAssignation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/InvocationResultAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof InvocationOperandResult);
	}

	@Test
	public void testStaticInvocation() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/InvocationResultAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof InvocationOperandResult);
	}
	
	@Test
	public void testPrimitifStaticInvocation (){
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/InvocationResultAssignation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof AssignationInstruction);
		AssignationInstruction ai = (AssignationInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getValue() instanceof InvocationOperandResult);
	}
}
