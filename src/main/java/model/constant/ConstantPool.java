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

package model.constant;

/**
 * Java virtual machine instructions do not rely on the runtime layout of
 * classes, interfaces, class instances, or arrays. Instead, instructions refer
 * to symbolic information in the constant_pool table.
 * 
 * All constant_pool table entries have the following general format:
 * 
 * cp_info { u1 tag; u1 info[]; }
 * 
 * Each item in the constant_pool table must begin with a 1-byte tag indicating
 * the kind of cp_info entry. The contents of the info array vary with the value
 * of tag. The valid tags and their values are listed in Table 4.3. Each tag
 * byte must be followed by two or more bytes giving information about the
 * specific constant. The format of the additional information varies with the
 * tag value.
 * 
 * @author Erwan ALLIAUME
 */
public enum ConstantPool {

	CONSTANT_Utf8(1),
	CONSTANT_Integer(3),
	CONSTANT_Float(4),
	CONSTANT_Long(5),
	CONSTANT_Double(6),
	CONSTANT_Class(7),
	CONSTANT_Fieldref(9),
	CONSTANT_String(8),
	CONSTANT_Methodref(10),
	CONSTANT_InterfaceMethodref(11),
	CONSTANT_NameAndType(12), ;

	private final byte value;

	private ConstantPool(Integer value) {
		this.value = value.byteValue();
	}

	public int getValue() {
		return value;
	}

}
