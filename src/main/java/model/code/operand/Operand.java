package model.code.operand;

import visitor.Visitor;

public interface Operand {
	void accept(Visitor visitor);
}
