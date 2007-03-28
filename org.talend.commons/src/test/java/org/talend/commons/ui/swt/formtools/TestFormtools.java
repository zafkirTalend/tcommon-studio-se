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
package org.talend.commons.ui.swt.formtools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Test org.talend.commons.ui.swt.formtools<br/>.
 * 
 * $Id$
 * 
 */
public final class TestFormtools {

    /**
     * Default Constructor. Must not be used.
     */
    private TestFormtools() {
    }

    private static final String WINDOWTITLE = "Test org.talend.commons.ui.swt.formtools";

    // private static Label statusLabel;

    private static Display display;

    @SuppressWarnings("unused")
    private static Button sampleButton;

    @SuppressWarnings("unused")
    private static Text sampleText;

    @SuppressWarnings("unused")
    private static Combo sampleCombo;

    /**
     * m�thode principale.
     * 
     * @param args
     */
    public static void main(String[] args) {

        // The Shell
        display = new Display();
        Shell shell = new Shell(display);
        shell.setText(WINDOWTITLE);
        shell.setLayout(new FillLayout());

        // Main Layout
        Composite composite = new Composite(shell, SWT.BORDER);
        composite.setLayout(new FillLayout());
        GridLayout gridLayout = new GridLayout();
        composite.setLayout(gridLayout);

        // Utils : createStatusBar, startNewGridLayout, createGroup

        // use to SWTform / not use to Wizard

        Composite composite1 = Form.startNewGridLayout(composite, 1);

        Group group = Form.createGroup(composite1, 2, "Group Sample");
        Composite compositeGroup = Form.startNewGridLayout(group, 2);

        LabelledText nameText = new LabelledText(compositeGroup, "Name");
        nameText.setEditable(false);

        String[] itemTable = { "option1", "option2" };
        LabelledCombo tableCombo = new LabelledCombo(compositeGroup, "Table", "Aide � la saisie", itemTable);

        String[] extensions = { "*.mdb" };
        LabelledFileField file = new LabelledFileField(compositeGroup, "Mdb File", extensions);

        // Button Check
        Composite compositeCheckButton = Form.startNewGridLayout(composite1, 1, false, SWT.CENTER, SWT.CENTER);
        UtilsButton checkButton = new UtilsButton(compositeCheckButton, "C&heck", false);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    /**
     * updateStatus.
     * 
     * @param String
     */
    // private static void updateStatus(String string) {
    // if (string != null) {
    // statusLabel.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
    // statusLabel.setText(string);
    // } else {
    // statusLabel.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    // statusLabel.setText("");
    // }
    // }
}
