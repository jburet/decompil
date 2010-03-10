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

import java.io.UnsupportedEncodingException;

import jdecomp.core.model.classes.ClassAccessFlag;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.classes.ConstantClass;
import jdecomp.core.model.classes.ConstantField;
import jdecomp.core.model.classes.ConstantNameType;
import jdecomp.core.model.classes.ConstantPoolInfo;
import jdecomp.core.model.classes.UTF8Constant;
import jdecomp.core.model.code.Descriptor;
import jdecomp.core.model.constant.DescriptorType;
import jdecomp.core.model.field.FieldAccessFlag;
import jdecomp.core.model.method.MethodAccessFlag;

public class ClassFileUtils {

	public static String decodeThisClass(ClassFile cf) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodedByte(((UTF8Constant) cp[((ConstantClass) cp[cf.getThisClass() - 1]).getNameConstantIndex() - 1])
				.getBytes());
	}

	public static String decodeSuperClass(ClassFile cf) {
		if (cf.getSuperClass() != 0) {
			ConstantPoolInfo[] cp = cf.getConstantPool();
			return decodedByte(((UTF8Constant) cp[((ConstantClass) cp[cf.getSuperClass() - 1]).getNameConstantIndex() - 1])
					.getBytes());
		}
		return "No super class (java/lang/Object only)";
	}

	public static String decodeConstant(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodedByte(((UTF8Constant) cp[((ConstantClass) cp[index - 1]).getNameConstantIndex() - 1]).getBytes());
	}

	public static String decodeUTF(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodedByte(((UTF8Constant) cp[index - 1]).getBytes());
	}

	public static String decodeStaticFieldClass(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodeConstant(cf, ((ConstantField) cp[index - 1]).getClassIndex());
	}

	public static String decodeStaticField(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodeUTF(cf, (((ConstantNameType) cp[((ConstantField) cp[index - 1]).getNameTypeIndex() - 1])
				.getNameIndex()));

	}

	public static boolean classAccessFlagContains(ClassAccessFlag[] cafarrays, ClassAccessFlag caf) {
		for (ClassAccessFlag c : cafarrays) {
			if (c.equals(caf)) {
				return true;
			}
		}
		return false;
	}

	public static boolean methodAccessFlagContains(MethodAccessFlag[] mafarrays, MethodAccessFlag maf) {
		for (MethodAccessFlag m : mafarrays) {
			if (m.equals(maf)) {
				return true;
			}
		}
		return false;
	}

	public static boolean fieldAccessFlagContains(FieldAccessFlag[] fafarrays, FieldAccessFlag faf) {
		for (FieldAccessFlag f : fafarrays) {
			if (f.equals(faf)) {
				return true;
			}
		}
		return false;
	}

	public static String convertByteToJavaClassFormat(String byteFormat) {
		return byteFormat.replace('/', '.');
	}

	public static Descriptor parseDescriptor(String descriptor) {
		if (descriptor.length() == 1) {
			return new Descriptor(parseDescriptorPrimitif(descriptor));
		} else if (descriptor.startsWith("[")) {
			// On cherche le nombre de [
			int arrayLevel = 0;
			String type;
			StringBuffer returnType = new StringBuffer();
			while (descriptor.startsWith("[")) {
				arrayLevel++;
				descriptor = descriptor.substring(1);
			}
			if (descriptor.length() == 1) {
				return new Descriptor(parseDescriptorPrimitif(descriptor), arrayLevel);
			} else {
				return new Descriptor(DescriptorType.CLASS, parseDescriptorClass(descriptor.substring(1)), arrayLevel);
			}
		} else if (descriptor.startsWith("L")) {
			return new Descriptor(DescriptorType.CLASS, parseDescriptorClass(descriptor.substring(1)));
		}
		// TODO Runtime exception
		return null;
	}

	private static String parseDescriptorClass(String classString) {
		if (classString.indexOf(";") > -1) {
			classString = classString.substring(0, classString.indexOf(";"));
		}
		return ClassFileUtils.convertByteToJavaClassFormat(classString);
	}

	private static String decodedByte(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static DescriptorType parseDescriptorPrimitif(String descriptor) {
		return DescriptorType.getDescriptorFromVmtype(descriptor.charAt(0));
	}

	public static boolean isDescriptorPrimitif(String descriptor) {
		DescriptorType dt = DescriptorType.getDescriptorFromVmtype(descriptor.charAt(0));
		return dt != null && !dt.equals(DescriptorType.CLASS);
	}

	public static String getClassName(ClassFile classFile) {
		String className = ClassFileUtils.decodeThisClass(classFile);
		return className.substring(className.lastIndexOf('/') + 1, className.length());
	}
}
