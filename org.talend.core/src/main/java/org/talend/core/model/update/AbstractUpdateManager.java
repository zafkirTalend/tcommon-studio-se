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
package org.talend.core.model.update;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * ggu class global comment. Detailled comment
 * 
 * update manager for the jobs / joblets.
 */
public abstract class AbstractUpdateManager implements IUpdateManager {

    private List<UpdateResult> updatedResultList = new ArrayList<UpdateResult>();

    // for joblet changed
    private List<PropertyChangeEvent> nodesChangerList = new ArrayList<PropertyChangeEvent>();

    /**
     * ggu AbstractUpdateManager constructor comment.
     * 
     * @param process
     */
    public AbstractUpdateManager() {
        super();
    }

    public List<PropertyChangeEvent> getNodePropertyChanger() {
        return this.nodesChangerList;
    }

    /**
     * 
     * ggu Comment method "clearResult". clear all result .
     */
    public void clearResult() {
        getUpdatesNeeded().clear();
    }

    public List<UpdateResult> getUpdatesNeeded() {
        return this.updatedResultList;
    }

    public boolean isUpdatedNeeded(EUpdateItemType type) {
        if (type == null) {
            return false;
        }
        for (UpdateResult result : getUpdatesNeeded()) {
            if (result.getUpdateType() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * ggu Comment method "addNodesPropertyChanger".
     * 
     * for joblet updated.
     */
    public void addNodesPropertyChanger(PropertyChangeEvent event) {
        nodesChangerList.add(event);
    }

    public boolean update(EUpdateItemType type) {
        return executeUpdates(getUpdatesNeeded(type));
    }

    @SuppressWarnings("unchecked")
    public void checkAllModification() {
        clearResult();
        for (EUpdateItemType type : EUpdateItemType.values()) {
            List<UpdateResult> result = getUpdatesNeeded(type);
            if (result != null) {
                getUpdatesNeeded().addAll(result);
            }
        }
    }

    /**
     * udpate all difference.
     */
    public boolean updateAll() {
        checkAllModification();
        return executeUpdates();
    }

    /**
     * execute Updates.
     */
    public boolean executeUpdates() {
        return executeUpdates(getUpdatesNeeded());
    }

}
