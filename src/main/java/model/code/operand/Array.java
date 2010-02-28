package model.code.operand;

import model.constant.Type;

public interface Array extends Operand {

	void addValue(Operand index, Operand value);

	int getDimension();

	Type getObjectType();

}
