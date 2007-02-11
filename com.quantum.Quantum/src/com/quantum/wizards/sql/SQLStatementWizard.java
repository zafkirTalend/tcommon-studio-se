package com.quantum.wizards.sql;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.quantum.sql.parser.SQL;
import com.quantum.wizards.PropertyChangeWizardPage;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

/**
 * @author BC Holmes
 */
public class SQLStatementWizard extends Wizard implements PropertyChangeListener {
	
	private List wizardPages = Collections.synchronizedList(new ArrayList());
	private ShowSQLStatementWizardPage finalPage;
	
	public SQLStatementWizard(WizardPage[] pages, SQL sql) {
		setWindowTitle("SQL Statement: " + sql.getCommand());
		
		for (int i = 0, length = pages == null ? 0 : pages.length; i < length; i++) {
			this.wizardPages.add(pages[i]);
		}
		this.finalPage = new ShowSQLStatementWizardPage("finalPage");
		this.finalPage.setSQLStatement(sql == null ? "" : sql.toString());
	}
	
	public void addPages() {
		for (Iterator i = this.wizardPages.iterator(); i.hasNext();) {
			WizardPage wizardPage = (WizardPage) i.next();
			addPage(wizardPage);
			if (wizardPage instanceof PropertyChangeWizardPage) {
				((PropertyChangeWizardPage) wizardPage).addPropertyChangeListener(this);
			}
		}
		addPage(this.finalPage);
	}
	
	public void dispose() {
		for (Iterator i = this.wizardPages.iterator(); i.hasNext();) {
			WizardPage wizardPage = (WizardPage) i.next();
			if (wizardPage instanceof PropertyChangeWizardPage) {
				((PropertyChangeWizardPage) wizardPage).removePropertyChangeListener(this);
			}
		}
		super.dispose();
	}
	
	public boolean performFinish() {
		return true;
	}

	public void propertyChange(PropertyChangeEvent event) {
		if ("sqlStatement".equals(event.getPropertyName())) {
			this.finalPage.setSQLStatement((String) event.getNewValue());
		}
	}
}
