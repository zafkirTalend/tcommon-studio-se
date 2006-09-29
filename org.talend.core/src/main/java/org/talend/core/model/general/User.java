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
package org.talend.core.model.general;

import org.talend.core.model.properties.PropertiesFactory;

/**
 * Manages users.<br/>
 * 
 * TODO Code this class !!
 * 
 * $Id$
 * 
 */
public class User {

    private org.talend.core.model.properties.User user;

    public User() {
        user = PropertiesFactory.eINSTANCE.createUser();
    }

    public User(org.talend.core.model.properties.User user) {
        this.user = user;
    }

    public User(String login) {
        this();
        user.setLogin(login);
    }

    public User(String login, String password) {
        this(login);
        user.setPassword(password);
    }

    public String toString() {
        return user.getLogin();
    }

    public String getLogin() {
        return this.user.getLogin();
    }

    public org.talend.core.model.properties.User getEmfUser() {
        return user;
    }
}
