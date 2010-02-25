package model.attribute;

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
