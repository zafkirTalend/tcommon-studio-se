package org.talend.core.model.repository;

import org.eclipse.jface.viewers.ViewerFilter;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.nodes.IProjectRepositoryNode;

/**
 * created by ycbai on 2013-3-4 Detailled comment
 * 
 * Move from RepositoryReviewDialog.ITypeProcessor to expand its use.
 * 
 */
public interface IRepositoryTypeProcessor {

    public boolean isSelectionValid(RepositoryNode node);

    public IRepositoryNode getInputRoot(IProjectRepositoryNode projectRepoNode);

    public ViewerFilter makeFilter();

    public String getDialogTitle();

}