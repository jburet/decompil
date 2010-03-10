package jdecomp.plugin.java;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.plugin.java.formatter.JavaSimpleFormatter;

import org.junit.Before;
import org.junit.Test;

public class TestClassVisitorDecompiler {
	private ByteCodeReader bci;
	private ClassVisitorDecompiler cvd;
	private JavaSimpleFormatter jsf;

	@Before
	public void setup() {
		bci = new ByteCodeReader();
		cvd = new ClassVisitorDecompiler();
		jsf = new JavaSimpleFormatter();
	}

	@Test
	public void test() throws IOException {
		Writer testWriter = new OutputStreamWriter(System.out);
		ClassFile cf = bci.readClassFile("src/test/classes/testclasses/assignation/ArrayAssignation.class");
		cvd.visitClassFile(cf);
		jsf.formatJavaCode(cvd.getJavaSource(), testWriter);
		testWriter.close();
	}
}
