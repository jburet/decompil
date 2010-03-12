package testclasses.instanciation;

public class Instanciation {

	public void simpleWithoutAssignation() {
		new Object();
	}

	public void argumentsWithoutAssignation() {
		new Integer("1");
	}

	public void simpleWithAssignation() {
		Object obj = new Object();
	}

	public void argumentsWithAssignation() {
		Integer i = new Integer("1");
	}

	public Object simpleReturn() {
		return new Object();
	}

	public Integer argumentsReturn() {
		return new Integer("1");
	}

}
