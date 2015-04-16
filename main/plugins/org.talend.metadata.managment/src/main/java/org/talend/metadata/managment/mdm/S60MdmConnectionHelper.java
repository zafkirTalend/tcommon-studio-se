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
package org.talend.metadata.managment.mdm;

import java.io.File;
import java.util.List;

import org.apache.axis.client.Stub;
import org.talend.core.model.metadata.builder.connection.MDMConnection;

/**
 * created by wchen on Apr 15, 2015 Detailled comment
 *
 */
public class S60MdmConnectionHelper extends AbsMdmConnectionHelper {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.metadata.managment.mdm.AbsMdmConnectionHelper#checkConnection(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public Stub checkConnection(String url, String universe, String userName, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.metadata.managment.mdm.AbsMdmConnectionHelper#getPKs(org.apache.axis.client.Stub,
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<String> getPKs(Stub stub, String modelOrContainerMethod, String modelOrContainerClass) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.metadata.managment.mdm.AbsMdmConnectionHelper#initConcept(org.talend.core.model.metadata.builder.
     * connection.MDMConnection, java.io.File)
     */
    @Override
    public void initConcept(MDMConnection mdmConn, File file) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.metadata.managment.mdm.AbsMdmConnectionHelper#getXsdSchema(org.apache.axis.client.Stub,
     * java.lang.String)
     */
    @Override
    public String getXsdSchema(Stub stub, String resName) {
        // TODO Auto-generated method stub
        return null;
    }

}
