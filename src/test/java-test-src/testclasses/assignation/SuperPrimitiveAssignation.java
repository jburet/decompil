/**
 *
 */
package testclasses.assignation;

/**
 * @author jburet
 * 
 */
public class SuperPrimitiveAssignation extends PrimitiveAssignation {

	private int varint = 5;
	private static int staticint = 5;

	public void readIntSuperInstanceField() {
		int i = super.varint;
	}

	public void writeIntSuperInstanceField(int i) {
		super.varint = i;
	}

	public void readIntSuperStaticField() {
		int i = PrimitiveAssignation.staticint;
	}

	public void writeIntSuperStaticField(int i) {
		PrimitiveAssignation.staticint = i;
	}

	@Override
	public void readIntInstanceField() {
		int i = varint;
	}

	@Override
	public void writeIntInstanceField(int i) {
		varint = i;
	}

	@Override
	public void readIntStaticField() {
		int i = staticint;
	}

	@Override
	public void writeIntStaticField(int i) {
		staticint = i;
	}

	public void tst() {
		this.varint = super.varint;
	}
}
