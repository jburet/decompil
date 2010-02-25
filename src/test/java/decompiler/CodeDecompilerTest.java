package decompiler;

import interpreter.impl.ByteCodeReader;
import interpreter.impl.OpCodeInterpreter;

import model.classes.ClassFile;
import model.code.instruction.MethodInstruction;

import org.junit.Before;
import org.junit.Test;

import visitor.JavaResumeVisitor;



public class CodeDecompilerTest {
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
	public void parseClassBasic() {
		ClassFile cf = bci.readClassFile("src/test/classes/test/main/TestCode.class");
		MethodInstruction mi = cd.constructTree(cf.getMethods()[1]);
		mi.accept(jrv);
	}
}
