package interpreter.utils;

import model.classes.ClassAccessFlag;
import model.classes.ClassFile;
import model.classes.ConstantClass;
import model.classes.ConstantField;
import model.classes.ConstantNameType;
import model.classes.ConstantPoolInfo;
import model.classes.UTF8Constant;
import model.code.Descriptor;
import model.constant.DescriptorType;
import model.field.FieldAccessFlag;
import model.method.MethodAccessFlag;

public class ClassFileUtils {

	public static final String NEWLINE = System.getProperty("line.separator");

	public static String decodeThisClass(ClassFile cf) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return ((UTF8Constant) cp[((ConstantClass) cp[cf.getThisClass() - 1])
				.getNameConstantIndex() - 1]).getDecodedByte();
	}

	public static String decodeSuperClass(ClassFile cf) {
		if (cf.getSuperClass() != 0) {
			ConstantPoolInfo[] cp = cf.getConstantPool();
			return ((UTF8Constant) cp[((ConstantClass) cp[cf.getSuperClass() - 1])
					.getNameConstantIndex() - 1]).getDecodedByte();
		}
		return "No super class (java/lang/Object only)";
	}

	public static String decodeConstant(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return ((UTF8Constant) cp[((ConstantClass) cp[index - 1])
				.getNameConstantIndex() - 1]).getDecodedByte();
	}

	public static String decodeUTF(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return ((UTF8Constant) cp[index - 1]).getDecodedByte();
	}

	public static String decodeStaticFieldClass(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodeConstant(cf, ((ConstantField) cp[index - 1])
				.getClassIndex());
	}

	public static String decodeStaticField(ClassFile cf, short index) {
		ConstantPoolInfo[] cp = cf.getConstantPool();
		return decodeUTF(cf,
				(((ConstantNameType) cp[((ConstantField) cp[index - 1])
						.getNameTypeIndex() - 1]).getNameIndex()));

	}

	public static boolean classAccessFlagContains(ClassAccessFlag[] cafarrays,
			ClassAccessFlag caf) {
		for (ClassAccessFlag c : cafarrays) {
			if (c.equals(caf)) {
				return true;
			}
		}
		return false;
	}

	public static boolean methodAccessFlagContains(
			MethodAccessFlag[] mafarrays, MethodAccessFlag maf) {
		for (MethodAccessFlag m : mafarrays) {
			if (m.equals(maf)) {
				return true;
			}
		}
		return false;
	}

	public static boolean fieldAccessFlagContains(FieldAccessFlag[] fafarrays,
			FieldAccessFlag faf) {
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
				return new Descriptor(parseDescriptorPrimitif(descriptor),
						arrayLevel);
			} else {
				return new Descriptor(DescriptorType.CLASS,
						parseDescriptorClass(descriptor.substring(1)),
						arrayLevel);
			}
		} else if (descriptor.startsWith("L")) {
			return new Descriptor(DescriptorType.CLASS,
					parseDescriptorClass(descriptor.substring(1)));
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

	public static DescriptorType parseDescriptorPrimitif(String descriptor) {
		return DescriptorType.getDescriptorFromVmtype(descriptor.charAt(0));
	}

	public static boolean isDescriptorPrimitif(String descriptor) {
		DescriptorType dt = DescriptorType.getDescriptorFromVmtype(descriptor
				.charAt(0));
		return dt != null && !dt.equals(DescriptorType.CLASS);
	}

	public static String getClassName(ClassFile classFile) {
		String className = ClassFileUtils.decodeThisClass(classFile);
		return className.substring(className.lastIndexOf('/') + 1, className
				.length());
	}
}
