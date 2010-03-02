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


import java.io.OutputStreamWriter;
import java.io.Writer;

import jdecomp.core.generator.impl.ClassGenerator;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.model.classes.ClassFile;


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
