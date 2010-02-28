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

public class ConstantInteger extends ConstantPoolValue {
	private int bytes;

	@Override
	public short getConstantCount() {
		return 1;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	@Override
	public Object getDecodedBytes() {
		return getBytes();
	}
}
