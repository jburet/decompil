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

import jdecomp.core.model.constant.Type;

public enum ArrayType implements Type {

	T_BOOLEAN((short) 4),
	T_CHAR((short) 5),
	T_FLOAT((short) 6),
	T_DOUBLE((short) 7),
	T_BYTE((short) 8),
	T_SHORT((short) 9),
	T_INT((short) 10),
	T_LONG((short) 11),
	T_REF((short) 0);

	private short code;

	private ArrayType(short code) {
		this.code = code;
	}

	public short getCode() {
		return this.code;
	}

	public static ArrayType getByCode(short code) {
		for (ArrayType at : values()) {
			if (at.getCode() == code) {
				return at;
			}
		}
		return T_REF;
	}

	@Override
	public boolean isArray() {
		return true;
	}
}
