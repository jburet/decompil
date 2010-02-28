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

/**
 *
 */
package testclasses.assignation;

/**
 * @author jburet
 * 
 */
public class SuperPrimitiveAssignation extends PrimitiveAssignation {

	private int varint = 5;
	private static int staticint = 5;

	public void readIntSuperInstanceField() {
		int i = super.varint;
	}

	public void writeIntSuperInstanceField(int i) {
		super.varint = i;
	}

	public void readIntSuperStaticField() {
		int i = PrimitiveAssignation.staticint;
	}

	public void writeIntSuperStaticField(int i) {
		PrimitiveAssignation.staticint = i;
	}

	@Override
	public void readIntInstanceField() {
		int i = varint;
	}

	@Override
	public void writeIntInstanceField(int i) {
		varint = i;
	}

	@Override
	public void readIntStaticField() {
		int i = staticint;
	}

	@Override
	public void writeIntStaticField(int i) {
		staticint = i;
	}

	public void tst() {
		this.varint = super.varint;
	}
}
