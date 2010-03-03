package testclasses.integration;

public class Integration {

	public boolean integration(int a, int b, int c) {
		boolean res = false;
		long[] longRes = new long[20];
		for (int i = 0; i < 20; i++) {
			longRes[i] = i;
		}
		Integer.valueOf(c);
		if (a > b) {
			res = true;
		} else if (b > c) {
			res = (c % a) > 10;
		} else {
			return true;
		}
		return false;
	}
}
