package testclasses;

/**
 * @author Erwan ALLIAUME
 */
public class MyClass extends MyAbstract implements MyInterface {

	private String test = null;
	
	@Override
	public String getTest() {
		if (test == null) {
			return TEST;
		}
		return test;
	}

	@Override
	public void setTest(String test) {
		this.test = test;
	}

	public static void main(String... args) {
		final MyClass myClass = new MyClass();
		myClass.setTest("azerty");
		System.err.println(myClass.getTest());
	}

}
