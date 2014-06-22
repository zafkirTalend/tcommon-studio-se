// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.targetschema.editor.actions;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CopyTargetSchemaAction2 {

    // private List<SchemaTarget> selectedSchemaTarget;
    //
    // /**
    // * DOC amaumont AddMetadataAction constructor comment.
    // *
    // * @param metadatEditorView
    // */
    // public CopyTargetSchemaAction2(XPathNodeSchemaEditorView metadatEditorView) {
    // super(metadatEditorView);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see org.talend.designer.mapper.actions.IMapperAction#run(org.talend.designer.mapper.actions.IMapperEvent)
    // */
    // public void run(IEventAction eventAction) {
    // TargetSchemaEditorEvent targetSchemaEditorEvent = (TargetSchemaEditorEvent) eventAction;
    //
    // List<SchemaTarget> columns = getTargetSchemaEditor().getBeansList();
    //
    // if (targetSchemaEditorEvent.type == TYPE.COPY) {
    // selectedSchemaTarget = new ArrayList<SchemaTarget>();
    // for (int columnSelectedId : targetSchemaEditorEvent.entriesIndices) {
    // selectedSchemaTarget.add(columns.get(columnSelectedId));
    // }
    // } else if (targetSchemaEditorEvent.type == TYPE.PASTE) {
    // if ((targetSchemaEditorEvent.entriesIndices != null) && (targetSchemaEditorEvent.entriesIndices.length > 0)) {
    // int indice = targetSchemaEditorEvent.entriesIndices[0];
    // for (SchemaTarget schemaTarget : selectedSchemaTarget) {
    // // create a new column as a copy of this column
    // SchemaTarget newColumnCopy = new ConnectionFactoryImpl().copy(schemaTarget, indice + ""); // FIXME
    // // MHE
    // indice++;
    // getTargetSchemaEditor().add(newColumnCopy, indice);
    // }
    // }
    // }
    // }
    //
    // public XPathNodeSchemaEditorView getCurrentTableEditor() {
    // return targetSchemaTableEditorView;
    // }
}
