// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.nexus;

/**
 * created by wchen on 2015-5-12 Detailled comment
 *
 */
public class NexusServerBean {

    String server;

    String userName;

    String password;

    String repositoryId;

    boolean official;

    String snapshotRepId;

    String repositoryBaseURI;

    public NexusServerBean() {
    }

    public NexusServerBean(Boolean official) {
        this.official = official;
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
     * Getter for userName.
     * 
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the userName.
     * 
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * Getter for repositoryId.
     * 
     * @return the repositoryId
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the repositoryId.
     * 
     * @param repositoryId the repositoryId to set
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    /**
     * Getter for snapshotRepId.
     * 
     * @return the snapshotRepId
     */
    public String getSnapshotRepId() {
        return this.snapshotRepId;
    }

    /**
     * Sets the snapshotRepId.
     * 
     * @param snapshotRepId the snapshotRepId to set
     */
    public void setSnapshotRepId(String snapshotRepId) {
        this.snapshotRepId = snapshotRepId;
    }

    /**
     * Getter for official.
     * 
     * @return the official
     */
    public boolean isOfficial() {
        return this.official;
    }

    /**
     * Sets the official.
     * 
     * @param official the official to set
     */
    public void setOfficial(boolean official) {
        this.official = official;
    }

    /**
     * Getter for repositoryBaseURI.
     * 
     * @return the repositoryBaseURI
     */
    public String getRepositoryBaseURI() {
        return repositoryBaseURI;
    }

    /**
     * Sets the repositoryBaseURI.
     * 
     * @param repositoryBaseURI the repositoryBaseURI to set
     */
    public void setRepositoryBaseURI(String repositoryBaseURI) {
        this.repositoryBaseURI = repositoryBaseURI;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((server == null) ? 0 : server.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((repositoryId == null) ? 0 : repositoryId.hashCode());
        result = prime * result + ((snapshotRepId == null) ? 0 : snapshotRepId.hashCode());
        result = prime * result + ((repositoryBaseURI == null) ? 0 : repositoryBaseURI.hashCode());
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
        if (!(obj instanceof NexusServerBean)) {
            return false;
        }
        NexusServerBean other = (NexusServerBean) obj;
        if (server == null) {
            if (other.server != null) {
                return false;
            }
        } else if (!server.equals(other.server)) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (repositoryId == null) {
            if (other.repositoryId != null) {
                return false;
            }
        } else if (!repositoryId.equals(other.repositoryId)) {
            return false;
        }

        if (snapshotRepId == null) {
            if (other.snapshotRepId != null) {
                return false;
            }
        } else if (!snapshotRepId.equals(other.snapshotRepId)) {
            return false;
        }
        if (repositoryBaseURI == null) {
            if (other.repositoryBaseURI != null) {
                return false;
            }
        } else if (!repositoryBaseURI.equals(other.repositoryBaseURI)) {
            return false;
        }

        return true;

    }
}
