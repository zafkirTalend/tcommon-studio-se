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
package org.talend.commons.ui.utils;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class TypedTextCommandExecutor {

    private static final int KEY_CODE_REDO = 121; // 'y'

    private static final int KEY_CODE_UNDO = 122; // 'z'

    private static final int KEY_CODE_PROPOSAL = 27; // ' '

    private Key previousKey;

    private String previousText;

    private KeyListener keyListener = new KeyListener() {

        public void keyPressed(KeyEvent e) {
            // keyPressedExecute(e);
        }

        public void keyReleased(KeyEvent e) {
            keyReleasedExecute(e);
        }

    };

    private FocusListener focusListener = new FocusListener() {

        public void focusGained(FocusEvent e) {
            focusGainedExecute(e);
        }

        public void focusLost(FocusEvent e) {
            focusLostExecute(e);
        }

    };

    private Pattern patternAlphaNum;

    private Perl5Matcher matcher;

    /**
     * DOC amaumont TypedTextCommandExecutor constructor comment.
     * 
     * @param stack
     */
    public TypedTextCommandExecutor() {
        super();
        init();
    }

    /**
     * DOC amaumont Comment method "init".
     */
    private void init() {

        Perl5Compiler compiler = new Perl5Compiler();
        matcher = new Perl5Matcher();
        patternAlphaNum = null;
        try {
            patternAlphaNum = compiler.compile("\\w");
        } catch (MalformedPatternException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected void keyPressedExecute(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    protected void keyReleasedExecute(KeyEvent e) {

        Control control = (Control) e.getSource();

        String currentText = ControlUtils.getText(control);

        // System.out.println(e);

        boolean alphaNumMatched = matcher.matches(String.valueOf(e.character), patternAlphaNum);
        boolean ctrlKey = (e.stateMask & (SWT.CTRL)) != 0;
        boolean combinedKeys = (e.stateMask & (SWT.CTRL | SWT.ALT | SWT.SHIFT)) != 0;
        boolean undoOrRedo = ctrlKey && (e.keyCode == KEY_CODE_REDO || e.keyCode == KEY_CODE_UNDO);
        boolean proposal = ctrlKey && (e.keyCode == KEY_CODE_PROPOSAL);
        if (combinedKeys && !undoOrRedo && currentText.equals(previousText) || proposal) {
            // nothing
        } else if (!currentText.equals(previousText)) {
            // System.out.println("undoOrRedo===============" + undoOrRedo);
            // System.out.println("ctrlKey===============" + ctrlKey);
            if (undoOrRedo) {
                // nothing
            } else if ((this.previousKey != null && alphaNumMatched && this.previousKey.alphaNumMatched) || e.character == ' ') {
                updateCommand(control);
            } else {
                addNewCommand(control);
            }
            previousText = currentText;
            this.previousKey = new Key(e, alphaNumMatched);
        }
    }

    /**
     * DOC amaumont Comment method "updateCommand".
     * 
     * @param commandStack2
     */
    public void updateCommand(Control control) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * To store previous typed key informations. <br/>
     * 
     * $Id$
     * 
     */
    class Key {

        public char character;

        public int stateMask;

        public int keyCode;

        private boolean alphaNumMatched;

        /**
         * DOC amaumont Key constructor comment.
         * 
         * @param e
         * @param alphaNumMatched
         */
        public Key(KeyEvent e, boolean alphaNumMatched) {
            this.character = e.character;
            this.stateMask = e.stateMask;
            this.keyCode = e.keyCode;
            this.alphaNumMatched = alphaNumMatched;
        }

    }

    /**
     * 
     * Implement your method by overriding.
     */
    public void addNewCommand(Control control) {

    }

    /**
     * DOC amaumont Comment method "register".
     * 
     * @param control
     */
    public void register(Control control) {
        control.addKeyListener(keyListener);
        control.addFocusListener(focusListener);
    }

    /**
     * DOC amaumont Comment method "unregister".
     * 
     * @param control
     */
    public void unregister(Control control) {
        control.removeKeyListener(keyListener);
        control.removeFocusListener(focusListener);
    }

    private void focusGainedExecute(FocusEvent e) {
        previousKey = null;
        previousText = ControlUtils.getText((Control) e.getSource());

    }

    private void focusLostExecute(FocusEvent e) {
        updateCommand((Control) e.getSource());
    }

}
