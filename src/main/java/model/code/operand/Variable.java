package model.code.operand;

import model.constant.Type;

public interface Variable extends Operand{

	Type getType();
	
	String getName();
	
}
