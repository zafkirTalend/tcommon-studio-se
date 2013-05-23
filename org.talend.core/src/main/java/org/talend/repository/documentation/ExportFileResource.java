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
package org.talend.repository.documentation;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.repository.model.RepositoryNode;

/**
 * 
 * DOC Administrator class global comment. Detailled comment <br/>
 * 
 * $Id: ExportFileResource.java 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
/**
 * DOC class global comment. Detailled comment <br/>
 * 
 * $Id: ExportFileResource.java 1 2007-1-30下午05:16:53 +0000 ylv $
 * 
 */
public class ExportFileResource {

    private Item item;

    private String directoryName;

    private RepositoryNode node;

    // string is the relative path of the directoryName, where to store the resources in List.
    private Map<String, Set<URL>> map = new HashMap<String, Set<URL>>();

    public String getDirectoryName() {
        return directoryName;
    }

    /**
     * ftang ExportFileResource constructor comment.
     */
    public ExportFileResource() {
        // super();
        // this.item = null;
        // this.directoryName = "";
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    /**
     * Adds the resource list to this.
     * 
     * @param relativePath relative Path
     * @param resources
     */
    public void addResources(String relativePath, List<URL> resources) {
        Set<URL> storeList = map.get(relativePath);
        if (storeList == null) {
            storeList = new HashSet<URL>(resources);
            map.put(relativePath, storeList);
        } else {
            storeList.addAll(resources);
        }
    }

    /**
     * DOC ycbai Comment method "addResource".
     * 
     * @param relativePath
     * @param resource
     */
    public void addResource(String relativePath, URL resource) {
        Set<URL> storeList = map.get(relativePath);
        if (storeList == null) {
            storeList = new HashSet<URL>();
            map.put(relativePath, storeList);
        }
        storeList.add(resource);
    }

    public void removeResources(String relativePath, URL resource) {
        Set<URL> storeList = map.get(relativePath);
        if (storeList == null) {
            return;
        } else {
            storeList.remove(resource);
        }
    }

    public void addResources(List<URL> resources) {
        addResources("", resources); //$NON-NLS-1$
    }

    public Item getItem() {
        return item;
    }

    public void setProcess(ProcessItem process) {
        this.item = process;
    }

    public ExportFileResource(Item item, String directoryName) {
        super();
        this.item = item;
        this.directoryName = directoryName;
    }

    /**
     * Answer the total number of file resources that exist at or below self in the resources hierarchy.
     * 
     * @return int
     * @param checkResource org.eclipse.core.resources.IResource
     */
    protected int countChildrenOf(String checkResource) throws CoreException {

        File file = new File(checkResource);

        if (file.isFile()) {
            return 1;
        }

        int count = 0;

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) {
                count += countChildrenOf(children[i].getPath());
            }
        }
        return count;
    }

    public Set<String> getRelativePathList() {
        return map.keySet();
    }

    public Set<URL> getResourcesByRelativePath(String path) {
        return map.get(path);
    }

    public Collection<Set<URL>> getAllResources() {
        return map.values();
    }

    public void removeAllMap() {
        this.map = new HashMap<String, Set<URL>>();
    }

    /**
     * Gets the count of the files.
     * 
     * @return
     */
    public int getFilesCount() throws CoreException {
        Set<String> paths = getRelativePathList();
        int result = 0;
        for (Iterator iter = paths.iterator(); iter.hasNext();) {
            String path = (String) iter.next();
            Set<URL> resource = getResourcesByRelativePath(path);
            for (Iterator iterator = resource.iterator(); iterator.hasNext();) {
                URL url = (URL) iterator.next();
                result += countChildrenOf(url.getPath());
            }
        }
        return result;
    }

    /**
     * Getter for node.
     * 
     * @return the node
     */
    public RepositoryNode getNode() {
        return this.node;
    }

    /**
     * Sets the node.
     * 
     * @param node the node to set
     */
    public void setNode(RepositoryNode node) {
        this.node = node;
    }
}
