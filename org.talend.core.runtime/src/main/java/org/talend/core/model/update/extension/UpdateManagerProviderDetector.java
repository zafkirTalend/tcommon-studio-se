// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.update.extension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.update.IUpdateItemType;
import org.talend.core.model.update.UpdateResult;

/**
 * DOC ggu class global comment. Detailled comment
 */
public enum UpdateManagerProviderDetector {
    INSTANCE;

    private UpdateManagerProviderReader reader = new UpdateManagerProviderReader();

    public IUpdateItemType[] getAllUpdateItemTypes() {
        return reader.getExtensionUpdateItemTypes().values().toArray(new IUpdateItemType[0]);
    }

    public IUpdateItemType getUpdateItemType(String name) {
        return reader.getExtensionUpdateItemTypes().get(name);
    }

    /**
     * 
     * DOC ggu Comment method "retrieveProcessUpdateResults".
     * 
     * @param process check the process to detect the different.
     * @param type
     * @return
     */
    public List<UpdateResult> retrieveProcessUpdateResults(IProcess process, IUpdateItemType type) {
        List<UpdateResult> results = new ArrayList<UpdateResult>();
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            if (provider.validate(type)) {
                List<UpdateResult> processResults = provider.retrieveUpdateResults(process);
                if (processResults != null) {
                    results.addAll(processResults);
                }
            }
        }
        return results;
    }

    /**
     * 
     * DOC ggu Comment method "getDisplayImage".
     * 
     * mainly for label provider of update manager dialog.
     * 
     * @param type
     * @return
     */
    public Object getDisplayImage(Object element, int columnIndex) {
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            Object displayImage = provider.getDisplayImage(element, columnIndex);
            if (displayImage != null) {
                return displayImage;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu Comment method "getDisplayText".
     * 
     * mainly for label provider of update manager dialog.
     * 
     * @param element
     * @param columnIndex
     * @return
     */
    public String getDisplayText(Object element, int columnIndex) {
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            String displayText = provider.getDisplayText(element, columnIndex);
            if (displayText != null) {
                return displayText;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu Comment method "getDisplayCategory".
     * 
     * mainly for label provider of update manager dialog.
     * 
     * @param result
     * @return
     */
    public String getDisplayCategory(UpdateResult result) {
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            String displayLabel = provider.getDisplayCategory(result);
            if (displayLabel != null) {
                return displayLabel;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu Comment method "getResultName".
     * 
     * the label of result.
     * 
     * @param result
     * @return
     */
    public String getResultName(UpdateResult result) {
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            String displayLabel = provider.getResultName(result);
            if (displayLabel != null) {
                return displayLabel;
            }
        }
        return null;
    }

    /**
     * 
     * DOC ggu Comment method "doUpdate".
     * 
     * @param monitor
     * @param result
     * @return
     */
    public boolean doUpdate(IProgressMonitor monitor, UpdateResult result) {
        boolean updated = false;
        if (result != null) {
            IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
            for (IProcessUpdateManagerProvider provider : processProviders) {
                updated = updated || provider.doUpdate(monitor, result);
            }
        }
        return updated;
    }

    /**
     * 
     * DOC ggu Comment method "needRefreshRelatedViews".
     * 
     * @param results
     * @return
     */
    public Set<String> needRefreshRelatedViews(List<UpdateResult> results) {
        Set<String> viewIds = new HashSet<String>();
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            Set<String> viewSet = provider.needRefreshRelatedViews(results);
            if (viewSet != null) {
                viewIds.addAll(viewSet);
            }
        }
        return viewIds;
    }

    /**
     * 
     * DOC ggu Comment method "postUpdate".
     * 
     * after update, will do something.
     * 
     * @param results
     */
    public void postUpdate(List<UpdateResult> results) {
        IProcessUpdateManagerProvider[] processProviders = reader.getProcessProviders();
        for (IProcessUpdateManagerProvider provider : processProviders) {
            provider.postUpdate(results);
        }
    }

    /**
     * 
     * DOC ggu Comment method "validateAction".
     * 
     * Worked for DetecteViewImpactAction
     * 
     * @param viewer
     * @param selection
     * @return
     */
    public boolean validateAction(TreeViewer viewer, IStructuredSelection selection) {
        IRepositoryUpdateManagerProvider[] repositoryProviders = reader.getRepositoryProviders();
        for (IRepositoryUpdateManagerProvider provider : repositoryProviders) {
            if (provider.validateAction(viewer, selection)) { // if one is valid, will enable this action.
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * DOC ggu Comment method "updateForRepository".
     * 
     * Worked for DetecteViewImpactAction
     * 
     * @param node
     * @return
     */
    public boolean updateForRepository(Object node, boolean needConfirm) {
        boolean updated = false;
        IRepositoryUpdateManagerProvider[] repositoryProviders = reader.getRepositoryProviders();
        for (IRepositoryUpdateManagerProvider provider : repositoryProviders) {
            // if one upate successfully, will return true.
            updated = updated || provider.updateForRepository(node, needConfirm);
        }
        return updated;
    }
}
