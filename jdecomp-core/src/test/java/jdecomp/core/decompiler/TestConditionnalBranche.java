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
import jdecomp.core.model.code.instruction.ConditionalBrancheInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.operand.ConditionalOperator;

import org.junit.Before;
import org.junit.Test;

public class TestConditionnalBranche {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
	}

	@Test
	public void testCompare0EQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.EQ, cbi.getCondition().getCo());
	}

	@Test
	public void testCompare0NEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.NE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompare0INF() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.LT, cbi.getCondition().getCo());
	}

	@Test
	public void testCompare0SUP() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.GT, cbi.getCondition().getCo());
	}

	@Test
	public void testCompare0INFEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.LE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompare0SUPEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareTo0.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 1) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 1);
		assertEquals(ConditionalOperator.GE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.EQ, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarNEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.NE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarINF() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.LT, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarSUP() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.GT, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarINFEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.LE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareVarSUPEQ() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareVar.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.GE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareInvocationResult() {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheInvocationResult.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().get((short) 2) instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().get((short) 2);
		assertEquals(ConditionalOperator.GE, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareToNull() {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareToNull.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().firstEntry()
				.getValue();
		assertEquals(ConditionalOperator.ISNULL, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareToNotNull() {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareToNull.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().firstEntry()
				.getValue();
		assertEquals(ConditionalOperator.ISNOTNULL, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareObjectEqual() {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareObject.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().firstEntry()
				.getValue();
		assertEquals(ConditionalOperator.EQ, cbi.getCondition().getCo());
	}

	@Test
	public void testCompareObjectNotEqual() {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/conditional/ConditionalBrancheCompareObject.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ConditionalBrancheInstruction);
		ConditionalBrancheInstruction cbi = (ConditionalBrancheInstruction) mi.getInstructionsMap().firstEntry()
				.getValue();
		assertEquals(ConditionalOperator.NE, cbi.getCondition().getCo());
	}
}
