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
package org.talend.core.model.metadata.connection.hive;

import java.util.ArrayList;
import java.util.List;

import org.talend.commons.utils.platform.PluginChecker;

/**
 * @author Marvin Wang
 * @version 1.0 jdk1.6
 * @date Aug 9, 2012
 */
public class HiveConnUtils {

    /**
     * Returns the distribution names that can be used to show in UI. If no items, return <code>new String[0]</code>.
     * Added by Marvin Wang on Aug. 9, 2012.
     * 
     * @see #getDistributionNameList()
     * @return
     */
    public static String[] getDistributionNames() {
        List<String> list = getDistributionNameList();
        if (list == null) {
            return new String[0];
        } else {
            return list.toArray(new String[list.size()]);
        }
    }

    /**
     * Returns the name list of distribution. If no items, return <code>null</code>. These names are from
     * {@link HiveConnVersionInfo} that defines the level <code>0</code>. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @see #extractVersionInfo(int)
     * @return
     */
    public static List<String> getDistributionNameList() {
        return getNameListByLevel(0);
    }

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
     * Returns all names with a specified level. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @param level
     * @return
     */
    protected static List<String> getNameListByLevel(int level) {
        List<HiveConnVersionInfo> beans = getObjectsByLevel(level);
        if (beans != null && beans.size() > 0) {
            List<String> nameList = new ArrayList<String>();
            for (HiveConnVersionInfo bean : beans) {
                nameList.add(bean.getDisplayName());
            }
            return nameList;
        }
        return null;
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
            if (PluginChecker.isOnlyTopLoaded() && (level == 0 || level == 2)) {
                list.remove(0);
            }
            // TDQ-6407~
            return list;
        }
        return null;
    }

    /**
     * Returns the index of distribution that is level 0 by display name. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @param displayName
     * @return
     */
    public static int getDistributionIndex(String displayName) {
        String[] names = getDistributionNames();
        if (names != null && names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                if (displayName != null && displayName.equals(names[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the key by display name and level. Added by Marvin Wang on Aug 9, 2012.
     * 
     * @param displayName
     * @param level
     * @return
     */
    protected static String getKeyByDisplayName(String displayName, int level) {
        List<HiveConnVersionInfo> beans = getObjectsByLevel(level);
        if (beans != null && beans.size() > 0) {
            for (HiveConnVersionInfo bean : beans) {
                if (displayName != null && displayName.equals(bean.getDisplayName())) {
                    return bean.getKey();
                }
            }
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

    protected static List<String> getHiveVersionNameList(int distributionIndex) {
        List<HiveConnVersionInfo> hiveVersionObjs = getHiveVersions(distributionIndex);
        if (hiveVersionObjs != null && hiveVersionObjs.size() > 0) {
            List<String> hiveVersionNameList = new ArrayList<String>();
            for (HiveConnVersionInfo bean : hiveVersionObjs) {
                hiveVersionNameList.add(bean.getDisplayName());
            }
            return hiveVersionNameList;
        }
        return null;
    }

    public static String[] getHiveVersionNames(int distributionIndex) {
        List<String> hiveVersionNameList = getHiveVersionNameList(distributionIndex);
        if (hiveVersionNameList != null && hiveVersionNameList.size() > 0) {
            return hiveVersionNameList.toArray(new String[hiveVersionNameList.size()]);
        } else {
            return new String[0];
        }
    }

    /**
     * Returns the list of <code>HiveConnVersionInfo</code> for hive modes by the index of distribution and version.
     * Added by Marvin Wang on Aug 10, 2012.
     * 
     * @param distributionIndex
     * @param versionIndex
     * @return
     */
    protected static List<HiveConnVersionInfo> getHiveModes(int distributionIndex, int versionIndex) {
        List<HiveConnVersionInfo> distriObjects = getHiveVersions(distributionIndex);
        if (distriObjects != null && distriObjects.size() > 0) {
            HiveConnVersionInfo hiveVersionObj = distriObjects.get(versionIndex);
            return getFollowersOfObject(hiveVersionObj);
        }
        return null;
    }

    protected static List<String> getHiveModeNameList(int distributionIndex, int versionIndex) {
        List<HiveConnVersionInfo> hiveModeObjs = getHiveModes(distributionIndex, versionIndex);
        // ADD msjian TDQ-6407 2012-11-26: for top, not support hive embeded mode,hide this menu
        if (PluginChecker.isOnlyTopLoaded() && hiveModeObjs.size() > 1) {
            hiveModeObjs.remove(0);
        }
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

    public static String[] getHiveModeNames(int distributionIndex, int versionIndex) {
        List<String> hiveModeNameList = getHiveModeNameList(distributionIndex, versionIndex);
        if (hiveModeNameList != null && hiveModeNameList.size() > 0) {
            return hiveModeNameList.toArray(new String[hiveModeNameList.size()]);
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
        return -1;
    }

    protected static int getIndexOfHiveVersion(HiveConnVersionInfo distribution, HiveConnVersionInfo hiveVersion) {
        String distributionKey = distribution.getKey();
        String hiveVersionKey = hiveVersion.getKey();
        return getIndexOfHiveVersion(distributionKey, hiveVersionKey);
    }

    public static int getIndexOfHiveMode(String distributionKey, String hiveVersionKey, String hiveModeKey) {
        int distributionIndex = getIndexOfDistribution(distributionKey);
        int hiveVersionIndex = getIndexOfHiveVersion(distributionKey, hiveVersionKey);
        List<HiveConnVersionInfo> hiveModes = getHiveModes(distributionIndex, hiveVersionIndex);
        if (hiveModes != null && hiveModes.size() > 0) {
            for (int i = 0; i < hiveModes.size(); i++) {
                if (hiveModes.get(i).getKey().equals(hiveModeKey)) {
                    return i;
                }
            }
        }
        return -1;
    }

    protected static int getIndexOfHiveMode(HiveConnVersionInfo distribution, HiveConnVersionInfo hiveVersion,
            HiveConnVersionInfo hiveMode) {
        String distributionKey = distribution.getKey();
        String hiveModeKey = hiveMode.getKey();
        String hiveVersionkey = hiveVersion.getKey();
        return getIndexOfHiveMode(distributionKey, hiveVersionkey, hiveModeKey);
    }

    public static boolean isEmbeddedMode(int distributionIndex, int hiveVersionIndex, int hiveModeIndex) {
        List<HiveConnVersionInfo> hiveModes = getHiveModes(distributionIndex, hiveVersionIndex);
        if (hiveModes != null && hiveModes.size() > 0) {
            HiveConnVersionInfo hiveMode = hiveModes.get(hiveModeIndex);
            if (HiveConnVersionInfo.MODE_EMBEDDED.getKey().equals(hiveMode.getKey())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the distribution <code>HiveConnVersionInfo</code> object by the given <code>index</code> from level 0.
     * Added by Marvin Wang on Aug 13, 2012.
     * 
     * @param index
     * @return
     */
    public static HiveConnVersionInfo getDistributionObj(int index) {
        List<HiveConnVersionInfo> distributions = getObjectsByLevel(0);
        if (distributions != null && distributions.size() > 0) {
            return distributions.get(index);
        }
        return null;
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
    public static HiveConnVersionInfo getHiveModeObj(int distributionIndex, int hiveVersionIndex, int hiveModeIndex) {
        List<HiveConnVersionInfo> objs = getHiveModes(distributionIndex, hiveVersionIndex);
        if (objs != null && objs.size() > 0) {
            return objs.get(hiveModeIndex);
        }
        return null;
    }

    public static String getHiveModeObjKey(int distributionIndex, int hiveVersionIndex, int hiveModeIndex) {
        HiveConnVersionInfo hiveModeObj = getHiveModeObj(distributionIndex, hiveVersionIndex, hiveModeIndex);
        if (hiveModeObj != null) {
            return hiveModeObj.getKey();
        }
        return null;
    }

}
