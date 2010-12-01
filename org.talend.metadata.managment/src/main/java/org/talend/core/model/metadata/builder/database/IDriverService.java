// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.metadata.builder.database;

import java.sql.Driver;

import org.talend.core.model.metadata.IMetadataConnection;

/**
 * @author zshen
 *
 */
public interface IDriverService {

    /**
     * zshen get driver by metadataConnection information.
     * 
     * @param metadataConnection contain the information which about driver.
     * @return if can't find the driver will get a null.
     */
    abstract public Driver getDriver(IMetadataConnection metadataConnection) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException;

}
