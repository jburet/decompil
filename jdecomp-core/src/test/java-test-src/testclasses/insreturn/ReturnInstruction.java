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

package testclasses.insreturn;

public class ReturnInstruction {

	public void voidReturn() {
		return;
	}

	public boolean returnBoolean() {
		return true;
	}

	public byte returnByte() {
		return 0;
	}

	public short returnShort() {
		return 0;
	}

	public int returnInt() {
		return 0;
	}

	public long returnLong() {
		return 99999999999999l;
	}

	public char returnChar() {
		return 'a';
	}

	public float returnFloat() {
		return 0.0f;
	}

	public double returnDouble() {
		return 0.0;
	}

}
