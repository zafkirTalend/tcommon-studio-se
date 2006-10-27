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
package org.talend.core.model.metadata.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.talend.commons.utils.data.list.IListenableListListener;
import org.talend.commons.utils.data.list.ListenableList;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataTableEditor {

    // private static final String VALID_PATTERN_COLUMN_NAME = "[\\w]&&[\\D]\\w*";

    private static final PatternCompiler COMPILER = new Perl5Compiler();

    private static Pattern validPatternColumnNameRegexp = null;

    private String titleName;

    private IMetadataTable metadataTable;

    private ListenableList<IMetadataColumn> metadataColumnList;

    private List<IMetadataEditorListener> listeners = new ArrayList<IMetadataEditorListener>();

    public MetadataTableEditor() {
        super();
    }

    public MetadataTableEditor(IMetadataTable metadataTable, String titleName) {
        super();
        this.metadataTable = metadataTable;
        this.titleName = titleName;
        initFromMetadataTable();
    }

    /**
     * DOC amaumont Comment method "addListener".
     * 
     * @param view
     */
    public void addListener(IMetadataEditorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IMetadataEditorListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(MetadataEditorEvent event) {
        for (IMetadataEditorListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param metadataColumn
     * @param index can be null
     */
    public void add(IMetadataColumn metadataColumn, Integer index) {
        if (index == null || index < 0 || index > this.metadataColumnList.size() - 1) {
            this.metadataColumnList.add(metadataColumn);
        } else {
            this.metadataColumnList.add(index, metadataColumn);
        }
    }

    /**
     * DOC amaumont Comment method "add".
     * 
     * @param metadataColumn
     * @param index can be null
     */
    public void addAll(List<IMetadataColumn> metadataColumn, Integer index) {
        if (index == null || index < 0 || index > this.metadataColumnList.size() - 1) {
            this.metadataColumnList.addAll(metadataColumn);
        } else {
            this.metadataColumnList.addAll(index, metadataColumn);
        }
    }

    public void addModifiedListListener(IListenableListListener listenableListListener) {
        this.metadataColumnList.addListener(listenableListListener);
    }

    public void removeModifiedListListener(IListenableListListener listenableListListener) {
        this.metadataColumnList.removeListener(listenableListListener);
    }

    public void initFromMetadataTable() {
        initData();
    }

    private void initData() {
        this.metadataColumnList = new ListenableList<IMetadataColumn>(this.metadataTable.getListColumns());
    }

    public String getTitleName() {
        return this.titleName;
    }

    public ListenableList<IMetadataColumn> getMetadataColumnList() {
        return this.metadataColumnList;
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param metadataColumn
     */
    public void remove(MetadataColumn metadataColumn) {
        this.metadataColumnList.remove(metadataColumn);
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param i
     */
    public void remove(int index) {
        this.metadataColumnList.remove(index);
    }

    public void clear() {
        this.metadataColumnList.clear();
    }

    public IMetadataTable getMetadataTable() {
        return this.metadataTable;
    }

    /**
     * set MetadaTable.
     * 
     * @param metadataEditorTable
     */
    public void setMetadataTable(IMetadataTable metadataTable) {
        this.metadataTable = metadataTable;
        initFromMetadataTable();
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param selectedIndices
     */
    public void remove(int[] selectedIndices) {
        ArrayList<IMetadataColumn> objectsToRemove = new ArrayList<IMetadataColumn>(selectedIndices.length);
        for (int i = 0; i < selectedIndices.length; i++) {
            objectsToRemove.add(metadataColumnList.get(selectedIndices[i]));
        }
        metadataColumnList.removeAll(objectsToRemove);
    }

    /**
     * 
     * DOC amaumont Comment method "validateColumnName".
     * 
     * @param columnName
     * @return true if columnName has a valid value
     */
    public String validateColumnName(String columnName, int beanPosition) {
        if (columnName == null) {
            return "Error: Column name is null";
        }
        String VALID_PATTERN_COLUMN_NAME = "^[a-zA-Z_][a-zA-Z_0-9]*$";
        validPatternColumnNameRegexp = null;
        if (validPatternColumnNameRegexp == null) {
            try {
                validPatternColumnNameRegexp = COMPILER.compile(VALID_PATTERN_COLUMN_NAME);
            } catch (MalformedPatternException e) {
                throw new RuntimeException(e);
            }
        }
        Perl5Matcher matcher = new Perl5Matcher();
        boolean match = matcher.matches(columnName, validPatternColumnNameRegexp);

        if (!match) {
            return "The column name '" + columnName + "' is invalid.";
        }

        int lstSize = metadataColumnList.size();
        for (int i = 0; i < lstSize; i++) {
            if (columnName.equals(metadataColumnList.get(i).getLabel()) && i != beanPosition) {
                return "The column name '" + columnName + "' already exists.";
            }

        }
        return null;
    }

    public String getNextGeneratedColumnName() {
        return getNextGeneratedColumnName("newColumn");
    }

    public String getNextGeneratedColumnName(String oldColumnName) {
        String[] labels = new String[this.metadataColumnList.size()];
        int lstSize = this.metadataColumnList.size();
        for (int i = 0; i < lstSize; i++) {
            IMetadataColumn metadataColumn = this.metadataColumnList.get(i);
            labels[i] = metadataColumn.getLabel();
        }

        boolean found = false;
        int indexNewColumn = 0;
        String newColumnName = null;
        while (!found) {
            newColumnName = oldColumnName + (++indexNewColumn);
            boolean allAreDifferent = true;
            for (int j = 0; j < labels.length; j++) {
                String label = labels[j];
                if (label.equals(newColumnName)) {
                    allAreDifferent = false;
                    break;
                }
            }
            if (allAreDifferent) {
                found = true;
            }
        }

        return newColumnName;
    }
}
