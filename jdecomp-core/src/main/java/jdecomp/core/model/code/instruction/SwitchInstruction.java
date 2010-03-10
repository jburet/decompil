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

package jdecomp.core.model.code.instruction;

import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.visitor.MethodVisitor;

public class SwitchInstruction extends Instruction {

	private Operand index;
	private int defaultIndex;
	private int[] match;
	private int[] jumpOffset;

	public SwitchInstruction(short currentIndex, Operand index,
			int defaultIndex, int[] match, int[] jumpOffset) {
		super(currentIndex);
		this.index = index;
		this.defaultIndex = defaultIndex;
		this.match = match;
		this.jumpOffset = jumpOffset;
	}

	@Override
	public void accept(MethodVisitor visitor) {
		visitor.visitSwitch(this);
	}

	public Operand getIndex() {
		return index;
	}

	public int getDefaultIndex() {
		return defaultIndex;
	}

	public int[] getMatch() {
		return match;
	}

	public int[] getJumpOffset() {
		return jumpOffset;
	}

}
