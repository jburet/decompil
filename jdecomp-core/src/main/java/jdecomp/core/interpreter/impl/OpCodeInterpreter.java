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

package jdecomp.core.interpreter.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import jdecomp.core.interpreter.ConstantType;
import jdecomp.core.interpreter.utils.ClassFileUtils;
import jdecomp.core.interpreter.utils.DescriptorParser;
import jdecomp.core.model.attribute.Code;
import jdecomp.core.model.classes.ClassFile;
import jdecomp.core.model.classes.ConstantClass;
import jdecomp.core.model.classes.ConstantDouble;
import jdecomp.core.model.classes.ConstantField;
import jdecomp.core.model.classes.ConstantFloat;
import jdecomp.core.model.classes.ConstantInteger;
import jdecomp.core.model.classes.ConstantLong;
import jdecomp.core.model.classes.ConstantNameType;
import jdecomp.core.model.classes.ConstantPoolInfo;
import jdecomp.core.model.classes.ConstantString;
import jdecomp.core.model.code.Descriptor;
import jdecomp.core.model.code.OpCodes;
import jdecomp.core.model.code.instruction.AssignationArrayInstruction;
import jdecomp.core.model.code.instruction.AssignationInstruction;
import jdecomp.core.model.code.instruction.BlockInstruction;
import jdecomp.core.model.code.instruction.ConditionalBrancheInstruction;
import jdecomp.core.model.code.instruction.InstanceMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.MethodInstruction;
import jdecomp.core.model.code.instruction.MonitorEnterInstruction;
import jdecomp.core.model.code.instruction.MonitorExitInstruction;
import jdecomp.core.model.code.instruction.ReturnInstruction;
import jdecomp.core.model.code.instruction.StatementInstruction;
import jdecomp.core.model.code.instruction.StaticMethodInvocationInstruction;
import jdecomp.core.model.code.instruction.SwitchInstruction;
import jdecomp.core.model.code.instruction.UnconditionalBranching;
import jdecomp.core.model.code.operand.ArithmeticOperationType;
import jdecomp.core.model.code.operand.Array;
import jdecomp.core.model.code.operand.ArrayType;
import jdecomp.core.model.code.operand.ConditionalOperator;
import jdecomp.core.model.code.operand.ObjectReference;
import jdecomp.core.model.code.operand.Operand;
import jdecomp.core.model.code.operand.Variable;
import jdecomp.core.model.code.operand.impl.ArithmeticOperation;
import jdecomp.core.model.code.operand.impl.ArrayAccessInstruction;
import jdecomp.core.model.code.operand.impl.ArrayReference;
import jdecomp.core.model.code.operand.impl.Constant;
import jdecomp.core.model.code.operand.impl.ConstantArrayReference;
import jdecomp.core.model.code.operand.impl.SimpleInvocationOperandResult;
import jdecomp.core.model.code.operand.impl.SimpleVariable;
import jdecomp.core.model.code.operand.impl.ThrowInstruction;
import jdecomp.core.model.constant.ClassReferenceType;
import jdecomp.core.model.constant.DescriptorType;
import jdecomp.core.model.constant.Type;
import jdecomp.core.model.method.MethodInfo;

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
		byte[] code = codeInfo.getCode();

		// Tmp current index
		short currentPosition = 0;

		OpCodes opc;
		for (short i = 0; i < code.length; i++) {
			currentPosition = i;
			opc = OpCodes.getBySignedByte(code[i]);
			switch (opc) {
			case ifeq:
			case ifne:
			case iflt:
			case ifle:
			case ifgt:
			case ifge:
			case if_icmpeq:
			case if_icmpge:
			case if_icmpgt:
			case if_icmple:
			case if_icmplt:
			case if_icmpne:
			case if_acmpeq:
			case if_acmpne:
			case ifnonnull:
			case ifnull:
				i = parseConditionalOpcode(methodInfo, constants, localVariable, currentInstruction, operandStack,
						code, currentPosition, opc, i);
				break;

			case iload_0:
			case iload_1:
			case iload_2:
			case iload_3:
			case iload:
			case lload_0:
			case lload_1:
			case lload_2:
			case lload_3:
			case lload:
			case fload_0:
			case fload_1:
			case fload_2:
			case fload_3:
			case fload:
			case dload_0:
			case dload_1:
			case dload_2:
			case dload_3:
			case dload:
			case aload_0:
			case aload_1:
			case aload_2:
			case aload_3:
			case aload:
				i = parseLocalVariableLoad(methodInfo, constants, localVariable, currentInstruction, operandStack,
						code, currentPosition, opc, i);
				break;

			case istore:
			case lstore:
			case fstore:
			case dstore:
			case astore:
			case istore_0:
			case lstore_0:
			case fstore_0:
			case dstore_0:
			case astore_0:
			case istore_1:
			case lstore_1:
			case fstore_1:
			case dstore_1:
			case astore_1:
			case istore_2:
			case lstore_2:
			case fstore_2:
			case dstore_2:
			case astore_2:
			case istore_3:
			case lstore_3:
			case fstore_3:
			case dstore_3:
			case astore_3:
				i = parseLocalVariableStore(methodInfo, constants, localVariable, currentInstruction, operandStack,
						code, currentPosition, opc, i);
				break;

			case iadd:
			case ladd:
			case fadd:
			case dadd:
			case isub:
			case lsub:
			case fsub:
			case dsub:
			case imul:
			case lmul:
			case fmul:
			case dmul:
			case idiv:
			case ldiv:
			case fdiv:
			case ddiv:
			case irem:
			case lrem:
			case frem:
			case drem:
			case ineg:
			case lneg:
			case fneg:
			case dneg:
			case ishl:
			case lshl:
			case ishr:
			case lshr:
			case iushr:
			case lushr:
			case iand:
			case land:
			case ior:
			case lor:
			case ixor:
			case lxor:
				i = parsePrimitiveOperationOnStack(methodInfo, constants, localVariable, currentInstruction,
						operandStack, code, currentPosition, opc, i);
				break;

			default:
				i = parseOpcode(methodInfo, constants, localVariable, currentInstruction, operandStack, code,
						currentPosition, opc, i);
			}
			// Analyse de l'opcode courant

		}
		return methodInstruction;
	}

	private short parseConditionalOpcode(MethodInfo methodInfo, ConstantPoolInfo[] constants,
			Map<Integer, Variable> localVariable, BlockInstruction currentInstruction, Stack<Operand> operandStack,
			byte[] code, short currentPosition, OpCodes opc, short i) {
		short endOfCurrentBlock;
		switch (opc) {
		// Comparaison d'un int par rapport a 0
		// Le int a comparer se recupere dans la pile
		// Les 2 arguments permette de construire l'adresse ou aller si
		// la condition n'est pas respectee.
		case ifeq:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.EQ, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		case ifne:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.NE, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		case iflt:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LT, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		case ifle:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LE, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		case ifgt:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GT, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		case ifge:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GE, i,
					endOfCurrentBlock, operandStack.pop(), new Constant(DescriptorType.INT, "0")));
			break;
		// Comparaison des 2 premiers int de la stack
		// Les 2 arguments permette de construire l'adresse ou aller si
		// la condition n'est pas respectee.
		case if_icmpeq:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.EQ, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_icmpge:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GE, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_icmpgt:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.GT, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_icmple:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LE, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_icmplt:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.LT, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_icmpne:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.NE, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;
		case if_acmpeq:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.EQ, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;

		case if_acmpne:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.NE, i,
					endOfCurrentBlock, operandStack.pop(), operandStack.pop()));
			break;

		case ifnonnull:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(ConditionalOperator.ISNULL,
					i, endOfCurrentBlock, operandStack.pop(), null));
			break;

		case ifnull:
			endOfCurrentBlock = (short) (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createConditionalBranching(
					ConditionalOperator.ISNOTNULL, i, endOfCurrentBlock, operandStack.pop(), null));
			break;
		}
		return i;
	}

	private short parseLocalVariableLoad(MethodInfo methodInfo, ConstantPoolInfo[] constants,
			Map<Integer, Variable> localVariable, BlockInstruction currentInstruction, Stack<Operand> operandStack,
			byte[] code, short currentPosition, OpCodes opc, short i) {
		int tmpIndex;
		switch (opc) {
		// Chargement de variable local dans la stack
		case iload_0:
		case lload_0:
		case fload_0:
		case dload_0:
		case aload_0:
			operandStack.push(localVariable.get(0));
			break;
		case lload_1:
		case fload_1:
		case dload_1:
		case aload_1:
		case iload_1:
			operandStack.push(localVariable.get(1));
			break;
		case lload_2:
		case fload_2:
		case dload_2:
		case aload_2:
		case iload_2:
			operandStack.push(localVariable.get(2));
			break;
		case lload_3:
		case fload_3:
		case dload_3:
		case aload_3:
		case iload_3:
			operandStack.push(localVariable.get(3));
			break;
		case lload:
		case fload:
		case dload:
		case aload:
		case iload:
			tmpIndex = getUnsignedValue(code[++i]);
			operandStack.push(localVariable.get(tmpIndex));
			break;
		}
		return i;
	}

	private short parsePrimitiveOperationOnStack(MethodInfo methodInfo, ConstantPoolInfo[] constants,
			Map<Integer, Variable> localVariable, BlockInstruction currentInstruction, Stack<Operand> operandStack,
			byte[] code, short currentPosition, OpCodes opc, short i) {
		int tmpIndex;
		switch (opc) {
		case iadd:
		case ladd:
		case fadd:
		case dadd:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.PLUS));
			break;

		case isub:
		case lsub:
		case fsub:
		case dsub:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.MINUS));
			break;

		case imul:
		case lmul:
		case fmul:
		case dmul:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.MUL));
			break;

		case idiv:
		case ldiv:
		case fdiv:
		case ddiv:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.DIV));
			break;

		case irem:
		case lrem:
		case frem:
		case drem:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.REM));
			break;

		case ineg:
		case lneg:
		case fneg:
		case dneg:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), null, ArithmeticOperationType.MINUS));
			break;

		case ishl:
		case lshl:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.SHL));
			break;

		// FIXME See difference on java code between SHR and USHR
		case ishr:
		case lshr:
		case iushr:
		case lushr:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.SHR));
			break;

		case iand:
		case land:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.AND));
			break;

		case ior:
		case lor:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.OR));
			break;

		case ixor:
		case lxor:
			operandStack.push(new ArithmeticOperation(operandStack.pop(), operandStack.pop(),
					ArithmeticOperationType.XOR));
			break;

		}
		return i;
	}

	private short parseLocalVariableStore(MethodInfo methodInfo, ConstantPoolInfo[] constants,
			Map<Integer, Variable> localVariable, BlockInstruction currentInstruction, Stack<Operand> operandStack,
			byte[] code, short currentPosition, OpCodes opc, short i) {
		int tmpIndex;
		String tmpVarName;
		Operand operand;
		switch (opc) {
		// Assignation de int dans les variables locale
		case istore:
		case lstore:
		case fstore:
		case dstore:
		case astore:
			tmpIndex = getUnsignedValue(code[++i]);
			tmpVarName = getVariableName(tmpIndex, localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					operandStack.pop()));
			break;
		case istore_0:
		case lstore_0:
		case fstore_0:
		case dstore_0:
		case astore_0:
			tmpVarName = getVariableName(0, localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					operandStack.pop()));
			break;
		case istore_1:
		case lstore_1:
		case fstore_1:
		case dstore_1:
		case astore_1:
			tmpVarName = getVariableName(1, localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					operandStack.pop()));
			break;
		case istore_2:
		case lstore_2:
		case fstore_2:
		case dstore_2:
		case astore_2:
			tmpVarName = getVariableName(2, localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					operandStack.pop()));
			break;
		case istore_3:
		case lstore_3:
		case fstore_3:
		case dstore_3:
		case astore_3:
			tmpVarName = getVariableName(3, localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					operandStack.pop()));
			break;
		}
		return i;
	}

	private short parseOpcode(MethodInfo methodInfo, ConstantPoolInfo[] constants,
			Map<Integer, Variable> localVariable, BlockInstruction currentInstruction, Stack<Operand> operandStack,
			byte[] code, short currentPosition, OpCodes opc, short i) {
		int endOfCurrentBlock;
		int tmpIndex;
		String tmpVarName;
		ConstantField constantField;
		ConstantNameType constantNameType;
		ConstantClass constantClass;
		String tmpMethodName;
		String tmpClassName;
		Operand operand;
		Operand[] operandArray;
		Constant c;
		int dimension;
		int defaultIndex;
		int highIndex;
		int lowIndex;
		int[] jumpOffset;
		int[] valueMatch;
		int nbOfCase;
		int argCount;
		String descriptor;
		Type[] argsType;
		Descriptor returnType;
		Operand[] operands;
		switch (opc) {

		// Modification de variable locale
		case iinc:
			tmpVarName = getVariableName(getUnsignedValue(code[++i]), localVariable);
			currentInstruction.addInstruction(currentPosition, new AssignationInstruction(currentPosition, tmpVarName,
					new ArithmeticOperation(new SimpleVariable(DescriptorType.INT, tmpVarName), new Constant(
							DescriptorType.INT, Integer.toString(getUnsignedValue(code[++i]))),
							ArithmeticOperationType.PLUS)));
			break;

		// Boucle non conditionelle
		case goto_:
			endOfCurrentBlock = (i + getIndex(code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createUnconditionalBranching(currentPosition,
					endOfCurrentBlock));
			break;
		case goto_w:
			endOfCurrentBlock = (i + getWideIndex(code[++i], code[++i], code[++i], code[++i]));
			currentInstruction.addInstruction(currentPosition, createUnconditionalBranching(currentPosition,
					endOfCurrentBlock));
			break;
		// FIXME Not use on JDK6 ????
		// Find an exemple
		case jsr:
		case jsr_w:
			break;
		case ret:
			break;
		// Chargement de constant dans la stack
		case iconst_m1:
			operandStack.push(new Constant(DescriptorType.INT, "-1"));
			break;
		case iconst_0:
			operandStack.push(new Constant(DescriptorType.INT, "0"));
			break;
		case iconst_1:
			operandStack.push(new Constant(DescriptorType.INT, "1"));
			break;
		case iconst_2:
			operandStack.push(new Constant(DescriptorType.INT, "2"));
			break;
		case iconst_3:
			operandStack.push(new Constant(DescriptorType.INT, "3"));
			break;
		case iconst_4:
			operandStack.push(new Constant(DescriptorType.INT, "4"));
			break;
		case iconst_5:
			operandStack.push(new Constant(DescriptorType.INT, "5"));
			break;
		case bipush:
			operandStack.push(new Constant(DescriptorType.INT, Byte.toString((code[++i]))));
			break;
		case sipush:
			operandStack.push(new Constant(DescriptorType.INT, Short.toString(getIndex(code[++i], code[++i]))));
			break;
		case aconst_null:
			operandStack.push(ObjectReference.NULL_REFERENCE);
			break;
		case fconst_0:
			operandStack.push(new Constant(DescriptorType.FLOAT, "0.0"));
			break;
		case fconst_1:
			operandStack.push(new Constant(DescriptorType.FLOAT, "1.0"));
			break;
		case fconst_2:
			operandStack.push(new Constant(DescriptorType.FLOAT, "2.0"));
			break;
		case dconst_0:
			operandStack.push(new Constant(DescriptorType.DOUBLE, "0.0d"));
			break;
		case dconst_1:
			operandStack.push(new Constant(DescriptorType.DOUBLE, "1.0d"));
			break;
		case lconst_0:
			operandStack.push(new Constant(DescriptorType.LONG, "0l"));
			break;
		case lconst_1:
			operandStack.push(new Constant(DescriptorType.LONG, "1l"));
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
			tmpMethodName = ClassFileUtils
					.decodeUTF(methodInfo.getReferentClassFile(), constantNameType.getNameIndex());
			// On la signatures de la méthode
			descriptor = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
					.getDescritptorIndex());
			argsType = DescriptorParser.parseDecodedMethodDescriptor(descriptor.substring(descriptor.indexOf('(') + 1,
					descriptor.indexOf(')')));
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
				operandStack.push(new SimpleInvocationOperandResult(new StaticMethodInvocationInstruction(
						currentPosition, "TODO-CLASSNAME", tmpMethodName, returnType.getDescriptorType(), operands)));
			} else {
				// Sinon on creer l'instruction
				currentInstruction.addInstruction(currentPosition, new StaticMethodInvocationInstruction(
						currentPosition, "TODO-CLASSNAME", tmpMethodName, returnType.getDescriptorType(), operands));
			}

			break;
		case invokespecial:

		case invokevirtual:
			// Methode de d'instance
			// On recupere la reference ce la methode a appeler
			constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
			constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
			// On recupere le nom de la methode a appeler.
			tmpMethodName = ClassFileUtils
					.decodeUTF(methodInfo.getReferentClassFile(), constantNameType.getNameIndex());
			// On la signatures de la methode
			descriptor = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
					.getDescritptorIndex());
			argsType = DescriptorParser.parseDecodedMethodDescriptor(descriptor.substring(descriptor.indexOf('(') + 1,
					descriptor.indexOf(')')));
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
						currentPosition, operandStack.pop(), tmpMethodName, returnType.getDescriptorType(), operands)));
			} else {
				// Sinon on creer l'instruction
				currentInstruction.addInstruction(currentPosition, new InstanceMethodInvocationInstruction(
						currentPosition, operandStack.pop(), tmpMethodName, DescriptorType.VOID, operands));
			}

			break;

		case invokeinterface:
			// Methode de d'instance
			// On recupere la reference ce la methode a appeler
			constantField = (ConstantField) constants[getIndex(code[++i], code[++i]) - 1];
			constantNameType = (ConstantNameType) constants[constantField.getNameTypeIndex() - 1];
			argCount = getUnsignedValue(code[++i]);
			// Drop an octet (0)
			i++;
			// On recupere le nom de la methode a appeler.
			tmpMethodName = ClassFileUtils
					.decodeUTF(methodInfo.getReferentClassFile(), constantNameType.getNameIndex());
			// On la signatures de la methode
			descriptor = ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(), constantNameType
					.getDescritptorIndex());
			argsType = DescriptorParser.parseDecodedMethodDescriptor(descriptor.substring(descriptor.indexOf('(') + 1,
					descriptor.indexOf(')')));
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
						currentPosition, operandStack.pop(), tmpMethodName, returnType.getDescriptorType(), operands)));
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
			operandStack.push(new ConstantArrayReference(ArrayType.T_REF, ClassFileUtils.parseDescriptor(ClassFileUtils
					.decodeUTF(methodInfo.getReferentClassFile(), constantClass.getNameConstantIndex())), operandStack
					.pop()));
			break;
		case multianewarray:
			tmpIndex = getIndex(code[++i], code[++i]);
			constantClass = (ConstantClass) constants[tmpIndex - 1];
			dimension = getUnsignedValue(code[++i]);
			operandArray = new Operand[dimension];
			for (int j = 0; j < dimension; j++) {
				operandArray[j] = operandStack.pop();
			}
			operandStack
					.push(new ConstantArrayReference(ArrayType.T_REF, ClassFileUtils.parseDescriptor(ClassFileUtils
							.decodeUTF(methodInfo.getReferentClassFile(), constantClass.getNameConstantIndex())),
							operandArray));
			break;
		case arraylength:
			// Appel l'instruction java length sur un tableau
			operandStack.pop();
			// FIXME On push une primitive de type entier recuperer du
			// champs d'un object
			operandStack.push(new Constant(DescriptorType.INT, "length"));
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
					operandStack.pop(), operandStack.pop(), operandStack.pop()));
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
			Descriptor desc = ClassFileUtils.parseDescriptor(ClassFileUtils.decodeUTF(
					methodInfo.getReferentClassFile(), constantNameType.getDescritptorIndex()));
			if (desc.isArray()) {
				// FIXME get the dimension from descriptor...
				operandStack.push(new ArrayReference(operandStack.pop(), ClassFileUtils.decodeUTF(methodInfo
						.getReferentClassFile(), constantNameType.getNameIndex()), null, 1));
			} else {
				operandStack.push(new ObjectReference(operandStack.pop(), ClassFileUtils.decodeUTF(methodInfo
						.getReferentClassFile(), constantClass.getNameConstantIndex()), ClassFileUtils.decodeUTF(
						methodInfo.getReferentClassFile(), constantNameType.getNameIndex()), DescriptorParser
						.parseReturnDecodedMethodDescriptor(ClassFileUtils.decodeUTF(methodInfo.getReferentClassFile(),
								constantNameType.getDescritptorIndex()))));
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
		case dup2:
			// Duplicate the top one or two operand stack values
			operandStack.push(operandStack.get(operandStack.size() - 2));
			operandStack.push(operandStack.get(operandStack.size() - 2));
			break;
		case dup_x1:
			operandStack.add(operandStack.size() - 2, operandStack.peek());
			break;
		case dup_x2:
			// Duplicate the top operand stack value and insert two or three
			// values down
			// FIXME if (operand value 2 is type double or long made the same
			// thing as
			// dup_x1)
			operandStack.add(operandStack.size() - 3, operandStack.peek());
			break;
		case dup2_x1:
			// FIXME if (operand value 2 is type double or long made the same
			// thing as
			// dup_x1)
			operandStack.add(operandStack.size() - 4, operandStack.peek());
			operandStack.add(operandStack.size() - 5, operandStack.get(operandStack.size() - 2));
			break;
		case dup2_x2:
			// FIXME Only form 1 is implemented
			operandStack.add(operandStack.size() - 5, operandStack.peek());
			operandStack.add(operandStack.size() - 6, operandStack.get(operandStack.size() - 2));
			break;
		case pop:
			popOperation(currentInstruction, operandStack, currentPosition);
			break;

		case pop2:
			popOperation(currentInstruction, operandStack, currentPosition);
			popOperation(currentInstruction, operandStack, currentPosition);
			break;
		case swap:
			operand = operandStack.pop();
			operandStack.add(operandStack.size() - 1, operand);
			break;

		// Type conversion on stack
		// FIXME
		case i2b:
		case i2c:
		case i2d:
		case i2f:
		case i2l:
		case i2s:
		case l2i:
		case l2f:
		case l2d:
		case f2i:
		case f2l:
		case f2d:
		case d2i:
		case d2l:
		case d2f:
			break;

		// Primitive comparaison
		// FIXME Usage ?
		case lcmp:
		case fcmpl:
		case fcmpg:
		case dcmpl:
		case dcmpg:

			// Exception management
		case athrow:
			// Throw an exception
			operand = operandStack.pop();
			currentInstruction.addInstruction(currentPosition, new ThrowInstruction(currentPosition, operand));
			// TODO Check if must be made
			// Clean the stack
			operandStack.clear();
			operandStack.add(operand);
			break;

		case checkcast:
			// TODO
			break;
		case instanceof_:
			// TODO
			break;
		case monitorenter:
			currentInstruction.addInstruction(currentPosition, new MonitorEnterInstruction(currentPosition,
					operandStack.pop()));
			break;

		case monitorexit:
			currentInstruction.addInstruction(currentPosition, new MonitorExitInstruction(currentPosition, operandStack
					.pop()));
			break;
		case wide:
			// TODO Modify behavior of many opcode
			break;

		default:
			currentInstruction.addInstruction(i, new StatementInstruction(i, opc));
		}
		return i;
	}

	private void popOperation(BlockInstruction currentInstruction, Stack<Operand> operandStack, short currentPosition) {
		Operand operand;
		operand = operandStack.pop();
		if (operand instanceof StaticMethodInvocationInstruction) {
			currentInstruction.addInstruction(currentPosition, (StaticMethodInvocationInstruction) operand);
		}
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
			c = new Constant(new ClassReferenceType("java/lang/String"), ClassFileUtils.decodeUTF(cf,
					((ConstantString) cpi).getStringIndex()));
			break;
		case ConstantType.DOUBLE:
			c = new Constant(DescriptorType.DOUBLE, Double.toString(((ConstantDouble) cpi).getBytes()));
			break;
		case ConstantType.FLOAT:
			c = new Constant(DescriptorType.FLOAT, Float.toString(((ConstantFloat) cpi).getBytes()));
			break;
		case ConstantType.INTEGER:
			c = new Constant(DescriptorType.INT, Integer.toString(((ConstantInteger) cpi).getBytes()));
			break;
		case ConstantType.LONG:
			c = new Constant(DescriptorType.LONG, Long.toString(((ConstantLong) cpi).getBytes()));
			break;
		default:
			// TODO RuntimeError
			c = new Constant(DescriptorType.VOID, "");
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

	private ConditionalBrancheInstruction createConditionalBranching(ConditionalOperator co, int beginIndex,
			int endIndex, Operand operand1, Operand operand2) {
		return new ConditionalBrancheInstruction(co, beginIndex, endIndex, operand1, operand2);
	}

	private UnconditionalBranching createUnconditionalBranching(int beginIndex, int endIndex) {
		return new UnconditionalBranching(beginIndex, endIndex);
	}
}
