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
