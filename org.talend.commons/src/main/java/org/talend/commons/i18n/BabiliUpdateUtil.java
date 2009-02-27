// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.i18n;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.JavaTypeMapper;

/**
 * DOC wzhang class global comment. Detailled comment
 */
public class BabiliUpdateUtil {

    private static final String REVERSION_LIST = "http://talendforge.org/babili/api/get_labels.php"; //$NON-NLS-1$

    /**
     * 
     * DOC wzhang Comment method "getBabiliList". get the list from Babili.
     * 
     * @param language
     * @param validated
     * @param version
     * @return
     * @throws Exception
     */
    public static List<BabiliInfo> getBabiliList(String language, boolean validated, String version) throws Exception {
        StringBuffer url = new StringBuffer();
        url.append(REVERSION_LIST).append("?language=").append(language).append("&validated_only=").append(validated).append( //$NON-NLS-1$ //$NON-NLS-2$
                "&release=").append(version); //$NON-NLS-1$
        String jsonContent = sendGetRequest(url.toString());
        return parseJsonObject(jsonContent, BabiliInfo.class);
    }

    /**
     * 
     * DOC wzhang Comment method "sendGetRequest".
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public static String sendGetRequest(String url) throws Exception {
        HttpClient httpclient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        httpclient.executeMethod(getMethod);
        String response = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return response;
    }

    /**
     * 
     * DOC wzhang Comment method "parseJsonObject".
     * 
     * @param jsonContent
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List parseJsonObject(String jsonContent, Class clazz) throws Exception {
        // need factory for creating parser to use
        JsonFactory jf = new JsonFactory();
        List result = (List) new JavaTypeMapper().read(jf.createJsonParser(new StringReader(jsonContent)));
        List objList = new ArrayList(result.size());
        for (int i = 0; i < result.size(); i++) {
            Object obj = clazz.newInstance();
            Object source = result.get(i);
            BeanUtils.copyProperties(obj, source);
            objList.add(obj);
        }
        return objList;
    }

}
