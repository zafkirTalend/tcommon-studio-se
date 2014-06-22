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
package org.talend.core.model.repository;

import org.eclipse.jface.viewers.Viewer;
import org.talend.core.model.properties.Property;

/**
 * created by wchen on 2013-8-22 Detailled comment
 * 
 */
public interface IExtendedNodeHandler {

    public Property getProperty(Object nodeObject);

    public boolean isExportEnable(Object nodeObject);

    public boolean exportFilter(Viewer viewer, Object parentElement, Object element);

}
