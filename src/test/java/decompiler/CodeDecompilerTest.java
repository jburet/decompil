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
