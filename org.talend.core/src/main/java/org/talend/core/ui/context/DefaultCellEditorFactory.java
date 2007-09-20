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
package org.talend.core.ui.context;

import java.util.Arrays;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.talend.commons.ui.swt.tableviewer.celleditor.DateDialog;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public final class DefaultCellEditorFactory {

    public static final String[] BOOLEANS = new String[] { "false", "true" };

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
        modelManager.getCommandStack().execute(c);
    }

    public CellEditor getCustomCellEditor(final IContextParameter para, final Tree tree) {
        this.para = para;
        final String defalutDataValue = para.getValue();
        String type = para.getType();

        if (isBoolean(type)) {

            ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(tree, BOOLEANS, SWT.READ_ONLY) {

                final List<String> list = Arrays.asList(BOOLEANS);

                protected void focusLost() {
                    super.focusLost();
                    refreshAll();
                }

                public Object doGetValue() {
                    // Get the index into the list via this call to super.
                    //
                    int index = ((Integer) super.doGetValue()).intValue();
                    final String string = index < list.size() && index >= 0 ? list.get(((Integer) super.doGetValue()).intValue())
                            : null;
                    if (string != null) {
                        para.setValue(string.toString());
                    }
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

        CellEditor cellEditor = null;

        if (type != null) {
            if (isFile(type)) {
                cellEditor = createFileCellEditor(tree, defalutDataValue);
            } else if (isDate(type)) {
                cellEditor = createDateCellEditor(tree);
            } else if (isDirectory(type)) {
                cellEditor = createDirectoryCellEditor(tree, defalutDataValue);
            }
        }

        if (cellEditor == null) {
            return null;
        }
        cellEditor.setValue(defalutDataValue);
        return cellEditor;
    }

    public CellEditor getCustomCellEditor(final IContextParameter para, final Table table) {
        this.para = para;
        final String defalutDataValue = para.getValue();
        String type = para.getType();

        if (isBoolean(type)) {

            ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(table, BOOLEANS, SWT.READ_ONLY) {

                final List<String> list = Arrays.asList(BOOLEANS);

                protected void focusLost() {
                    super.focusLost();
                    refreshAll();
                }

                public Object doGetValue() {
                    // Get the index into the list via this call to super.
                    //
                    int index = ((Integer) super.doGetValue()).intValue();
                    final String string = index < list.size() && index >= 0 ? list.get(((Integer) super.doGetValue()).intValue())
                            : null;
                    if (string != null) {
                        para.setValue(string.toString());
                    }
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

        CellEditor cellEditor = null;

        if (type != null) {
            if (isFile(type)) {
                cellEditor = createFileCellEditor(table, defalutDataValue);
            } else if (isDate(type)) {
                cellEditor = createDateCellEditor(table);
            } else if (isDirectory(type)) {
                cellEditor = createDirectoryCellEditor(table, defalutDataValue);
            }
        }

        if (cellEditor == null) {
            return null;
        }
        cellEditor.setValue(defalutDataValue);
        return cellEditor;
    }

    private CellEditor createDirectoryCellEditor(Composite parent, final String defaultPath) {
        return new CustomCellEditor(parent) {

            protected Object openDialogBox(Control cellEditorWindow) {
                DirectoryDialog dialog = new DirectoryDialog(cellEditorWindow.getShell());
                String path = defaultPath;
                if (path != null) {
                    dialog.setFilterPath(PathUtils.getOSPath(getRemoveQuoteString(path)));
                }

                path = dialog.open();
                if (path != null) {
                    path = PathUtils.getPortablePath(path);
                }
                return getAddQuoteString(path);
            }

            public void deactivate() {
                super.deactivate();
            }
        };
    }

    private CellEditor createDateCellEditor(Composite parent) {
        return new CustomCellEditor(parent) {

            protected Object openDialogBox(Control cellEditorWindow) {
                DateDialog d = new DateDialog(cellEditorWindow.getShell());
                int res = d.open();
                if (res == Dialog.OK) {
                    return getAddQuoteString(d.getTalendDateString());
                } else {
                    return getValue();
                }
            }

            public void deactivate() {
                super.deactivate();
            }
        };
    }

    private CellEditor createFileCellEditor(Composite parent, final String defaultPath) {
        return new CustomCellEditor(parent) {

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

            public void deactivate() {
                super.deactivate();
            }
        };
    }

    /**
     * qzhang Comment method "getAddQuoteString".
     * 
     * @param path
     * @return
     */
    public String getAddQuoteString(String path) {
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
    public String getRemoveQuoteString(String path) {
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
            para.setValue(value.toString());
            refreshAll();
        }
    }
}
