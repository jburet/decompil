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

package jdecomp.core.generator.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.StringTokenizer;

import jdecomp.core.generator.utils.Keyword;
import jdecomp.core.interpreter.impl.AccessFlagParser;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.interpreter.utils.ClassFileUtils;
import jdecomp.core.model.attribute.Attribute;
import jdecomp.core.model.attribute.AttributeType;
import jdecomp.core.model.classes.ClassAccessFlag;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.instruction.Instruction;
import jdecomp.core.model.constant.Type;
import jdecomp.core.model.field.FieldAccessFlag;
import jdecomp.core.model.field.FieldInfo;
import jdecomp.core.model.method.MethodAccessFlag;
import jdecomp.core.model.method.MethodInfo;

public class ClassGenerator {

	private OpCodeInterpreter codeDecompiler = new OpCodeInterpreter();

	public void generateSource(ClassFile classFile, Writer inWriter) throws IOException {
		BufferedWriter writer = new BufferedWriter(inWriter);
		// La declaration de la class
		writePackageDeclaration(classFile, writer);
		// TODO Gestion d'un pool d'import
		// TODO Annotation niveau classe...
		writeClassDeclaration(classFile, writer);
		writeField(classFile, writer);
		writeMethod(classFile, writer);
		writeEndClass(writer);
		writer.flush();
	}

	private void writePackageDeclaration(ClassFile classFile, BufferedWriter writer) throws IOException {
		// On recupere le nom de la classe au format package/ClassInstance
		String thisClass = ClassFileUtils.decodeThisClass(classFile);
		StringTokenizer st = new StringTokenizer(thisClass, "/", false);
		int thisClassNbToken = st.countTokens();
		StringBuffer packageSb = new StringBuffer();
		// Si un package est pr�sent
		if (thisClassNbToken > 1) {
			packageSb.append(Keyword.PACKAGE_KW);
			packageSb.append(" ");
		}
		for (int i = 1; i < thisClassNbToken; i++) {
			if (i > 1) {
				packageSb.append(".");
			}
			packageSb.append(st.nextToken());
		}
		packageSb.append(";");
		writer.append(packageSb.toString());
		writer.newLine();
	}

