// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.editor.connection;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.talend.dataprofiler.core.ui.editor.AbstractFormPage;

/**
 * DOC rli class global comment. Detailled comment
 */
public class ConnnectionInfoPage extends AbstractFormPage {

    public ConnnectionInfoPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
    }
    
    /**
     * Primes the form page with the parent editor instance.
     * 
     * @param editor
     *            the parent editor
     */
    public void initialize(FormEditor editor) {
        super.initialize(editor);
        
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        final ScrolledForm form = managedForm.getForm();
        createAnalysisMetadataSection(form, topComp, "Connection Metadata", "Set the properties of connnection.");
    }

    @Override
    protected void fireTextChange() {
//        analysisHandler.setName(nameText.getText());
//        analysisHandler.setPurpose(purposeText.getText());
//        analysisHandler.setDescription(descriptionText.getText());
//        analysisHandler.setAuthor(authorText.getText());
//        analysisHandler.setStatus(statusCombo.getText());
    }

    @Override
    protected void initTextFied() {
//        nameText.setText(analysisHandler.getName() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getName());
//        purposeText.setText(analysisHandler.getPurpose() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getPurpose());
//        descriptionText.setText(analysisHandler.getDescription() == null ? PluginConstant.EMPTY_STRING : analysisHandler
//                .getDescription());
//        authorText.setText(analysisHandler.getAuthor() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getAuthor());
//        statusCombo.setText(analysisHandler.getStatus() == null ? PluginConstant.EMPTY_STRING : analysisHandler.getStatus());
    }

    @Override
    public void setDirty(boolean isDirty) {
        if (this.isDirty != isDirty) {
            this.isDirty = isDirty;
            ((ConnectionEditor) this.getEditor()).firePropertyChange(IEditorPart.PROP_DIRTY);
        }

    }

}
