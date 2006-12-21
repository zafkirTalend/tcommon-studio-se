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
package org.talend.core.model.repository;

import org.talend.core.i18n.Messages;

/**
 * This enum represents all objects types that can be store in the repository.<br/> Exception is the recycle bin that
 * isn't really an object type (could think of moving it).
 * 
 * $Id$
 * 
 */
public enum ERepositoryObjectType {
    BUSINESS_PROCESS("repository.businessProcess"),
    PROCESS("repository.process"),
    ROUTINES("repository.routines"),
    SNIPPETS("repository.snippets"),
    DOCUMENTATION("repository.documentation"),
    METADATA("repository.metadata"),
    METADATA_CON_TABLE("repository.metadataTable"),
    METADATA_CON_QUERY("repository.query"),
    METADATA_CONNECTIONS("repository.metadataConnections", "repository.metadataConnections.alias"),
    METADATA_FILE_DELIMITED("repository.metadataFileDelimited", "repository.metadataFileDelimited.alias"),
    METADATA_FILE_POSITIONAL("repository.metadataFilePositional", "repository.metadataFilePositional.alias"),
    METADATA_FILE_REGEXP("repository.metadataFileRegexp", "repository.metadataFileRegexp.alias"),
    METADATA_FILE_XML("repository.metadataFileXml", "repository.metadataFileXml.alias"),
    METADATA_FILE_LDIF("repository.metadataFileLdif", "repository.metadataFileLdif.alias"),
    FOLDER("repository.folder");

    private String key;

    private String alias;

    /**
     * Constructor with the i18n key used to display the folder wich contains this object type.
     * 
     * @param key
     */
    ERepositoryObjectType(String key) {
        this.key = key;
    }

    ERepositoryObjectType(String key, String alias) {
        this(key);
        this.alias = alias;
    }

    public String toString() {
        return Messages.getString(getKey());
    }

    public String getAlias() {
        if (alias == null) {
            return null;
        }
        return Messages.getString(alias);
    }

    /**
     * Getter for key.
     * 
     * @return the key
     */
    private String getKey() {
        return this.key;
    }

    public static String getFolderName(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return "businessProcess";
        case PROCESS:
            return "process";
        case ROUTINES:
            return "code/routines";
        case DOCUMENTATION:
            return "documentations";
        case METADATA:
            return "metadata";
        case METADATA_CONNECTIONS:
            return "metadata/connections";
        case METADATA_FILE_DELIMITED:
            return "metadata/fileDelimited";
        case METADATA_FILE_POSITIONAL:
            return "metadata/filePositional";
        case METADATA_FILE_REGEXP:
            return "metadata/fileRegex";
        case METADATA_FILE_XML:
            return "metadata/fileXml";
        case METADATA_FILE_LDIF:
            return "metadata/fileLdif";
        default:
            throw new IllegalArgumentException("Folder for type " + type + " cannot be found");
        }
    }

}
