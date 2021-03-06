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

package testclasses.assignation;

public class ArrayAssignation {
	public void newBooleanArray() {
		boolean[] a = new boolean[10];
	}

	public void newBooleanConstantArray() {
		boolean[] a = { true, false };
	}

	public void newByteArray() {
		byte[] a = new byte[10];
	}

	public void newByteConstantArray() {
		byte[] a = { 1, 2 };
	}

	public void newShortArray() {
		short[] a = new short[10];
	}

	public void newShortConstantArray() {
		short[] a = { 1, 2 };
	}

	public void newIntArray() {
		int[] a = new int[10];
	}

	public void newIntConstantArray() {
		int[] a = { 1, 2 };
	}

	public void newLongArray() {
		long[] a = new long[10];
	}

	public void newLongConstantArray() {
		long[] a = { 1l, 2l };
	}

	public void newCharArray() {
		char[] a = new char[10];
	}

	public void newCharConstantArray() {
		int[] a = { 1, 2 };
	}

	public void newCharFromCharConstantArray() {
		int[] a = { 'a', 'b' };
	}

	public void newFloatArray() {
		float[] a = new float[10];
	}

	public void newFloatConstantArray() {
		float[] a = { 1.1f, 2.5f };
	}

	public void newDoubleArray() {
		double[] a = new double[10];
	}

	public void newDoubleConstantArray() {
		double[] a = { 1.5d, 2.1d };
	}

	public void newObjectArray() {
		Object[] a = new Object[10];
	}

	public void newObjectConstantArray() {
		Object[] a = { new Object(), new Object() };
	}

	public void newMultiDimPrimArray() {
		int[][] a = new int[10][10];
	}

	public void newMultiDimPrimConstantArray() {
		int[][] a = { new int[10], new int[10] };
	}

	public void newMultiDimPrimConstantArray2() {
		int[][] a = { { 1, 2 }, { 3, 4 } };
	}

	public void newMultiDimObjectArray() {
		Object[][] a = new Object[10][10];
	}

	public void newMultiDimObjectConstantArray() {
		Object[][] a = { new Object[10], new Object[10] };
	}

	public void newMultiDimObjectConstantArray2() {
		Object[][] a = { { new Object(), new Object() }, { new Object(), new Object() } };
	}
}
