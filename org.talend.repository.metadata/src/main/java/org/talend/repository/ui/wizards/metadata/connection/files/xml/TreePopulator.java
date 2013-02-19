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
package org.talend.repository.ui.wizards.metadata.connection.files.xml;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.xerces.xs.XSModel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.xsd.XSDSchema;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.utils.RepositoryManagerHelper;
import org.talend.datatools.xml.utils.ATreeNode;
import org.talend.datatools.xml.utils.OdaException;
import org.talend.datatools.xml.utils.SchemaPopulationUtil;
import org.talend.datatools.xml.utils.XSDPopulationUtil2;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.util.CopyDeleteFileUtilForWizard;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class TreePopulator {

    private final Tree availableXmlTree;

    private final BidiMap xPathToTreeItem = new DualHashBidiMap();

    private String filePath;

    private static int limit;

    /**
     * DOC amaumont TreePopulator constructor comment.
     * 
     * @param availableXmlTree
     */
    public TreePopulator(Tree availableXmlTree) {
        super();
        this.availableXmlTree = availableXmlTree;
    }

    /**
     * populate xml tree.
     * 
     */
    public boolean populateTree(String filePath, ATreeNode treeNode) {
        return populateTree(filePath, treeNode, null);
    }

    public boolean populateTree(String filePath, ATreeNode treeNode, String selectedEntity) {
        availableXmlTree.removeAll();
        xPathToTreeItem.clear();
        if (filePath != null && !filePath.equals("")) { //$NON-NLS-1$
            String newFilePath;
            try {
                newFilePath = CopyDeleteFileUtilForWizard.copyToTemp(filePath);
            } catch (PersistenceException e1) {
                newFilePath = filePath;
            }
            List<String> attList = CopyDeleteFileUtilForWizard.getComplexNodes(filePath);
            try {
                if (attList != null && !attList.isEmpty()) {
                    treeNode = SchemaPopulationUtil.getSchemaTree(newFilePath, true, false, limit, attList);
                } else {
                    treeNode = SchemaPopulationUtil.getSchemaTree(newFilePath, true, limit);
                }
            } catch (MalformedURLException e) {
                ExceptionHandler.process(e);
            } catch (OdaException e) {
                ExceptionHandler.process(e);
            } catch (URISyntaxException e) {
                ExceptionHandler.process(e);
            } catch (Exception e) {
                return false;
            }
            if (treeNode == null || treeNode.getChildren().length == 0) {
                // OdaException ex = new OdaException(Messages.getString("dataset.error.populateXMLTree"));
                // //$NON-NLS-1$
                // ExceptionHandler.process(ex);
                return false;
            } else {
                boolean isXml = newFilePath.toUpperCase().endsWith(".XML");
                Object[] childs = treeNode.getChildren();
                if (selectedEntity != null) {
                    Object selected = null;
                    for (Object child : childs) {
                        if (child instanceof ATreeNode) {
                            if (selectedEntity.equals(((ATreeNode) child).getValue())) {
                                selected = child;
                                break;
                            }
                        }
                    }
                    if (selected == null && childs.length > 0) {
                        selected = childs[0];
                    }
                    populateTreeItems(isXml, availableXmlTree, new Object[] { selected }, 0, "", "/"); //$NON-NLS-1$
                } else {
                    populateTreeItems(isXml, availableXmlTree, childs, 0, "", "/"); //$NON-NLS-1$
                }
                this.filePath = filePath;
                return true;
            }
        }
        return false;
    }

    public boolean populateTree(XSModel xsModel, ATreeNode selectedNode, List<ATreeNode> treeNodes) {
        availableXmlTree.removeAll();
        xPathToTreeItem.clear();
        ATreeNode treeNode = null;
        if (xsModel != null) {
            try {
                treeNode = SchemaPopulationUtil.getSchemaTree(xsModel, selectedNode, true);
                if (treeNodes != null && treeNode.getChildren().length > 0) {
                    for (Object obj : treeNode.getChildren()) {
                        if (obj instanceof ATreeNode) {
                            treeNodes.add((ATreeNode) obj);
                        }
                    }
                }

            } catch (MalformedURLException e) {
                ExceptionHandler.process(e);
            } catch (OdaException e) {
                ExceptionHandler.process(e);
            } catch (URISyntaxException e) {
                ExceptionHandler.process(e);
            } catch (Exception e) {
                return false;
            }
            if (treeNode == null || treeNode.getChildren().length == 0) {
                return false;
            } else {
                Object[] childs = treeNode.getChildren();
                populateTreeItems(false, availableXmlTree, childs, 0, "", "/"); //$NON-NLS-1$
                return true;
            }
        }
        return false;
    }

    public boolean populateTree(XSDSchema schema, ATreeNode selectedNode, List<ATreeNode> treeNodes) {
        return populateTree(null, schema, selectedNode, treeNodes);
    }

    public boolean populateTree(XSDPopulationUtil2 popUtil, XSDSchema schema, ATreeNode selectedNode, List<ATreeNode> treeNodes) {
        availableXmlTree.removeAll();
        xPathToTreeItem.clear();
        ATreeNode treeNode = null;
        if (schema != null) {
            try {
                if (popUtil == null) {
                    treeNode = SchemaPopulationUtil.getSchemaTree(schema, selectedNode);
                } else {
                    treeNode = SchemaPopulationUtil.getSchemaTree(popUtil, schema, selectedNode);
                }
                if (treeNodes != null) {
                    treeNodes.add(treeNode);
                }
            } catch (MalformedURLException e) {
                ExceptionHandler.process(e);
            } catch (OdaException e) {
                ExceptionHandler.process(e);
            } catch (URISyntaxException e) {
                ExceptionHandler.process(e);
            } catch (Exception e) {
                ExceptionHandler.process(e);
                return false;
            }
            if (treeNode == null || treeNode.getChildren().length == 0) {
                return false;
            } else {
                populateTreeItems(false, availableXmlTree, new Object[] { treeNode }, 0, "", "/"); //$NON-NLS-1$
                return true;
            }
        }
        return false;
    }

    /**
     * populate tree items.
     * 
     * @param tree
     * @param node
     */
    private void populateTreeItems(boolean isXml, Object tree, Object[] node, int level, String parentPathForTreeLink,
            String parentPathToAvoidLoop) {
        level++;
        // if (level > 10) {
        // return;
        // } else {
        for (Object element : node) {
            TreeItem treeItem;
            if (tree instanceof Tree) {
                treeItem = new TreeItem((Tree) tree, 0);
            } else {
                treeItem = new TreeItem((TreeItem) tree, 0);
            }

            ATreeNode treeNode = (ATreeNode) element;
            treeItem.setData(treeNode);
            int type = treeNode.getType();
            if (type == ATreeNode.NAMESPACE_TYPE) {
                if ("".equals(treeNode.getDataType())) {
                    treeItem.setText("xmlns=" + treeNode.getLabel());
                } else {
                    treeItem.setText("xmlns:" + treeNode.getDataType() + "=" + treeNode.getLabel());
                }
                treeItem.setForeground(new Color(Display.getDefault(), new RGB(0, 130, 0)));
            } else if (type == ATreeNode.ATTRIBUTE_TYPE) {
                treeItem.setText("@" + treeNode.getLabel()); //$NON-NLS-1$
            } else {
                treeItem.setText(treeNode.getLabel());
            }
            if (!isXml && parentPathToAvoidLoop.contains("/" + treeItem.getText() + "/")) {
                treeItem.setForeground(new Color(Display.getDefault(), new RGB(255, 102, 102)));
                continue;
            }

            String currentXPathForTreeLink = parentPathForTreeLink + "/" + treeItem.getText(); //$NON-NLS-1$
            xPathToTreeItem.put(currentXPathForTreeLink, treeItem);

            String currentXPathToAvoidLoop = parentPathForTreeLink + treeItem.getText() + "/"; //$NON-NLS-1$
            if (treeNode.getChildren() != null && treeNode.getChildren().length > 0) {
                populateTreeItems(isXml, treeItem, treeNode.getChildren(), level, currentXPathForTreeLink,
                        currentXPathToAvoidLoop);
            }
            setExpanded(treeItem);
        }
        // }
    }

    private boolean haveParentWithSameType(ATreeNode item) {
        List<ATreeNode> parentList = getParentList(item);
        for (ATreeNode pNode : parentList) {
            if (item.getOriginalDataType() != null && pNode.getOriginalDataType() != null
                    && pNode.getOriginalDataType().equals(item.getOriginalDataType())) {
                return true;
            }
        }
        return false;
    }

    private List<ATreeNode> getParentList(ATreeNode item) {
        List<ATreeNode> parentList = new ArrayList<ATreeNode>();
        ATreeNode parentitem = item.getParent();
        if (parentitem == null) {
            return parentList;
        }
        parentList.add(parentitem);
        if (parentitem.getParent() != null) {
            parentList.addAll(getParentList(parentitem));
        }
        return parentList;
    }

    // expand the tree
    private void setExpanded(TreeItem treeItem) {
        // if (treeItem.getParentItem() != null) {
        // setExpanded(treeItem.getParentItem());
        // }
        treeItem.setExpanded(true);
    }

    public TreeItem getTreeItem(String absoluteXPath) {
        return (TreeItem) xPathToTreeItem.get(absoluteXPath);
    }

    public String getAbsoluteXPath(TreeItem treeItem) {
        return (String) xPathToTreeItem.getKey(treeItem);
    }

    /**
     * Getter for filePath.
     * 
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    public static int getMaximumRowsToPreview() {
        return RepositoryManagerHelper.getMaximumRowsToPreview();
    }

    /**
     * Sets the limit.
     * 
     * @param limit the limit to set
     */
    public void setLimit(int lit) {
        limit = lit;
    }

    /**
     * Getter for limit.
     * 
     * @return the limit
     */
    public static int getLimit() {
        return limit;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
