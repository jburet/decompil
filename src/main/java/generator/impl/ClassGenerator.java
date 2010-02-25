package generator.impl;

import generator.utils.Keyword;
import interpreter.impl.AccessFlagParser;
import interpreter.impl.OpCodeInterpreter;
import interpreter.utils.ClassFileUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import model.attribute.Attribute;
import model.attribute.AttributeType;
import model.attribute.Code;
import model.classes.ClassAccessFlag;
import model.classes.ClassFile;
import model.code.instruction.Instruction;
import model.field.FieldAccessFlag;
import model.field.FieldInfo;
import model.method.MethodAccessFlag;
import model.method.MethodInfo;


public class ClassGenerator {

	private OpCodeInterpreter codeDecompiler = new OpCodeInterpreter();

	public void generateSource(ClassFile classFile, Writer inWriter) throws IOException {
		BufferedWriter writer = new BufferedWriter(inWriter);
		// La déclaration de la class
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
		// On récupère le nom de la classe au format pack/age/ClassInstance
		String thisClass = ClassFileUtils.decodeThisClass(classFile);
		StringTokenizer st = new StringTokenizer(thisClass, "/", false);
		int thisClassNbToken = st.countTokens();
		StringBuffer packageSb = new StringBuffer();
		// Si un package est présent
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
		// Faire un petit commentaire quand synthetic (générer par le
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
			// TODO Instanciation à faire là ou dans les constructeurs...
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
			// TODO Lire et gérer les attributs de type signature...
			fieldEncodedReturnType = ClassFileUtils.decodeUTF(classFile, field.getDescriptorIndex());
			sb.append(ClassFileUtils.parseDescriptor(fieldEncodedReturnType.substring(0, fieldEncodedReturnType
					.length() - 1)));

			// NAME
			sb.append(" ");
			sb.append(ClassFileUtils.decodeUTF(classFile, field.getNameIndex()));

			// Constant value...
			// TODO lire et gérer les attributs de type constantValue
			sb.append(";");
			writer.write(sb.toString());
			writer.newLine();
		}
	}

	private void writeMethod(ClassFile classFile, BufferedWriter writer) throws IOException {
		MethodInfo mi;
		MethodAccessFlag[] flags;
		StringBuffer sb;
		String descriptorDecoded;
		String[] argType;
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
			descriptorDecoded = classFile.getDecodeMethodDescriptor(mi);
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
					// TODO Le nom devrait être generé par une methode de
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
		// On doit au minimum gérer des attributs de type
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
