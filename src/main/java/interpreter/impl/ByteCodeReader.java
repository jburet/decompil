package interpreter.impl;

import interpreter.ConstantType;
import interpreter.ErrorInterpretor;
import interpreter.InterpreterException;
import interpreter.utils.ClassFileUtils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.attribute.Attribute;
import model.attribute.AttributeType;
import model.attribute.ClassAttribute;
import model.attribute.Code;
import model.attribute.ConstantValue;
import model.attribute.Exception;
import model.attribute.ExceptionTable;
import model.attribute.InnerClassAttribute;
import model.attribute.RuntimeInvisibleAnnotations;
import model.attribute.RuntimeParameterAnnotations;
import model.attribute.SyntheticAttribute;
import model.classes.ClassFile;
import model.classes.ConstantClass;
import model.classes.ConstantDouble;
import model.classes.ConstantField;
import model.classes.ConstantFloat;
import model.classes.ConstantInteger;
import model.classes.ConstantLong;
import model.classes.ConstantNameType;
import model.classes.ConstantPoolInfo;
import model.classes.ConstantString;
import model.classes.UTF8Constant;
import model.field.FieldInfo;
import model.method.MethodInfo;

public class ByteCodeReader {

	public ClassFile readClassFile(String classFilePath) {
		File f = new File(classFilePath);
		DataInputStream dis;
		try {
			dis = new DataInputStream(new FileInputStream(f));
			return readClass(dis);
		} catch (FileNotFoundException e) {
			throw new InterpreterException(e);
		}

	}

