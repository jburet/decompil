/**
 *
 */
package model.constant;

/**
 * @author jburet
 * 
 */
public class ClassReferenceType implements Type {

	private final String classDescriptor;

	/**
	 * @param substring
	 */
	public ClassReferenceType(String classDescriptor) {
		this.classDescriptor = classDescriptor;
	}

	@Override
	public boolean isArray() {
		return false;
	}

}
