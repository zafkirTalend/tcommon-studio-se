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
package org.talend.core.model.metadata.connection.hive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 9, 2012
 */
public class HiveConnUtils {

    /**
     * Returns the children of the given object, refer to the method {@link #getFollowersByKeyAndLevel(String, int)}.
     * Added by Marvin Wang on Aug 10, 2012.
     * 
     * @param obj
     * @return
     */
    protected static List<HiveConnVersionInfo> getFollowersOfObject(HiveConnVersionInfo obj) {
        String key = obj.getKey();
        int level = obj.getLevel();
        List<HiveConnVersionInfo> beans = getObjectsByLevel(level + 1);
        if (beans != null && beans.size() > 0) {
            List<HiveConnVersionInfo> listFollowingKey = new ArrayList<HiveConnVersionInfo>();
            for (HiveConnVersionInfo bean : beans) {
                extractObjectFollowsKey(key, bean, listFollowingKey);
            }
            return listFollowingKey;
        }
        return null;
    }

    /**
     * If the object from the given array <code>HiveConnVersionInfo[]</code> includes the key, then put the object into
     * the given list to store. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @param key
     * @param beans
     * @param listFollowingKey
     */
    protected static void extractObjectFollowsKey(String key, HiveConnVersionInfo bean, List<HiveConnVersionInfo> listFollowingKey) {
        HiveConnVersionInfo[] followsBeans = bean.getFollows();
        if (followsBeans != null && followsBeans.length > 0) {
            for (HiveConnVersionInfo followsBean : followsBeans) {
                if (followsBean.getKey().equals(key)) {
                    listFollowingKey.add(bean);
                }
            }
        }
    }

    /**
     * Returns all {@link HiveConnVersionInfo} objects with the given level. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @param level
     * @return
     */
    protected static List<HiveConnVersionInfo> getObjectsByLevel(int level) {
        HiveConnVersionInfo[] beans = HiveConnVersionInfo.values();
        if (beans != null && beans.length > 0) {
            List<HiveConnVersionInfo> list = new ArrayList<HiveConnVersionInfo>();
            for (HiveConnVersionInfo bean : beans) {
                int beanLevel = bean.getLevel();
                if (level == beanLevel) {
                    list.add(bean);
                }
            }
            // ADD msjian TDQ-6407 2012-11-26:for top not support hive embedded mode
            // if (PluginChecker.isOnlyTopLoaded() && (level == 0 || level == 2)) {
            // list.remove(0);
            // }
            // TDQ-6407~
            return list;
        }
        return null;
    }

    /**
     * Returns all hive versions by distribution index, it means these hive versions follow the distribution. Added by
     * Marvin Wang on Aug 10, 2012.
     * 
     * @param level0Index
     * @return
     */
    protected static List<HiveConnVersionInfo> getHiveVersions(int distributionIndex) {
        List<HiveConnVersionInfo> level0Objects = getObjectsByLevel(0);
        HiveConnVersionInfo objOfLevel0ByIndex = level0Objects.get(distributionIndex);
        return getFollowersOfObject(objOfLevel0ByIndex);
    }

    protected static List<HiveConnVersionInfo> getHiveModes(int distributionIndex, int versionIndex, int hiveServerIndex) {
        List<HiveConnVersionInfo> supportedModes = new ArrayList<HiveConnVersionInfo>();
        HiveConnVersionInfo hiveVersionObj = getHiveVersionObj(distributionIndex, versionIndex);
        if (hiveVersionObj != null) {
            boolean supportStandalone = isSupportStandalone(hiveVersionObj, hiveServerIndex);
            List<HiveConnVersionInfo> modes = getFollowersOfObject(hiveVersionObj);
            if (modes != null && modes.size() > 0) {
                for (HiveConnVersionInfo mode : modes) {
                    if (HiveConnVersionInfo.MODE_EMBEDDED.equals(mode) || HiveConnVersionInfo.MODE_STANDALONE.equals(mode)
                            && supportStandalone) {
                        supportedModes.add(mode);
                    }
                }
            }
        }

        return supportedModes;
    }

