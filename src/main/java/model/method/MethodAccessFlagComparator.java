package model.method;

import java.util.Comparator;

public class MethodAccessFlagComparator implements Comparator<MethodAccessFlag> {

	@Override
	public int compare(MethodAccessFlag o1, MethodAccessFlag o2) {
		return Integer.valueOf(o2.getFlag()).compareTo(
				Integer.valueOf(o1.getFlag()));
	}
}
