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

package testclasses.array;

public class ArrayManipulation {
	boolean[] booleanArray = new boolean[10];
	byte[] byteArray = new byte[10];
	short[] shortArray = new short[10];
	int[] intArray = new int[10];
	long[] longArray = new long[10];
	char[] charArray = new char[10];
	float[] floatArray = new float[10];
	double[] doubleArray = new double[10];
	Object[] objectArray = new Object[10];

	public void assignValueOnBooleanArray() {
		booleanArray[0] = true;
	}

	public void assignValueOnByteArray() {
		byteArray[0] = 1;
	}

	public void assignValueOnShortArray() {
		shortArray[0] = 1;
	}

	public void assignValueOnIntArray() {
		intArray[0] = 1;
	}

	public void assignValueOnLongArray() {
		longArray[0] = 1l;
	}

	public void assignValueOnCharArray() {
		charArray[0] = 'r';
	}

	public void assignValueOnFloatArray() {
		floatArray[0] = 1.0f;
	}

	public void assignValueOnDoubleArray() {
		doubleArray[0] = 1.5d;
	}

	public void assignValueOnObjectArray() {
		objectArray[0] = new Object();
	}

	public boolean getValueOnBooleanArray() {
		return booleanArray[0];
	}

	public byte getValueOnByteArray() {
		return byteArray[0];
	}

	public short getValueOnShortArray() {
		return shortArray[0];
	}

	public int getValueOnIntArray() {
		return intArray[0];
	}

	public long getValueOnLongArray() {
		return longArray[0];
	}

	public char getValueOnCharArray() {
		return charArray[0];
	}

	public float getValueOnFloatArray() {
		return floatArray[0];
	}

	public double getValueOnDoubleArray() {
		return doubleArray[0];
	}

	public Object getValueOnObjectArray() {
		return objectArray[0];
	}
}
