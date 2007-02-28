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
package org.talend.core.model.process;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IContextParameter {

    public String getName();

    public void setName(final String name);

    public String getPrompt();

    public void setPrompt(final String prompt);

    public String getType();

    public void setType(final String type);

    public String getValue();

    public void setValue(final String value);

    public String getComment();

    public void setComment(final String comment);

    public boolean isPromptNeeded();

    public void setPromptNeeded(boolean promptNeeded);

    public IContextParameter clone();

    public boolean sameAs(IContextParameter contextParam);
}
