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

package interpreter.impl;

import interpreter.ConstantType;
import interpreter.utils.ClassFileUtils;
import interpreter.utils.DescriptorParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.attribute.Code;
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
import model.code.Descriptor;
import model.code.OpCodes;
import model.code.instruction.AssignationArrayInstruction;
import model.code.instruction.AssignationInstruction;
import model.code.instruction.BlockInstruction;
import model.code.instruction.ConditionalBrancheInstruction;
import model.code.instruction.InstanceMethodInvocationInstruction;
import model.code.instruction.MethodInstruction;
import model.code.instruction.ReturnInstruction;
import model.code.instruction.StatementInstruction;
import model.code.instruction.StaticMethodInvocationInstruction;
import model.code.instruction.SwitchInstruction;
import model.code.instruction.UnconditionalBranching;
import model.code.operand.ArithmeticOperationType;
import model.code.operand.Array;
import model.code.operand.ArrayType;
import model.code.operand.ConditionalOperator;
import model.code.operand.ObjectReference;
import model.code.operand.Operand;
import model.code.operand.Variable;
import model.code.operand.impl.ArithmeticOperation;
import model.code.operand.impl.ArrayAccessInstruction;
import model.code.operand.impl.ArrayReference;
import model.code.operand.impl.Constant;
import model.code.operand.impl.ConstantArrayReference;
import model.code.operand.impl.SimpleInvocationOperandResult;
import model.code.operand.impl.SimpleVariable;
import model.constant.DescriptorType;
import model.constant.Type;
import model.method.MethodInfo;

/**
 * @author jburet
 * 
 */
public class OpCodeInterpreter {

