package interpreter.utils;

import java.util.ArrayList;
import java.util.List;

import model.code.Descriptor;
import model.constant.ClassReferenceType;
import model.constant.Type;

public class DescriptorParser {

	public static Type[] parseDecodedMethodDescriptor(String descriptor) {
		String internalDescriptor = descriptor;
		List<Type> res = new ArrayList<Type>();
		// On prend 1 caractere et on verifie si type primitif
		while (internalDescriptor.length() > 0) {
			if (ClassFileUtils.isDescriptorPrimitif(internalDescriptor
					.substring(0, 1))) {
				res.add(ClassFileUtils
						.parseDescriptorPrimitif(internalDescriptor.substring(
								0, 1)));
				internalDescriptor = internalDescriptor.substring(1,
						internalDescriptor.length());
			}
			// Sinon on parse la classe
			else if (internalDescriptor.indexOf(';') > 0) {
				res.add(new ClassReferenceType(internalDescriptor.substring(0,
						internalDescriptor.indexOf(';'))));
				internalDescriptor = internalDescriptor.substring(
						internalDescriptor.indexOf(';') + 1, internalDescriptor
								.length());
			} else {
				res.add(new ClassReferenceType(internalDescriptor));
				internalDescriptor = "";
			}
		}

		return res.toArray(new Type[res.size()]);
	}

	/**
	 * @param substring
	 * @return
	 */
	public static Descriptor parseReturnDecodedMethodDescriptor(
			String descriptor) {
		String res;
		return ClassFileUtils.parseDescriptor(descriptor);
	}

}
