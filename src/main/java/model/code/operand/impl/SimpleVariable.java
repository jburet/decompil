package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.Variable;
import model.constant.Type;

public class SimpleVariable implements Variable {

	private Type type;
	private String name;

	public SimpleVariable(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitVariable(this);
	}
}
