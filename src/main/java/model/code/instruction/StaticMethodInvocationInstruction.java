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

/**
 *
 */
package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;
import model.constant.Type;

/**
 * @author jburet
 * 
 */
public class StaticMethodInvocationInstruction extends MethodInvocation {

	private String methodName;
	private String className;
	private Type returnType;
	private Operand[] args;

	/**
	 * @param currentIndex
	 */
	public StaticMethodInvocationInstruction(short currentIndex, String className, String methodName, Type returnType,
			Operand... args) {
		super(currentIndex);
		this.methodName = methodName;
		this.className = className;
		this.returnType = returnType;
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * decompiler.instruction.Instruction#accept(decompiler.visitor.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visitStaticMethodInvocation(this);
	}

	@Override
	public Type getReturnType() {
		return this.returnType;
	}

	public String getMethodName() {
		return methodName;
	}

	public Operand[] getArgs() {
		return args;
	}

	public String getClassName() {
		return className;
	}

}
