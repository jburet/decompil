/**
 * Copyright (C) 2010 Julien Buret <julien.buret@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jdecomp.core.integration;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import jdecomp.core.generator.impl.ClassGenerator;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.model.classes.ClassFile;


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
