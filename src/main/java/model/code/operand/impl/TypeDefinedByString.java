package model.code.operand.impl;

import model.constant.Type;

// FIXME A supprimer et a remplacer une fois la resolution des type locaux effectuees
@Deprecated
public class TypeDefinedByString implements Type {

	private final String type;

	public TypeDefinedByString(String type) {
		this.type = type;
	}

	@Override
	public boolean isArray() {
		return false;
	}

}
