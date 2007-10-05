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
package org.talend.core.ui.metadata.editor;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.advanced.dataeditor.ExtendedToolbarView;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.DbDefaultLengthAndPrecision;
import org.talend.core.model.metadata.Dbms;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.editor.MetadataEmfTableEditor;
import org.talend.core.model.metadata.types.TypesManager;
import org.talend.core.ui.proposal.JavaSimpleDateFormatProposalProvider;

/**
 * DOC amaumont class global comment. Detailled comment <br/> TGU same purpose as MetadataTableEditorView but uses EMF
 * model directly
 * 
 * $Id$
 * 
 */
public class MetadataEmfTableEditorView extends AbstractMetadataTableEditorView<MetadataColumn> {

    public static final String ID_COLUMN_NAME = "ID_COLUMN_NAME"; //$NON-NLS-1$

    /**
     * You must initialize graphicals components by calling <code>initGraphicComponents()</code>.
     * 
     * @param parent
     * @param style
     */
    public MetadataEmfTableEditorView(Composite parent, int style) {
        super(parent, style, false);
        initGraphicComponents();
    }

    /**
     * DOC amaumont MetadataEmfTableEditorView constructor comment.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param initGraphicsComponents
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle, boolean initGraphicsComponents) {
        super(parentComposite, mainCompositeStyle, initGraphicsComponents);
    }

    /**
     * Graphics components are automatically initialized.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     * @param readOnly
     * @param toolbarVisible
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<MetadataColumn> extendedTableModel, boolean readOnly, boolean toolbarVisible) {
        super(parentComposite, mainCompositeStyle, extendedTableModel, readOnly, toolbarVisible, true);
    }

    /**
     * Graphics components are automatically initialized.
     * 
     * @param parentComposite
     * @param mainCompositeStyle
     * @param extendedTableModel
     */
    public MetadataEmfTableEditorView(Composite parentComposite, int mainCompositeStyle,
            ExtendedTableModel<MetadataColumn> extendedTableModel) {
        super(parentComposite, mainCompositeStyle, extendedTableModel);
    }

    public MetadataEmfTableEditor getMetadataEditor() {
        return (MetadataEmfTableEditor) getExtendedTableModel();
    }

    public void setMetadataEditor(MetadataEmfTableEditor metadataTableEditor) {
        setExtendedTableModel(metadataTableEditor);
    }

