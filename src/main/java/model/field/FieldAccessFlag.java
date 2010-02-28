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

package model.field;

import java.util.Arrays;

public enum FieldAccessFlag {
	ACC_PUBLIC(0x1), ACC_PRIVATE(0x2), ACC_PROTECTED(0x4), ACC_STATIC(0x8), ACC_FINAL(
			0x10), ACC_VOLATILE(0x40), ACC_TRANSIENT(0x80), ACC_SYNTHETIC(
			0x1000), ACC_ENUM(0x4000);

	private int flag;

	public int getFlag() {
		return flag;
	}

	private FieldAccessFlag(int flag) {
		this.flag = flag;
	}

	public static FieldAccessFlag[] getAccessFlagDesc() {
		FieldAccessFlag[] res = FieldAccessFlag.values();
		Arrays.sort(res, new FieldAccessFlagComparator());
		return res;
	}
}
