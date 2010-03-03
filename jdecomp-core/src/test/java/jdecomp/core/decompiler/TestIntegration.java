package jdecomp.core.decompiler;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.Instruction;
import jdecomp.core.model.code.instruction.MethodInstruction;

import org.junit.Before;
import org.junit.Test;

public class TestIntegration {
	private ByteCodeReader bci;
	private OpCodeInterpreter cd;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cd = new OpCodeInterpreter();
	}

	@Test
	public void testIntegration() {
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/integration/Integration.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		assertNotNull(mi);
		List<Instruction> res = new ArrayList<Instruction>(mi.getInstructionsMap().values());
		List<Short> ress = new ArrayList<Short>(mi.getInstructionsMap().keySet());
		res.toString();
	}
}
