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

package model.attribute;

public class ClassAttribute {
	private short innerClassInfoIndex;
	private short outerClassInfoIndex;
	private short innerNameIndex;
	private short innerClassAccessFlags;

	public short getInnerClassInfoIndex() {
		return innerClassInfoIndex;
	}

	public void setInnerClassInfoIndex(short innerClassInfoIndex) {
		this.innerClassInfoIndex = innerClassInfoIndex;
	}

	public short getOuterClassInfoIndex() {
		return outerClassInfoIndex;
	}

	public void setOuterClassInfoIndex(short outerClassInfoIndex) {
		this.outerClassInfoIndex = outerClassInfoIndex;
	}

	public short getInnerNameIndex() {
		return innerNameIndex;
	}

	public void setInnerNameIndex(short innerNameIndex) {
		this.innerNameIndex = innerNameIndex;
	}

	public short getInnerClassAccessFlags() {
		return innerClassAccessFlags;
	}

	public void setInnerClassAccessFlags(short innerClassAccessFlags) {
		this.innerClassAccessFlags = innerClassAccessFlags;
	}
}
