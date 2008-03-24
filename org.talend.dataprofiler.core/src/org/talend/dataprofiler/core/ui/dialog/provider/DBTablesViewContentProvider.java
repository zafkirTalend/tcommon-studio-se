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
package org.talend.dataprofiler.core.ui.dialog.provider;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.talend.dataprofiler.core.helper.EObjectHelper;
import org.talend.dataprofiler.core.helper.FileResourceMapHelper;
import org.talend.dataprofiler.core.ui.views.provider.MNComposedAdapterFactory;
import orgomg.cwm.resource.relational.NamedColumnSet;

/**
 * @author rli
 * 
 */
public class DBTablesViewContentProvider extends AdapterFactoryContentProvider {

    private static Logger log = Logger.getLogger(DBTablesViewContentProvider.class);

    /**
     * @param adapterFactory
     */
    public DBTablesViewContentProvider() {
        super(MNComposedAdapterFactory.getAdapterFactory());
    }

    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IContainer) {
            try {
                return ((IContainer) parentElement).members();
            } catch (CoreException e) {
                log.error("Can't get the children of container:" + ((IContainer) parentElement).getLocation());
            }
        } else if (parentElement instanceof IFile) {
            parentElement = FileResourceMapHelper.get((IFile) parentElement);
        } else if (parentElement instanceof NamedColumnSet) {
                return null;
        }
        return super.getChildren(parentElement);
    }

    public Object[] getElements(Object object) {
        return this.getChildren(object);
    }

    public Object getParent(Object element) {
        if (element instanceof IContainer) {
            return ((IContainer) element).getParent();
        }
        return super.getParent(element);
    }

    public boolean hasChildren(Object element) {
        return !(element instanceof NamedColumnSet);
    }

}