    public TableViewerCreator<MetadataColumn> getTableViewerCreator() {
        return getExtendedTableViewer().getTableViewerCreator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView#getNullableAccessor()
     */
    @Override
    protected IBeanPropertyAccessors getNullableAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, Boolean>() {

            public Boolean get(MetadataColumn bean) {
                return bean.isNullable() ? Boolean.TRUE : Boolean.FALSE;
            }

            public void set(MetadataColumn bean, Boolean value) {
                bean.setNullable(value);
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getCommentAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getComment();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setComment(value);
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getDefaultValueAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                String value = bean.getDefaultValue();

                // Replaces all single quote mark and double quotes.
                value = handleDefaultValue(bean, value);
                return value;
            }

            public void set(MetadataColumn bean, String value) {

                // Replaces all single quote mark and double quotes.
                value = handleDefaultValue(bean, value);
                bean.setDefaultValue(value);
            }

            /**
             * Adds double quotes if Talend type is Date or String.
             * 
             * @param bean
             * @param value
             * @return
             */
            private String handleDefaultValue(MetadataColumn bean, String value) {
                // Checks if Talend type is String or Date.

                String returnValue = value;

                switch (LanguageManager.getCurrentLanguage()) {
                case JAVA:
                    if (bean.getTalendType().equals("id_String") || bean.getTalendType().equals("id_Date")) {
                        if (returnValue == null) {
                            returnValue = "null";
                        } else if (returnValue.length() == 0) {
                            returnValue = "\"" + "\"";
                        } else if (returnValue.equalsIgnoreCase("null")) {
                            returnValue = "null";
                        } else {
                            returnValue = returnValue.replaceAll("\"", "");
                            returnValue = returnValue.replaceAll("\'", "");
                            returnValue = "\"" + returnValue + "\"";
                        }
                    }
                default:
                    // if (bean.getTalendType() != null && bean.getTalendType().equals("string")
                    // || bean.getTalendType().equals("date")) {
                    // if (returnValue == null) {
                    // returnValue = "null";
                    // } else if (returnValue.length() == 0) {
                    // returnValue = "\"" + "\"";
                    // } else if (returnValue.equalsIgnoreCase("null")) {
                    // returnValue = "null";
                    // } else {
                    // returnValue = returnValue.replaceAll("\"", "");
                    // returnValue = returnValue.replaceAll("\'", "");
                    // returnValue = "\"" + returnValue + "\"";
                    // }
                    // }
                }
                return returnValue;
            }
        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, Integer> getPrecisionAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, Integer>() {

            public Integer get(MetadataColumn bean) {
                // String dbmsId = getCurrentDbms();
                // if (dbmsId != null && bean.getPrecision() < 0) {
                // Dbms dbms = MetadataTalendType.getDbms(dbmsId);
                // List<DbDefaultLengthAndPrecision> dlpList = dbms.getDefaultLengthPrecision();
                // for (DbDefaultLengthAndPrecision dlp : dlpList) {
                // if (dlp.getDbTypeName().equals(bean.getSourceType()))
                // if (dlp.getDefaultPrecision() == null) {
                // bean.setPrecision(0);
                // } else {
                // bean.setPrecision(dlp.getDefaultPrecision());
                // }
                // }
                // }
                return bean.getPrecision();
            }

            public void set(MetadataColumn bean, Integer value) {
                bean.setPrecision(value);
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, Integer> getLengthAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, Integer>() {

            public Integer get(MetadataColumn bean) {
                // String dbmsId = getCurrentDbms();
                // if (dbmsId != null && bean.getLength() <= 0) {
                // Dbms dbms = MetadataTalendType.getDbms(dbmsId);
                // List<DbDefaultLengthAndPrecision> dlpList = dbms.getDefaultLengthPrecision();
                // for (DbDefaultLengthAndPrecision dlp : dlpList) {
                // if (dlp.getDbTypeName().equals(bean.getSourceType())) {
                // if (dlp.getDefaultLength() == null) {
                // bean.setLength(0);
                // } else {
                // bean.setLength(dlp.getDefaultLength());
                // }
                // }
                // }
                // }
                return bean.getLength();
            }

            public void set(MetadataColumn bean, Integer value) {
                bean.setLength(value);
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getPatternAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getPattern();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setPattern(value); // MetadataEmfTableEditorView.this.getJavaDateTypeForDefaultPattern(bean));
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, Boolean> getKeyAccesor() {
        return new IBeanPropertyAccessors<MetadataColumn, Boolean>() {

            public Boolean get(MetadataColumn bean) {
                return new Boolean(bean.isKey());
            }

            public void set(MetadataColumn bean, Boolean value) {
                bean.setKey(value);
            }

        };
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getLabelAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getLabel();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setLabel(value);
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView#validateColumnName(java.lang.String, int)
     */
    @Override
    protected String validateColumnName(String newValue, int beanPosition) {
        return getMetadataEditor().validateColumnName(newValue, beanPosition);
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getTalendTypeAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getTalendType();
            }

            public void set(MetadataColumn bean, String value) {
                String oldTalendType = bean.getTalendType();
                bean.setTalendType(value);
                String dbms = getCurrentDbms();
                if (showDbTypeColumn && (dbms != null)) {
                    String oldDbType = bean.getSourceType();
                    String oldDefaultDbType = null;
                    if (!"".equals(oldDbType)) {
                        oldDefaultDbType = TypesManager.getDBTypeFromTalendType(dbms, oldTalendType);
                    }
                    if (oldDbType == null || oldDbType.equals(oldDefaultDbType) || "".equals(oldDbType)) {
                        bean.setSourceType(TypesManager.getDBTypeFromTalendType(dbms, value));
                    }
                }
                if (currentBeanHasJavaDateType(bean)) {
                    bean.setPattern(new JavaSimpleDateFormatProposalProvider().getProposals(null, 0)[0].getContent());
                    // adaptLengthAndPrecision(bean, dbms);
                }
            }
        };
    }

    private void adaptLengthAndPrecision(MetadataColumn bean, String dbms) {
        Dbms dbmsD = MetadataTalendType.getDbms(dbms);
        List<DbDefaultLengthAndPrecision> dlpList = dbmsD.getDefaultLengthPrecision();
        for (DbDefaultLengthAndPrecision dlp : dlpList) {
            if (dlp.getDbTypeName().equals(bean.getSourceType())) {
                if (dlp.getDefaultLength() == null) {
                    bean.setLength(0);
                } else {
                    bean.setLength(dlp.getDefaultLength());
                }
                if (dlp.getDefaultPrecision() == null) {
                    bean.setPrecision(0);
                } else {
                    bean.setPrecision(dlp.getDefaultPrecision());
                }
            }
        }

    }

    @Override
    protected IBeanPropertyAccessors getDbTypeAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getSourceType();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setSourceType(value);
                // String dbms = getCurrentDbms();
                // if (dbms != null) {
                // adaptLengthAndPrecision(bean, dbms);
                // }
            }

        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView#initToolBar()
     */
    @Override
    protected ExtendedToolbarView initToolBar() {
        return new MetadataEmfToolbarEditor(getMainComposite(), SWT.NONE, this.getExtendedTableViewer(), getCurrentDbms());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.metadata.editor.AbstractMetadataTableEditorView#configureDefaultColumn(org.talend.commons.ui.swt.tableviewer.TableViewerCreator)
     */
    @Override
    protected void configureDefaultColumn(TableViewerCreator<MetadataColumn> tableViewerCreator) {
        super.configureDefaultColumn(tableViewerCreator);
    }

    @Override
    protected IBeanPropertyAccessors<MetadataColumn, String> getDbColumnNameAccessor() {
        return new IBeanPropertyAccessors<MetadataColumn, String>() {

            public String get(MetadataColumn bean) {
                return bean.getOriginalField();
            }

            public void set(MetadataColumn bean, String value) {
                bean.setOriginalField(value);
            }

        };
    }
}