	private ClassFile readClass(DataInputStream dis) {
		// On lit les differents parametre attendu
		ClassFile classFile = new ClassFile();
		// Magic
		try {
			classFile.setMagic(dis.readInt());
			// On check si c'est une fichier class, sinon c'est pas la peine...
			if (!checkIsClassFile(classFile.getMagic())) {
				throw new InterpreterException(ErrorInterpretor.NOT_A_CLASS);
			}
			classFile.setMinorVersion(dis.readShort());
			classFile.setMajorVersion(dis.readShort());
			classFile.setConstantPoolCount(dis.readShort());
			// On doit maintenat parser toutes les constants pool (Double et
			// Long compte double....)
			ConstantPoolInfo tmpCpi;
			int nbConstantToRead = classFile.getConstantPoolCount() - 1;
			List<ConstantPoolInfo> constants = new ArrayList<ConstantPoolInfo>();
			while (nbConstantToRead > 0) {
				tmpCpi = readConstantPoolInfo(dis);
				constants.add(tmpCpi);
				// FIX On doit ajouter des entrees vide si la constant est
				// stocke sur plusieurs entrees (Double, Long) see Classfile
				// format 4.5.5
				for (int i = 1; i < tmpCpi.getConstantCount(); i++) {
					constants.add(null);
				}
				nbConstantToRead = nbConstantToRead - getConstantCount(tmpCpi);
			}
			ConstantPoolInfo[] cpis = new ConstantPoolInfo[constants.size()];
			cpis = constants.toArray(cpis);
			classFile.setConstantPool(cpis);
			// Ensuite access flag
			classFile.setAccessFlags(dis.readShort());
			// Voir pour decodage de l'access flag
			// this_class
			classFile.setThisClass(dis.readShort());
			// super_class
			classFile.setSuperClass(dis.readShort());
			// interface count
			classFile.setInterfacesCount(dis.readShort());
			// interface
			short[] interfaces = new short[classFile.getInterfacesCount()];
			for (int i = 0; i < classFile.getInterfacesCount(); i++) {
				interfaces[i] = dis.readShort();
			}
			classFile.setInterfacesIndex(interfaces);
			// field count
			classFile.setFieldsCount(dis.readShort());
			// Field info
			FieldInfo[] fields = new FieldInfo[classFile.getFieldsCount()];
			for (int i = 0; i < classFile.getFieldsCount(); i++) {
				fields[i] = readFieldInfo(dis, classFile);
			}
			classFile.setFields(fields);
			// Method count
			classFile.setMethodsCount(dis.readShort());
			// Method
			MethodInfo[] methods = new MethodInfo[classFile.getMethodsCount()];
			for (int i = 0; i < classFile.getMethodsCount(); i++) {
				methods[i] = readMethod(dis, classFile);
			}
			classFile.setMethods(methods);
			// Attributes count
			classFile.setAttributesCount(dis.readShort());
			// Attributes
			Attribute[] attributes = new Attribute[classFile.getAttributesCount()];
			for (int i = 0; i < classFile.getAttributesCount(); i++) {
				attributes[i] = readAttribute(dis, classFile);
			}
			classFile.setAttributes(attributes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classFile;
	}

	private Attribute readAttribute(DataInputStream dis, ClassFile classFile) throws IOException {
		Attribute att = new Attribute();
		att.setNameIndex(dis.readShort());
		att.setLength(dis.readInt());
		att.setTypeName(ClassFileUtils.decodeUTF(classFile, att.getNameIndex()));
		// En fonction du type on reconstruit l'objet fils..
		AttributeType type = AttributeType.valueOf(ClassFileUtils.decodeUTF(classFile, att.getNameIndex()));
		att.setAttributeType(type);
		switch (type) {
		case ConstantValue:
			ConstantValue cv = new ConstantValue(att);
			cv.setConstantValueIndex(dis.readShort());
			return cv;
			// case LocalVariableTable:
			// LocalVariableTableAttribute lvt = new
			// LocalVariableTableAttribute(att);
			// TODO Optionnel
			// return lvt;
		case Code:
			Code code = new Code(att);
			code.setMaxStack(dis.readShort());
			code.setMaxLocals(dis.readShort());
			code.setCodeLength(dis.readInt());
			byte[] bufferCode = new byte[code.getCodeLength()];
			dis.read(bufferCode);
			code.setCode(bufferCode);
			code.setExceptionTableLength(dis.readShort());
			ExceptionTable[] exceptionTables = new ExceptionTable[code.getExceptionTableLength()];
			for (int i = 0; i < code.getExceptionTableLength(); i++) {
				exceptionTables[i] = readExceptionTable(classFile, code, dis);
			}
			code.setAttributeCount(dis.readShort());
			Attribute[] attributes = new Attribute[code.getAttributeCount()];
			for (int i = 0; i < code.getAttributeCount(); i++) {
				attributes[i] = readAttribute(dis, classFile);
			}
			code.setAttributes(attributes);
			return code;
		case Exceptions:
			Exception exception = new Exception(att);
			exception.setNumberOfException(dis.readShort());
			short[] exceptionIndex = new short[exception.getNumberOfException()];
			for (int i = 0; i < exception.getNumberOfException(); i++) {
				exceptionIndex[i] = dis.readShort();
			}
			exception.setConstantExceptionIndex(exceptionIndex);
			return exception;
		case InnerClasses:
			InnerClassAttribute innerClass = new InnerClassAttribute(att);
			innerClass.setNumberOfClasses(dis.readShort());
			ClassAttribute[] classesAttribute = new ClassAttribute[innerClass.getNumberOfClasses()];
			ClassAttribute tmpClassAtt;
			for (int i = 0; i < innerClass.getNumberOfClasses(); i++) {
				tmpClassAtt = new ClassAttribute();
				tmpClassAtt.setInnerClassAccessFlags(dis.readShort());
				tmpClassAtt.setOuterClassInfoIndex(dis.readShort());
				tmpClassAtt.setInnerNameIndex(dis.readShort());
				tmpClassAtt.setInnerClassAccessFlags(dis.readShort());
				classesAttribute[i] = tmpClassAtt;
			}
			innerClass.setClasses(classesAttribute);
			return innerClass;
		case Synthetic:
			SyntheticAttribute syntheticAttribute = new SyntheticAttribute(att);
			return syntheticAttribute;
			// TODO Tous les optionnels qui peuventetre ulterieurement au
			// formattage
			// La les truc merdiques (pas vraiment optionnel) (java 5)
			// commence....
		case RuntimeInvisibleAnnotations:
			RuntimeInvisibleAnnotations runtimeInvisibleAnnotations = new RuntimeInvisibleAnnotations(att);
			RuntimeParameterAnnotations tmpAnnotation;
			// Parsings des annations
			for (int i = 0; i < runtimeInvisibleAnnotations.getNumAnnotation(); i++) {
				tmpAnnotation = new RuntimeParameterAnnotations();

			}

		default:
			byte[] buffer = new byte[att.getLength()];
			dis.read(buffer);
			att.setUninterpretedByte(buffer);
			return att;
		}

	}

	private ExceptionTable readExceptionTable(ClassFile classFile, Code code, DataInputStream dis) throws IOException {
		ExceptionTable et = new ExceptionTable();
		et.setStartPc(dis.readShort());
		et.setEndPc(dis.readShort());
		et.setHandlerPc(dis.readShort());
		et.setCatchType(dis.readShort());
		return et;
	}

	private MethodInfo readMethod(DataInputStream dis, ClassFile classFile) throws IOException {
		MethodInfo methodInfo;
		methodInfo = new MethodInfo(classFile);
		methodInfo.setAccessFlags(dis.readShort());
		methodInfo.setNameIndex(dis.readShort());
		methodInfo.setDescriptorIndex(dis.readShort());
		methodInfo.setAttributesCount(dis.readShort());
		Attribute[] attributes = new Attribute[methodInfo.getAttributesCount()];
		for (int i = 0; i < methodInfo.getAttributesCount(); i++) {
			attributes[i] = readAttribute(dis, classFile);
		}
		methodInfo.setAttributes(attributes);
		return methodInfo;
	}

	private FieldInfo readFieldInfo(DataInputStream dis, ClassFile classFile) throws IOException {
		FieldInfo tmpFieldInfo;
		tmpFieldInfo = new FieldInfo();
		tmpFieldInfo.setAccessFlag(dis.readShort());
		tmpFieldInfo.setNameIndex(dis.readShort());
		tmpFieldInfo.setDescriptorIndex(dis.readShort());
		tmpFieldInfo.setAttributeCount(dis.readShort());
		Attribute[] attributes = new Attribute[tmpFieldInfo.getAttributeCount()];
		for (int i = 0; i < tmpFieldInfo.getAttributeCount(); i++) {
			attributes[i] = readAttribute(dis, classFile);
		}
		tmpFieldInfo.setAttributes(attributes);
		return tmpFieldInfo;
	}

	/*
	 * Retourne la valeur de chaque type pour le constantPoolCount
	 */
	private int getConstantCount(ConstantPoolInfo cpi) {
		return cpi.getConstantCount();
	}

	private ConstantPoolInfo readConstantPoolInfo(DataInputStream dis) throws IOException {
		// On lit un octet pour determiner le type.
		byte tag = dis.readByte();
		switch (tag) {
		case ConstantType.CLASS:
			return readConstantClass(dis);
		case ConstantType.FIELD_REF:
			return readConstantField(dis);
		case ConstantType.METHOD_REF:
			return readConstantMethod(dis);
		case ConstantType.INTERFACE_REF:
			return readConstantInterfaceMethod(dis);
		case ConstantType.STRING:
			return readConstantString(dis);
		case ConstantType.INTEGER:
			return readConstantInteger(dis);
		case ConstantType.FLOAT:
			return readConstantFloat(dis);
		case ConstantType.LONG:
			return readConstantLong(dis);
		case ConstantType.DOUBLE:
			return readConstantDouble(dis);
		case ConstantType.NAME_TYPE:
			return readConstantNameType(dis);
		case ConstantType.UTF8:
			return readConstantUtf8(dis);
		default:
			throw new InterpreterException(ErrorInterpretor.NOT_A_CLASS);
		}
	}

	private ConstantPoolInfo readConstantUtf8(DataInputStream dis) throws IOException {
		UTF8Constant res = new UTF8Constant();
		res.setTag(ConstantType.UTF8);
		res.setLength(dis.readShort());
		byte[] buffer = new byte[res.getLength()];
		dis.read(buffer);
		res.setBytes(buffer);
		return res;
	}

	private ConstantPoolInfo readConstantNameType(DataInputStream dis) throws IOException {
		ConstantNameType res = new ConstantNameType();
		res.setTag(ConstantType.NAME_TYPE);
		res.setNameIndex(dis.readShort());
		res.setDescritptorIndex(dis.readShort());
		return res;

	}

	private ConstantPoolInfo readConstantDouble(DataInputStream dis) throws IOException {
		ConstantDouble res = new ConstantDouble();
		res.setTag(ConstantType.DOUBLE);
		res.setBytes(dis.readDouble());
		return res;
	}

	private ConstantPoolInfo readConstantLong(DataInputStream dis) throws IOException {
		ConstantLong res = new ConstantLong();
		res.setTag(ConstantType.LONG);
		res.setBytes(dis.readLong());
		return res;
	}

	private ConstantPoolInfo readConstantFloat(DataInputStream dis) throws IOException {
		ConstantFloat res = new ConstantFloat();
		res.setTag(ConstantType.FLOAT);
		res.setBytes(dis.readFloat());
		return res;
	}

	private ConstantPoolInfo readConstantInteger(DataInputStream dis) throws IOException {
		ConstantInteger res = new ConstantInteger();
		res.setTag(ConstantType.INTEGER);
		res.setBytes(dis.readInt());
		return res;
	}

	private ConstantPoolInfo readConstantString(DataInputStream dis) throws IOException {
		ConstantString res = new ConstantString();
		res.setTag(ConstantType.STRING);
		res.setStringIndex(dis.readShort());
		return res;
	}

	private ConstantPoolInfo readConstantInterfaceMethod(DataInputStream dis) throws IOException {
		ConstantField res = new ConstantField();
		res.setTag(ConstantType.INTERFACE_REF);
		res.setClassIndex(dis.readShort());
		res.setNameTypeIndex(dis.readShort());
		return res;
	}

	private ConstantPoolInfo readConstantMethod(DataInputStream dis) throws IOException {
		ConstantField res = new ConstantField();
		res.setTag(ConstantType.METHOD_REF);
		res.setClassIndex(dis.readShort());
		res.setNameTypeIndex(dis.readShort());
		return res;
	}

	private ConstantPoolInfo readConstantField(DataInputStream dis) throws IOException {
		ConstantField res = new ConstantField();
		res.setTag(ConstantType.FIELD_REF);
		res.setClassIndex(dis.readShort());
		res.setNameTypeIndex(dis.readShort());
		return res;
	}

	private ConstantPoolInfo readConstantClass(DataInputStream dis) throws IOException {
		ConstantClass res = new ConstantClass();
		res.setTag(ConstantType.CLASS);
		res.setNameConstantIndex(dis.readShort());
		return res;
	}

	private boolean checkIsClassFile(int magic) {
		// On doit verifier si le fichier commence par 'cafebabe' ....
		return "cafebabe".equals(Integer.toHexString(magic));
	}

}
