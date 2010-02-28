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

package model.code.instruction;

import visitor.Visitor;

public class UnconditionalBranching extends Instruction {

	private short currentIndex;

	private short branchIndex;

	public UnconditionalBranching(short currentIndex, short branchIndex) {
		super(currentIndex);
		this.currentIndex = currentIndex;
		this.branchIndex = branchIndex;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUnconditionalBranching(this);
	}

	public short getCurrentIndex() {
		return currentIndex;
	}

	public short getBranchIndex() {
		return branchIndex;
	}

}
