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

package model.method;

import java.util.Arrays;

/**
 * Each method, including each instance initialization method ($3.9) and the
 * class or interface initialization method ($�3.9), is described by a
 * method_info structure. No two methods in one class file may have the same
 * name and descriptor ($4.4.3).
 * 
 * The structure has the following format: method_info { u2 access_flags; u2
 * name_index; u2 descriptor_index; u2 attributes_count; attribute_info
 * attributes[attributes_count]; }
 * 
 * @author Erwan ALLIAUME
 */
public enum MethodAccessFlag {

	/**
	 * Declared public; may be accessed from outside its package.
	 */
	ACC_PUBLIC(0x1),

	/**
	 * Declared private; accessible only within the defining class.
	 */
	ACC_PRIVATE(0x2),

	/**
	 * Declared protected; may be accessed within subclasses.
	 */
	ACC_PROTECTED(0x4),

	/**
	 * Declared static
	 */
	ACC_STATIC(0x8),

	/**
	 * Declared final; must not be overridden.
	 */
	ACC_FINAL(0x10),

	/**
	 * Declared synchronized; invocation is wrapped in a monitor lock.
	 */
	ACC_SYNCHRONIZED(0x20),

	/**
	 * A bridge method, generated by the compiler.
	 */
	ACC_BRIDGE(0x40),

	/**
	 * Declared with variable number of arguments.
	 */
	ACC_VARARGS(0x80),

	/**
	 * Declared native; implemented in a language other than Java.
	 */
	ACC_NATIVE(0x100),

	/**
	 * Declared abstract; no implementation is provided.
	 */
	ACC_ABSTRACT(0x400),

	/**
	 * Declared strictfp; floating-point mode is FP-strict
	 */
	ACC_STRICT(0x800),

	/**
	 * Declared synthetic; Not present in the source code.
	 */
	ACC_SYNTHETIC(0x1000);

	private int flag;

	public int getFlag() {
		return flag;
	}

	private MethodAccessFlag(int flag) {
		this.flag = flag;
	}

	public static MethodAccessFlag[] getMethodAccessFlag() {
		MethodAccessFlag[] res = MethodAccessFlag.values();
		Arrays.sort(res, new MethodAccessFlagComparator());
		return res;
	}
}
