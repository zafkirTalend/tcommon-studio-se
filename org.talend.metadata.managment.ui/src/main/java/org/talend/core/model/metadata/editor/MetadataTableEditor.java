// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
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

import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.utils.data.list.UniqueStringGenerator;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.TypesManager;
import org.talend.core.prefs.ui.MetadataTypeLengthConstants;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.metadata.managment.ui.i18n.Messages;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: MetadataTableEditor.java 51244 2010-11-15 03:28:34Z cli $
 * 
 */
public class MetadataTableEditor extends ExtendedTableModel<IMetadataColumn> {

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

        if (!MetadataToolHelper.isValidColumnName(columnName)) {
            return ""; //$NON-NLS-1$ 
        }

        int lstSize = getBeansList().size();
        for (int i = 0; i < lstSize; i++) {
            if (columnName.equals(getBeansList().get(i).getLabel()) && i != beanPosition) {
                return ""; //$NON-NLS-1$
            } else if (columnName.toLowerCase().equals(getBeansList().get(i).getLabel().toLowerCase()) && i != beanPosition) {
                String index = columnName.substring(0, 1);
                String last = getBeansList().get(i).getLabel().substring(0, 1);
                if (index.toLowerCase().equals(last.toLowerCase())) {
                    return Messages.getString("MetadataTableEditor.ColumnNameIsInvalid", columnName); //$NON-NLS-1$
                }
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
            if (CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                    .getString(MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE) != null
                    && !CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                            .getString(MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE).equals("")) { //$NON-NLS-1$
                metadataColumn.setTalendType(CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                        .getString(MetadataTypeLengthConstants.FIELD_DEFAULT_TYPE));
                if (CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                        .getString(MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH) != null
                        && !CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                                .getString(MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH).equals("")) { //$NON-NLS-1$
                    metadataColumn.setLength(Integer.parseInt(CoreRuntimePlugin.getInstance().getCoreService()
                            .getPreferenceStore().getString(MetadataTypeLengthConstants.FIELD_DEFAULT_LENGTH)));
                }
            } else {
                metadataColumn.setTalendType(JavaTypesManager.getDefaultJavaType().getId());
                if (metadataTable.getDbms() != null) {
                    metadataColumn.setType(TypesManager.getDBTypeFromTalendType(metadataTable.getDbms(),
                            metadataColumn.getTalendType()));
                }
            }
        } else {
            if (CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                    .getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE) != null
                    && !CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                            .getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE).equals("")) { //$NON-NLS-1$
                metadataColumn.setTalendType(CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                        .getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_TYPE));
                if (CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                        .getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH) != null
                        && !CoreRuntimePlugin.getInstance().getCoreService().getPreferenceStore()
                                .getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH).equals("")) { //$NON-NLS-1$
                    metadataColumn.setLength(Integer.parseInt(CoreRuntimePlugin.getInstance().getCoreService()
                            .getPreferenceStore().getString(MetadataTypeLengthConstants.PERL_FIELD_DEFAULT_LENGTH)));
                }
            }
        }

        return metadataColumn;
    }
}
