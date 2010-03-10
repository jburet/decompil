package jdecomp.plugin.java.formatter;

import java.io.IOException;
import java.io.Writer;

import jdecomp.plugin.java.JavaSource;
import jdecomp.plugin.java.JavaSourceInstruction;
import jdecomp.plugin.java.MethodSource;

public class JavaSimpleFormatter implements JavaFormatter {

	public static final String NEWLINE = System.getProperty("line.separator");

	@Override
	public void formatJavaCode(JavaSource javaSource, Writer writer) throws IOException {
		writer.write(javaSource.getPackageDeclaration());
		writer.write(NEWLINE);
		writer.write(javaSource.getClassDeclaration());
		writer.write("{");
		writer.write(NEWLINE);
		for (String field : javaSource.getFields()) {
			writer.write(field);
			writer.write(";");
			writer.write(NEWLINE);
		}
		writer.write(NEWLINE);
		for (MethodSource method : javaSource.getMethods()) {
			writer.write(method.getMethodDeclaration());
			if (method.getSources() != null && method.getSources().size() > 0) {
				writer.write("{");
				writer.write(NEWLINE);
				for (JavaSourceInstruction jsi : method.getSources()) {
					writer.write(jsi.getSourceInstruction());
					writer.write(NEWLINE);
				}

				writer.write("}");
			} else {
				writer.write(";");
			}
			writer.write(NEWLINE);
		}
		// End of class
		writer.write("}");
	}
}