	// Le but va etre de construire une premiere structure (sous forme de block
	// de code).
	public MethodInstruction constructTree(MethodInfo methodInfo) {
		// Constant pool
		ConstantPoolInfo[] constants = methodInfo.getReferentClassFile().getConstantPool();
		// Creation de la table des variable locales
		// Contient l'index et le nom de la varible
		Map<Integer, Variable> localVariable = constructLocalVariable();
		// On remplit la table avec les argument de la methode.
		fillLocalVariableWithArg(localVariable, methodInfo);

		// Recuperation du code
		Code codeInfo = MethodInfo.getCode(methodInfo);

		// Instruction representant la methode dans sa globalite.
		MethodInstruction methodInstruction = new MethodInstruction(null, (short) 0,
				(short) (codeInfo.getCode().length - 1));
		// Block d'instruction courant
		BlockInstruction currentInstruction = methodInstruction;

		// Stack
		Stack<Operand> operandStack = new Stack<Operand>();

		short endOfCurrentBlock = -1;
		byte[] code = codeInfo.getCode();

		// variable tmp index
		int tmpIndex;
		// variable tmp nom de variable
		String tmpVarName;
		// tmp constantField
		ConstantField constantField;
		// Tmp ConstantNametype
		ConstantNameType constantNameType;
		// Tmp constant class
		ConstantClass constantClass;
		// tmp methode name
		String tmpMethodName;
		// tmp classe name
		String tmpClassName;
		// Tmp current index
		short currentPosition = 0;
		// Tmp Operand
		Operand operand;
		// Tmp Operand[]
		Operand[] operandArray;
		// Tmp Constant
		Constant c;
		// tmp dimenssion
		int dimension;
		// Variable temporaire tableswitch
		int defaultIndex;
		int highIndex;
		int lowIndex;
		int[] jumpOffset;
		int[] valueMatch;
		int nbOfCase;

		// Variable temp invocation
		String descriptor;
		Type[] argsType;
		Descriptor returnType;
		Operand[] operands;

		OpCodes opc;
		for (short i = 0; i < code.length; i++) {
			currentPosition = i;
			opc = OpCodes.getBySignedByte(code[i]);

			// Analyse de l'opcode courant
			switch (opc) {
			// Comparaison d'un int par rapport a 0
			// Le int a comparer se recupere dans la pile
			// Les 2 arguments permette de construire l'adresse ou aller si
			// la condition n'est pas respectee.
			case ifeq:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.EQ,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			case ifne:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.NE,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			case iflt:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LT,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			case ifle:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LE,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			case ifgt:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GT,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			case ifge:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GE,
						i, endOfCurrentBlock, operandStack.pop(), new Constant("int", "0")));
				break;
			// Comparaison des 2 premiers int de la stack
			// Les 2 arguments permette de construire l'adresse ou aller si
			// la condition n'est pas respectee.
			case if_icmpeq:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.EQ,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;
			case if_icmpge:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GE,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;
			case if_icmpgt:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GT,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;
			case if_icmple:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LE,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;
			case if_icmplt:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LT,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;
			case if_icmpne:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.NE,
						i, endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
				break;

			// Boucle conditionelle
			case goto_:
				endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
				currentInstruction.addInstruction(currentPosition, createUnconditionalBranching(currentPosition,
						endOfCurrentBlock));
				break;

			// Assignation de int dans les variables locale
			case istore:
				tmpIndex = getUnsignedValue(code[++i]);
				tmpVarName = getVariableName(tmpIndex, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case istore_0:
				tmpVarName = getVariableName(0, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case istore_1:
				tmpVarName = getVariableName(1, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case istore_2:
				tmpVarName = getVariableName(2, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case istore_3:
				tmpVarName = getVariableName(3, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;

			case lstore_0:
				tmpVarName = getVariableName(0, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case lstore_1:
				tmpVarName = getVariableName(1, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case lstore_2:
				tmpVarName = getVariableName(2, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case lstore_3:
				tmpVarName = getVariableName(3, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case lstore:
				tmpIndex = getUnsignedValue(code[++i]);
				tmpVarName = getVariableName(tmpIndex, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;

			case fstore_0:
				tmpVarName = getVariableName(0, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case fstore_1:
				tmpVarName = getVariableName(1, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case fstore_2:
				tmpVarName = getVariableName(2, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case fstore_3:
				tmpVarName = getVariableName(3, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case fstore:
				tmpIndex = getUnsignedValue(code[++i]);
				tmpVarName = getVariableName(tmpIndex, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;

			case dstore_0:
				tmpVarName = getVariableName(0, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case dstore_1:
				tmpVarName = getVariableName(1, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case dstore_2:
				tmpVarName = getVariableName(2, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case dstore_3:
				tmpVarName = getVariableName(3, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			case dstore:
				tmpIndex = getUnsignedValue(code[++i]);
				tmpVarName = getVariableName(tmpIndex, localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operandStack.pop()));
				break;
			// Modification de variable locale
			case iinc:
				tmpVarName = getVariableName(getUnsignedValue(code[++i]), localVariable);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, new ArithmeticOperation(new SimpleVariable(DescriptorType.INT, tmpVarName),
								new Constant("int", Integer.toString(getUnsignedValue(code[++i]))),
								ArithmeticOperationType.PLUS)));
				break;
			// Stocke une reference du stack vers les variable locales
			case astore_0:
				operand = operandStack.pop();
				tmpVarName = getVariableNameFromRef(0, localVariable, operand);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operand));
				break;
			case astore_1:
				operand = operandStack.pop();
				tmpVarName = getVariableNameFromRef(1, localVariable, operand);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operand));
				break;
			case astore_2:
				operand = operandStack.pop();
				tmpVarName = getVariableNameFromRef(2, localVariable, operand);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operand));
				break;
			case astore_3:
				operand = operandStack.pop();
				tmpVarName = getVariableNameFromRef(3, localVariable, operand);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operand));
				break;
			case astore:
				tmpIndex = getUnsignedValue(code[++i]);
				operand = operandStack.pop();
				tmpVarName = getVariableNameFromRef(tmpIndex, localVariable, operand);
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						tmpVarName, operand));
				break;

			// Chargement de variable local dans la stack
			case iload_0:
				operandStack.push(localVariable.get(0));
				break;
			case iload_1:
				operandStack.push(localVariable.get(1));
				break;
			case iload_2:
				operandStack.push(localVariable.get(2));
				break;
			case iload_3:
				operandStack.push(localVariable.get(3));
				break;
			case iload:
				tmpIndex = getUnsignedValue(code[++i]);
				operandStack.push(localVariable.get(tmpIndex));
				break;

			case lload_0:
				operandStack.push(localVariable.get(0));
				break;
			case lload_1:
				operandStack.push(localVariable.get(1));
				break;
			case lload_2:
				operandStack.push(localVariable.get(2));
				break;
			case lload_3:
				operandStack.push(localVariable.get(3));
				break;
			case lload:
				tmpIndex = getUnsignedValue(code[++i]);
				operandStack.push(localVariable.get(tmpIndex));
				break;

			case fload_0:
				operandStack.push(localVariable.get(0));
				break;
			case fload_1:
				operandStack.push(localVariable.get(1));
				break;
			case fload_2:
				operandStack.push(localVariable.get(2));
				break;
			case fload_3:
				operandStack.push(localVariable.get(3));
				break;
			case fload:
				tmpIndex = getUnsignedValue(code[++i]);
				operandStack.push(localVariable.get(tmpIndex));
				break;

			case dload_0:
				operandStack.push(localVariable.get(0));
				break;
			case dload_1:
				operandStack.push(localVariable.get(1));
				break;
			case dload_2:
				operandStack.push(localVariable.get(2));
				break;
			case dload_3:
				operandStack.push(localVariable.get(3));
				break;
			case dload:
				tmpIndex = getUnsignedValue(code[++i]);
				operandStack.push(localVariable.get(tmpIndex));
				break;

			case aload_0:
				operandStack.push(localVariable.get(0));
				break;
			case aload_1:
				operandStack.push(localVariable.get(1));
				break;
			case aload_2:
				operandStack.push(localVariable.get(2));
				break;
			case aload_3:
				operandStack.push(localVariable.get(3));
				break;
			case aload:
				tmpIndex = getUnsignedValue(code[++i]);
				operandStack.push(localVariable.get(tmpIndex));
				break;

			// Chargement de constant dans la stack
			case iconst_m1:
				operandStack.push(new Constant("int", "-1"));
				break;
			case iconst_0:
				operandStack.push(new Constant("int", "0"));
				break;
			case iconst_1:
				operandStack.push(new Constant("int", "1"));
				break;
			case iconst_2:
				operandStack.push(new Constant("int", "2"));
				break;
			case iconst_3:
				operandStack.push(new Constant("int", "3"));
				break;
			case iconst_4:
				operandStack.push(new Constant("int", "4"));
				break;
			case iconst_5:
				operandStack.push(new Constant("int", "5"));
				break;
			case bipush:
				operandStack.push(new Constant("int", Byte.toString((code[++i]))));
				break;
			case sipush:
				operandStack.push(new Constant("int", Short.toString(getIndex(code[++i], code[++i]))));
				break;
			case aconst_null:
				operandStack.push(ObjectReference.NULL_REFERENCE);
				break;
			case fconst_0:
				operandStack.push(new Constant("float", "0.0"));
				break;
			case fconst_1:
				operandStack.push(new Constant("float", "1.0"));
				break;
			case fconst_2:
				operandStack.push(new Constant("float", "2.0"));
				break;
			case dconst_0:
				operandStack.push(new Constant("double", "0.0d"));
				break;
			case dconst_1:
				operandStack.push(new Constant("double", "1.0d"));
				break;
			case lconst_0:
				operandStack.push(new Constant("long", "0l"));
				break;
			case lconst_1:
				operandStack.push(new Constant("long", "1l"));
				break;

			// Ajout d'un field static dans la stack
			case getstatic:
				// On recupere le field du constant pool
				// FIXME On genere de ObjectReference ou un ArrayReference selon
				// le type
				tmpIndex = getIndex(code[++i], code[++i]);
				constantField = (ConstantField) constants[tmpIndex - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				operandStack.push(new ObjectReference(null, ClassFileUtils.decodeStaticFieldClass(methodInfo
						.getReferentClassFile(), (short) tmpIndex), ClassFileUtils.decodeUTF(methodInfo
						.getReferentClassFile(), constantNameType.getNameIndex()), DescriptorParser
						.parseReturnDecodedMethodDescriptor(ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(),
								constantNameType.getDescritptorIndex()))));
				break;

			case putstatic:
				constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType.getNameIndex()),
						operandStack.pop()));

				break;

			// Ajout d'une constante du constant pool dans la stack
			case ldc:
				tmpIndex = getUnsignedValue(code[++i]);
				c = getConstantFromConstantPool(tmpIndex, methodInfo.getReferentClassFile());
				operandStack.push(c);
				break;

			case ldc_w:
				tmpIndex = getIndex(code[++i], code[++i]);
				c = getConstantFromConstantPool(tmpIndex, methodInfo.getReferentClassFile());
				operandStack.push(c);
				break;

			case ldc2_w:
				tmpIndex = getIndex(code[++i], code[++i]);
				c = getConstantFromConstantPool(tmpIndex, methodInfo.getReferentClassFile());
				operandStack.push(c);
				break;

			// Invocation de methode
			case invokestatic:
				// Methode de d'instance
				// On recupere la reference ce la methode a appeler
				constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				// On recupere le nom de la methode a appeler.
				tmpMethodName = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
						.getNameIndex());
				// On la signatures de la méthode
				descriptor = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
						.getDescritptorIndex());
				argsType = DescriptorParser.parseDecodedMethodDescriptor(descriptor.substring(
						descriptor.indexOf('(') + 1, descriptor.indexOf(')')));
				returnType = DescriptorParser.parseReturnDecodedMethodDescriptor(descriptor.substring(descriptor
						.indexOf(')') + 1));

				operands = new Operand[argsType.length];
				for (int j = 0; j < argsType.length; j++) {
					operands[j] = operandStack.pop();
				}

				if (returnType.getDescriptorType() != DescriptorType.VOID) {
					// S'il y a un retour alors c'est un operand, le resultat
					// est stocke dans la stack (c'est une
					// autre
					// instruction qui contient le resultat de la methode)
					operandStack
							.push(new SimpleInvocationOperandResult(new StaticMethodInvocationInstruction(
									currentPosition, "TODO-CLASSNAME", tmpMethodName, returnType.getDescriptorType(),
									operands)));
				} else {
					// Sinon on creer l'instruction
					currentInstruction
							.addInstruction(currentPosition, new StaticMethodInvocationInstruction(currentPosition,
									"TODO-CLASSNAME", tmpMethodName, returnType.getDescriptorType(), operands));
				}

				break;
			case invokespecial:

			case invokevirtual:
				// Methode de d'instance
				// On recupere la reference ce la methode a appeler
				constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				// On recupere le nom de la methode a appeler.
				tmpMethodName = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
						.getNameIndex());
				// On la signatures de la methode
				descriptor = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
						.getDescritptorIndex());
				argsType = DescriptorParser.parseDecodedMethodDescriptor(descriptor.substring(
						descriptor.indexOf('(') + 1, descriptor.indexOf(')')));
				returnType = DescriptorParser.parseReturnDecodedMethodDescriptor(descriptor.substring(descriptor
						.indexOf(')') + 1));
				operands = new Operand[argsType.length];
				for (int j = 0; j < argsType.length; j++) {
					operands[j] = operandStack.pop();
				}

