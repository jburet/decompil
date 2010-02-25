/**
 *
 */
package model.code.instruction;

import visitor.Visitor;
import model.code.operand.Operand;
import model.code.operand.Type;

/**
 * @author jburet
 * 
 */
public class StaticMethodInvocationInstruction extends MethodInvocation {

	private String methodName;
	private String className;
	private Type returnType;
	private Operand[] args;

	/**
	 * @param currentIndex
	 */
	public StaticMethodInvocationInstruction(short currentIndex, String className, String methodName, Type returnType,
			Operand... args) {
		super(currentIndex);
		this.methodName = methodName;
		this.className = className;
		this.returnType = returnType;
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * decompiler.instruction.Instruction#accept(decompiler.visitor.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visitStaticMethodInvocation(this);
	}

	@Override
	public Type getReturnType() {
		return this.returnType;
	}

	public String getMethodName() {
		return methodName;
	}

	public Operand[] getArgs() {
		return args;
	}

	public String getClassName() {
		return className;
	}

}
