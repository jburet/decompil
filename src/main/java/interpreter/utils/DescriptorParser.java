package interpreter.utils;


import java.util.ArrayList;
import java.util.List;

import model.code.Descriptor;

public class DescriptorParser {

	public static String[] parseDecodedMethodDescriptor(String descriptor) {
		String internalDescriptor = descriptor;
		List<String> res = new ArrayList<String>();
		// On prend 1 caractère et on verifie si type primitif
		while (internalDescriptor.length() > 0) {
			if (ClassFileUtils.isDescriptorPrimitif(internalDescriptor
					.substring(0, 1))) {
				res.add(ClassFileUtils.parseDescriptorPrimitif(
						internalDescriptor.substring(0, 1)).getJavaType());
				internalDescriptor = internalDescriptor.substring(1,
						internalDescriptor.length());
			}
			// Sinon on parse la classe
			else if (internalDescriptor.indexOf(';') > 0) {
				res.add(internalDescriptor.substring(0, internalDescriptor
						.indexOf(';')));
				internalDescriptor = internalDescriptor.substring(
						internalDescriptor.indexOf(';') + 1, internalDescriptor
								.length());
			} else {
				res.add(internalDescriptor);
				internalDescriptor = "";
			}
		}

		return res.toArray(new String[res.size()]);
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
