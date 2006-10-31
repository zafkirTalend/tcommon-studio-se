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

    public static final String VALID_CHAR_COLUMN_NAME = "a-zA-Z_0-9";

    private static final PatternCompiler COMPILER = new Perl5Compiler();

    private static Pattern validPatternColumnNameRegexp = null;

    public String titleName;

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
     * 
     * DOC amaumont Comment method "validateColumnName". Check columName width the list of columns to be see on the
     * table
     * 
     * @param columnName
     * @param beanPosition
     * @return
     */
    public String validateColumnName(String columnName, int beanPosition) {
        return validateColumnName(columnName, beanPosition, schemaTargetList);
    }

    /**
     * 
     * DOC amaumont Comment method "validateColumnName".
     * 
     * @param columnName
     * @param beanPosition
     * @param List<SchemaTarget>
     * @return
     */
    public String validateColumnName(String columnName, int beanPosition, List<SchemaTarget> schemaTargets) {
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

        int lstSize = schemaTargets.size();
        for (int i = 0; i < lstSize; i++) {
            if (columnName.equals(schemaTargets.get(i)) && i != beanPosition) {
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
     * @param List<SchemaTarget>
     * @return string
     */
    public String getValidateColumnName(String columnLabel, int i) {
        return getValidateColumnName(columnLabel, i, schemaTargetList);
    }

    /**
     * DOC ocarbone. getValidateColumnName return a valid label to a column (not empty, no doublon, no illegal char).
     * 
     * @param columnLabel
     * @param i
     * @param List<SchemaTarget>
     * @return string
     */
    public String getValidateColumnName(String columnLabel, int i, List<SchemaTarget> schemaTargets) {

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
        if (schemaTargets != null) {
            while (validateColumnName(columnLabel, -1, schemaTargets) != null) {
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
