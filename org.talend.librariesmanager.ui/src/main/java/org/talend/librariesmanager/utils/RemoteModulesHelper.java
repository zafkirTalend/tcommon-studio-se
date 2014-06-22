// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleToInstall;
import org.talend.librariesmanager.ui.dialogs.IModulesListener;
import org.talend.librariesmanager.ui.i18n.Messages;

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

    private static final String serviceUrl = "http://www.talendforge.org/modules/webservices/modules.php";//$NON-NLS-1$

    private static final String SEPARATOR_DISPLAY = " | ";//$NON-NLS-1$

    private static final String SEPARATOR = "|";//$NON-NLS-1$

    private static final String SEPARATOR_SLIP = "\\|";//$NON-NLS-1$

    private Map<String, ModuleToInstall> cache = new HashMap<String, ModuleToInstall>();

    private RemoteModulesHelper() {

    }

    public synchronized static RemoteModulesHelper getInstance() {
        if (helper == null) {
            helper = new RemoteModulesHelper();
        }
        return helper;
    }

    public void getNotInstalledModules(List<ModuleNeeded> neededModules, List<ModuleToInstall> toInstall,
            IModulesListener listener) {
        getNotInstalledModules(neededModules, toInstall, listener, false);
    }

    public void getNotInstalledModules(List<ModuleNeeded> neededModules, List<ModuleToInstall> toInstall,
            IModulesListener listener, boolean isUser) {
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
        List<String> notFound = new ArrayList<String>();
        // get from cache first
        if (!cache.isEmpty()) {
            for (String moduleName : contextMap.keySet()) {
                ModuleToInstall moduleToInstall = cache.get(moduleName);
                if (moduleToInstall != null) {
                    List<ModuleNeeded> moduleContext = contextMap.get(moduleName);
                    moduleToInstall.setContext(getContext(moduleContext));
                    toInstall.add(moduleToInstall);
                } else {
                    notFound.add(moduleName);
                }
            }
        }

        String jarNames = jars.toString();
        if (jarNames.isEmpty()) {
            for (String jarNotFound : notFound) {
                ExceptionHandler.log("The download URL for " + jarNotFound + " is not available");//$NON-NLS-1$
            }
            listener.listModulesDone();
            return;
        }

        getModuleUrlsFromWebService(jarNames, toInstall, contextMap, listener, isUser);

    }

    public void getNotInstalledModules(String[] names, List<ModuleToInstall> toInstall, IModulesListener listener) {
        StringBuffer jars = new StringBuffer();
        if (names != null && names.length > 0) {
            for (String module : names) {
                String moduleName = module.trim();
                ModuleToInstall moduleToInstall = cache.get(moduleName);
                if (moduleToInstall != null) {
                    moduleToInstall.setContext("Current Operation");//$NON-NLS-1$
                    toInstall.add(moduleToInstall);
                } else {
                    if (jars.length() != 0) {
                        jars.append(SEPARATOR);
                        jars.append(moduleName);
                    } else {
                        jars.append(moduleName);
                    }
                }
            }
        }
        String jarNames = jars.toString();

        if (jarNames.isEmpty()) {
            listener.listModulesDone();
            return;
        }
        getModuleUrlsFromWebService(jarNames, toInstall, null, listener, false);

    }

    private synchronized void getModuleUrlsFromWebService(final String jarNames, final List<ModuleToInstall> toInstall,
            final Map<String, List<ModuleNeeded>> contextMap, final IModulesListener listener, boolean isUser) {

        Job job = new Job(Messages.getString("RemoteModulesHelper.job.title")) {//$NON-NLS-1$

            @Override
            protected IStatus run(IProgressMonitor monitor) {

                int size = jarNames.split(SEPARATOR_SLIP).length;
                monitor.beginTask(Messages.getString("RemoteModulesHelper.job.task"), size * 10);//$NON-NLS-1$
                JSONObject message = new JSONObject();
                try {
                    JSONObject child = new JSONObject();
                    child.put("vaction", "getModules");//$NON-NLS-1$
                    child.put("name", jarNames);//$NON-NLS-1$
                    message.put("module", child);//$NON-NLS-1$
                    String url = serviceUrl + "?data=" + message;
                    monitor.worked(10);
                    JSONObject resultStr = readJsonFromUrl(url);
                    if (resultStr != null) {
                        JSONArray jsonArray = resultStr.getJSONArray("result");//$NON-NLS-1$
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                if (obj != null) {
                                    String url_description = obj.getString("url_description");//$NON-NLS-1$
                                    String url_download = obj.getString("url_download");//$NON-NLS-1$
                                    String name = obj.getString("filename");//$NON-NLS-1$
                                    if ((url_description == null && url_download == null)
                                            || (("".equals(url_description) || "null".equals(url_description)) && (""//$NON-NLS-1$
                                            .equals(url_download) || "null".equals(url_download)))) {//$NON-NLS-1$
                                        ExceptionHandler.log("The download URL for " + name + " is not available");//$NON-NLS-1$
                                        // keep null in cache no need to check from server again
                                        cache.put(name, null);

                                        continue;
                                    }

                                    ModuleToInstall m = new ModuleToInstall();

                                    m.setName(name);
                                    if (contextMap != null) {
                                        List<ModuleNeeded> nm = contextMap.get(m.getName());
                                        m.setContext(getContext(nm));
                                        m.setRequired(isRequired(nm));
                                    } else {
                                        m.setContext("Current Operation");//$NON-NLS-1$
                                        m.setRequired(true);
                                    }
                                    String license = obj.getString("licence");//$NON-NLS-1$
                                    m.setLicenseType(license);
                                    if ("".equals(license) || "null".equals(license)) {//$NON-NLS-1$
                                        m.setLicenseType(null);
                                    }
                                    String description = obj.getString("description");//$NON-NLS-1$
                                    if (description == null || "".equals(description) || "null".equals(description)) {//$NON-NLS-1$
                                        description = m.getName();
                                    }
                                    m.setDescription(description);
                                    m.setUrl_description(url_description);
                                    if (url_download == null || "".equals(url_download) || "null".equals(url_download)) {//$NON-NLS-1$
                                        m.setUrl_download(null);
                                    } else {
                                        m.setUrl_download(url_download);
                                    }
                                    toInstall.add(m);
                                    cache.put(m.getName(), m);
                                }
                                monitor.worked(10);
                            }
                        }
                    }
                } catch (JSONException e) {
                    ExceptionHandler.process(e);
                } catch (IOException e) {
                    ExceptionHandler.process(e);
                }

                monitor.done();
                return Status.OK_STATUS;
            }
        };
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                listener.listModulesDone();
            }
        });

        job.setUser(isUser);
        job.setPriority(Job.INTERACTIVE);
        job.schedule();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        String jsonText = "";
        JSONObject json = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));//$NON-NLS-1$
            jsonText = readAll(rd);
            json = new JSONObject(jsonText);
        } catch (Exception e) {
            ExceptionHandler.process(new Exception(Messages.getString("RemoteModulesHelper.readJsonFromUrl.error")));
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
        if (neededModules == null) {
            return "";
        }
        StringBuffer context = new StringBuffer();
        for (ModuleNeeded module : neededModules) {
            if (context.length() != 0) {
                context.append(SEPARATOR_DISPLAY);
            }
            context.append(module.getContext());
        }
        return context.toString();

    }

    private boolean isRequired(List<ModuleNeeded> neededModules) {
        if (neededModules == null) {
            return false;
        }
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
        JSONObject message = new JSONObject();
        try {
            JSONObject child = new JSONObject();
            child.put("vaction", "getLicense");//$NON-NLS-1$
            child.put("label", licenseType);//$NON-NLS-1$
            message.put("module", child);//$NON-NLS-1$
            String url = serviceUrl + "?data=" + message;
            JSONObject resultStr = readJsonFromUrl(url);
            JSONArray jsonArray = resultStr.getJSONArray("result");//$NON-NLS-1$
            if (jsonArray != null) {
                JSONObject object = jsonArray.getJSONObject(0);
                if (object != null) {
                    return object.getString("licenseText");//$NON-NLS-1$
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
        m1.setContext("tMysqlInput | tMysqlOutput");
        m1.setDescription("Mysql Driver");
        m1.setUrl_description("http://jtds.sourceforge.net/");
        m1.setUrl_download(null);
        m1.setLicenseType("LGPL_v3");
        toInstall.add(m1);
        m1 = new ModuleToInstall();
        m1 = new ModuleToInstall();
        m1.setName("test.jar");
        m1.setContext("tMysqalInput | tMysfqlOutput");
        m1.setDescription("testaaaaa");
        m1.setUrl_description("http://jtds.sourceforge.net/");
        m1.setUrl_download(null);
        m1.setLicenseType("LGPL_v3");
        toInstall.add(m1);
        return toInstall;
    }

}