    private static boolean isSupportStandalone(HiveConnVersionInfo hiveVersionObj, int hiveServerIndex) {
        boolean isHiveServer1 = "HIVE".equals(HiveServerVersionUtils.extractKey(hiveServerIndex)); //$NON-NLS-1$
        return !(isHiveServer1 && (HiveConnVersionInfo.HDP_1_2.equals(hiveVersionObj)
                || HiveConnVersionInfo.HDP_1_3.equals(hiveVersionObj) || HiveConnVersionInfo.HDP_2_0.equals(hiveVersionObj)));
    }

    protected static List<String> getHiveModeNameList(int distributionIndex, int versionIndex, int hiveServerIndex) {
        List<HiveConnVersionInfo> hiveModeObjs = getHiveModes(distributionIndex, versionIndex, hiveServerIndex);
        // ADD msjian TDQ-6407 2012-11-26: for top, not support hive embeded mode,hide this menu
        // if (PluginChecker.isOnlyTopLoaded() && hiveModeObjs.size() > 1) {
        // hiveModeObjs.remove(0);
        // }
        // TDQ-6407~
        if (hiveModeObjs != null && hiveModeObjs.size() > 0) {
            List<String> hiveModeNameList = new ArrayList<String>();
            for (HiveConnVersionInfo bean : hiveModeObjs) {
                hiveModeNameList.add(bean.getDisplayName());
            }
            return hiveModeNameList;
        }
        return null;
    }

    public static String[] getHiveModeNames(int distributionIndex, int versionIndex, int hiveServerIndex) {
        List<String> hiveModeNameList = getHiveModeNameList(distributionIndex, versionIndex, hiveServerIndex);
        if (hiveModeNameList != null && hiveModeNameList.size() > 0) {
            return hiveModeNameList.toArray(new String[hiveModeNameList.size()]);
        } else {
            return new String[0];
        }
    }

    protected static List<String> getHiveModeKeyList(int distributionIndex, int versionIndex, int hiveServerIndex) {
        List<HiveConnVersionInfo> hiveModeObjs = getHiveModes(distributionIndex, versionIndex, hiveServerIndex);
        // ADD msjian TDQ-6407 2012-11-26: for top, not support hive embeded mode,hide this menu
        // if (PluginChecker.isOnlyTopLoaded() && hiveModeObjs.size() > 1) {
        // hiveModeObjs.remove(0);
        // }
        // TDQ-6407~
        if (hiveModeObjs != null && hiveModeObjs.size() > 0) {
            List<String> hiveModeKeyList = new ArrayList<String>();
            for (HiveConnVersionInfo bean : hiveModeObjs) {
                hiveModeKeyList.add(bean.getKey());
            }
            return hiveModeKeyList;
        }
        return null;
    }

    public static String[] getHiveModeKeys(int distributionIndex, int versionIndex, int hiveServerIndex) {
        List<String> hiveModeKeyList = getHiveModeKeyList(distributionIndex, versionIndex, hiveServerIndex);
        if (hiveModeKeyList != null && hiveModeKeyList.size() > 0) {
            return hiveModeKeyList.toArray(new String[hiveModeKeyList.size()]);
        } else {
            return new String[0];
        }
    }

    public static int getIndexOfDistribution(String distributionKey) {
        int level = 0;
        List<HiveConnVersionInfo> distributions = getObjectsByLevel(level);
        if (distributions != null && distributions.size() > 0) {
            for (int i = 0; i < distributions.size(); i++) {
                if (distributions.get(i).getKey().equals(distributionKey)) {
                    return i;
                }
            }
        }
        return -1;
    }

    protected static int getIndexOfDistribution(HiveConnVersionInfo distribution) {
        String key = distribution.getKey();
        return getIndexOfDistribution(key);
    }

