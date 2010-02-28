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

package model.classes;

import java.util.Arrays;

public enum ClassAccessFlag {
	
	/**
	 * Declared public; may be accessed from outside its package.
	 */
	ACC_PUBLIC(0x1), 
	
	/**
	 * Declared final; no subclasses allowed.
	 */
	ACC_FINAL(0x10), 
	
	/**
	 * Treat superclass methods specially when invoked by the invokespecial instruction.
	 */
	ACC_SUPER(0x20), 
	
	/**
	 * Is an interface, not a class
	 */
	ACC_INTERFACE(0x200), 
	
	/**
	 * Declared abstract; must not be instantiated.
	 */
	ACC_ABSTRACT(0x400), 
	
	/**
	 * Declared synthetic; Not present in the source code.
	 */
	ACC_SYNTHETIC(0x1000), 
	
	/**
	 * Declared as an annotation type.
	 * If the ACC_ANNOTATION flag is set, the ACC_INTERFACE flag must be set as well.
	 */
	ACC_ANNOTATION(0x2000),
	
	/**
	 * Declared as an enum type.
	 */
	ACC_ENUM(0x4000);

	private int flag;

	public int getFlag() {
		return flag;
	}

	private ClassAccessFlag(int flag) {
		this.flag = flag;
	}

	public static ClassAccessFlag[] getAccessFlagDesc() {
		ClassAccessFlag[] res = ClassAccessFlag.values();
		Arrays.sort(res, new ClassAccessFlagComparator());
		return res;
	}
}
