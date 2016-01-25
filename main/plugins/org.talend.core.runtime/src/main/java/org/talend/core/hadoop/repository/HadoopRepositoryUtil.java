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
package org.talend.core.hadoop.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.talend.core.utils.TalendQuoteUtils;
import org.talend.utils.json.JSONArray;
import org.talend.utils.json.JSONException;
import org.talend.utils.json.JSONObject;

/**
 * created by ycbai on 2013-10-24 Detailled comment
 * 
 */
public class HadoopRepositoryUtil {

    /**
     * DOC ycbai Comment method "getHadoopPropertiesJsonStr".
     * 
     * <p>
     * Get the json string type of hadoop properties from list type.
     * </p>
     * 
     * @param properties
     * @return
     * @throws JSONException
     */
    public static String getHadoopPropertiesJsonStr(List<HashMap<String, Object>> properties) throws JSONException {
        JSONArray jsonArr = new JSONArray();
        if (properties != null && properties.size() > 0) {
            for (HashMap<String, Object> map : properties) {
                JSONObject object = new JSONObject();
                Iterator<String> it = map.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    object.put(key, map.get(key));
                }
                jsonArr.put(object);
            }
        }

        return jsonArr.toString();
    }

    public static List<HashMap<String, Object>> getHadoopPropertiesList(String propertiesJsonStr) throws JSONException {
        return getHadoopPropertiesList(propertiesJsonStr, false);
    }

    /**
     * DOC ycbai Comment method "getHadoopPropertiesList".
     * 
     * <p>
     * Get the list type of hadoop properties from json string.
     * </p>
     * 
     * @param propertiesJsonStr
     * @param includeQuotes
     * @return
     * @throws JSONException
     */
    public static List<HashMap<String, Object>> getHadoopPropertiesList(String propertiesJsonStr, boolean includeQuotes)
            throws JSONException {
        List<HashMap<String, Object>> properties = new ArrayList<HashMap<String, Object>>();
        if (StringUtils.isNotEmpty(propertiesJsonStr)) {
            JSONArray jsonArr = new JSONArray(propertiesJsonStr);
            for (int i = 0; i < jsonArr.length(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                JSONObject object = jsonArr.getJSONObject(i);
                Iterator<String> it = object.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = String.valueOf(object.get(key));
                    if (includeQuotes) {
                        value = TalendQuoteUtils.addQuotesIfNotExist(value);
                    } else {
                        value = TalendQuoteUtils.removeQuotesIfExist(value);
                    }
                    map.put(key, value);
                }
                properties.add(map);
            }
        }

        return properties;
    }

}
