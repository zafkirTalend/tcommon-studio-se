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
package org.talend.core.ui.context;

import java.util.Arrays;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.talend.commons.ui.swt.tableviewer.celleditor.DateDialog;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public final class DefaultCellEditorFactory {

    public static final String[] BOOLEANS = new String[] { "false", "true" }; //$NON-NLS-1$ //$NON-NLS-2$

    IContextParameter para = null;

    private IContextModelManager modelManager = null;

    /**
     * qzhang DefaultCellEditorFactory constructor comment.
     */
    public DefaultCellEditorFactory(IContextModelManager modelManager) {
        this.modelManager = modelManager;
    }

    /**
     * DOC bqian Comment method "refreshAll".
     */
    private void refreshAll() {
        Command c = new Command() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.gef.commands.Command#execute()
             */
            @Override
            public void execute() {
                modelManager.refresh();
            }
        };
        if (modelManager.getCommandStack() == null) {
            c.execute();
        } else {
            modelManager.getCommandStack().execute(c);
        }

    }

    private void setModifyFlag() {
        // set updated flag.
        if (modelManager != null) {
            IContextManager manager = modelManager.getContextManager();
            if (manager != null && manager instanceof JobContextManager) {
                JobContextManager jobContextManager = (JobContextManager) manager;
                if (para != null) {
                    if (!modelManager.isRepositoryContext() || modelManager.isRepositoryContext()
                            && jobContextManager.isOriginalParameter(para.getName())) {
                        jobContextManager.setModified(true);
                    }
                }
            }
        }
    }

    public CellEditor getCustomCellEditor(final IContextParameter para, final Composite table) {
        this.para = para;
        String defalutDataValue = para.getValue();
        String type = para.getType();

        final ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            if (isBoolean(type)) {

                ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(table, BOOLEANS, SWT.READ_ONLY) {

                    final List<String> list = Arrays.asList(BOOLEANS);

                    @Override
                    protected void focusLost() {
                        super.focusLost();
                        String value = doGetValue().toString();
                        if (para.getValue().equals(value)) {
                            return;
                        }
                        para.setValue(value);
                        refreshAll();
                        setModifyFlag();
                    }

                    @Override
                    public Object doGetValue() {
                        // Get the index into the list via this call to super.
                        //
                        int index = ((Integer) super.doGetValue()).intValue();
                        final String string = index < list.size() && index >= 0 ? list.get(((Integer) super.doGetValue())
                                .intValue()) : null;
                        return string;
                    }
                };
                Integer integer = new Integer(0);
                if (BOOLEANS[1].equals(defalutDataValue)) {
                    integer = new Integer(1);
                }
                boxCellEditor.setValue(integer);
                return boxCellEditor;
            }
        } else {
            if ("boolean".equals(type)) { //$NON-NLS-1$

                ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(table, BOOLEANS, SWT.READ_ONLY) {

                    final List<String> list = Arrays.asList(BOOLEANS);

                    @Override
                    protected void focusLost() {
                        super.focusLost();
                        String value = doGetValue().toString();
                        if (para.getValue().equals(value)) {
                            return;
                        }
                        para.setValue(value);
                        refreshAll();
                        setModifyFlag();
                    }

                    @Override
                    public Object doGetValue() {
                        // Get the index into the list via this call to super.
                        //
                        int index = ((Integer) super.doGetValue()).intValue();
                        final String string = index < list.size() && index >= 0 ? list.get(((Integer) super.doGetValue())
                                .intValue()) : null;

                        return string;
                    }
                };
                Integer integer = new Integer(0);
                if (BOOLEANS[1].equals(defalutDataValue)) {
                    integer = new Integer(1);
                }
                boxCellEditor.setValue(integer);
                return boxCellEditor;
            }
        }

        CellEditor cellEditor = null;

        if (type != null) {
            if (isFile(type)) {
                cellEditor = createFileCellEditor(table, defalutDataValue);
                // ((CustomCellEditor) cellEditor).getDefaultLabel().setEditable(false);
            } else if (isDate(type)) {
                cellEditor = createDateCellEditor(table, para);
                ((CustomCellEditor) cellEditor).getDefaultLabel().setEditable(false);
            } else if (isDirectory(type)) {
                cellEditor = createDirectoryCellEditor(table, defalutDataValue);
                // ((CustomCellEditor) cellEditor).getDefaultLabel().setEditable(false);
            } else if (isList(type)) {
                cellEditor = createListCellEditor(table, para);
                defalutDataValue = para.getDisplayValue();
            }
        }

        if (cellEditor == null) {
            cellEditor = createDefaultTextCellEditor(table);
        }
        cellEditor.setValue(defalutDataValue);
        return cellEditor;
    }

    private CellEditor createDefaultTextCellEditor(Composite parent) {
        return new TextCellEditor(parent) {

            @Override
            protected void focusLost() {
                super.focusLost();
                String value = doGetValue().toString();
                if (para.getValue().equals(value)) {
                    return;
                }
                para.setValue(value);
                refreshAll();
                setModifyFlag();
            }

        };
    }

    private CellEditor createDirectoryCellEditor(Composite parent, final String defaultPath) {
        final CustomCellEditor editor = new CustomCellEditor(parent) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                DirectoryDialog dialog = new DirectoryDialog(cellEditorWindow.getShell());
                String path = defaultPath;
                if (path != null) {
                    dialog.setFilterPath(PathUtils.getOSPath(getRemoveQuoteString(path)));
                }

                path = dialog.open();
                if (path != null) {
                    path = PathUtils.getPortablePath(path);
                    path += "/"; //$NON-NLS-1$
                }
                return getAddQuoteString(path);
            }

        };
        onTextChange(editor);
        return editor;
    }

    /**
     * DOC chuang Comment method "onTextChange".
     * 
     * @param editor
     */
    private void onTextChange(final CustomCellEditor editor) {
        editor.getDefaultLabel().addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                String value = editor.doGetValue().toString();
                if (para.getValue().equals(value)) {
                    return;
                }
                para.setValue(value);
                refreshAll();
                setModifyFlag();

            }
        });

    }

    private CellEditor createListCellEditor(Composite parent, final IContextParameter para2) {
        return new CustomCellEditor(parent) {

            /*
             * (non-Javadoc)
             * 
             * @see org.talend.core.ui.context.DefaultCellEditorFactory.CustomCellEditor#doSetValue(java.lang.Object)
             */
            // @Override
            @Override
            protected void doSetValue(Object value) {
                if (value instanceof String[]) {
                    para2.setValueList((String[]) value);
                }

                setLocalValue(para.getDisplayValue());
                updateContents(para.getDisplayValue());
                if (value instanceof String[]) {
                    refreshAll();
                    setModifyFlag();
                }

            }

            @Override
            protected boolean isTextEditable() {
                return false;
            }

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                // Because the text is not editable.
                // String input = getDefaultLabel().getText();
                String[] input = para2.getValueList();
                MultiStringSelectionDialog d = new MultiStringSelectionDialog(cellEditorWindow.getShell(), input);
                int res = d.open();
                if (res == Dialog.OK) {
                    String[] result = d.getResultString();
                    return result;
                }
                return para2.getDisplayValue();

            }

        };
    }

    private CellEditor createDateCellEditor(Composite parent, final IContextParameter param) {
        return new CustomCellEditor(parent) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                DateDialog d = new PatternCalendarDialog(cellEditorWindow.getShell(), param);
                int res = d.open();
                if (res == Dialog.OK) {
                    return getAddQuoteString(d.getTalendDateString());
                } else {
                    return getValue();
                }
            }

            @Override
            public void deactivate() {
                super.deactivate();
            }
        };
    }

    private CellEditor createFileCellEditor(Composite parent, final String defaultPath) {
        final CustomCellEditor editor = new CustomCellEditor(parent) {

            @Override
            protected Object openDialogBox(Control cellEditorWindow) {
                FileDialog dialog = new FileDialog(cellEditorWindow.getShell());
                String path = defaultPath;
                if (path != null) {
                    dialog.setFileName(PathUtils.getOSPath(getRemoveQuoteString(path)));
                }

                path = dialog.open();
                if (path != null) {
                    path = PathUtils.getPortablePath(path);
                }
                return getAddQuoteString(path);
            }

            @Override
            public void deactivate() {
                super.deactivate();
            }
        };
        onTextChange(editor);

        return editor;
    }

    /**
     * qzhang Comment method "getAddQuoteString".
     * 
     * @param path
     * @return
     */
    public static String getAddQuoteString(String path) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.PERL) {
            return TalendTextUtils.addQuotes(path);
        }
        return path;
    }

    /**
     * qzhang Comment method "getRemoveQuoteString".
     * 
     * @param path
     * @return
     */
    public static String getRemoveQuoteString(String path) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.PERL) {
            return TalendTextUtils.removeQuotes(path);
        }
        return path;
    }

    /**
     * qzhang Comment method "isBoolean".
     * 
     * @param value
     * @return
     */
    public static boolean isBoolean(final String value) {
        return value.equals(JavaTypesManager.BOOLEAN.getId());
    }

    /**
     * qzhang Comment method "isDirectory".
     * 
     * @param value
     * @return
     */
    public static boolean isDirectory(final String value) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            return value.equals(JavaTypesManager.DIRECTORY.getId());
        } else {
            return value.equals(ContextParameterJavaTypeManager.PERL_DIRECTORY);
        }
    }

    public static boolean isList(final String value) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            return value.equals(JavaTypesManager.VALUE_LIST.getId());
        } else {
            return value.equals(ContextParameterJavaTypeManager.PERL_VALUE_LIST);
        }
    }

    /**
     * qzhang Comment method "isDate".
     * 
     * @param value
     * @return
     */
    public static boolean isDate(final String value) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            return value.equals(JavaTypesManager.DATE.getId());
        } else {
            return value.equals(ContextParameterJavaTypeManager.PERL_DAY);
        }

    }

    /**
     * qzhang Comment method "isFile".
     * 
     * @param value
     * @return
     */
    public static boolean isFile(final String value) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            return value.equals(JavaTypesManager.FILE.getId());
        } else {
            return value.equals(ContextParameterJavaTypeManager.PERL_FILE);
        }
    }

    public static boolean isPassword(final String value) {
        ECodeLanguage codeLanguage = LanguageManager.getCurrentLanguage();
        if (codeLanguage == ECodeLanguage.JAVA) {
            return value.equals(JavaTypesManager.PASSWORD.getId());
        } else {
            return value.equals(ContextParameterJavaTypeManager.PERL_PASSWORD);
        }

    }

    /**
     * qzhang DefaultCellEditorFactory class global comment. Detailled comment.
     * 
     */
    private abstract class CustomCellEditor extends CustomDialogCellEditor {

        public CustomCellEditor(Composite parent) {
            super(parent);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.core.ui.context.CustomDialogCellEditor#doSetValue(java.lang.Object)
         */
        @Override
        protected void doSetValue(Object value) {
            super.doSetValue(value);
            if (para.getValue().equals(value)) {
                return;
            }
            // if (!isList(para.getType())) {
            para.setValue(value.toString());
            // }
            refreshAll();
            setModifyFlag();
        }
    }
}
