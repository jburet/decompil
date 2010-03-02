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

import javax.lang.model.SourceVersion;

public class ReferenceAssignation {

	private final Object OBJECT = new Object();

	public void constantReferenceAssignation() {
		Object obj = OBJECT;
	}

	public void varReferenceAssignation(Object var) {
		Object obj = var;
	}

	public void varArrayReferenceAssignation(Object[] var) {
		Object[] obj = var;
	}

	public void varBiDimArrayReferenceAssignation(Object[][] var) {
		Object[][] obj = var;
	}

	public void varGenericsReferenceAssignation(Class<Object> var) {
		Class<Object> obj = var;
	}

	public void varEnumReferenceAssignation(SourceVersion var) {
		SourceVersion sv = var;
	}
}
