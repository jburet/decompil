package interpreter;

import interpreter.impl.ByteCodeReader;

import model.classes.ClassFile;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.debug.ClassFilePrinter;

public class PrintBasicTestByteCodeInterpreter {
	
	// Tested class
	private ByteCodeReader bci;
	// Utils
	private ClassFilePrinter cfp;
	
	@Before
	public void setup(){
		bci = new ByteCodeReader();
		cfp = new ClassFilePrinter();
	}
	
	@Test
	public void parseClassBasic(){
		ClassFile cf = bci.readClassFile("src/test/classes/test/main/TestCode.class");
		cfp.print(cf);
	}

}
