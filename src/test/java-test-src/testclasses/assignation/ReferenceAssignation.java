package testclasses.assignation;

import javax.lang.model.SourceVersion;

public class ReferenceAssignation {

	private final Object OBJECT = new Object();

	public void constantReferenceAssignation() {
		Object obj = OBJECT;
	}

	public void varReferenceAssignation(Object var) {
		Object obj = var;
	}

	public void varArrayReferenceAssignation(Object[] var) {
		Object[] obj = var;
	}

	public void varBiDimArrayReferenceAssignation(Object[][] var) {
		Object[][] obj = var;
	}

	public void varGenericsReferenceAssignation(Class<Object> var) {
		Class<Object> obj = var;
	}

	public void varEnumReferenceAssignation(SourceVersion var) {
		SourceVersion sv = var;
	}
}
