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
package org.talend.core.runtime.hd.hive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.talend.commons.utils.platform.PluginChecker;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.hadoop.IHadoopDistributionService;
import org.talend.core.model.metadata.connection.hive.HiveModeInfo;
import org.talend.core.model.metadata.connection.hive.HiveServerVersionInfo;
import org.talend.core.runtime.hd.IDistributionsManager;
import org.talend.core.runtime.hd.IHDConstants;
import org.talend.core.runtime.hd.IHDistribution;
import org.talend.core.runtime.hd.IHDistributionVersion;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class HiveMetadataHelper {

    private static IHadoopDistributionService getHadoopDistributionService() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(IHadoopDistributionService.class)) {
            return (IHadoopDistributionService) GlobalServiceRegister.getDefault().getService(IHadoopDistributionService.class);
        }
        return null;
    }

    private static IDistributionsManager getHiveDistributionsManager() {
        IHadoopDistributionService hadoopDistributionService = getHadoopDistributionService();
        if (hadoopDistributionService != null) {
            return hadoopDistributionService.getHiveDistributionManager();
        }
        return null;
    }

    private static boolean isTOPStandaloneMode() {
        return PluginChecker.isOnlyTopLoaded();
    }

    public static String[] getDistributionsDisplay() {
        List<String> distributionItems = new ArrayList<String>();
        IDistributionsManager hiveDistributionManager = getHiveDistributionsManager();
        boolean topStandaloneMode = isTOPStandaloneMode();
        if (hiveDistributionManager != null) {
            IHDistribution[] distributions = hiveDistributionManager.getDistributions();
            for (IHDistribution d : distributions) {
                if (topStandaloneMode && IHDConstants.DISTRIBUTION_HORTONWORKS.equals(d.getName())) { //$NON-NLS-1$
                    continue;
                }
                distributionItems.add(d.getDisplayName());
            }
        }
        return distributionItems.toArray(new String[distributionItems.size()]);
    }

    public static IHDistribution getDistribution(String hiveDistribution, boolean byDisplay) {
        return getDistribution(hiveDistribution, byDisplay, false);
    }

    public static IHDistribution getDistribution(String hiveDistribution, boolean byDisplay, boolean withDefault) {
        IDistributionsManager hiveDistributionManager = getHiveDistributionsManager();
        if (hiveDistributionManager != null && hiveDistribution != null) {
            IHDistribution distribution = hiveDistributionManager.getDistribution(hiveDistribution, byDisplay);
            if (withDefault && distribution == null) {
                IHDistribution[] distributions = hiveDistributionManager.getDistributions();
                if (distributions.length > 0) {
                    distribution = distributions[0];
                }
            }
            return distribution;
        }
        return null;
    }

    public static String[] getHiveModesDisplay(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        List<String> hiveModeItems = new ArrayList<String>();
        // As the embedded mode is not working for top, we need to hide some menus
        if (doSupportEmbeddedMode(hiveDistribution, hiveVersion, byDisplay) && !isTOPStandaloneMode()) {
            hiveModeItems.add(HiveModeInfo.EMBEDDED.getDisplayName());
        }
        // TODO, what mean doSupportHive1Standalone in HiveComponent?
        if (doSupportStandaloneMode(hiveDistribution, hiveVersion, byDisplay)) {
            hiveModeItems.add(HiveModeInfo.STANDALONE.getDisplayName());
        }
        return hiveModeItems.toArray(new String[0]);
    }

    public static boolean isHiveEmbeddedMode(String dbTypeDisplay, String hiveModeDisplay) {
        if (dbTypeDisplay != null && hiveModeDisplay != null
                && EDatabaseTypeName.HIVE.getDisplayName().equalsIgnoreCase(dbTypeDisplay)) {
            return HiveModeInfo.getByDisplay(hiveModeDisplay) == HiveModeInfo.EMBEDDED;
        }
        return false;
    }

    public static String[] getHiveServersDisplay(String hiveDistribution, String hiveVersion, String hiveMode, boolean byDisplay) {
        List<String> hiveServerItems = new ArrayList<String>();
        // TODO, what mean doSupportHive1Standalone in HiveComponent?
        if (doSupportHive1(hiveDistribution, hiveVersion, byDisplay)) {
            hiveServerItems.add(HiveServerVersionInfo.HIVE_SERVER_1.getDisplayName());
        }
        if (doSupportHive2(hiveDistribution, hiveVersion, byDisplay)) {
            hiveServerItems.add(HiveServerVersionInfo.HIVE_SERVER_2.getDisplayName());
        }
        return hiveServerItems.toArray(new String[0]);
    }

    /**
     * return true/false of {@link HiveComponent#doSupportHive1}.
     */
    public static boolean doSupportHive1(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        return doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportHive1");//$NON-NLS-1$
    }

    /**
     * return true/false of {@link HiveComponent#doSupportHive2}.
     */
    public static boolean doSupportHive2(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        return doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportHive2");//$NON-NLS-1$
    }

    /**
     * return true/false of {@link HiveComponent#doSupportTezForHive}.
     */
    public static boolean doSupportTez(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        return doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportTezForHive");//$NON-NLS-1$
    }

    /**
     * return true/false of {@link HiveComponent#doSupportSecurity}. also, when hive service or embedded
     */
    public static boolean doSupportSecurity(String hiveDistribution, String hiveVersion, String hiveMode,
            String hiveServerVersion, boolean byDisplay) {
        HiveModeInfo hiveModeInfo = byDisplay ? HiveModeInfo.getByDisplay(hiveMode) : HiveModeInfo.valueOf(hiveMode);
        HiveServerVersionInfo hiveServerVersionInfo = byDisplay ? HiveServerVersionInfo.getByDisplay(hiveServerVersion)
                : HiveServerVersionInfo.valueOf(hiveServerVersion);

        boolean supportSecurity = doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportSecurity");//$NON-NLS-1$
        if (supportSecurity
                && (HiveServerVersionInfo.HIVE_SERVER_2 == hiveServerVersionInfo || hiveModeInfo == HiveModeInfo.EMBEDDED)) {
            return true;
        }
        return false;
    }

    /**
     * return true/false of {@link HiveComponent#doSupportEmbeddedMode}.
     */
    public static boolean doSupportEmbeddedMode(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        return doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportEmbeddedMode");//$NON-NLS-1$
    }

    /**
     * return true/false of {@link HiveComponent#doSupportStandaloneMode}.
     */
    public static boolean doSupportStandaloneMode(String hiveDistribution, String hiveVersion, boolean byDisplay) {
        return doSupportMethod(hiveDistribution, hiveVersion, byDisplay, "doSupportStandaloneMode");//$NON-NLS-1$
    }

    public static boolean doSupportMethod(String hiveDistribution, String hiveVersion, boolean byDisplay, String supportMethodName) {
        IHDistribution distribution = getDistribution(hiveDistribution, byDisplay);
        if (distribution != null) {
            IHDistributionVersion version = distribution.getHDVersion(hiveVersion, byDisplay);
            IHadoopDistributionService hadoopDistributionService = getHadoopDistributionService();

            if (version != null && hadoopDistributionService != null) {
                Map<String, Boolean> doSupportMethods = hadoopDistributionService.doSupportMethods(version, supportMethodName);
                Boolean support = doSupportMethods.get(supportMethodName);

                return support != null && support;
            }
        }
        return false;
    }

}
