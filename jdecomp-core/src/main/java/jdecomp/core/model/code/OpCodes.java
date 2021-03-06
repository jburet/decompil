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

package jdecomp.core.model.code;

// All opcodes
public enum OpCodes {
	nop(0),
	aconst_null(1),
	iconst_m1(2),
	iconst_0(3),
	iconst_1(4),
	iconst_2(5),
	iconst_3(6),
	iconst_4(7),
	iconst_5(8),
	lconst_0(9),
	lconst_1(10),
	fconst_0(11),
	fconst_1(12),
	fconst_2(13),
	dconst_0(14),
	dconst_1(15),
	bipush(16, OpcodeNbParam.ONE),
	sipush(17, OpcodeNbParam.TWO),
	ldc(18, OpcodeNbParam.ONE),
	ldc_w(19, OpcodeNbParam.TWO),
	ldc2_w(20, OpcodeNbParam.TWO),
	iload(21, OpcodeNbParam.ONE),
	lload(22, OpcodeNbParam.ONE),
	fload(23, OpcodeNbParam.ONE),
	dload(24, OpcodeNbParam.ONE),
	aload(25, OpcodeNbParam.ONE),
	iload_0(26),
	iload_1(27),
	iload_2(28),
	iload_3(29),
	lload_0(30),
	lload_1(31),
	lload_2(32),
	lload_3(33),
	fload_0(34),
	fload_1(35),
	fload_2(36),
	fload_3(37),
	dload_0(38),
	dload_1(39),
	dload_2(40),
	dload_3(41),
	aload_0(42),
	aload_1(43),
	aload_2(44),
	aload_3(45),
	iaload(46),
	laload(47),
	faload(48),
	daload(49),
	aaload(50),
	baload(51),
	caload(52),
	saload(53),
	istore(54, OpcodeNbParam.ONE),
	lstore(55, OpcodeNbParam.ONE),
	fstore(56, OpcodeNbParam.ONE),
	dstore(57, OpcodeNbParam.ONE),
	astore(58, OpcodeNbParam.ONE),
	istore_0(59),
	istore_1(60),
	istore_2(61),
	istore_3(62),
	lstore_0(63),
	lstore_1(64),
	lstore_2(65),
	lstore_3(66),
	fstore_0(67),
	fstore_1(68),
	fstore_2(69),
	fstore_3(70),
	dstore_0(71),
	dstore_1(72),
	dstore_2(73),
	dstore_3(74),
	astore_0(75),
	astore_1(76),
	astore_2(77),
	astore_3(78),
	iastore(79),
	lastore(80),
	fastore(81),
	dastore(82),
	aastore(83),
	bastore(84),
	castore(85),
	sastore(86),
	pop(87),
	pop2(88),
	dup(89),
	dup_x1(90),
	dup_x2(91),
	dup2(92),
	dup2_x1(93),
	dup2_x2(94),
	swap(95),
	iadd(96),
	ladd(97),
	fadd(98),
	dadd(99),
	isub(100),
	lsub(101),
	fsub(102),
	dsub(103),
	imul(104),
	lmul(105),
	fmul(106),
	dmul(107),
	idiv(108),
	ldiv(109),
	fdiv(110),
	ddiv(111),
	irem(112),
	lrem(113),
	frem(114),
	drem(115),
	ineg(116),
	lneg(117),
	fneg(118),
	dneg(119),
	ishl(120),
	lshl(121),
	ishr(122),
	lshr(123),
	iushr(124),
	lushr(125),
	iand(126),
	land(127),
	ior(128),
	lor(129),
	ixor(130),
	lxor(131),
	iinc(132, OpcodeNbParam.TWO),
	i2l(133),
	i2f(134),
	i2d(135),
	l2i(136),
	l2f(137),
	l2d(138),
	f2i(139),
	f2l(140),
	f2d(141),
	d2i(142),
	d2l(143),
	d2f(144),
	i2b(145),
	i2c(146),
	i2s(147),
	lcmp(148),
	fcmpl(149),
	fcmpg(150),
	dcmpl(151),
	dcmpg(152),
	ifeq(153, OpcodeNbParam.TWO),
	ifne(154, OpcodeNbParam.TWO),
	iflt(155, OpcodeNbParam.TWO),
	ifge(156, OpcodeNbParam.TWO),
	ifgt(157, OpcodeNbParam.TWO),
	ifle(158, OpcodeNbParam.TWO),
	if_icmpeq(159, OpcodeNbParam.TWO),
	if_icmpne(160, OpcodeNbParam.TWO),
	if_icmplt(161, OpcodeNbParam.TWO),
	if_icmpge(162, OpcodeNbParam.TWO),
	if_icmpgt(163, OpcodeNbParam.TWO),
	if_icmple(164, OpcodeNbParam.TWO),
	if_acmpeq(165, OpcodeNbParam.TWO),
	if_acmpne(166, OpcodeNbParam.TWO),
	goto_(167, OpcodeNbParam.TWO),
	jsr(168, OpcodeNbParam.TWO),
	ret(169, OpcodeNbParam.ONE),
	tableswitch(170, OpcodeNbParam.VAR_SWITCH),
	lookupswitch(171, OpcodeNbParam.VAR_SWITCH),
	ireturn(172),
	lreturn(173),
	freturn(174),
	dreturn(175),
	areturn(176),
	return_(177),
	getstatic(178, OpcodeNbParam.TWO),
	putstatic(179, OpcodeNbParam.TWO),
	getfield(180, OpcodeNbParam.TWO),
	putfield(181, OpcodeNbParam.TWO),
	invokevirtual(182, OpcodeNbParam.TWO),
	invokespecial(183, OpcodeNbParam.TWO),
	invokestatic(184, OpcodeNbParam.TWO),
	invokeinterface(185, OpcodeNbParam.FOUR),
	xxxunusedxxx1(186),
	new_(187, OpcodeNbParam.TWO),
	newarray(188, OpcodeNbParam.ONE),
	anewarray(189, OpcodeNbParam.TWO),
	arraylength(190),
	athrow(191),
	checkcast(192, OpcodeNbParam.TWO),
	instanceof_(193, OpcodeNbParam.TWO),
	monitorenter(194),
	monitorexit(195),
	wide(196, OpcodeNbParam.VAR_WIDE),
	multianewarray(197, OpcodeNbParam.THREE),
	ifnull(198, OpcodeNbParam.TWO),
	ifnonnull(199),
	goto_w(200, OpcodeNbParam.FOUR),
	jsr_w(201, OpcodeNbParam.FOUR),
	breakpoint(202),
	impdep1(254),
	impdep2(255),
	unknown(257);

	OpCodes(int num) {
		this.num = num;
		this.nbArg = OpcodeNbParam.ZERO;
	}

	OpCodes(int num, OpcodeNbParam nbParam) {
		this.num = num;
		this.nbArg = nbParam;
	}

	private int num;

	private OpcodeNbParam nbArg;

	public int getNum() {
		return num;
	}

	public OpcodeNbParam getNbArg() {
		return nbArg;
	}

	public static OpCodes getByUnsignedByte(int b) {
		for (OpCodes oc : OpCodes.values()) {
			if (oc.getNum() == b) {
				return oc;
			}
		}
		return OpCodes.unknown;
	}

	public static OpCodes getBySignedByte(int b) {
		for (OpCodes oc : OpCodes.values()) {
			if (oc.getNum() == (b & 0xFF)) {
				return oc;
			}
		}
		return OpCodes.unknown;
	}
}
