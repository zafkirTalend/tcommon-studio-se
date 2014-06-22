// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.hadoop;

import java.util.List;
import java.util.Map;

import org.talend.core.IService;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.IRepositoryNode;

/**
 * created by ycbai on 2013-1-28 Detailled comment
 * 
 */
public interface IHadoopClusterService extends IService {

    /**
     * DOC ycbai Comment method "containedByCluster".
     * 
     * Estimate whether or not the hadoopConnection belongs to the hadoopClusterConnection.
     * 
     * @param hadoopClusterConnection
     * @param hadoopConnection
     * @return
     */
    public boolean containedByCluster(Connection hadoopClusterConnection, Connection hadoopConnection);

    /**
     * DOC ycbai Comment method "getHadoopClusterType".
     * 
     * Get the hadoop cluster repository type.
     * 
     * @return
     */
    public ERepositoryObjectType getHadoopClusterType();

    /**
     * DOC ycbai Comment method "isHadoopClusterNode".
     * 
     * Estimate whether or not the node is a hadoop cluster node.
     * 
     * @param node
     * @return
     */
    public boolean isHadoopClusterNode(IRepositoryNode node);

    /**
     * DOC ycbai Comment method "isHadoopSubnode".
     * 
     * Estimate whether or not the node is a subnode of a hadoop cluster.
     * 
     * @param node
     * @return
     */
    public boolean isHadoopSubnode(IRepositoryNode node);

    /**
     * 
     * DOC ycbai Comment method "isHadoopFolderNode".
     * 
     * Estimate whether or not the node is a folder node of a hadoop cluster.
     * 
     * @param node
     * @return
     */
    public boolean isHadoopFolderNode(IRepositoryNode node);

    /**
     * DOC ycbai Comment method "isHadoopClusterItem".
     * 
     * Estimate whether or not the item is a hadoop cluster item.
     * 
     * @param item
     * @return
     */
    public boolean isHadoopClusterItem(Item item);

    /**
     * DOC ycbai Comment method "isHadoopSubItem".
     * 
     * Estimate whether or not the item is a subitem of a hadoop cluster item.
     * 
     * @param item
     * @return
     */
    public boolean isHadoopSubItem(Item item);

    /**
     * DOC ycbai Comment method "isValidHadoopSubItem".
     * 
     * Estimate whether or not the item is a valid subitem of a hadoop cluster item.
     * 
     * @param item
     * @return
     */
    public boolean isValidHadoopSubItem(Item item);

    /**
     * DOC ycbai Comment method "getSubitemIdsOfHadoopCluster".
     * 
     * Get subitem ids of hadoop cluster.
     * 
     * @param item hadoop cluster item
     * @return
     */
    public List<String> getSubitemIdsOfHadoopCluster(Item item);

    /**
     * DOC ycbai Comment method "refreshCluster".
     * 
     * Refresh this Hadoop Cluster.
     * 
     * @param clusterId
     */
    public void refreshCluster(String clusterId);

    /**
     * DOC ycbai Comment method "getHadoopDbParameters".
     * 
     * Get db connection(like hbase, hive) parameters by hadoop cluster.
     * 
     * @param clusterId
     * @return
     */
    public Map<String, String> getHadoopDbParameters(String clusterId);

    public void removeHadoopDbParameters(DatabaseConnection connection);

}
