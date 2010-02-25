package model.classes;

import java.util.Comparator;

public class ClassAccessFlagComparator implements Comparator<ClassAccessFlag> {

	@Override
	public int compare(ClassAccessFlag o1, ClassAccessFlag o2) {
		return Integer.valueOf(o2.getFlag()).compareTo(
				Integer.valueOf(o1.getFlag()));
	}
}
