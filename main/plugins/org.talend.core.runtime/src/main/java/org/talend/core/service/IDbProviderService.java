// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.service;

import org.talend.core.IProviderService;
import org.talend.core.model.process.INode;

/**
 * created by ldong on Jan 13, 2015 Detailled comment
 * 
 */
public interface IDbProviderService extends IProviderService {

    public boolean isRedShiftNode(INode node);
}
