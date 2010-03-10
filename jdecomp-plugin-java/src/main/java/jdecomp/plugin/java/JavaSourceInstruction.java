package jdecomp.plugin.java;

public class JavaSourceInstruction {

	private String sourceInstruction;
	private int preferedLine;
	private int indexOpcode;

	public JavaSourceInstruction(String sourceInstruction, int preferedLine, int indexOpcode) {
		this.sourceInstruction = sourceInstruction;
		this.preferedLine = preferedLine;
		this.indexOpcode = indexOpcode;
	}

	public JavaSourceInstruction(String sourceInstruction) {
		this.sourceInstruction = sourceInstruction;
	}

	public String getSourceInstruction() {
		return sourceInstruction;
	}

	public int getPreferedLine() {
		return preferedLine;
	}

	public int getIndexOpcode() {
		return indexOpcode;
	}
}
