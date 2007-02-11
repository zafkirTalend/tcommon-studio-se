package com.quantum.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author root
 */
public class DBPerspective implements IPerspectiveFactory {
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		IFolderLayout side =
			layout.createFolder(
				"side", //$NON-NLS-1$
				IPageLayout.LEFT,
				0.33F,
				layout.getEditorArea());
		side.addView("com.quantum.view.bookmarkview"); //$NON-NLS-1$
		layout.addView("com.quantum.view.sqlqueryview", IPageLayout.BOTTOM, 0.33F, layout.getEditorArea()); //$NON-NLS-1$
		IFolderLayout bottomRight =
			layout.createFolder(
				"bottomRight", //$NON-NLS-1$
				IPageLayout.BOTTOM,
				0.33F,
                "com.quantum.view.sqlqueryview");
		bottomRight.addView("com.quantum.view.tableview.TableView"); //$NON-NLS-1$
		bottomRight.addView("com.quantum.view.logview"); //$NON-NLS-1$
	}
}
