package jdecomp.core.decompiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.model.code.operand.ArithmeticOperationType;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.visitor.JavaResumeVisitor;

import org.junit.Before;
import org.junit.Test;


public class TestOperation {

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
	public void testIntAdd() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.PLUS, ao.getType());
	}

	@Test
	public void testLongAdd() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[2]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.PLUS, ao.getType());
	}

	@Test
	public void testFloatAdd() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[3]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.PLUS, ao.getType());
	}

	@Test
	public void testDoubleAdd() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[4]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.PLUS, ao.getType());
	}

	@Test
	public void testIntMinus() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[5]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testLongMinus() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[6]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testFloatMinus() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[7]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testDoubleMinus() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[8]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testIntMul() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[9]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MUL, ao.getType());
	}

	@Test
	public void testLongMul() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[10]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MUL, ao.getType());
	}

	@Test
	public void testFloatMul() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[11]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MUL, ao.getType());
	}

	@Test
	public void testDoubleMul() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[12]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MUL, ao.getType());
	}

	@Test
	public void testIntDiv() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[13]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.DIV, ao.getType());
	}

	@Test
	public void testLongDiv() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[14]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.DIV, ao.getType());
	}

	@Test
	public void testFloatDiv() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[15]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.DIV, ao.getType());
	}

	@Test
	public void testDoubleDiv() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[16]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.DIV, ao.getType());
	}

	@Test
	public void testIntRem() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[17]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.REM, ao.getType());
	}

	@Test
	public void testLongRem() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[18]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.REM, ao.getType());
	}

	@Test
	public void testFloatRem() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[19]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.REM, ao.getType());
	}

	@Test
	public void testDoubleRem() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[20]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.REM, ao.getType());
	}

	@Test
	public void testIntNeg() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[21]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testLongNeg() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[22]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testFloatNeg() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[23]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testDoubleNeg() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[24]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.MINUS, ao.getType());
	}

	@Test
	public void testIntShl() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[25]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHL, ao.getType());
	}

	@Test
	public void testLongShl() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[26]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHL, ao.getType());
	}

	@Test
	public void testIntShr() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[27]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHR, ao.getType());
	}

	@Test
	public void testLongShr() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[28]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHR, ao.getType());
	}

	@Test
	public void testIntUshr() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[29]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHR, ao.getType());
	}

	@Test
	public void testLongUshr() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[30]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.SHR, ao.getType());
	}

	@Test
	public void testIntAnd() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[31]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.AND, ao.getType());
	}

	@Test
	public void testIntOr() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[32]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.OR, ao.getType());
	}

	@Test
	public void testIntXor() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/operation/MathOperation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[33]);
		assertNotNull(mi);
		assertTrue(mi.getInstructionsMap().firstEntry().getValue() instanceof ReturnInstruction);
		ReturnInstruction ai = (ReturnInstruction) mi.getInstructionsMap().firstEntry().getValue();
		assertTrue(ai.getOperand() instanceof ArithmeticOperation);
		ArithmeticOperation ao = (ArithmeticOperation) ai.getOperand();
		assertEquals(ArithmeticOperationType.XOR, ao.getType());
	}
}
