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

import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.utils.data.list.UniqueStringGenerator;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataEmfTableEditor extends ExtendedTableModel<MetadataColumn> {

    private String defaultLabel = "newColumn";

    private static int indexNewColumn;

    public static final String VALID_CHAR_COLUMN_NAME = "a-zA-Z_0-9";

    private static final PatternCompiler COMPILER = new Perl5Compiler();

    private static final String VALID_PATTERN_COLUMN_NAME = "^[a-zA-Z_][" + VALID_CHAR_COLUMN_NAME + "]*$";

    private static Pattern validPatternColumnNameRegexp = null;

    private MetadataTable metadataTable;

    public MetadataEmfTableEditor(String titleName) {
        super(titleName);
    }

    public MetadataEmfTableEditor() {
        super();
    }

    public MetadataEmfTableEditor(MetadataTable metadataTable, String titleName) {
        super(titleName);
        setMetadataTable(metadataTable);
    }

    public String getTitleName() {
        return getName();
    }

    public List<MetadataColumn> getMetadataColumnList() {
        return getBeansList();
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
        registerDataList((List<MetadataColumn>) this.metadataTable.getColumns());
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
        return validateColumnName(columnName, beanPosition, getBeansList());
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
     * @param List<MetadataColumn>
     * @return string
     */
    public String getNextGeneratedColumnName(String columnLabel) {
        return getNextGeneratedColumnName(columnLabel, getBeansList());
    }

    /**
     * DOC ocarbone. getValidateColumnName return a valid label to a column (not empty, no doublon, no illegal char).
     * 
     * @param columnLabel
     * @param i
     * @param List<MetadataColumn>
     * @return string
     */
    protected String getNextGeneratedColumnName(String oldColumnName, List<MetadataColumn> metadataColumns) {

        UniqueStringGenerator<MetadataColumn> uniqueStringGenerator = new UniqueStringGenerator<MetadataColumn>(oldColumnName,
                metadataColumns) {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.data.list.UniqueStringGenerator#getBeanString(java.lang.Object)
             */
            @Override
            protected String getBeanString(MetadataColumn bean) {
                return bean.getLabel();
            }

        };

        return uniqueStringGenerator.getUniqueString();
    }

    /**
     * DOC amaumont Comment method "createNewMetadataColumn".
     * 
     * @return
     */
    public MetadataColumn createNewMetadataColumn() {
        MetadataColumn metadataColumn = ConnectionFactory.eINSTANCE.createMetadataColumn();
        metadataColumn.setLabel(getNextGeneratedColumnName(defaultLabel));
        return metadataColumn;
    }

    /**
     * Getter for defaultLabel.
     * 
     * @return the defaultLabel
     */
    public String getDefaultLabel() {
        return this.defaultLabel;
    }

    /**
     * Sets the defaultLabel.
     * 
     * @param defaultLabel the defaultLabel to set
     */
    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

}
