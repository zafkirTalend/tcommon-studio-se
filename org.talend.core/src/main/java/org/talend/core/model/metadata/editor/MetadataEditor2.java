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
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataEditor2 {

    public static final String VALID_CHAR_COLUMN_NAME = "a-zA-Z_0-9";

    private static final PatternCompiler COMPILER = new Perl5Compiler();

    private static Pattern validPatternColumnNameRegexp = null;

    public String titleName;

    private MetadataTable metadataTable;

    private ListenableList<MetadataColumn> metadataColumnList;

    private List<IMetadataEditorListener> listeners = new ArrayList<IMetadataEditorListener>();

    public MetadataEditor2(String titleName) {
        this.titleName = titleName;
    }

    public MetadataEditor2() {
        super();
    }

    public MetadataEditor2(MetadataTable metadataTable, String titleName) {
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
    public void add(MetadataColumn metadataColumn, Integer index) {
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
    public void addAll(List<MetadataColumn> metadataColumn, Integer index) {
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
        this.metadataColumnList = new ListenableList<MetadataColumn>(this.metadataTable.getColumns());
    }

    public String getTitleName() {
        return this.titleName;
    }

    public ListenableList<MetadataColumn> getMetadataColumnList() {
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

    public MetadataTable getMetadataTable() {
        return this.metadataTable;
    }

    /**
     * set MetadataTable.
     * 
     * @param metadataEditorTable
     */
    public void setMetadataTable(MetadataTable metadataTable) {
        this.metadataTable = metadataTable;
        initFromMetadataTable();
    }

    /**
     * DOC amaumont Comment method "remove".
     * 
     * @param selectedIndices
     */
    public void remove(int[] selectedIndices) {
        ArrayList<MetadataColumn> objectsToRemove = new ArrayList<MetadataColumn>(selectedIndices.length);
        for (int i = 0; i < selectedIndices.length; i++) {
            objectsToRemove.add(metadataColumnList.get(selectedIndices[i]));
        }
        metadataColumnList.removeAll(objectsToRemove);
    }

    /**
     * 
     * DOC amaumont Comment method "validateColumnName". Check columName width the list of columns to be see on the
     * table
     * 
     * @param columnName
     * @param beanPosition
     * @return
     */
    public String validateColumnName(String columnName, int beanPosition) {
        return validateColumnName(columnName, beanPosition, metadataColumnList);
    }

    /**
     * 
     * DOC amaumont Comment method "validateColumnName".
     * 
     * @param columnName
     * @param beanPosition
     * @param List<MetadataColumn>
     * @return
     */
    public String validateColumnName(String columnName, int beanPosition, List<MetadataColumn> metadataColumns) {
        if (columnName == null) {
            return "Error: Column name is null";
        }
        String VALID_PATTERN_COLUMN_NAME = "^[a-zA-Z_][" + VALID_CHAR_COLUMN_NAME + "]*$";

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

        int lstSize = metadataColumns.size();
        for (int i = 0; i < lstSize; i++) {
            if (columnName.equals(metadataColumns.get(i).getLabel()) && i != beanPosition) {
                return "The column name '" + columnName + "' already exists.";
            }
        }
        return null;
    }

    /**
     * DOC ocarbone. getValidateColumnName return a valid label to a column (not empty, no doublon, no illegal char).
     * 
     * @param columnLabel
     * @param i
     * @param List<MetadataColumn>
     * @return string
     */
    public String getValidateColumnName(String columnLabel, int i) {
        return getValidateColumnName(columnLabel, i, metadataColumnList);
    }

    /**
     * DOC ocarbone. getValidateColumnName return a valid label to a column (not empty, no doublon, no illegal char).
     * 
     * @param columnLabel
     * @param i
     * @param List<MetadataColumn>
     * @return string
     */
    public String getValidateColumnName(String columnLabel, int i, List<MetadataColumn> metadataColumns) {

        String defaultNameColumn = Messages.getString("repository.column");

        // if not empty, define a default value
        if ((columnLabel == null) || columnLabel.equals("")) {
            columnLabel = defaultNameColumn + i;
        } else {

            // trim whitespaces
            columnLabel = columnLabel.replaceAll(" ", "");

            // replace illegal character by underscore
            columnLabel = columnLabel.replaceAll("[^" + VALID_CHAR_COLUMN_NAME + "]", "_");

            // Reinitilize String when begin with Number for example
            if (columnLabel.equals("")) {
                columnLabel = defaultNameColumn + i;
            } else {
                if (Character.isDigit(columnLabel.charAt(0))) {
                    // if the start of column is a Number, concatenate "Column"+Value of columnLabel
                    columnLabel = defaultNameColumn + columnLabel;
                }
            }
        }

        // determine the index of the indice on the string columnLabel (by default : columnLabel.length())
        int indiceIndex = columnLabel.length();

        // ignore the first character (it's always alphabetic)
        for (int f = columnLabel.length() - 1; f > 0; f--) {
            if (Character.isDigit(columnLabel.charAt(f))) {
                indiceIndex = f;
            } else {
                f = 0;
            }
        }

        // validate the value is unique and indice then if needed
        if (metadataColumns != null) {
            while (validateColumnName(columnLabel, -1, metadataColumns) != null) {
                try {
                    String indiceString = columnLabel.substring(indiceIndex, columnLabel.length());
                    columnLabel = columnLabel.substring(0, indiceIndex) + (Integer.parseInt(indiceString) + 1);
                } catch (NumberFormatException e) {
                    columnLabel = columnLabel + "1";
                }
            }
        }
        return columnLabel;
    }

    /**
     * DOC ocarbone Comment method "removeAll" : remove All Item.
     */
    public void removeAll() {
        metadataColumnList.clear();
    }

    /**
     * DOC ocarbone Comment method "getItemLength()" : return the item count.
     * 
     * @return
     */
    public int getItemCount() {
        return metadataColumnList.size();
    }

}
