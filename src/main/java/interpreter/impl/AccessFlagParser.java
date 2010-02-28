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

package interpreter.impl;


import java.util.ArrayList;
import java.util.List;

import model.classes.ClassAccessFlag;
import model.field.FieldAccessFlag;
import model.method.MethodAccessFlag;

public class AccessFlagParser {

	public static ClassAccessFlag[] getClassAccessFlag(int classAccessFlag) {
		int flag = classAccessFlag;
		List<ClassAccessFlag> res = new ArrayList<ClassAccessFlag>();
		for (ClassAccessFlag caf : ClassAccessFlag.getAccessFlagDesc()) {
			if (flag >= caf.getFlag()) {
				res.add(caf);
				flag = flag - caf.getFlag();
			}
		}
		return res.toArray(new ClassAccessFlag[res.size()]);
	}

	public static FieldAccessFlag[] getFieldAccessFlag(int fieldAccessFlag) {
		int flag = fieldAccessFlag;
		List<FieldAccessFlag> res = new ArrayList<FieldAccessFlag>();
		for (FieldAccessFlag caf : FieldAccessFlag.getAccessFlagDesc()) {
			if (flag >= caf.getFlag()) {
				res.add(caf);
				flag = flag - caf.getFlag();
			}
		}
		return res.toArray(new FieldAccessFlag[res.size()]);
	}

	public static MethodAccessFlag[] getMethodAccessFlag(int methodAccessFlag) {
		int flag = methodAccessFlag;
		List<MethodAccessFlag> res = new ArrayList<MethodAccessFlag>();
		for (MethodAccessFlag caf : MethodAccessFlag.getMethodAccessFlag()) {
			if (flag >= caf.getFlag()) {
				res.add(caf);
				flag = flag - caf.getFlag();
			}
		}
		return res.toArray(new MethodAccessFlag[res.size()]);
	}

}
