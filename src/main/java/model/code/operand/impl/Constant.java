package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.Operand;

public class Constant implements Operand {
	private String type;
	private String value;

	public Constant(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String name) {
		this.value = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitConstant(this);
	}

	public boolean isString() {
		return "java.lang.String".equals(type);
	}
}
