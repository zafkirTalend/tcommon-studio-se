// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
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

//    private List<SchemaTarget> selectedSchemaTarget;
//
//    /**
//     * DOC amaumont AddMetadataAction constructor comment.
//     * 
//     * @param metadatEditorView
//     */
//    public CopyTargetSchemaAction2(XPathNodeSchemaEditorView metadatEditorView) {
//        super(metadatEditorView);
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see org.talend.designer.mapper.actions.IMapperAction#run(org.talend.designer.mapper.actions.IMapperEvent)
//     */
//    public void run(IEventAction eventAction) {
//        TargetSchemaEditorEvent targetSchemaEditorEvent = (TargetSchemaEditorEvent) eventAction;
//
//        List<SchemaTarget> columns = getTargetSchemaEditor().getBeansList();
//
//        if (targetSchemaEditorEvent.type == TYPE.COPY) {
//            selectedSchemaTarget = new ArrayList<SchemaTarget>();
//            for (int columnSelectedId : targetSchemaEditorEvent.entriesIndices) {
//                selectedSchemaTarget.add(columns.get(columnSelectedId));
//            }
//        } else if (targetSchemaEditorEvent.type == TYPE.PASTE) {
//            if ((targetSchemaEditorEvent.entriesIndices != null) && (targetSchemaEditorEvent.entriesIndices.length > 0)) {
//                int indice = targetSchemaEditorEvent.entriesIndices[0];
//                for (SchemaTarget schemaTarget : selectedSchemaTarget) {
//                    // create a new column as a copy of this column
//                    SchemaTarget newColumnCopy = new ConnectionFactoryImpl().copy(schemaTarget, indice + ""); // FIXME
//                                                                                                                // MHE
//                    indice++;
//                    getTargetSchemaEditor().add(newColumnCopy, indice);
//                }
//            }
//        }
//    }
//
//    public XPathNodeSchemaEditorView getCurrentTableEditor() {
//        return targetSchemaTableEditorView;
//    }
}
