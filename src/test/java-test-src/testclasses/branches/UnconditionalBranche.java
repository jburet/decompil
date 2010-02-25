/**
 *
 */
package testclasses.branches;

/**
 * @author jburet
 * 
 */
public class UnconditionalBranche {

	public void boucleFor() {
		int j = 0;
		for (int i = 0; i < 10; i++) {
			j++;
		}
	}

	public void boucleInfiniteWhile() {
		int j = 0;
		while (true) {
			j++;
		}
	}

	public void boucleWhile(int i) {
		int j = 0;
		while (j < i) {
			j++;
		}
	}

	public void boucleDoWhile(int i) {
		int j = 0;
		do {
			j++;
		} while (j < i);
	}

	public void boucleForBreak(int k) {
		int j = 0;
		for (int i = 0; i < 10; i++) {
			if (j > k) {
				break;
			}
			j++;
		}
	}
}
