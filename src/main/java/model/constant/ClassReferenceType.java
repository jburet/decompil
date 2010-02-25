/**
 *
 */
package model.constant;

/**
 * @author jburet
 * 
 */
public class ClassReferenceType implements Type {

	private String classDescriptor;

	/**
	 * @param substring
	 */
	public ClassReferenceType(String classDescriptor) {
		this.classDescriptor = classDescriptor;
	}

}