	private void writeClassDeclaration(ClassFile classFile, BufferedWriter writer) throws IOException {

		// On construis les access et le type (class, enum, annotation,
		// interface)
		ClassAccessFlag[] flag = AccessFlagParser.getClassAccessFlag(classFile.getAccessFlags());
		StringBuffer sb = new StringBuffer();
		if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_PUBLIC)) {
			sb.append(Keyword.PUBLIC_KW);
			sb.append(" ");
		}
		if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_FINAL)) {
			sb.append(Keyword.FINAL_KW);
			sb.append(" ");
		}
		if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_ABSTRACT)) {
			sb.append(Keyword.ABSTRACT_KW);
			sb.append(" ");
		}
		// Faire un petit commentaire quand synthetic (generer par le
		// compililateur)
		if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_SYNTHETIC)) {

		}
		if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_ENUM)) {
			sb.append(Keyword.ENUM_KW);
			sb.append(" ");
		} else if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_ANNOTATION)) {
			sb.append(Keyword.ANNOTATION_KW);
			sb.append(" ");
		} else if (ClassFileUtils.classAccessFlagContains(flag, ClassAccessFlag.ACC_INTERFACE)) {
			sb.append(Keyword.INTERFACE_KW);
			sb.append(" ");
		} else {
			sb.append(Keyword.CLASS_KW);
			sb.append(" ");
		}
		// On mets le nom
		sb.append(ClassFileUtils.getClassName(classFile));
		// Extends
		String superClass = ClassFileUtils.decodeSuperClass(classFile);
		if (superClass != null && !superClass.isEmpty() && !superClass.equals(Keyword.CLASS_OBJECT)) {
			sb.append(" ");
			sb.append(Keyword.EXTEND_KW);
			sb.append(" ");
			sb.append(ClassFileUtils.convertByteToJavaClassFormat(superClass));
		}
		// Implements
		if (classFile.getInterfacesCount() > 0) {
			sb.append(" ");
			sb.append(Keyword.IMPLEMENTS_KW);
			sb.append(" ");
			for (int i = 0; i < classFile.getInterfacesCount(); i++) {
				if (i != 0) {
					sb.append(", ");
				}
				sb.append(ClassFileUtils.convertByteToJavaClassFormat(ClassFileUtils.decodeConstant(classFile,
						classFile.getInterfacesIndex()[i])));
			}
		}
		sb.append("{");
		writer.write(sb.toString());
		writer.newLine();
	}

	private void writeField(ClassFile classFile, BufferedWriter writer) throws IOException {
		FieldInfo field;
		FieldAccessFlag[] flag;
		StringBuffer sb;
		String fieldEncodedReturnType;
		for (int i = 0; i < classFile.getFieldsCount(); i++) {
			sb = new StringBuffer();
			// Pour chacun des champs on cherche les access, le type, le nom
			// TODO Instanciation � faire l� ou dans les constructeurs...
			field = classFile.getFields()[i];
			flag = AccessFlagParser.getFieldAccessFlag(field.getAccessFlag());
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_PRIVATE)) {
				sb.append(Keyword.PRIVATE_KW);
				sb.append(" ");
			} else if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_PROTECTED)) {
				sb.append(Keyword.PROTECTED_KW);
				sb.append(" ");
			} else if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_PUBLIC)) {
				sb.append(Keyword.PUBLIC_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_STATIC)) {
				sb.append(Keyword.STATIC_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_FINAL)) {
				sb.append(Keyword.FINAL_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_VOLATILE)) {
				sb.append(Keyword.VOLATILE_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_TRANSIENT)) {
				sb.append(Keyword.TRANSIENT_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_ENUM)) {

			}
			if (ClassFileUtils.fieldAccessFlagContains(flag, FieldAccessFlag.ACC_SYNTHETIC)) {

			}
			// TYPE
			// TODO Lire et g�rer les attributs de type signature...
			fieldEncodedReturnType = ClassFileUtils.decodeUTF(classFile, field.getDescriptorIndex());
			sb.append(ClassFileUtils.parseDescriptor(fieldEncodedReturnType.substring(0, fieldEncodedReturnType
					.length() - 1)));

			// NAME
			sb.append(" ");
			sb.append(ClassFileUtils.decodeUTF(classFile, field.getNameIndex()));

			// Constant value...
			// TODO lire et gerer les attributs de type constantValue
			sb.append(";");
			writer.write(sb.toString());
			writer.newLine();
		}
	}

	private void writeMethod(ClassFile classFile, BufferedWriter writer) throws IOException {
		MethodInfo mi;
		MethodAccessFlag[] flags;
		StringBuffer sb;
		Type[] argType;
		String[] argName;
		String methodName;
		boolean constructorMethod = false;
		boolean staticConstructorMethod = false;
		for (int i = 0; i < classFile.getMethodsCount(); i++) {
			sb = new StringBuffer();
			mi = classFile.getMethods()[i];
			methodName = ClassFileUtils.decodeUTF(classFile, mi.getNameIndex());
			staticConstructorMethod = methodName.equals(Keyword.STATIC_CONSTRUCTOR_METHOD_NAME);
			constructorMethod = methodName.equals(Keyword.CONSTRUCTOR_METHOD_NAME);
			// Gestion des flag d'access
			flags = AccessFlagParser.getMethodAccessFlag(mi.getAccessFlags());
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_PUBLIC)) {
				sb.append(Keyword.PUBLIC_KW);
				sb.append(" ");
			} else if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_PROTECTED)) {
				sb.append(Keyword.PROTECTED_KW);
				sb.append(" ");
			} else if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_PRIVATE)) {
				sb.append(Keyword.PRIVATE_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_ABSTRACT)) {
				sb.append(Keyword.ABSTRACT_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_STATIC)) {
				sb.append(Keyword.STATIC_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_FINAL)) {
				sb.append(Keyword.FINAL_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_NATIVE)) {
				sb.append(Keyword.NATIVE_KW);
				sb.append(" ");
			}
			if (ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_SYNCHRONIZED)) {
				sb.append(Keyword.SYNCHRONIZED_KW);
				sb.append(" ");
			}
			// TODO Gestion du VARARGS
			// TODO Des autres flags.. (bridge, synthetic, ...)
			// TODO Gestion SIGNATURE (Generics);
			// Type de retour
			if (!constructorMethod && !staticConstructorMethod) {
				sb.append(mi.getReturnType());
				sb.append(" ");
			}
			if (constructorMethod) {
				sb.append(ClassFileUtils.getClassName(classFile));
			} else if (staticConstructorMethod) {

			} else {
				sb.append(methodName);
			}

			if (!staticConstructorMethod) {
				sb.append("(");
				argType = mi.getArgType();
				argName = mi.getArgName();
				for (int j = 0; j < argType.length; j++) {
					sb.append(argType[j]);
					// TODO Le nom devrait etre genere par une methode de
					// MethodInfo
					sb.append(" ");
					sb.append(argName[j]);
					// Sauf dernier
					if (j != argType.length - 1) {
						sb.append(", ");
					}
				}
				sb.append(")");
			}
			if (!ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_ABSTRACT)) {
				sb.append("{");
				sb.append(ClassFileUtils.NEWLINE);
				sb.append(writeCode(mi));
				sb.append(ClassFileUtils.NEWLINE);
				sb.append("}");
			} else {
				sb.append(";");
			}
			writer.write(sb.toString());
			writer.newLine();
		}

	}

	private String writeCode(MethodInfo mi) {
		// On doit au minimum g�rer des attributs de type
		// Code, Exception, Signature
		StringBuffer sb = new StringBuffer();
		for (Attribute att : mi.getAttributes()) {
			if (att.getAttributeType().equals(AttributeType.Code)) {
				// sb.append(printCode(codeDecompiler.decompileCode((Code)att)));
			}
		}
		return sb.toString();
	}

	private String printCode(List<Instruction> decompileCode) {
		// TODO
		return "TODO";
	}

	private void writeEndClass(BufferedWriter writer) throws IOException {
		writer.newLine();
		writer.write('}');
	}
}
