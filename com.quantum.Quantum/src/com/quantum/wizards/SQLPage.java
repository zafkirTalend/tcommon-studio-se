package com.quantum.wizards;

import com.quantum.sql.SQLResultSetResults;

import org.eclipse.jface.wizard.IWizardPage;

public interface SQLPage extends IWizardPage {
	public void init(SQLResultSetResults results, SQLResultSetResults.Row row);
	public boolean performFinish();
}
