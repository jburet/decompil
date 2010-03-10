package jdecomp.plugin.java;

import java.util.ArrayList;
import java.util.List;

public class JavaSource {

	private String packageDeclaration;

	private String classDeclaration;

	private List<String> fields = new ArrayList<String>();

	private List<MethodSource> methods = new ArrayList<MethodSource>();

	public List<String> getFields() {
		return fields;
	}

	public void addField(String field) {
		if (this.fields == null) {
			this.fields = new ArrayList<String>();
		}
		this.fields.add(field);
	}

	public String getClassDeclaration() {
		return classDeclaration;
	}

	public void setClassDeclaration(String classDeclaration) {
		this.classDeclaration = classDeclaration;
	}

	public String getPackageDeclaration() {
		return packageDeclaration;
	}

	public void setPackageDeclaration(String packageDeclaration) {
		this.packageDeclaration = packageDeclaration;
	}

	public List<MethodSource> getMethods() {
		return methods;
	}

	public void addMethod(MethodSource method) {
		if (this.methods == null) {
			this.methods = new ArrayList<MethodSource>();
		}
		this.methods.add(method);
	}

}
