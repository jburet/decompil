package interpreter;

import interpreter.impl.ByteCodeReader;

import model.classes.ClassFile;

import org.junit.Before;
import org.junit.Test;

import utils.debug.ClassFilePrinter;

public class TestAnnotationInterpreter {
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
		ClassFile cf = bci.readClassFile("src/test/classes/test/main/AnnotationTest.class");
		cfp.print(cf);
	}
}
