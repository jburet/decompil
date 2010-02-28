package testclasses.assignation;

import testclasses.TestUtils;

public class PrimitiveAssignation {

	private int varint = 10;
	private static int staticint = 10;

	// From constant pool
	public void constantIntAssignation() {
		int a = 1;
	}

	public void constantLongAssignation() {
		long a = 9999999999999999l;
	}

	public void constantShortAssignation() {
		short a = 1;
	}

	public void constantByteAssignation() {
		byte a = 1;
	}

	public void constantBooleanAssignation() {
		boolean a = true;
	}

	public void constantFloatAssignation() {
		float a = 1.1f;
	}

	public void constantDoubleAssignation() {
		double a = 1.5d;
	}

	public void constantCharAssignation() {
		char a = 1;
		char b = 'a';
	}

	// From arg variable
	public void varIntAssignation(int var) {
		int a = var;
	}

	public void varLongAssignation(long var) {
		long a = var;
	}

	public void varShortAssignation(short var) {
		short a = var;
	}

	public void varByteAssignation(byte var) {
		byte a = var;
	}

	public void varBooleanAssignation(boolean var) {
		boolean a = var;
	}

	public void varFloatAssignation(float var) {
		float a = var;
	}

	public void varDoubleAssignation(double var) {
		double a = var;
	}

	public void varCharAssignation(char var) {
		char a = var;
	}

	// From method execution
	public void methodIntAssignation(int var) {
		int a = TestUtils.getInt();
	}

	public void methodLongAssignation(long var) {
		long a = TestUtils.getLong();
	}

	public void methodShortAssignation(short var) {
		short a = TestUtils.getShort();
	}

	public void methodByteAssignation(byte var) {
		byte a = TestUtils.getByte();
	}

	public void methodBooleanAssignation(boolean var) {
		boolean a = TestUtils.getBoolean();
	}

	public void methodFloatAssignation(float var) {
		float a = TestUtils.getFloat();
	}

	public void methodDoubleAssignation(double var) {
		double a = TestUtils.getDouble();
	}

	public void methodCharAssignation(char var) {
		char a = TestUtils.getChar();
	}

	// Field manipulation
	public void readIntInstanceField() {
		int i = varint;
	}

	public void writeIntInstanceField(int i) {
		varint = i;
	}

	public void readIntStaticField() {
		int i = staticint;
	}

	public void writeIntStaticField(int i) {
		staticint = i;
	}
}
