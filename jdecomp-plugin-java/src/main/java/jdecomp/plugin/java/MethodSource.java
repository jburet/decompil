package jdecomp.plugin.java;

import java.util.ArrayList;
import java.util.List;

public class MethodSource {

	private String methodDeclaration;

	private List<JavaSourceInstruction> sources;

	public String getMethodDeclaration() {
		return methodDeclaration;
	}

	public void setMethodDeclaration(String methodDeclaration) {
		this.methodDeclaration = methodDeclaration;
	}

	public List<JavaSourceInstruction> getSources() {
		return sources;
	}

	public void addSource(JavaSourceInstruction source) {
		if (this.sources == null) {
			this.sources = new ArrayList<JavaSourceInstruction>();
		}
		this.sources.add(source);
	}

	public void addSource(List<JavaSourceInstruction> javaIns) {
		if (this.sources == null) {
			this.sources = new ArrayList<JavaSourceInstruction>();
		}
		this.sources.addAll(javaIns);
	}

}
