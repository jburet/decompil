package jdecomp.visualisation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Opens a file
 */
public class OpenFileChooserHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.class" });
		dialog.setFilterNames(new String[] { "Class File" });
		String fileSelected = dialog.open();

		if (fileSelected != null) {
			// Perform Action, like open the file.
			System.out.println("Selected file: " + fileSelected);
		}
		return null;
	}
}
