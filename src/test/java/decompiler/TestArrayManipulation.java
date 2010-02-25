package decompiler;

import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.MethodInstruction;

import org.junit.Before;
import org.junit.Test;

import visitor.JavaResumeVisitor;


public class TestArrayManipulation {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
	}

	@Test
	public void testAssignValueOnBooleanArray() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/array/ArrayManipulation.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		// FIXME Definir test
		mi.accept(new JavaResumeVisitor());
	}

}
