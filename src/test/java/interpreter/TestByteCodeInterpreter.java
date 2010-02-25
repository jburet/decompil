package interpreter;

import interpreter.impl.ByteCodeReader;

import model.classes.ClassFile;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.debug.ClassFilePrinter;

public class TestByteCodeInterpreter {
	
	// Tested class
	private ByteCodeReader bci;
	// Utils
	private ClassFilePrinter cfp;
	
	@Before
	public void setup(){
		bci = new ByteCodeReader();
		cfp = new ClassFilePrinter();
	}
	
	
	@Test(expected=InterpreterException.class)
	public void parseFichierMauvaisMagic(){
		ClassFile cf = bci.readClassFile("src/test/classes/test/main/BasicMauvaisMagic.class");	
	}
}
