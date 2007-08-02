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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.celleditor.DateDialog;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.core.CorePlugin;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.types.ContextParameterJavaTypeManager;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.utils.TalendTextUtils;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 */
public final class DefaultCellEditorFactory {

    private static DefaultCellEditorFactory instance;

    private Table table;

    public static final String[] BOOLEANS = new String[] { "false", "true" };

    /**
     * qzhang DefaultCellEditorFactory constructor comment.
     */
    public DefaultCellEditorFactory() {
    }

    public static DefaultCellEditorFactory getInstance() {
        if (instance == null) {
            instance = new DefaultCellEditorFactory();
        }
        return instance;
    }

    public CellEditor getCustomCellEditor(final String value, final Table table, final IContext context,
            final TableViewerCreator creator) {
        this.table = table;
        final String defalutDataValue = getDefalutDataValue(table);
        TextCellEditor cellEditor = new TextCellEditor(table) {

            protected void editOccured(ModifyEvent e) {
                super.editOccured(e);
                String value = text.getText();
                if (value != null) {
                    TableItem[] selection = table.getSelection();
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        data2.setValue(value);
                    }
                }
            }

            public void deactivate() {
                creator.setInputList(context.getContextParameterList());
                CorePlugin.getDefault().getRunProcessService().refreshView();
                super.deactivate();
            }

        };

