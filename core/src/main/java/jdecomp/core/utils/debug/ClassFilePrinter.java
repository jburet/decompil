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

package jdecomp.core.utils.debug;

import jdecomp.core.interpreter.impl.AccessFlagParser;
import jdecomp.core.interpreter.utils.ClassFileUtils;
import jdecomp.core.model.attribute.Attribute;
import jdecomp.core.model.attribute.AttributeType;
import jdecomp.core.model.attribute.Code;
import jdecomp.core.model.attribute.ConstantValue;
import jdecomp.core.model.classes.ClassAccessFlag;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.classes.ConstantPoolInfo;
import jdecomp.core.model.classes.ConstantPoolValue;
import jdecomp.core.model.classes.ConstantString;
import jdecomp.core.model.field.FieldAccessFlag;
import jdecomp.core.model.field.FieldInfo;
import jdecomp.core.model.method.MethodAccessFlag;
import jdecomp.core.model.method.MethodInfo;

public class ClassFilePrinter {

	public static void print(ClassFile cf) {
		System.out.println("u4 magic : " + Integer.toHexString(cf.getMagic()));
		System.out.println("u2 minor_version : " + cf.getMinorVersion());
		System.out.println("u2 major_version : " + cf.getMajorVersion());
		System.out.println("u2 constant_pool_count : "
				+ cf.getConstantPoolCount());
		int i = 1;
		for (ConstantPoolInfo cpi : cf.getConstantPool()) {
			System.out.println("Constant " + i + " : " + cpi.toString());
			i++;
		}
		// cp_info constant_pool[constant_pool_count-1];
		System.out.println("u2 access_flags : "
				+ Integer.toHexString(cf.getAccessFlags()));
		for (ClassAccessFlag caf : AccessFlagParser.getClassAccessFlag(cf
				.getAccessFlags())) {
			System.out.println("access_flag : " + caf.toString());
		}
		System.out.println("u2 this_class_index : " + cf.getThisClass());
		System.out.println("u2 this_class_decoded : "
				+ ClassFileUtils.decodeThisClass(cf));
		System.out.println("u2 super_class : " + cf.getSuperClass());
		System.out.println("u2 super_class_decoded : "
				+ ClassFileUtils.decodeSuperClass(cf));
		System.out.println("u2 interfaces_count : " + cf.getInterfacesCount());
		for (short interfaceIndex : cf.getInterfacesIndex()) {
			System.out.println("decoded interface : "
					+ ClassFileUtils.decodeConstant(cf, interfaceIndex));
		}
		System.out.println("u2 fields_count : " + cf.getFieldsCount());
		for (FieldInfo fi : cf.getFields()) {
			printFieldInfo(fi, cf);
		}
		// field_info fields[fields_count];
		System.out.println("u2 methods_count : " + cf.getMethodsCount());
		for (MethodInfo mi : cf.getMethods()) {
			printMethodInfo(mi, cf);
		}
		// method_info methods[methods_count];
		System.out.println("u2 attributes_count : " + cf.getAttributesCount());
		// attribute_info attributes[attributes_count];
		for (Attribute att : cf.getAttributes()) {
			printAttribute(att, cf);
		}
	}

	private static void printAttribute(Attribute att, ClassFile cf) {
		System.out.println("attribute name index :" + att.getNameIndex());
		System.out.println("attribute name :" + att.getAttributeType());
		System.out.println("attribute type name :" + att.getTypeName());
		System.out.println("attribute length : " + att.getLength());
		// Gestion des sousclasses
		if (att.getAttributeType() != null) {
			if (att.getAttributeType().equals(AttributeType.ConstantValue)) {
				ConstantValue cv = (ConstantValue) att;
				System.out.println("Constant Value...");
				printConstant(cv.getConstantValueIndex(), cf);
			} else if (att.getAttributeType().equals(AttributeType.Code)) {
				Code code = (Code) att;
				System.out.println("Code !!!");
				printCode(code, cf);
			}
		}

	}

	private static void printCode(Code code, ClassFile cf) {
		System.out.println("maxStack : " + code.getMaxStack());
		System.out.println("maxLocals : " + code.getMaxLocals());
		System.out.println("codeLength : " + code.getCodeLength());
		// Print code
		System.out.println("exceptionTableLength : "
				+ code.getExceptionTableLength());
		// print exception table
		System.out.println("attributeCount : " + code.getAttributeCount());
		for (Attribute att : code.getAttributes()) {
			printAttribute(att, cf);
		}
	}

	private static void printConstant(short constantValueIndex, ClassFile cf) {
		ConstantPoolInfo cpi = cf.getConstantPool()[constantValueIndex - 1];
		if (cpi instanceof ConstantPoolValue) {
			System.out.println("value : "
					+ ((ConstantPoolValue) cpi).getDecodedBytes());
		} else if (cpi instanceof ConstantString) {
			System.out.println("value : "
					+ ClassFileUtils.decodeUTF(cf, ((ConstantString) cpi)
							.getStringIndex()));
		}

	}

	private static void printMethodInfo(MethodInfo mi, ClassFile cf) {
		System.out.println("method access : "
				+ Integer.toHexString(mi.getAccessFlags()));
		for (MethodAccessFlag maf : AccessFlagParser.getMethodAccessFlag(mi
				.getAccessFlags())) {
			System.out.println("access_flag : " + maf.toString());
		}
		System.out.println("method name : "
				+ ClassFileUtils.decodeUTF(cf, mi.getNameIndex()));
		System.out.println("method descriptorindex : "
				+ ClassFileUtils.decodeUTF(cf, mi.getDescriptorIndex()));
		System.out.println("method attribute count : "
				+ mi.getAttributesCount());
		for (Attribute ai : mi.getAttributes()) {
			printAttribute(ai, cf);
		}
	}

	private static void printFieldInfo(FieldInfo fi, ClassFile cf) {
		System.out.println("field name : "
				+ ClassFileUtils.decodeUTF(cf, fi.getNameIndex()));
		System.out.println("descriptor : "
				+ ClassFileUtils.decodeUTF(cf, fi.getDescriptorIndex()));
		System.out.println("field accessFlag : "
				+ Integer.toHexString(fi.getAccessFlag()));
		for (FieldAccessFlag faf : AccessFlagParser.getFieldAccessFlag(fi
				.getAccessFlag())) {
			System.out.println("access_flag : " + faf.toString());
		}
		System.out.println("field attibute count: " + fi.getAttributeCount());
		for (Attribute ai : fi.getAttributes()) {
			printAttribute(ai, cf);
		}

	}
}
