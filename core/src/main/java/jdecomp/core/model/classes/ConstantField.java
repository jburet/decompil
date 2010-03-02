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

package jdecomp.core.model.classes;

public class ConstantField extends ConstantPoolInfo {
	private short classIndex;
	private short nameTypeIndex;
	
	@Override
	public short getConstantCount() {
		return 1;
	}

	public short getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(short classIndex) {
		this.classIndex = classIndex;
	}

	public short getNameTypeIndex() {
		return nameTypeIndex;
	}

	public void setNameTypeIndex(short nameTypeIndex) {
		this.nameTypeIndex = nameTypeIndex;
	}

}
