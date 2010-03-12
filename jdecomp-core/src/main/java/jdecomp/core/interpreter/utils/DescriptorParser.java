/**
 * Copyright (C) 2010 Julien Buret <julien.buret@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jdecomp.core.interpreter.utils;

import java.util.ArrayList;
import java.util.List;

import jdecomp.core.model.code.Descriptor;

public class DescriptorParser {

	public static Descriptor[] parseDecodedMethodDescriptor(String descriptor) {
		String internalDescriptor = descriptor;
		List<Descriptor> res = new ArrayList<Descriptor>();
		// On prend 1 caractere et on verifie si type primitif
		while (internalDescriptor.length() > 0) {
			if (ClassFileUtils.isDescriptorPrimitif(internalDescriptor.substring(0, 1))) {
				res.add(new Descriptor(ClassFileUtils.parseDescriptorPrimitif(internalDescriptor.substring(0, 1))));
				internalDescriptor = internalDescriptor.substring(1, internalDescriptor.length());
			}
			// Sinon on parse la classe
			else if (internalDescriptor.indexOf(';') > 0) {
				res.add(parseReturnDecodedMethodDescriptor(internalDescriptor.substring(0, internalDescriptor
						.indexOf(';'))));
				internalDescriptor = internalDescriptor.substring(internalDescriptor.indexOf(';') + 1,
						internalDescriptor.length());
			} else {
				res.add(parseReturnDecodedMethodDescriptor(internalDescriptor));
				internalDescriptor = "";
			}
		}

		return res.toArray(new Descriptor[res.size()]);
	}

	/**
	 * @param substring
	 * @return
	 */
	public static Descriptor parseReturnDecodedMethodDescriptor(String descriptor) {
		String res;
		return ClassFileUtils.parseDescriptor(descriptor);
	}

}
