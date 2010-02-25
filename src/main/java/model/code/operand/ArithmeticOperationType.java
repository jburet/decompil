package model.code.operand;

public enum ArithmeticOperationType {
	
	PLUS("+"), MINUS("-");
	
	private String sign;
	
	private ArithmeticOperationType(String sign){
		this.sign = sign;
	}
	
	public String getSign(){
		return this.sign;
	}
}
