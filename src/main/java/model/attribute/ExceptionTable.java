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

public class ExceptionTable {
	private short startPc;
	private short endPc;
	private short handlerPc;
	private short catchType;

	public short getStartPc() {
		return startPc;
	}

	public void setStartPc(short startPc) {
		this.startPc = startPc;
	}

	public short getEndPc() {
		return endPc;
	}

	public void setEndPc(short endPc) {
		this.endPc = endPc;
	}

	public short getHandlerPc() {
		return handlerPc;
	}

	public void setHandlerPc(short handlerPc) {
		this.handlerPc = handlerPc;
	}

	public short getCatchType() {
		return catchType;
	}

	public void setCatchType(short catchType) {
		this.catchType = catchType;
	}

}
