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
package org.talend.commons.ui.swt.formtools;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class LabelText {

    private Label label;

    private Text text;

    /**
     * DOC smallet LabelText constructor comment.
     * 
     * @param label
     * @param text
     */
    public LabelText(Label label, Text text) {
        super();
        this.label = label;
        this.text = text;
    }

    public String getText() {
        return this.text.getText();
    }

    public void setText(String string) {
        this.text.setText(string);
    }

    public void setVisible(boolean visible) {
        this.label.setVisible(visible);
        this.text.setVisible(visible);
    }

    public void addModifyListener(ModifyListener listener) {
        this.text.addModifyListener(listener);
    }

    public void removeModifyListener(ModifyListener listener) {
        this.text.removeModifyListener(listener);
    }

    public String getLabel() {
        return this.label.getText();
    }

}
