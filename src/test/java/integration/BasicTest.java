package integration;

import generator.impl.ClassGenerator;
import interpreter.impl.ByteCodeReader;

import java.io.OutputStreamWriter;
import java.io.Writer;

import model.classes.ClassFile;

import org.junit.Before;
import org.junit.Test;

public class BasicTest {
	// Tested class
	private ByteCodeReader bci;
	private ClassGenerator gen;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		gen = new ClassGenerator();
	}

	@Test
	public void parseClassBasic() throws Exception {
		ClassFile cf = bci
				.readClassFile("src/test/classes/testclasses/TestCode.class");
		Writer out = new OutputStreamWriter(System.out);
		gen.generateSource(cf, out);
		out.close();
	}
}
