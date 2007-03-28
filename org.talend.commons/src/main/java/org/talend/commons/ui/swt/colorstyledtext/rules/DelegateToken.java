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
package org.talend.commons.ui.swt.colorstyledtext.rules;

import org.eclipse.jface.util.Assert;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Rule;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Type;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class DelegateToken extends CToken {

    protected Rule delegate;

    protected String end;

    protected boolean consumed;

    public DelegateToken(Type type, Rule delegate, String end) {
        super(type);
        Assert.isNotNull(delegate);
        this.delegate = delegate;
        this.end = end;
        consumed = false;
    }

    public Object getData() {
        return delegate.getName() + super.getData();
    }

    public String getEnd() {
        return end;
    }
}
