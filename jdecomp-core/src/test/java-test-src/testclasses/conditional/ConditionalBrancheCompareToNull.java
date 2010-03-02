package testclasses.conditional;

public class ConditionalBrancheCompareToNull {

	public boolean compareToNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	public boolean compareToNotNull(Object obj) {
		if (obj != null) {
			return true;
		}
		return false;
	}

}
