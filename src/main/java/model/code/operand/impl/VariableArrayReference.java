package model.code.operand.impl;

import visitor.Visitor;
import model.code.operand.ArrayReference;
import model.code.operand.ArrayType;
import model.code.operand.Operand;
import model.code.operand.Variable;
import model.constant.Type;

/**
 * 
 * @author jburet
 * 
 *         Represente un tableau... Si le type de tableau est d'object.
 *         objectType est obligatoire et contient le type du tableau
 */
public class VariableArrayReference implements ArrayReference, Variable {

	private ArrayType arrayType;

	private String name;

	private String objectType;

	private Operand size;

	public VariableArrayReference(ArrayType arrayType, Operand size, String name) {
		this.name = name;
		this.arrayType = arrayType;
		this.size = size;
	}

	public VariableArrayReference(ArrayType arrayType, String objectType, Operand size, String name) {
		this.name = name;
		this.arrayType = arrayType;
		this.size = size;
		this.objectType = objectType;
	}

	public VariableArrayReference(ConstantArrayReference operand, String name) {
		this.name = name;
		this.arrayType = operand.getArrayType();
		this.size = operand.getSize();
		this.objectType = operand.getObjectType();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitArrayReference(this);
	}

	public ArrayType getArrayType() {
		return arrayType;
	}

	public Operand getSize() {
		return size;
	}

	public String getObjectType() {
		return objectType;
	}

	// FIXME ou pas... Dans le cas d'une variable on ne conserve pas les
	// valeurs.
	@Override
	public void addValue(Operand value) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return getArrayType();
	}

}
