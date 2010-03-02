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
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.visitor.JavaResumeVisitor;

import org.junit.Before;
import org.junit.Test;


public class TestUnconditionalBranches {
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
	public void testBoucleFor() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleInfiniteWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleDoWhile() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}

	@Test
	public void testBoucleForBreak() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/branches/UnconditionalBranche.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		jrv.visitMethodInstruction(mi);
	}
}
