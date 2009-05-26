// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
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
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.TypesManager;
import org.talend.core.prefs.ui.MetadataTypeLengthConstants;
import org.talend.core.utils.KeywordsValidator;

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

        if (!match || KeywordsValidator.isKeyword(columnName)) {
            return Messages.getString("MetadataTableEditor.ColumnNameIsInvalid", new Object[] { columnName }); //$NON-NLS-1$ 
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
        return getNextGeneratedColumnName("newColumn", getBeansList()); //$NON-NLS-1$
    }

    public String getNextGeneratedColumnName(String columnLabel) {
        return getNextGeneratedColumnName(columnLabel, getBeansList());
    }

    public String getNextGeneratedColumnName(String oldColumnName, List<IMetadataColumn> metadataColumns) {
        UniqueStringGenerator<IMetadataColumn> uniqueStringGenerator;
        if (metadataColumns != null) {
            uniqueStringGenerator = new UniqueStringGenerator<IMetadataColumn>(oldColumnName, metadataColumns) {

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
        } else {
            uniqueStringGenerator = new UniqueStringGenerator<IMetadataColumn>(oldColumnName, getBeansList()) {

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
        }

        // UniqueStringGenerator<IMetadataColumn> uniqueStringGenerator = new
        // UniqueStringGenerator<IMetadataColumn>(oldColumnName,
        // metadataColumns) {
        //
        // /*
        // * (non-Javadoc)
        // *
        // * @see org.talend.commons.utils.data.list.UniqueStringGenerator#getBeanString(java.lang.Object)
        // */
        // @Override
        // protected String getBeanString(IMetadataColumn bean) {
        // return bean.getLabel();
        // }
        //
        // };

        return uniqueStringGenerator.getUniqueString();
    }

    public IMetadataColumn createNewMetadataColumn() {
        MetadataColumn metadataColumn = new MetadataColumn();
        String columnName = getNextGeneratedColumnName();
        metadataColumn.setLabel(columnName);
        metadataColumn.setNullable(true);
        metadataColumn.setOriginalDbColumnName(columnName);
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            if (CorePlugin.getDefault().getPreferenceStore().getString(MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE) != null
                    && !CorePlugin.getDefault().getPreferenceStore().getString(MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE)
                            .equals("")) { //$NON-NLS-1$
                metadataColumn.setTalendType(CorePlugin.getDefault().getPreferenceStore().getString(
                        MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE));
                if (CorePlugin.getDefault().getPreferenceStore().getString(MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH) != null
                        && !CorePlugin.getDefault().getPreferenceStore().getString(
                                MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH).equals("")) { //$NON-NLS-1$
                    metadataColumn.setLength(Integer.parseInt(CorePlugin.getDefault().getPreferenceStore().getString(
                            MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH)));
                }
            } else {
                metadataColumn.setTalendType(JavaTypesManager.getDefaultJavaType().getId());
                if (metadataTable.getDbms() != null) {
                    metadataColumn.setType(TypesManager.getDBTypeFromTalendType(metadataTable.getDbms(), metadataColumn
                            .getTalendType()));
                }
            }
        } else {
            if (CorePlugin.getDefault().getPreferenceStore().getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE) != null
                    && !CorePlugin.getDefault().getPreferenceStore().getString(
                            MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE).equals("")) { //$NON-NLS-1$
                metadataColumn.setTalendType(CorePlugin.getDefault().getPreferenceStore().getString(
                        MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE));
                if (CorePlugin.getDefault().getPreferenceStore().getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH) != null
                        && !CorePlugin.getDefault().getPreferenceStore().getString(
                                MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH).equals("")) { //$NON-NLS-1$
                    metadataColumn.setLength(Integer.parseInt(CorePlugin.getDefault().getPreferenceStore().getString(
                            MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH)));
                }
            }
        }

        return metadataColumn;
    }
}