    public static int getIndexOfHiveVersion(String distributionKey, String hiveVersionKey) {
        int distributionIndex = getIndexOfDistribution(distributionKey);
        List<HiveConnVersionInfo> hiveVersions = getHiveVersions(distributionIndex);
        if (hiveVersions != null && hiveVersions.size() > 0) {
            for (int i = 0; i < hiveVersions.size(); i++) {
                if (hiveVersions.get(i).getKey().equals(hiveVersionKey)) {
                    return i;
                }
            }
        }
        return 0;
    }

    protected static int getIndexOfHiveVersion(HiveConnVersionInfo distribution, HiveConnVersionInfo hiveVersion) {
        String distributionKey = distribution.getKey();
        String hiveVersionKey = hiveVersion.getKey();
        return getIndexOfHiveVersion(distributionKey, hiveVersionKey);
    }

    public static int getIndexOfHiveMode(String distributionKey, String hiveVersionKey, String hiveModeKey, String hiveServerKey) {
        int distributionIndex = getIndexOfDistribution(distributionKey);
        int hiveVersionIndex = getIndexOfHiveVersion(distributionKey, hiveVersionKey);
        int hiveServerIndex = getIndexOfHiveServer(hiveServerKey);
        List<HiveConnVersionInfo> hiveModes = getHiveModes(distributionIndex, hiveVersionIndex, hiveServerIndex);
        if (hiveModes != null && hiveModes.size() > 0) {
            for (int i = 0; i < hiveModes.size(); i++) {
                if (hiveModes.get(i).getKey().equals(hiveModeKey)) {
                    return i;
                }
            }
        }
        return 0;
    }

    protected static int getIndexOfHiveMode(HiveConnVersionInfo distribution, HiveConnVersionInfo hiveVersion,
            HiveConnVersionInfo hiveMode, String hiveServerKey) {
        String distributionKey = distribution.getKey();
        String hiveModeKey = hiveMode.getKey();
        String hiveVersionkey = hiveVersion.getKey();
        return getIndexOfHiveMode(distributionKey, hiveVersionkey, hiveModeKey, hiveServerKey);
    }

    public static int getIndexOfHiveServer(String hiveServerKey) {
        List<String> hiveServers = HiveServerVersionUtils.extractListKeys();
        if (hiveServers != null && hiveServers.size() > 0) {
            for (int i = 0; i < hiveServers.size(); i++) {
                if (hiveServers.get(i).equals(hiveServerKey)) {
                    return i;
                }
            }
        }

        return 0;
    }

    /**
     * Returns the hive version object by the given index of distribution and hive version. Added by Marvin Wang on Aug
     * 13, 2012.
     * 
     * @param distributionIndex
     * @param hiveVersionIndex
     * @return
     */
    public static HiveConnVersionInfo getHiveVersionObj(int distributionIndex, int hiveVersionIndex) {
        List<HiveConnVersionInfo> objs = getHiveVersions(distributionIndex);
        if (objs != null && objs.size() > 0) {
            return objs.get(hiveVersionIndex);
        }
        return null;
    }

    /**
     * Returns the hive mode object by the given index of distribution, version and mode. Added by Marvin Wang on Aug
     * 13, 2012.
     * 
     * @param distributionIndex
     * @param hiveVersionIndex
     * @param hiveModeIndex
     * @return
     */
    public static HiveConnVersionInfo getHiveModeObj(int distributionIndex, int hiveVersionIndex, int hiveModeIndex,
            int hiveServerIndex) {
        List<HiveConnVersionInfo> objs = getHiveModes(distributionIndex, hiveVersionIndex, hiveServerIndex);
        if (objs != null && objs.size() > 0) {
            return objs.get(hiveModeIndex);
        }
        return null;
    }

    public static String getHiveModeObjKey(int distributionIndex, int hiveVersionIndex, int hiveModeIndex, int hiveServerIndex) {
        HiveConnVersionInfo hiveModeObj = getHiveModeObj(distributionIndex, hiveVersionIndex, hiveModeIndex, hiveServerIndex);
        if (hiveModeObj != null) {
            return hiveModeObj.getKey();
        }
        return null;
    }

}
