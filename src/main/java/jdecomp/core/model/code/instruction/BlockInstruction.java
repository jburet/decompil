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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author jburet
 * 
 *         Represente un block generic d'instruction (if, else, for, while, try,
 *         ....)
 * 
 */
public abstract class BlockInstruction extends Instruction {
	protected TreeMap<Short, Instruction> instructionsMap;

	private BlockInstruction precInstruction;

	private short startIndex;

	private short endIndex;

	public BlockInstruction(BlockInstruction precBlock, short startIndex, short endIndex) {
		super(startIndex);
		this.precInstruction = precBlock;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public void addInstruction(short index, Instruction instruction) {
		if (instructionsMap == null) {
			instructionsMap = new TreeMap<Short, Instruction>();
		}
		instructionsMap.put(index, instruction);
	}

	public BlockInstruction getPrecInstruction() {
		return precInstruction;
	}

	public TreeMap<Short, Instruction> getInstructionsMap() {
		return instructionsMap;
	}

	public short getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(short startIndex) {
		this.startIndex = startIndex;
	}

	public short getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(short endIndex) {
		this.endIndex = endIndex;
	}

}
