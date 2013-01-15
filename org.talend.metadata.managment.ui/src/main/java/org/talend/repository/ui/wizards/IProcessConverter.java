// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.ui.wizards;

import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.repository.IRepositoryViewObject;

/**
 * This interface is just for the abstract process who wants to convert "process" to other processes. Created by Marvin
 * Wang on Jan 14, 2013.
 */
public interface IProcessConverter {

    boolean isNeedConvert();

    void doConvert(IRepositoryViewObject object) throws PersistenceException, BusinessException;
}
