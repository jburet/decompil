package jdecomp.core.visitor;

import jdecomp.core.model.classes.ClassFile;

public interface ClassVisitor {

	void visitClassFile(ClassFile classFile);

}
