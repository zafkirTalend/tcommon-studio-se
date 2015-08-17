// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.hadoop.api.distribution.mapr;

import org.talend.core.hadoop.api.components.HBaseComponent;
import org.talend.core.hadoop.api.components.HDFSComponent;
import org.talend.core.hadoop.api.components.MRComponent;
import org.talend.core.hadoop.api.components.PigComponent;
import org.talend.core.hadoop.api.components.SqoopComponent;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;

/**
 * created by rdubois on 11 août 2015 Detailled comment
 *
 */
public class MapR200Distribution extends AbstractMapRDistribution implements HDFSComponent, MRComponent, HBaseComponent,
        SqoopComponent, PigComponent {

    public MapR200Distribution(EHadoopVersion4Drivers version) {
        this.version = version;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return false;
    }

    @Override
    public boolean doSupportSequenceFileShortType() {
        return false;
    }

    @Override
    public boolean doSupportCrossPlatformSubmission() {
        return false;
    }

    @Override
    public boolean doSupportNewHBaseAPI() {
        return false;
    }

    @Override
    public boolean doJavaAPISupportStorePasswordInFile() {
        return false;
    }

    @Override
    public boolean doSupportHCatalog() {
        return false;
    }

    @Override
    public boolean pigVersionPriorTo_0_12() {
        // return false because this distribution doesn't support HCatalog.
        return false;
    }

    @Override
    public boolean doSupportTez() {
        return false;
    }

    @Override
    public boolean doSupportHBase() {
        return true;
    }
}
