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

package jdecomp.core.model.code.operand.impl;

import jdecomp.core.model.code.instruction.MethodInvocation;
import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.model.constant.Type;
import jdecomp.core.visitor.Visitor;

public class ArrayRefInvocationOperandResult extends InvocationOperandResult implements Operand, Array {

	public ArrayRefInvocationOperandResult(MethodInvocation methodInvocation) {
		super(methodInvocation);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getReturnType() {
		return methodInvocation.getReturnType();
	}

	// FIXME ou pas... Dans le cas d'une variable on ne conserve pas les
	// valeurs.
	@Override
	public void addValue(Operand index, Operand value) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.code.operand.Array#getDimension()
	 */
	@Override
	public int getDimension() {
		// TODO Auto-generated method stub
		return 1;
	}

}
