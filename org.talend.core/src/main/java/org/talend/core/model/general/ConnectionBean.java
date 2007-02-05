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

import java.util.HashMap;
import java.util.Map;

import org.talend.core.i18n.Messages;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public class ConnectionBean implements Cloneable {

    private static final String DYN_FIELDS_SEPARATOR = "="; //$NON-NLS-1$

    private static final String FIELDS_SEPARATOR = "#"; //$NON-NLS-1$

    private String repositoryId;

    private String name;

    private String description;

    private String user;

    private String password;

    private Map<String, String> dynamicFields = new HashMap<String, String>();

    private boolean complete;

    /**
     * DOC smallet ConnectionBean constructor comment.
     */
    public ConnectionBean() {
        super();
    }

    public static ConnectionBean getDefaultConnectionBean() {
        ConnectionBean newConnection = new ConnectionBean();
        newConnection.setName("Local"); //$NON-NLS-1$
        newConnection.setDescription("Default connection"); //$NON-NLS-1$
        newConnection.setRepositoryId("local"); //$NON-NLS-1$
        newConnection.setPassword(""); //$NON-NLS-1$
        newConnection.setUser("your@userName.here"); //$NON-NLS-1$
        return newConnection;
    }

    public String getRepositoryId() {
        return this.repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * Getter for description.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for password.
     * 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for user.
     * 
     * @return the user
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Sets the user.
     * 
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, String> getDynamicFields() {
        return this.dynamicFields;
    }

    public void setDynamicFields(Map<String, String> dynamicFields) {
        this.dynamicFields = dynamicFields;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public ConnectionBean clone() throws CloneNotSupportedException {
        ConnectionBean object = (ConnectionBean) super.clone();
        object.setDynamicFields(new HashMap<String, String>(this.dynamicFields));
        return object;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer("Repository:" + getRepositoryId() + ", Name:" + getName() //$NON-NLS-1$ //$NON-NLS-2$
                + ", Desription:" + getDescription() + ", User:" + getUser() + ", Password:" + getPassword() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + ", Complete:" + isComplete()); //$NON-NLS-1$
        string.append(", Dyn:").append(dynamicFields); //$NON-NLS-1$
        return string.toString();
    }

    public String readToString() {
        StringBuffer fields = new StringBuffer(getRepositoryId() + FIELDS_SEPARATOR + getName() + FIELDS_SEPARATOR
                + getDescription() + FIELDS_SEPARATOR + getUser() + FIELDS_SEPARATOR + getPassword() + FIELDS_SEPARATOR
                + isComplete());

        if (dynamicFields.size() > 0) {
            for (String current : dynamicFields.keySet()) {
                fields.append(FIELDS_SEPARATOR);
                fields.append(current + DYN_FIELDS_SEPARATOR + dynamicFields.get(current));
            }
        }

        return fields.toString();
    }

    public static ConnectionBean writeFromString(String s) {
        ConnectionBean toReturn = new ConnectionBean();
        try {
            String[] st = s.split(FIELDS_SEPARATOR, -1);
            int i = 0;
            toReturn.setRepositoryId(st[i++]);
            toReturn.setName(st[i++]);
            toReturn.setDescription(st[i++]);
            toReturn.setUser(st[i++]);
            toReturn.setPassword(st[i++]);
            toReturn.setComplete(new Boolean(st[i++]));
            while (i < st.length) {
                String[] st2 = st[i++].split(DYN_FIELDS_SEPARATOR, -1);
                toReturn.getDynamicFields().put(st2[0], st2[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    // public static void main(String[] args) {
    // ConnectionBean tt = new ConnectionBean();
    // tt.setName("tagada");
    // System.out.println(tt);
    // tt.setDescription("MyDesc");
    // tt.setUser("smallet@talend.com");
    // tt.setPassword("");
    // tt.getDynamicFields().put("DbLogin", "root");
    // tt.getDynamicFields().put("DbPassword", "toor");
    // String test2 = tt.readToString();
    // ConnectionBean bean2 = writeFromString(test2);
    // System.out.println(bean2 + " (" + test2 + ")");
    // }
}
