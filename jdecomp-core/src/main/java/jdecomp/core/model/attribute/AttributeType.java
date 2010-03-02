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

package jdecomp.core.model.attribute;

public enum AttributeType {
	
	AnnotationDefault(5),
	Code,
	ConstantValue, 
	Deprecated,
	EnclosingMethod(5),
	Exceptions, 
	InnerClasses, 
	LineNumberTable, 
	LocalVariableTable,
	LocalVariableTypeTable(5),
	RuntimeInvisibleAnnotations(5),
	RuntimeInvisibleParameterAnnotations(5),
	RuntimeVisibleAnnotations(5),
	RuntimeVisibleParameterAnnotations(5),
	StackMapTable,
	Signature(5),
	SourceFile, 
	SourceDebugExtension(5),
	Synthetic, 
	;
	
	private final int since;

	private AttributeType() {
		this.since = -1;
	}
	private AttributeType(int since) {
		this.since = since;
	}
	
	public int getSince() {
		return since;
	}
	
	
	
}
