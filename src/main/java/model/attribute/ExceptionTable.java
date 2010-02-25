package model.attribute;

public class ExceptionTable {
	private short startPc;
	private short endPc;
	private short handlerPc;
	private short catchType;

	public short getStartPc() {
		return startPc;
	}

	public void setStartPc(short startPc) {
		this.startPc = startPc;
	}

	public short getEndPc() {
		return endPc;
	}

	public void setEndPc(short endPc) {
		this.endPc = endPc;
	}

	public short getHandlerPc() {
		return handlerPc;
	}

	public void setHandlerPc(short handlerPc) {
		this.handlerPc = handlerPc;
	}

	public short getCatchType() {
		return catchType;
	}

	public void setCatchType(short catchType) {
		this.catchType = catchType;
	}

}