        cellEditor.setValue(defalutDataValue);
        if (value == null) {
            return cellEditor;
        } else if (isFile(value)) {
            return new CustomCellEditor(table) {

                protected Object openDialogBox(Control cellEditorWindow) {
                    FileDialog dialog = new FileDialog(cellEditorWindow.getShell());
                    String path = defalutDataValue;
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
                    creator.setInputList(context.getContextParameterList());
                    CorePlugin.getDefault().getRunProcessService().refreshView();
                    super.deactivate();
                }
            };

        } else if (isDate(value)) {
            return new CustomCellEditor(table) {

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
                    creator.setInputList(context.getContextParameterList());
                    CorePlugin.getDefault().getRunProcessService().refreshView();
                    super.deactivate();
                }
            };
        } else if (isDirectory(value)) {
            return new CustomCellEditor(table) {

                protected Object openDialogBox(Control cellEditorWindow) {
                    DirectoryDialog dialog = new DirectoryDialog(cellEditorWindow.getShell());
                    String path = defalutDataValue;
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
                    creator.setInputList(context.getContextParameterList());
                    CorePlugin.getDefault().getRunProcessService().refreshView();
                    super.deactivate();
                }

            };
        } else if (isBoolean(value)) {

            ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(table, BOOLEANS, SWT.READ_ONLY) {

                final List<String> list = Arrays.asList(BOOLEANS);

                protected void focusLost() {
                    super.focusLost();
                    creator.setInputList(context.getContextParameterList());
                    CorePlugin.getDefault().getRunProcessService().refreshView();
                }

                public Object doGetValue() {
                    // Get the index into the list via this call to super.
                    //
                    int index = ((Integer) super.doGetValue()).intValue();
                    final String string = index < list.size() && index >= 0 ? list.get(((Integer) super.doGetValue()).intValue())
                            : null;
                    if (string != null) {
                        if (string != null) {
                            TableItem[] selection = table.getSelection();
                            if (selection.length == 1) {
                                IContextParameter data2 = (IContextParameter) selection[0].getData();
                                data2.setValue(string.toString());
                            }
                        }
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
        } else {
            return cellEditor;
        }
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
     * qzhang Comment method "getDefalutDataValue".
     * 
     * @param table2
     * @return
     */
    protected String getDefalutDataValue(Table table2) {
        String path = "";
        TableItem[] selection = table.getSelection();
        if (selection.length == 1) {
            IContextParameter data2 = (IContextParameter) selection[0].getData();
            path = data2.getValue();
        }
        return path;
    }

    public CellEditor getSWTCellEditor(final String value, final Table table) {
        TextCellEditor cellEditor = new TextCellEditor(table) {

            protected void editOccured(ModifyEvent e) {
                super.editOccured(e);
                String value = text.getText();
                if (value != null) {
                    TableItem[] selection = table.getSelection();
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        data2.setValue(value);
                    }
                }
            }
        };

        if (value == null) {
            return cellEditor;
        } else if (value.equals(JavaTypesManager.FILE.getId())) {
            return new DialogCellEditor(table) {

                protected Object openDialogBox(Control cellEditorWindow) {
                    FileDialog dialog = new FileDialog(cellEditorWindow.getShell());
                    TableItem[] selection = table.getSelection();
                    String path = "";
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        path = data2.getValue();
                    }
                    if (path != null) {
                        dialog.setFileName(PathUtils.getOSPath(path));
                    }

                    path = dialog.open();
                    if (path != null) {
                        path = PathUtils.getPortablePath(path);
                    }
                    return path;
                }

                protected void updateContents(Object value) {
                    TableItem[] selection = table.getSelection();
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        if (value != null) {
                            data2.setValue(value.toString());
                        }
                        getDefaultLabel().setText(data2.getValue());
                    }
                }
            };
        } else if (value.equals(JavaTypesManager.DATE.getId())) {
            return new DialogCellEditor(table) {

                protected Object openDialogBox(Control cellEditorWindow) {
                    DateDialog d = new DateDialog(cellEditorWindow.getShell());
                    int res = d.open();
                    if (res == Dialog.OK) {
                        return d.getTalendDateString();
                    } else {
                        return getValue();
                    }
                }

                protected void updateContents(Object value) {
                    TableItem[] selection = table.getSelection();
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        if (value != null) {
                            data2.setValue(value.toString());
                        }
                        getDefaultLabel().setText(data2.getValue());
                    }
                }

            };
        } else if (value.equals(JavaTypesManager.DIRECTORY.getId())) {
            return new DialogCellEditor(table) {

                protected Object openDialogBox(Control cellEditorWindow) {
                    DirectoryDialog dialog = new DirectoryDialog(cellEditorWindow.getShell());

                    TableItem[] selection = table.getSelection();
                    String path = "";
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        path = data2.getValue();
                    }
                    if (path != null) {
                        dialog.setFilterPath(PathUtils.getOSPath(path));
                    }

                    path = dialog.open();
                    if (path != null) {
                        path = PathUtils.getPortablePath(path);
                    }
                    return path;
                }

                protected void updateContents(Object value) {
                    TableItem[] selection = table.getSelection();
                    if (selection.length == 1) {
                        IContextParameter data2 = (IContextParameter) selection[0].getData();
                        if (value != null) {
                            data2.setValue(value.toString());
                        }
                        getDefaultLabel().setText(data2.getValue());
                    }
                }
            };
        } else if (value.equals(JavaTypesManager.BOOLEAN.getId())) {
            ComboBoxCellEditor boxCellEditor = new ComboBoxCellEditor(table, BOOLEANS, SWT.READ_ONLY) {

                protected void doSetValue(Object value) {
                    super.doSetValue(value);
                    if (value != null) {
                        TableItem[] selection = table.getSelection();
                        if (selection.length == 1) {
                            IContextParameter data2 = (IContextParameter) selection[0].getData();
                            data2.setValue(value.toString());
                        }
                    }
                }

            };
            return boxCellEditor;
        } else {
            return cellEditor;
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

        protected void updateContents(Object value) {
            TableItem[] selection = table.getSelection();
            if (selection.length == 1) {
                IContextParameter data2 = (IContextParameter) selection[0].getData();
                if (value != null) {
                    data2.setValue(value.toString());
                }
                getDefaultLabel().setText(data2.getValue());
            }
            CorePlugin.getDefault().getRunProcessService().refreshView();
        }

        protected void editOccured(ModifyEvent e) {
            super.editOccured(e);
            String value = defaultLabel.getText();
            if (value != null) {
                TableItem[] selection = table.getSelection();
                if (selection.length == 1) {
                    IContextParameter data2 = (IContextParameter) selection[0].getData();
                    data2.setValue(PathUtils.getPortablePath(value));
                }
            }
        }

    }
}
