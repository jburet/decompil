package jdecomp.plugin.java;

import java.util.StringTokenizer;

import jdecomp.core.interpreter.impl.AccessFlagParser;
import jdecomp.core.interpreter.impl.OpCodeInterpreter;
import jdecomp.core.interpreter.utils.ClassFileUtils;
import jdecomp.core.model.classes.ClassAccessFlag;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.code.Descriptor;
import jdecomp.core.model.field.FieldAccessFlag;
import jdecomp.core.model.field.FieldInfo;
import jdecomp.core.model.method.MethodAccessFlag;
import jdecomp.core.model.method.MethodInfo;
import jdecomp.core.visitor.ClassVisitor;

public class ClassVisitorDecompiler implements ClassVisitor {

	private JavaSource javaSource;

	// FIXME
	private OpCodeInterpreter opCodeInterpreter = new OpCodeInterpreter();

	public JavaSource getJavaSource() {
		return javaSource;
	}

	@Override
	public void visitClassFile(ClassFile classFile) {
		javaSource = new JavaSource();
		parsePackageDeclaration(classFile);
		parseClassDeclaration(classFile);
		parseField(classFile);
		parseMethodInfo(classFile);
	}

	private void parseMethodInfo(ClassFile classFile) {
		JavaCodeVisitor jcv;
		MethodSource methodSource;
		MethodInfo mi;
		MethodAccessFlag[] flags;
		StringBuffer sb;
		Descriptor[] argType;
		String[] argName;
		String methodName;
		boolean constructorMethod = false;
		boolean staticConstructorMethod = false;
		for (int i = 0; i < classFile.getMethodsCount(); i++) {
			methodSource = new MethodSource();
			jcv = new JavaCodeVisitor();
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
				sb.append(formatDescriptor(mi.getReturnType()));
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
					sb.append(DescriptorDecompiler.getJavaType(argType[j]));
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
			methodSource.setMethodDeclaration(sb.toString());
			if (!ClassFileUtils.methodAccessFlagContains(flags, MethodAccessFlag.ACC_ABSTRACT)) {
				// FIXME
				jcv.visitMethodInstruction(opCodeInterpreter.constructTree(mi));
				methodSource.addSource(jcv.getJavaIns());
			}
			javaSource.addMethod(methodSource);

		}

	}

	private String formatDescriptor(Descriptor returnType) {
		return DescriptorDecompiler.getJavaType(returnType);
	}

	private void parseField(ClassFile classFile) {
		FieldInfo field;
		FieldAccessFlag[] flag;
		StringBuffer sb;
		String fieldEncodedReturnType;
		for (int i = 0; i < classFile.getFieldsCount(); i++) {
			sb = new StringBuffer();
			// Pour chacun des champs on cherche les access, le type, le nom
			// TODO Instanciation a faire ou dans les constructeurs...
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
			// TODO Lire et gerer les attributs de type signature...
			fieldEncodedReturnType = ClassFileUtils.decodeUTF(classFile, field.getDescriptorIndex());
			sb.append(DescriptorDecompiler.getJavaType(ClassFileUtils.parseDescriptor(fieldEncodedReturnType)));

			// NAME
			sb.append(" ");
			sb.append(ClassFileUtils.decodeUTF(classFile, field.getNameIndex()));

			// Constant value...
			// TODO lire et gerer les attributs de type constantValue
			javaSource.addField(sb.toString());
		}

	}

	private void parseClassDeclaration(ClassFile classFile) {
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
		javaSource.setClassDeclaration(sb.toString());

	}

	private void parsePackageDeclaration(ClassFile classFile) {
		String thisClass = ClassFileUtils.decodeThisClass(classFile);
		StringTokenizer st = new StringTokenizer(thisClass, "/", false);
		int thisClassNbToken = st.countTokens();
		StringBuffer packageSb = new StringBuffer();
		// Si un package est present
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
		javaSource.setPackageDeclaration(packageSb.toString());
	}

}
