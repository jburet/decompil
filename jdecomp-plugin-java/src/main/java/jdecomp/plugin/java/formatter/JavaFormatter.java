package jdecomp.plugin.java.formatter;

import java.io.IOException;
import java.io.Writer;

import jdecomp.plugin.java.JavaSource;

public interface JavaFormatter {

	public void formatJavaCode(JavaSource javaSource, Writer writer) throws IOException;

}
