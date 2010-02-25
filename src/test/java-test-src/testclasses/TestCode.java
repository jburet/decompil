package testclasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import model.code.OpCodes;

public class TestCode {

	public int testCaseLookupSwith(String var) {
		int i = 0;
		switch (OpCodes.valueOf(var)) {
		case aaload:
			return 0;
		case aastore:
			return 1;
		case aconst_null:
			return 2;
		case aload:
			i++;
		case aload_0:
			i++;
			break;
		default:
			i--;
		}
		return i;
	}

	public void testValueOfEnum(String var) {
		OpCodes.valueOf(var);
		System.out.println("");
	}

	public int testCase2(int var) {
		switch (var) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 4:
			return 4;
		default:
			return 0;
		}
	}

	public int testCase(int var) {
		switch (var) {
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return 0;
		}
	}

	public void testArrayManipulation() {
		int[] a = new int[1];
		a[0] = 1;
		System.out.println(a[0]);
	}

	public Object testConstructObject2() throws FileNotFoundException {
		File file = new File("");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		return br;
	}

	public Object testConstructObject() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(new File("")));
		return br;
	}

	public void testInvoke2(Integer a, Integer b, char[] c) {
		System.out.flush();
		System.out.println("test");
		char[] cs = new char[2];
		cs[0] = 'a';
		cs[1] = 'b';
		System.out.print(cs);
		System.out.printf("test args", a, b);
	}

	public void testWhile2() {
		int i = 0;
		while (i < 10) {
			System.out.println(i);
			i++;
		}
	}

	public void testWhile1() {
		while (true) {
			System.out.println("test");
		}
	}

	public void testFor3() {
		for (;;) {
			System.out.println("test");
		}
	}

	public void testFor2(int[] a) {
		for (int i : a) {
			System.out.println(i);
		}
	}

	public void testFor1() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}

	public void testLabel2() {

		first: {
			int i = 1;
		}

		second: {
			int j = 2;
		}

		third: {
			int k = 3;
		}

	}

	public void testLabel(boolean t) {
		first: {
			second: {
				third: {
					if (t) {
						break second;
					}
					System.out.println("1");
				}
				System.out.println("2");
			}
			System.out.println("3");
		}
	}

	public void pbPop2(Integer a, Integer b) {
		System.out.printf("test args", a, b);
	}

	public void testArrayInit(Integer a, Integer b) {
		System.out.printf("test args", new Object[] { a, b });
	}

	public void pbPop(Integer a, Integer b) {
		System.out.printf("test args", a, b);
	}

	public boolean testConditionnal2(int a, int b) {
		int c = -5;
		int d = 145;
		if (a > d && b > c) {
			return true;
		}
		return false;
	}

	public boolean testConditionnal2(Integer a, Integer b) {
		if (a > 0 && b > 0) {
			return true;
		}
		return false;
	}

	public boolean testConditionnal(int a, int b) {
		return a < b;
	}

	public int testIfElseStatement(boolean b) {
		int i = 0;
		int j = 1;
		if (b) {
			return i;
		} else {
			return j;
		}
	}

	public int testIfElseIfDefaultStatement(int b) {
		int i = 0;
		int j = 1;
		int k = 2;
		if (b == 0) {
			return i;
		} else if (b == 1) {
			return j;
		}
		return k;
	}
}
