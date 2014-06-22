// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.snippet;

import java.text.MessageFormat;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.properties.SnippetItem;

/**
 * bqian class global comment. Detailled comment <br/>
 * 
 */
public class VariableItemHelper {

    public static String systemEOL = System.getProperty("line.separator"); //$NON-NLS-1$

    /*
     * Append the generated code comments
     * 
     */
    /* SNIPPET_START:test(String,test) */
    /*
     * for (String myVar : test) { System.out.println(myVar); }
     */
    /* SNIPPET_END:test */
    public static String getInsertString(Shell host, SnippetItem item) {
        String[] paras = getInsertSnippetCode(host, item, true);
        String paraDefinition = ""; //$NON-NLS-1$
        String idDefinition = null;
        StringBuilder sb = new StringBuilder();
        sb.append(systemEOL);
        if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.PERL)) {
            sb.append("#SNIPPET_START:{0}"); //$NON-NLS-1$
            sb.append(systemEOL);
            sb.append("{1}"); //$NON-NLS-1$
            sb.append(systemEOL);
            sb.append("#SNIPPET_END"); //$NON-NLS-1$
            for (String para : paras) {
                paraDefinition += "#"; //$NON-NLS-1$
                paraDefinition += para;
                paraDefinition += systemEOL;
            }
            idDefinition = "#{ID}=" + item.getProperty().getId(); //$NON-NLS-1$
        } else {
            // Java comment format
            sb.append("/*SNIPPET_START:{0}*/"); //$NON-NLS-1$
            sb.append(systemEOL);
            sb.append("{1}"); //$NON-NLS-1$
            sb.append(systemEOL);
            sb.append("/*SNIPPET_END*/"); //$NON-NLS-1$
            for (String para : paras) {
                paraDefinition += para;
                paraDefinition += systemEOL;
            }
            idDefinition = "{ID}=" + item.getProperty().getId(); //$NON-NLS-1$
        }
        sb.append(systemEOL);

        // StringBuilder b = new StringBuilder();
        // b.append("(");
        // for (SnippetVariable var : (List<SnippetVariable>) item.getVariables()) {
        // b.append(var.getValue()).append(",");
        // }
        // b.deleteCharAt(b.length() - 1);
        // b.append(")");
        String snippetDefinition = item.getProperty().getLabel() + systemEOL + paraDefinition + idDefinition;

        String msg = sb.toString();
        MessageFormat format = new MessageFormat(msg);
        Object[] args = new Object[] { snippetDefinition, item.getContent(), item.getProperty().getLabel() }; //$NON-NLS-1$
        msg = format.format(args);

        return msg;
    }

    public static String[] getInsertSnippetCode(final Shell host, SnippetItem item, boolean clearModality) {
        String[] insertString = new String[0];
        if (item == null) {
            return insertString; //$NON-NLS-1$
        }

        if (item.getVariables().size() > 0) {
            VariableInsertionDialog dialog = new VariableInsertionDialog(host, clearModality);
            dialog.setItem(item);
            // The editor itself influences the insertion's actions, so we
            // can't
            // allow the active editor to be changed.
            // Disabling the parent shell achieves psuedo-modal behavior
            // without
            // locking the UI under Linux
            int result = Window.CANCEL;
            try {
                if (clearModality) {
                    host.setEnabled(false);
                    dialog.addDisposeListener(new DisposeListener() {

                        public void widgetDisposed(DisposeEvent arg0) {
                            /*
                             * The parent shell must be reenabled when the dialog disposes, otherwise it won't
                             * automatically receive focus.
                             */
                            host.setEnabled(true);
                        }
                    });
                }
                result = dialog.open();
            } catch (Exception t) {
                ExceptionHandler.process(t);
            } finally {
                if (clearModality) {
                    host.setEnabled(true);
                }
            }
            if (result == Window.OK) {
                insertString = dialog.prepareVariablesText();
            }
        }
        return insertString;
    }

    // public static String getInsertSnippetCode(final Shell host, SnippetItem item, boolean clearModality) {
    // if (item == null) {
    // return ""; //$NON-NLS-1$
    // }
    // String insertString = null;
    // if (item.getVariables().size() > 0) {
    // VariableInsertionDialog dialog = new VariableInsertionDialog(host, clearModality);
    // dialog.setItem(item);
    // // The editor itself influences the insertion's actions, so we
    // // can't
    // // allow the active editor to be changed.
    // // Disabling the parent shell achieves psuedo-modal behavior
    // // without
    // // locking the UI under Linux
    // int result = Window.CANCEL;
    // try {
    // if (clearModality) {
    // host.setEnabled(false);
    // dialog.addDisposeListener(new DisposeListener() {
    //
    // public void widgetDisposed(DisposeEvent arg0) {
    // /*
    // * The parent shell must be reenabled when the dialog disposes, otherwise it won't
    // * automatically receive focus.
    // */
    // host.setEnabled(true);
    // }
    // });
    // }
    // result = dialog.open();
    // } catch (Exception t) {
    // ExceptionHandler.process(t);
    // } finally {
    // if (clearModality) {
    // host.setEnabled(true);
    // }
    // }
    // if (result == Window.OK) {
    // dialog.prepareVariablesText();
    // insertString = dialog.getPreparedText();
    // }
    //
    // } else {
    // insertString = item.getContent();
    // }
    // return insertString;
    // }

}