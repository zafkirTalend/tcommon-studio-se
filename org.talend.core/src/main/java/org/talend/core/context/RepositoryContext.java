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
package org.talend.core.context;

import org.talend.core.model.general.Project;
import org.talend.core.model.properties.User;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class RepositoryContext {

    private String server;

    private Integer port;

    private String context;

    private String dbLogin;

    private String dbPassword;

    private User user;

    private Project project;

    /**
     * DOC smallet RepositoryContext constructor comment.
     * 
     * @param server
     * @param port
     * @param context
     * @param login
     * @param password
     */
    public RepositoryContext(String server, Integer port, String context, User user) {
        super();
        this.server = server;
        this.port = port;
        this.context = context;
        this.user = user;
    }

    /**
     * Getter for context.
     * 
     * @return the context
     */
    public String getContext() {
        return this.context;
    }

    /**
     * Sets the context.
     * 
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Getter for port.
     * 
     * @return the port
     */
    public Integer getPort() {
        return this.port;
    }

    /**
     * Sets the port.
     * 
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Getter for server.
     * 
     * @return the server
     */
    public String getServer() {
        return this.server;
    }

    /**
     * Sets the server.
     * 
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Getter for user.
     * 
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Sets the user.
     * 
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for project.
     * 
     * @return the project
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Sets the project.
     * 
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.context == null) ? 0 : this.context.hashCode());
        result = prime * result + ((this.user == null) ? 0 : this.user.hashCode());
        result = prime * result + ((this.port == null) ? 0 : this.port.hashCode());
        result = prime * result + ((this.project == null) ? 0 : this.project.hashCode());
        result = prime * result + ((this.server == null) ? 0 : this.server.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepositoryContext other = (RepositoryContext) obj;
        if (this.context == null) {
            if (other.context != null) {
                return false;
            }
        } else if (!this.context.equals(other.context)) {
            return false;
        }
        if (this.user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!this.user.equals(other.user)) {
            return false;
        }
        if (this.port == null) {
            if (other.port != null) {
                return false;
            }
        } else if (!this.port.equals(other.port)) {
            return false;
        }
        if (this.project == null) {
            if (other.project != null) {
                return false;
            }
        } else if (!this.project.equals(other.project)) {
            return false;
        }
        if (this.server == null) {
            if (other.server != null) {
                return false;
            }
        } else if (!this.server.equals(other.server)) {
            return false;
        }
        return true;
    }

    /**
     * Getter for dbLogin.
     * 
     * @return the dbLogin
     */
    public String getDbLogin() {
        return this.dbLogin;
    }

    /**
     * Sets the dbLogin.
     * 
     * @param dbLogin the dbLogin to set
     */
    public void setDbLogin(String dbLogin) {
        this.dbLogin = dbLogin;
    }

    /**
     * Getter for dbPassword.
     * 
     * @return the dbPassword
     */
    public String getDbPassword() {
        return this.dbPassword;
    }

    /**
     * Sets the dbPassword.
     * 
     * @param dbPassword the dbPassword to set
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

}
