package com.quantum.wizards;

import com.quantum.sql.SQLResultSetResults;

import org.eclipse.jface.wizard.Wizard;

public class SQLRowWizard extends Wizard {
	protected SQLPage page;
	protected SQLResultSetResults.Row row;
	private SQLResultSetResults results;
	
	public void init(String title, SQLPage page, 
			SQLResultSetResults results, SQLResultSetResults.Row row) {
		System.out.println("Init SQL row wizard"); //$NON-NLS-1$
		this.row = row;
		this.page = page;
		this.results = results;
		setWindowTitle(title);
	}
	public boolean performFinish() {
		System.out.println("Perform SQL row wizard finish"); //$NON-NLS-1$
		return page.performFinish();
	}
	public void addPages() {
		System.out.println("QL row wizard adding pages"); //$NON-NLS-1$
		page.init(this.results, this.row);
		addPage(page);
	}
}
