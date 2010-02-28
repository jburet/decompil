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

package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;
import model.constant.Type;

public class InstanceMethodInvocationInstruction extends MethodInvocation {

	private Operand intance;
	private String methodName;
	private Operand[] args;
	private Type returnType;

	public InstanceMethodInvocationInstruction(short currentIndex,
			Operand instance, String methodName, Type returnType, Operand... args) {
		super(currentIndex);
		this.intance = instance;
		this.methodName = methodName;
		this.args = args;
		this.returnType = returnType;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitInstanceMethodInvocation(this);
	}
	
	@Override
	public Type getReturnType() {
		return this.returnType;
	}

	public Operand getIntance() {
		return intance;
	}

	public void setIntance(Operand intance) {
		this.intance = intance;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Operand[] getArgs() {
		return args;
	}

	public void setArgs(Operand[] args) {
		this.args = args;
	}

	
}
