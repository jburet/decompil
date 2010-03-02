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

package jdecomp.core.interpreter;

import jdecomp.core.interpreter.InterpreterException;
import jdecomp.core.interpreter.impl.ByteCodeReader;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.utils.debug.ClassFilePrinter;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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