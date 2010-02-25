package model.field;

import java.util.Comparator;

public class FieldAccessFlagComparator implements Comparator<FieldAccessFlag> {

	@Override
	public int compare(FieldAccessFlag o1, FieldAccessFlag o2) {
		return Integer.valueOf(o2.getFlag()).compareTo(
				Integer.valueOf(o1.getFlag()));
	}

}
