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
package org.talend.core.model.metadata.editor;

import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.utils.data.list.UniqueStringGenerator;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.TypesManager;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class MetadataTableEditor extends ExtendedTableModel<IMetadataColumn> {

    // private static final String VALID_PATTERN_COLUMN_NAME = "[\\w]&&[\\D]\\w*";

    private static final PatternCompiler COMPILER = new Perl5Compiler();

    private static Pattern validPatternColumnNameRegexp = null;

    private static final String VALID_PATTERN_COLUMN_NAME = "^[a-zA-Z_][a-zA-Z_0-9]*$"; //$NON-NLS-1$

    private IMetadataTable metadataTable;

    public MetadataTableEditor() {
        super();
    }

    public MetadataTableEditor(IMetadataTable metadataTable, String titleName) {
        super(titleName);
        this.metadataTable = metadataTable;
        initFromMetadataTable();
    }

    public void initFromMetadataTable() {
        initData();
    }

    private void initData() {
        registerDataList(this.metadataTable.getListColumns());
    }

    public String getTitleName() {
        return super.getName();
    }

    public List<IMetadataColumn> getMetadataColumnList() {
        return getBeansList();
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
     * 
     * DOC amaumont Comment method "validateColumnName".
     * 
     * @param columnName
     * @return true if columnName has a valid value
     */
    public String validateColumnName(String columnName, int beanPosition) {
        if (columnName == null) {
            return Messages.getString("MetadataTableEditor.ColumnNameIsNull"); //$NON-NLS-1$
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
            return Messages.getString("MetadataTableEditor.ColumnNameIsInvalid", new Object[] { columnName }); //$NON-NLS-1$ //$NON-NLS-2$
        }

        int lstSize = getBeansList().size();
        for (int i = 0; i < lstSize; i++) {
            if (columnName.equals(getBeansList().get(i).getLabel()) && i != beanPosition) {
                return Messages.getString("MetadataTableEditor.ColumnNameExists", columnName); //$NON-NLS-1$
            }

        }
        return null;
    }

    public String getNextGeneratedColumnName() {
        return getNextGeneratedColumnName("newColumn"); //$NON-NLS-1$
    }

    public String getNextGeneratedColumnName(String oldColumnName) {
        UniqueStringGenerator<IMetadataColumn> uniqueStringGenerator = new UniqueStringGenerator<IMetadataColumn>(
                oldColumnName, getBeansList()) {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.commons.utils.data.list.UniqueStringGenerator#getBeanString(java.lang.Object)
             */
            @Override
            protected String getBeanString(IMetadataColumn bean) {
                return bean.getLabel();
            }

        };

        return uniqueStringGenerator.getUniqueString();
    }

    public IMetadataColumn createNewMetadataColumn() {
        MetadataColumn metadataColumn = new MetadataColumn();
        String columnName = getNextGeneratedColumnName();
        metadataColumn.setLabel(columnName);
        metadataColumn.setOriginalDbColumnName(columnName);

        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            metadataColumn.setTalendType(JavaTypesManager.getDefaultJavaType().getId());
            if (metadataTable.getDbms() != null) {
                metadataColumn.setType(TypesManager.getDBTypeFromTalendType(metadataTable.getDbms(), JavaTypesManager
                        .getDefaultJavaType().getId()));
            }
        }
        return metadataColumn;
    }

}
