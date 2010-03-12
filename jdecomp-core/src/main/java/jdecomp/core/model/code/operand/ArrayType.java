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

package jdecomp.core.model.code.operand;

import jdecomp.core.model.code.Descriptor;
import jdecomp.core.model.constant.Type;

public class ArrayType implements Type {

	private ArrayTypeDescriptor arrayTypeDescriptor;

	private Descriptor classDescriptor;

	public ArrayType(short arrayTypeCode) {
		arrayTypeDescriptor = ArrayTypeDescriptor.getByCode(arrayTypeCode);
	}

	public ArrayType(Descriptor descriptor) {
		arrayTypeDescriptor = ArrayTypeDescriptor.T_REF;
		classDescriptor = descriptor;
	}

	@Override
	public boolean isArray() {
		return true;
	}

	public ArrayTypeDescriptor getArrayTypeDescriptor() {
		return arrayTypeDescriptor;
	}

	public Descriptor getClassDescriptor() {
		return classDescriptor;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isLongType() {
		// TODO Auto-generated method stub
		return false;
	}
}
