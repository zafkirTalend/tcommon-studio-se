// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.targetschema.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableList;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.builder.connection.MetadataSchema;
import org.talend.core.model.metadata.builder.connection.SchemaTarget;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class TargetSchemaEditor2 {

    private String titleName;

    private MetadataSchema metadataSchema;

    private ListenableList<SchemaTarget> schemaTargetList;

    private List<ITargetSchemaEditorListener> listeners = new ArrayList<ITargetSchemaEditorListener>();

    public TargetSchemaEditor2(String titleName) {
        this.titleName = titleName;
    }

    public TargetSchemaEditor2() {
        super();
    }

    public TargetSchemaEditor2(MetadataSchema metadataSchema, String titleName) {
        super();
        this.metadataSchema = metadataSchema;
        this.titleName = titleName;
        initFromMetadataSchema();
    }

    /**
     * DOC amaumont Comment method "addListener".
     * 
     * @param view
     */
    public void addListener(ITargetSchemaEditorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITargetSchemaEditorListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(TargetSchemaEditorEvent event) {
        for (ITargetSchemaEditorListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param schemaTarget
     * @param index can be null
     */
    public void add(SchemaTarget schemaTarget, Integer index) {
        if (index == null || index < 0 || index > this.schemaTargetList.size() - 1) {
            this.schemaTargetList.add(schemaTarget);
        } else {
            this.schemaTargetList.add(index, schemaTarget);
        }
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param schemaTarget
     * @param index can be null
     */
    public void addAll(List<SchemaTarget> schemaTarget, Integer index) {
        if (index == null || index < 0 || index > this.schemaTargetList.size() - 1) {
            this.schemaTargetList.addAll(schemaTarget);
        } else {
            this.schemaTargetList.addAll(index, schemaTarget);
        }
    }

    public void addModifiedListListener(IListenableListListener listenableListListener) {
        this.schemaTargetList.addListener(listenableListListener);
    }

    public void removeModifiedListListener(IListenableListListener listenableListListener) {
        this.schemaTargetList.removeListener(listenableListListener);
    }

    public void initFromMetadataSchema() {
        initData();
    }

    private void initData() {
        this.schemaTargetList = new ListenableList<SchemaTarget>(this.metadataSchema.getSchemaTargets());
    }

    public String getTitleName() {
        return this.titleName;
    }

    public ListenableList<SchemaTarget> getSchemaTargetList() {
        return this.schemaTargetList;
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param schemaTarget
     */
    public void remove(SchemaTarget schemaTarget) {
        this.schemaTargetList.remove(schemaTarget);
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param i
     */
    public void remove(int index) {
        this.schemaTargetList.remove(index);
    }

    public MetadataSchema getMetadataSchema() {
        return this.metadataSchema;
    }

    /**
     * set MetadataSchema.
     * 
     * @param metadataSchema
     */
    public void setMetadataSchema(MetadataSchema metadataSchema) {
        this.metadataSchema = metadataSchema;
        initFromMetadataSchema();
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param selectedIndices
     */
    public void remove(int[] selectedIndices) {
        ArrayList<SchemaTarget> objectsToRemove = new ArrayList<SchemaTarget>(selectedIndices.length);
        for (int i = 0; i < selectedIndices.length; i++) {
            objectsToRemove.add(schemaTargetList.get(selectedIndices[i]));
        }
        schemaTargetList.removeAll(objectsToRemove);
    }

    /**
     * DOC ocarbone Comment method "removeAll" : remove All Item.
     */
    public void removeAll() {
        schemaTargetList.clear();
    }

    /**
     * DOC ocarbone Comment method "getItemLength()" : return the item count.
     * 
     * @return
     */
    public int getItemCount() {
        return schemaTargetList.size();
    }

}
