package model.code.operand;

import model.constant.Type;

public interface Array extends Operand {

	void addValue(Operand value);

	Type getObjectType();

}
