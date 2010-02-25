package integration;

import generator.impl.ClassGenerator;
import interpreter.impl.ByteCodeReader;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import model.classes.ClassFile;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Erwan ALLIAUME
 */
public class InterfaceTest {

	private ByteCodeReader bci;
	private ClassGenerator gen;

	private static int i = 0;
	private static Writer out = null; 
	
	@BeforeClass
	public static void initStatic() {
		 out = new OutputStreamWriter(System.out);
	}
	
	@Before
	public void setup() {
		bci = new ByteCodeReader();
		gen = new ClassGenerator();

		System.out.println("");
		System.out.println(" // "+ ++i +" ////////////////////////////////////");
		System.out.println("");
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		out.close();
	}
	
	@Test
	public void parseInterfaceBasic() throws Exception {
		ClassFile cf = bci.readClassFile("bin-test/testclasses/MyInterface.class");
		gen.generateSource(cf, out);
	}

	@Test
	public void parseAbstactBasic() throws Exception {
		ClassFile cf = bci.readClassFile("bin-test/testclasses/MyAbstract.class");
		gen.generateSource(cf, out);
	}
	
	@Test
	public void parseClassBasic() throws Exception {
		ClassFile cf = bci.readClassFile("bin-test/testclasses/MyClass.class");
		gen.generateSource(cf, out);
	}


}
