package model.code.operand.impl;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

import model.code.operand.ArrayReference;
import model.code.operand.ArrayType;
import model.code.operand.Operand;


/**
 * 
 * @author jburet
 * 
 *         Represente un tableau... Si le type de tableau est d'object.
 *         objectType est obligatoire et contient le type du tableau
 */
public class ConstantArrayReference implements ArrayReference {

	private ArrayType arrayType;

	private String objectType;

	private Operand size;

	private List<Operand> values;

	public ConstantArrayReference(ArrayType arrayType, Operand size) {
		this.arrayType = arrayType;
		this.size = size;
	}

	public ConstantArrayReference(ArrayType arrayType, String objectType,
			Operand size) {
		this.arrayType = arrayType;
		this.size = size;
		this.objectType = objectType;
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

	public List<Operand> getValues() {
		return this.values;
	}

	public void addValue(Operand obj) {
		if (this.values == null) {
			this.values = new ArrayList<Operand>();
		}
		this.values.add(obj);
	}

}
