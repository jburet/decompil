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
package jdecomp.core.model.code;

import jdecomp.core.model.constant.DescriptorType;
import jdecomp.core.model.constant.Type;

/**
 * @author jburet
 * 
 */
public class Descriptor implements Type {

	private final DescriptorType descriptorType;
	private String className;
	private int arrayLevel = 0;

	public Descriptor(DescriptorType descriptorType) {
		this.descriptorType = descriptorType;
	}

	public Descriptor(DescriptorType descriptorType, String classname) {
		this(descriptorType);
		this.className = classname;
	}

	public Descriptor(String classname) {
		this(DescriptorType.CLASS);
		this.className = classname;
	}

	public Descriptor(DescriptorType descriptorType, int arrayLevel) {
		this(descriptorType);
		this.arrayLevel = arrayLevel;
	}

	public Descriptor(DescriptorType descriptorType, String classname, int arrayLevel) {
		this(descriptorType, classname);
		this.arrayLevel = arrayLevel;
	}

	public DescriptorType getDescriptorType() {
		return descriptorType;
	}

	public String getClassName() {
		return className;
	}

	public int getArrayLevel() {
		return arrayLevel;
	}

	@Override
	public boolean isArray() {
		return arrayLevel > 0;
	}

	@Override
	public boolean isString() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLongType() {
		return (descriptorType.equals(DescriptorType.LONG) || descriptorType.equals(DescriptorType.DOUBLE));
	}

}
