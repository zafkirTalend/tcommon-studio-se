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
package org.talend.librariesmanager.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleToInstall;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * created by Administrator on 2012-9-19 Detailled comment
 * 
 */
public class RemoteModulesHelper {

    private static RemoteModulesHelper helper;

    // private static final String serviceUrl = "http://www.talend.com/TalendRegisterWS/modules.php";

    private static final String serviceUrl = "http://www.talendforge.org/modules/webservices/modules.php";

    private static final String SEPARATOR = "|";

    private Map<String, ModuleToInstall> cache = new HashMap<String, ModuleToInstall>();

    private RemoteModulesHelper() {

    }

    public synchronized static RemoteModulesHelper getInstance() {
        if (helper == null) {
            helper = new RemoteModulesHelper();
        }
        return helper;
    }

    public List<ModuleToInstall> getNotInstalledModules(List<ModuleNeeded> neededModules) {
        List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();

        Map<String, List<ModuleNeeded>> contextMap = new HashMap<String, List<ModuleNeeded>>();

        StringBuffer jars = new StringBuffer();
        for (ModuleNeeded module : neededModules) {
            String moduleName = module.getModuleName().trim();

            if (!contextMap.keySet().contains(moduleName)) {
                List<ModuleNeeded> modules = new ArrayList<ModuleNeeded>();
                modules.add(module);
                contextMap.put(moduleName, modules);
                if (!cache.keySet().contains(moduleName)) {
                    if (jars.length() != 0) {
                        jars.append(SEPARATOR);
                    }
                    jars.append(module.getModuleName());
                }
            } else {
                contextMap.get(moduleName).add(module);
            }
        }

        // get from cache first
        if (!cache.isEmpty()) {
            for (String moduleName : contextMap.keySet()) {
                ModuleToInstall moduleToInstall = cache.get(moduleName);
                if (moduleToInstall != null) {
                    List<ModuleNeeded> moduleContext = contextMap.get(moduleName);
                    moduleToInstall.setContext(getContext(moduleContext));
                    toInstall.add(moduleToInstall);
                }
            }
        }

        String jarNames = jars.toString();
        if (jarNames.isEmpty()) {
            return toInstall;
        }
        getModuleUrlsFromWebService(jarNames, toInstall, contextMap);
        return toInstall;
    }

    public List<ModuleToInstall> getNotInstalledModules(String[] names) {
        List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();
        StringBuffer jars = new StringBuffer();
        if (names != null) {
            for (String module : names) {
                String moduleName = module.trim();
                ModuleToInstall moduleToInstall = cache.get(moduleName);
                if (moduleToInstall != null) {
                    moduleToInstall.setContext("Current Operation");
                    toInstall.add(moduleToInstall);
                } else {
                    if (jars.length() != 0) {
                        jars.append(SEPARATOR);
                    } else {
                        jars.append(moduleName);
                    }
                }
            }
        }
        String jarNames = jars.toString();

        if (jarNames.isEmpty()) {
            return toInstall;
        }
        getModuleUrlsFromWebService(jarNames, toInstall, null);

        return toInstall;
    }

    private void getModuleUrlsFromWebService(String jarNames, List<ModuleToInstall> toInstall,
            Map<String, List<ModuleNeeded>> contextMap) {
        JSONObject message = new JSONObject();
        try {
            JSONObject child = new JSONObject();
            child.put("vaction", "getModules");
            child.put("name", jarNames);
            message.put("module", child);
            String url = serviceUrl + "?data=" + message;

            JSONObject resultStr = readJsonFromUrl(url);
            JSONArray jsonArray = resultStr.getJSONArray("result");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj != null) {

                        String url_description = obj.getString("url_description");
                        String url_download = obj.getString("url_download");
                        String name = obj.getString("filename");
                        if ((url_description == null && url_download == null)
                                || (("".equals(url_description) || "null".equals(url_description)) && ("".equals(url_download) || "null"
                                        .equals(url_download)))) {
                            ExceptionHandler.log("Module " + name + " download url is not avialable currently");
                            continue;
                        }

                        ModuleToInstall m = new ModuleToInstall();

                        m.setName(name);
                        if (contextMap != null) {
                            List<ModuleNeeded> nm = contextMap.get(m.getName());
                            m.setContext(getContext(nm));
                            m.setRequired(isRequired(nm));
                        } else {
                            m.setContext("Current Operation");
                            m.setRequired(true);
                        }
                        String license = obj.getString("licence");
                        m.setLicenseType(license);
                        if ("".equals(license) || "null".equals(license)) {
                            m.setLicenseType(null);
                        }
                        String description = obj.getString("description");
                        if (description == null || "".equals(description) || "null".equals(description)) {
                            description = m.getName();
                        }
                        m.setDescription(description);
                        m.setUrl_description(url_description);
                        if (url_download == null || "".equals(url_download) || "null".equals(url_download)) {
                            m.setUrl_download(null);
                        } else {
                            m.setUrl_download(url_download);
                        }
                        toInstall.add(m);
                        cache.put(m.getName(), m);
                    }
                }
            }

        } catch (JSONException e) {
            ExceptionHandler.process(e);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }

    }

    private JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        String jsonText = "";
        JSONObject json = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
            json = new JSONObject(jsonText);
        } catch (Exception e) {
            System.out.println(jsonText);
        } finally {
            is.close();
        }
        return json;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String getContext(List<ModuleNeeded> neededModules) {
        StringBuffer context = new StringBuffer();
        for (ModuleNeeded module : neededModules) {
            if (context.length() != 0) {
                context.append(SEPARATOR);
            }
            context.append(module.getContext());
        }
        return context.toString();

    }

    private boolean isRequired(List<ModuleNeeded> neededModules) {
        boolean isRequired = false;
        for (ModuleNeeded module : neededModules) {
            isRequired = isRequired | module.isRequired();
            if (isRequired) {
                return isRequired;
            }
        }
        return isRequired;
    }

    public String getLicenseUrl(String licenseType) {

        // ///////////test
        licenseType = "LGPL_v3";
        // //////////test

        JSONObject message = new JSONObject();
        try {
            JSONObject child = new JSONObject();
            child.put("vaction", "getLicense");
            child.put("label", licenseType);
            message.put("module", child);
            String url = serviceUrl + "?data=" + message;
            JSONObject resultStr = readJsonFromUrl(url);
            JSONArray jsonArray = resultStr.getJSONArray("result");
            if (jsonArray != null) {
                JSONObject object = jsonArray.getJSONObject(0);
                if (object != null) {
                    return object.getString("licenseText");
                }
            }
        } catch (JSONException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public List<ModuleToInstall> getTest() {
        List<ModuleToInstall> toInstall = new ArrayList<ModuleToInstall>();
        ModuleToInstall m1 = new ModuleToInstall();
        m1 = new ModuleToInstall();
        m1.setName("jtds-1.2.5.jar");
        m1.setContext("tMysqlInput|tMysqlOutput");
        m1.setDescription("Mysql Driver");
        m1.setUrl_description("http://jtds.sourceforge.net/");
        m1.setUrl_download(null);
        m1.setLicenseType("LGPL_v3");
        toInstall.add(m1);
        return toInstall;
    }

}
