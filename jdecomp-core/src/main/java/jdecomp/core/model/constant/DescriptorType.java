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
package jdecomp.core.model.constant;

/**
 * @author jburet
 * 
 */
public enum DescriptorType implements Type {

	BYTE('B'),
	CHAR('C'),
	DOUBLE('D'),
	FLOAT('F'),
	INT('I'),
	LONG('J'),
	SHORT('S'),
	BOOLEAN('Z'),
	VOID('V'),
	CLASS('L');

	private char vmType;

	private DescriptorType(char vmType) {
		this.vmType = vmType;
	}

	public static DescriptorType getDescriptorFromVmtype(char vmType) {
		for (DescriptorType dt : values()) {
			if (dt.vmType == vmType) {
				return dt;
			}
		}
		return null;
	}

	public char getVmType() {
		return vmType;
	}

	@Override
	public boolean isArray() {
		return false;
	}

}
