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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.utils.debug.ClassFilePrinter;
import jdecomp.core.visitor.JavaResumeVisitor;

import org.junit.Before;
import org.junit.Test;


public class TestReturnInstruction {
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
	public void testReturnVoid() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNull(ri.getOperand());
	}

	@Test
	public void testReturnBoolean() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnByte() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnShort() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnInt() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnLong() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnChar() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnFloat() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}

	@Test
	public void testReturnDouble() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/insreturn/ReturnInstruction.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get(mi.getEndIndex()) instanceof ReturnInstruction);
		ReturnInstruction ri = (ReturnInstruction) mi.getInstructionsMap().get(mi.getEndIndex());
		assertNotNull(ri.getOperand());
	}
}
