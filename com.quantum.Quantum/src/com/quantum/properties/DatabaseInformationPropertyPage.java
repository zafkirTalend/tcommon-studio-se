package com.quantum.properties;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.bookmark.TreeNode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

public class DatabaseInformationPropertyPage extends PropertyPage {

	public DatabaseInformationPropertyPage() {
		super();
	}

    protected Control createContents(Composite parent) {

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(data);
        Bookmark bookmark =
            ((TreeNode) getElement()).getBookmark();

		createDatabaseNameArea(composite, bookmark);
        
        return composite;
	}

	/**
	 * @param composite
	 */
	private void createErrorMessage(Composite composite, Exception e) {
		Label icon = new Label(composite, SWT.NONE);
		icon.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		icon.setImage(ImageStore.getImage(ImageStore.WARNING));
		
		Label error = new Label(composite, SWT.NONE);
		error.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		error.setText(Messages.getString(getClass(), "error") 
				+ (e.getMessage() == null ? "" : "\n" + e.getMessage()));
	}

	/**
	 * @param composite
	 * @param bookmark
	 */
	private void createDatabaseNameArea(Composite composite, Bookmark bookmark) {
		Label productLabel = new Label(composite, SWT.NONE);
		productLabel.setText(Messages.getString(getClass(), "product"));

        Label productDescriptionLabel = new Label(composite, SWT.NONE);

        String description = null;
        if (bookmark.isConnected()) {
	        try {
	            description = bookmark.getDatabase().getInformation();
	        } catch (NotConnectedException e) {
	    		createErrorMessage(composite, e);
	        } catch (SQLException e) {
	    		createErrorMessage(composite, e);
	        } catch (RuntimeException e) {
	    		createErrorMessage(composite, e);
	        }
        }
        if (description == null) {
            description = Messages.getString(getClass(), "unknown");
        }
        productDescriptionLabel.setText(description);
        if (bookmark.isConnected()) {
            try {
                DatabaseMetaData dmd = bookmark.getDatabase().getMetaData();
                Label l = new Label(composite, SWT.NONE);
                l.setText("allProceduresAreCallable()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.allProceduresAreCallable()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("allTablesAreSelectable()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.allTablesAreSelectable()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("dataDefinitionCausesTransactionCommit()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.dataDefinitionCausesTransactionCommit()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("dataDefinitionIgnoredInTransactions()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.dataDefinitionIgnoredInTransactions()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("doesMaxRowSizeIncludeBlobs()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.doesMaxRowSizeIncludeBlobs()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("getCatalogSeparator()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getCatalogSeparator());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getCatalogTerm()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getCatalogTerm());
                
                l = new Label(composite, SWT.NONE);
                l.setText(" getExtraNameCharacters()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getExtraNameCharacters());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getIdentifierQuoteString()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getIdentifierQuoteString());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getMaxStatements()");
                l = new Label(composite, SWT.NONE);
                l.setText(new Integer(dmd.getMaxStatements()).toString());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getProcedureTerm()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getProcedureTerm());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getSchemaTerm()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getSchemaTerm());
                
                l = new Label(composite, SWT.NONE);
                l.setText("getSearchStringEscape()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getSearchStringEscape());

                l = new Label(composite, SWT.NONE);
                l.setText("getURL()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.getURL());
                
                l = new Label(composite, SWT.NONE);
                l.setText("isReadOnly()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.isReadOnly()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("nullPlusNonNullIsNull()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.nullPlusNonNullIsNull()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("storesLowerCaseIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesLowerCaseIdentifiers()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("storesLowerCaseQuotedIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesLowerCaseQuotedIdentifiers()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("storesMixedCaseIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesMixedCaseIdentifiers()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("storesMixedCaseQuotedIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesMixedCaseQuotedIdentifiers()?"Yes":"No");
                
                l = new Label(composite, SWT.NONE);
                l.setText("storesUpperCaseIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesUpperCaseIdentifiers()?"Yes":"No");

                l = new Label(composite, SWT.NONE);
                l.setText("storesUpperCaseQuotedIdentifiers()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.storesUpperCaseQuotedIdentifiers()?"Yes":"No");

                l = new Label(composite, SWT.NONE);
                l.setText("supportsStoredProcedures()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.supportsStoredProcedures()?"Yes":"No");

                l = new Label(composite, SWT.NONE);
                l.setText("supportsTransactions()");
                l = new Label(composite, SWT.NONE);
                l.setText(dmd.supportsTransactions()?"Yes":"No");

            }catch (Exception e){}
        }
	}

	protected void performDefaults() {
	}
	
	public boolean performOk() {
		return true;
	}

}