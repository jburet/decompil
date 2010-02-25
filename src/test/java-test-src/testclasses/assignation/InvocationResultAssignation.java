package testclasses.assignation;

import testclasses.TestUtils;

public class InvocationResultAssignation {

	private String test = "test";

	public void newClass() {
		Object obj = new Object();
	}

	public void instanceInvocation() {
		Object obj = "test".trim();
	}

	public void staticInvocation() {
		Object obj = TestUtils.getObject();
	}
	
	public void primitifStaticInvocation() {
		int i = TestUtils.getInt();
	}

}