				if (returnType.getDescriptorType() != DescriptorType.VOID) {
					// S'il y a un retour alors c'est un operand, le resultat
					// est stocke dans la stack (c'est une
					// autre
					// instruction qui contient le resultat de la methode)
					operandStack.push(new SimpleInvocationOperandResult(new InstanceMethodInvocationInstruction(
							currentPosition, operandStack.pop(), tmpMethodName, returnType.getDescriptorType(),
							operands)));
				} else {
					// Sinon on creer l'instruction
					currentInstruction.addInstruction(currentPosition, new InstanceMethodInvocationInstruction(
							currentPosition, operandStack.pop(), tmpMethodName, DescriptorType.VOID, operands));
				}

				break;

			// Gestion des tableaux
			case newarray:
				// Ajout d'une reference de tableau dans la stack
				operandStack.push(new ConstantArrayReference(ArrayType.getByCode((short) getUnsignedValue(code[++i])),
						operandStack.pop()));
				break;

			case anewarray:
				// Ajout d'un tableau de reference dans la stack
				tmpIndex = getIndex(code[++i], code[++i]);
				constantClass = (ConstantClass) constants[tmpIndex - 1];
				operandStack.push(new ConstantArrayReference(ArrayType.T_REF, ClassFileUtils
						.parseDescriptor(ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantClass
								.getNameConstantIndex())), operandStack.pop()));
				break;
			case multianewarray:
				tmpIndex = getIndex(code[++i], code[++i]);
				constantClass = (ConstantClass) constants[tmpIndex - 1];
				dimension = getUnsignedValue(code[++i]);
				operandArray = new Operand[dimension];
				for (int j = 0; j < dimension; j++) {
					operandArray[j] = operandStack.pop();
				}
				operandStack.push(new ConstantArrayReference(ArrayType.T_REF, ClassFileUtils
						.parseDescriptor(ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantClass
								.getNameConstantIndex())), operandArray));
				break;
			case arraylength:
				// Appel l'instruction java length sur un tableau
				operandStack.pop();
				// FIXME On push une primitive de type entier recuperer du
				// champs d'un object
				operandStack.push(new Constant("int", "length"));
				break;

			// Ajout d'une valeur dans un tableau
			case iastore:
			case bastore:
			case castore:
			case aastore:
			case sastore:
			case lastore:
			case fastore:
			case dastore:
				currentInstruction.addInstruction(currentPosition, new AssignationArrayInstruction(currentPosition,
						operandStack.pop(), operandStack.pop(), (Array) operandStack.pop()));
				break;

			case iaload:
			case baload:
			case caload:
			case aaload:
			case saload:
			case laload:
			case faload:
			case daload:
				operandStack.push(new ArrayAccessInstruction(operandStack.pop(), (Array) operandStack.pop()));
				break;

			// get a field
			case getfield:
				constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				constantClass = (ConstantClass) constants[constantField.getClassIndex() - 1];
				// FIXME Definition du type (disponible dans constantNameType)
				// On determine si c'est une reference a un tableau ou non
				Descriptor desc = ClassFileUtils.parseDescriptor(ClassFileUtils.decodeUTF(methodInfo
						.getReferentClassFile(), constantNameType.getDescritptorIndex()));
				if (desc.isArray()) {
					// FIXME get the dimension from descriptor...
					operandStack.push(new ArrayReference(operandStack.pop(), ClassFileUtils.decodeUTF(methodInfo
							.getReferentClassFile(), constantNameType.getNameIndex()), null, 1));
				} else {
					operandStack.push(new ObjectReference(operandStack.pop(), ClassFileUtils.decodeUTF(methodInfo
							.getReferentClassFile(), constantClass.getNameConstantIndex()), ClassFileUtils.decodeUTF(
							methodInfo.getReferentClassFile(), constantNameType.getNameIndex()), DescriptorParser
							.parseReturnDecodedMethodDescriptor(ClassFileUtils.decodeUTF(methodInfo
									.getReferentClassFile(), constantNameType.getDescritptorIndex()))));
				}

				break;

			case putfield:
				constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
				constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
				currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition,
						ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType.getNameIndex()),
						operandStack.pop()));
				// TODO Reference de l'objet à laquel le champ appartient... a
				// prendre en compte dans les assignations.
				operandStack.pop();
				break;

			// Return operation
			case return_:
				currentInstruction.addInstruction(currentPosition, new ReturnInstruction(currentPosition));
				break;

			case dreturn:
			case freturn:
			case lreturn:
			case areturn:
			case ireturn:
				currentInstruction.addInstruction(currentPosition, new ReturnInstruction(currentPosition, operandStack
						.pop()));
				break;

			case new_:
				constantClass = (ConstantClass) constants[getIndex(code[++i], code[++i]) - 1];
				// On recupere le nom de la methode a appeler.
				tmpClassName = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantClass
						.getNameConstantIndex());
				operandStack.push(new ObjectReference(null, null, tmpClassName, null));
				break;

			// Switch
			case tableswitch:
				// Des bytes de padding peuvent etre present pour que le
				// debut
				// du switch soit sur un multiple de 4
				i = alignOnInt(i);
				defaultIndex = getWideIndex(code[++i], code[++i], code[++i], code[++i]);
				lowIndex = getWideIndex(code[++i], code[++i], code[++i], code[++i]);
				highIndex = getWideIndex(code[++i], code[++i], code[++i], code[++i]);
				nbOfCase = highIndex - lowIndex;
				jumpOffset = new int[nbOfCase];
				i = (short) (i + createJumpOffset(currentPosition, defaultIndex, lowIndex, highIndex, code, jumpOffset));
				createSwitchFromTableSwitch(currentPosition, operandStack.pop(), defaultIndex, jumpOffset);
				break;

			case lookupswitch:
				i = alignOnInt(i);
				defaultIndex = getWideIndex(code[++i], code[++i], code[++i], code[++i]);
				nbOfCase = getWideIndex(code[++i], code[++i], code[++i], code[++i]);
				valueMatch = new int[nbOfCase];
				jumpOffset = new int[nbOfCase];
				i = (short) (i + createJumpOffsetMatch(currentPosition, defaultIndex, nbOfCase, code, valueMatch,
						jumpOffset));
				createSwitchFromLookupSwitch(currentPosition, operandStack.pop(), defaultIndex, valueMatch, jumpOffset);

				break;
			// Stack operation
			case dup:
				// Duplique le premier element de la pile
				operandStack.push(operandStack.peek());
				break;
			case pop:
				operand = operandStack.pop();
				if (operand instanceof StaticMethodInvocationInstruction) {
					currentInstruction.addInstruction(currentPosition, (StaticMethodInvocationInstruction) operand);
				}
				break;

			// Conversion de type
			// a priori rien a faire
			case i2b:
			case i2c:
			case i2d:
			case i2f:
			case i2l:
			case i2s:
				break;

			default:
				currentInstruction.addInstruction(i, new StatementInstruction(i, opc));
			}
		}
		return methodInstruction;
	}

	private short createJumpOffset(short currentIndex, int defaultIndex, int lowIndex, int highIndex, byte[] code,
			int[] jumpOffset) {
		return (short) ((highIndex - lowIndex) * 4);
	}

	/**
	 * @param currentPosition
	 * @param defaultIndex
	 * @param nbOfCase
	 * @param code
	 * @param valueMatch
	 * @param jumpOffset
	 * @return
	 */
	private short createJumpOffsetMatch(short currentPosition, int defaultIndex, int nbOfCase, byte[] code,
			int[] valueMatch, int[] jumpOffset) {
		return (short) (nbOfCase * 8);
	}

	private SwitchInstruction createSwitchFromTableSwitch(short currentIndex, Operand index, int defaultIndex,
			int[] jumpOffset) {
		int[] match = new int[jumpOffset.length];
		for (int i = 0; i < match.length; i++) {
			match[i] = i + 1;
		}
		SwitchInstruction si = new SwitchInstruction(currentIndex, index, defaultIndex, match, jumpOffset);
		return si;
	}

	private SwitchInstruction createSwitchFromLookupSwitch(short currentIndex, Operand index, int defaultIndex,
			int[] match, int[] jumpOffset) {
		SwitchInstruction si = new SwitchInstruction(currentIndex, index, defaultIndex, match, jumpOffset);
		return si;
	}

	private short alignOnInt(short i) {
		if (i % 4 != 0) {
			i += (3 - (i % 4));
		}
		return i;
	}

	private Constant getConstantFromConstantPool(int constantIndex, ClassFile cf) {
		ConstantPoolInfo cpi = cf.getConstantPool()[constantIndex - 1];
		int constantType = cpi.getTag();
		Constant c;
		switch (constantType) {
		case ConstantType.STRING:
			// TODO Changer les types par un trucs plus standart.... Et des
			// constants
			c = new Constant("java.lang.String", ClassFileUtils.decodeUTF(cf, ((ConstantString) cpi).getStringIndex()));
			break;
		case ConstantType.DOUBLE:
			c = new Constant("double", Double.toString(((ConstantDouble) cpi).getBytes()));
			break;
		case ConstantType.FLOAT:
			c = new Constant("float", Float.toString(((ConstantFloat) cpi).getBytes()));
			break;
		case ConstantType.INTEGER:
			c = new Constant("int", Integer.toString(((ConstantInteger) cpi).getBytes()));
			break;
		case ConstantType.LONG:
			c = new Constant("long", Long.toString(((ConstantLong) cpi).getBytes()));
			break;
		default:
			// TODO RuntimeError
			c = new Constant("", "");
			break;

		}
		return c;
	}

	private String getVariableName(int tmpIndex, Map<Integer, Variable> localVariable) {
		Variable var = localVariable.get(tmpIndex);
		if (var == null) {
			var = new SimpleVariable(null, "local" + tmpIndex);
			localVariable.put(tmpIndex, var);
		}
		return var.getName();
	}

	private String getVariableNameFromRef(int tmpIndex, Map<Integer, Variable> localVariable, Operand operand) {
		Variable var = localVariable.get(tmpIndex);
		if (var == null) {
			if (operand instanceof ConstantArrayReference) {
				// On remplace le type constant vers un type variable
				ArrayReference vArrayRef = new ArrayReference((ConstantArrayReference) operand, "local" + tmpIndex);
				localVariable.put(tmpIndex, vArrayRef);
				return vArrayRef.getName();
			} else if (operand instanceof ObjectReference) {
				((ObjectReference) operand).setName("local" + tmpIndex);
				localVariable.put(tmpIndex, (ObjectReference) operand);
				return ((ObjectReference) operand).getName();
			} else if (operand instanceof SimpleInvocationOperandResult) {
				var = new SimpleVariable(((SimpleInvocationOperandResult) operand).getReturnType(), "local" + tmpIndex);
				localVariable.put(tmpIndex, var);
				return var.getName();
			} else if (operand instanceof SimpleVariable) {
				var = new SimpleVariable(((SimpleVariable) operand).getType(), "local" + tmpIndex);
				localVariable.put(tmpIndex, var);
				return var.getName();
			}
		}
		return var.getName();
	}

	private Map<Integer, Variable> constructLocalVariable() {
		Map<Integer, Variable> res = new HashMap<Integer, Variable>();
		res.put(0, new SimpleVariable(null, "this"));
		return res;
	}

	private void fillLocalVariableWithArg(Map<Integer, Variable> localVariable, MethodInfo methodInfo) {
		String[] args = methodInfo.getArgName();
		for (int i = 0; i < args.length; i++) {
			localVariable.put(i + 1, new SimpleVariable(null, args[i]));
		}
	}

	/**
	 * 
	 * @param b
	 * @param c
	 * @return
	 * 
	 *         Construit l'index a partir des 2 octets suivant une
	 *         instruction.... the unsigned branchbyte1 and branchbyte2 are used
	 *         to construct a signed 16-bit offset, where the offset is
	 *         calculated to be (branchbyte1 << 8) | branchbyte2.
	 */
	private short getIndex(byte branchbyte1, byte branchbyte2) {
		short res = 0;
		res += (branchbyte1 & 0xFF) << 8;
		res += (branchbyte2 & 0xFF) << 0;
		return res;
	}

	private int getWideIndex(byte branchbyte1, byte branchbyte2, byte branchbyte3, byte branchbyte4) {
		int res = 0;
		res += (branchbyte1 & 0xFF) << 24;
		res += (branchbyte2 & 0xFF) << 16;
		res += (branchbyte3 & 0xFF) << 8;
		res += (branchbyte4 & 0xFF) << 0;
		return res;
	}

	private int getUnsignedValue(byte b) {
		return b & 0xFF;
	}

	private ConditionalBrancheInstruction createConditionalBranching(ConditionalOperator co, short beginIndex,
			short endIndex, Operand operand1, Operand operand2) {
		return new ConditionalBrancheInstruction(co, beginIndex, endIndex, operand1, operand2);
	}

	private UnconditionalBranching createUnconditionalBranching(short beginIndex, short endIndex) {
		return new UnconditionalBranching(beginIndex, endIndex);
	}
}
