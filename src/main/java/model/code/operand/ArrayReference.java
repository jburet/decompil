package model.code.operand;

public interface ArrayReference extends Operand{

	void addValue(Operand value);

	String getObjectType();

}
