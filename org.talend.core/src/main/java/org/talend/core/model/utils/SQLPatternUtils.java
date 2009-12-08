// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

import java.util.List;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.process.IElement;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public final class SQLPatternUtils {

    public static final String ID_SEPARATOR = "--"; //$NON-NLS-1$

    public static final String SQLPATTERNLIST = "SQLPATTERNLIST"; //$NON-NLS-1$

    public static SQLPatternItem getItemFromCompoundId(IElement element, String compoundId) {
        if (!compoundId.contains(ID_SEPARATOR)) {
            throw new IllegalArgumentException();
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();

        IRepositoryObject repositoryObject = null;
        String id = compoundId.split(SQLPatternUtils.ID_SEPARATOR)[0];
        try {
            repositoryObject = factory.getLastVersion(id);
            if (repositoryObject != null) {
                return (SQLPatternItem) repositoryObject.getProperty().getItem();
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        String name = compoundId.split(SQLPatternUtils.ID_SEPARATOR)[1];
        return getSQLPatternItem(element, name);
    }

    /**
     * yzhang Comment method "getSQLPatternItem".
     * 
     * @param element
     * @param sqlpatternName
     * @return
     */
    public static SQLPatternItem getSQLPatternItem(IElement element, String sqlpatternName) {
        //        String eltNodeName = (String) element.getElementParameter("SQLPATTERN_DB_NAME").getValue(); //$NON-NLS-1$
        SQLPatternItem sqlpatternItem = null;
        try {
            List<IRepositoryObject> list = CorePlugin.getDefault().getProxyRepositoryFactory().getAll(
                    ERepositoryObjectType.SQLPATTERNS, false);
            addSQLPatternObjectInRefProject(list);
            for (IRepositoryObject repositoryObject : list) {
                SQLPatternItem item = (SQLPatternItem) repositoryObject.getProperty().getItem();
                // modify for bug 10375
                if (item.getProperty().getLabel().equals(sqlpatternName)) {
                    sqlpatternItem = item;
                    break;
                }

            }

        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        return sqlpatternItem;
    }

    private static void addSQLPatternObjectInRefProject(List<IRepositoryObject> list) {
        try {
            List<Project> referencedProjects = ProjectManager.getInstance().getReferencedProjects();
            for (Project project : referencedProjects) {
                List<IRepositoryObject> refList;
                refList = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory().getAll(project,
                        ERepositoryObjectType.SQLPATTERNS, false);
                for (IRepositoryObject repositoryObject : refList) {
                    Item item = repositoryObject.getProperty().getItem();
                    if (item instanceof SQLPatternItem) {
                        if (!((SQLPatternItem) item).isSystem()) {
                            list.add(repositoryObject);
                        }
                    }
                }
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    /**
     * yzhang Comment method "getLastVersionRepositoryObjectById".
     * 
     * @param id
     * @return
     */
    public static IRepositoryObject getLastVersionRepositoryObjectById(String id) {

        if (id == null || "".equals(id)) { //$NON-NLS-1$
            return null;
        }
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            IRepositoryObject lastVersion = factory.getLastVersion(id);
            if (lastVersion != null && factory.getStatus(lastVersion) != ERepositoryStatus.DELETED) {
                return lastVersion;
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

        return null;
    }

}
