// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model.nodes;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public interface IProjectRepositoryNode {

    /**
     * Getter for codeNode.
     * 
     * @return the codeNode
     */
    public RepositoryNode getCodeNode();

    /**
     * Getter for processNode.
     * 
     * @return the processNode
     */
    public RepositoryNode getProcessNode();

    /**
     * Getter for metadataConNode.
     * 
     * @return the metadataConNode
     */
    public RepositoryNode getMetadataConNode();

    /**
     * Getter for metadataFileNode.
     * 
     * @return the metadataFileNode
     */
    public RepositoryNode getMetadataFileNode();

    /**
     * Getter for metadataFilePositionalNode.
     * 
     * @return the metadataFilePositionalNode
     */
    public RepositoryNode getMetadataFilePositionalNode();

    /**
     * Getter for metadataFileRegexpNode.
     * 
     * @return the metadataFileRegexpNode
     */
    public RepositoryNode getMetadataFileRegexpNode();

    /**
     * Getter for metadataFileXmlNode.
     * 
     * @return the metadataFileXmlNode
     */
    public RepositoryNode getMetadataFileXmlNode();

    /**
     * Getter for metadataFileLdifNode.
     * 
     * @return the metadataFileLdifNode
     */
    public RepositoryNode getMetadataFileLdifNode();

    /**
     * Getter for metadataLDAPSchemaNode.
     * 
     * @return the metadataLDAPSchemaNode
     */
    public RepositoryNode getMetadataLDAPSchemaNode();

    /**
     * Getter for metadataWSDLSchemaNode.
     * 
     * @return the metadataWSDLSchemaNode
     */
    public RepositoryNode getMetadataWSDLSchemaNode();

    /**
     * Getter for metadataFileExcelNode.
     * 
     * @return the metadataFileExcelNode
     */
    public RepositoryNode getMetadataFileExcelNode();

    /**
     * Getter for metadataSalesforceSchemaNode.
     * 
     * @return the metadataSalesforceSchemaNode
     */
    public RepositoryNode getMetadataSalesforceSchemaNode();

    /**
     * Getter for jobletNode.
     * 
     * @return the jobletNode
     */
    public RepositoryNode getJobletNode();

    /**
     * Getter for metadataNode.
     * 
     * @return the metadataNode
     */
    public RepositoryNode getMetadataNode();

    /**
     * Getter for metadataGenericSchemaNode.
     * 
     * @return the metadataGenericSchemaNode
     */
    public RepositoryNode getMetadataGenericSchemaNode();

    /**
     * Getter for project.
     * 
     * @return the project
     */
    public org.talend.core.model.general.Project getProject();

    public boolean isMainProject();

    public RepositoryNode getRootRepositoryNode(ERepositoryObjectType type);

    public RepositoryNode getRecBinNode();
}
