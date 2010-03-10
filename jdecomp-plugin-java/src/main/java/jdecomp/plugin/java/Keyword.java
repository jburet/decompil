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

package jdecomp.plugin.java;

public interface Keyword {
	
	public static final String PACKAGE_KW = "package";
	
	public static final String CLASS_KW = "class";
	public static final String INTERFACE_KW = "interface";
	public static final String ENUM_KW = "enum";
	public static final String ANNOTATION_KW = "annotation";
	
	public static final String PUBLIC_KW = "public";
	public static final String PRIVATE_KW = "private";
	public static final String PROTECTED_KW = "protected";
	
	public static final String STATIC_KW = "static";
	public static final String FINAL_KW = "final";
	public static final String ABSTRACT_KW = "abstract";
	public static final String VOLATILE_KW = "volatile";
	public static final String TRANSIENT_KW = "transient";
	public static final String NATIVE_KW = "native";
	public static final String SYNCHRONIZED_KW = "synchronized";
	
	public static final String EXTEND_KW = "extends";
	public static final String IMPLEMENTS_KW = "implements";
	
	public static final String CLASS_OBJECT = "java/lang/Object";
	
	public static final String CONSTRUCTOR_METHOD_NAME = "<init>";
	public static final String STATIC_CONSTRUCTOR_METHOD_NAME = "<clinit>";
	
}
