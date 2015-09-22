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
package org.talend.presentation.onboarding.ui.managers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.presentation.onboarding.exceptions.OnBoardingExceptionHandler;
import org.talend.presentation.onboarding.handlers.OnBoardingExtentionHandler;
import org.talend.presentation.onboarding.i18n.Messages;
import org.talend.presentation.onboarding.interfaces.IOnBoardingJsonI18n;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingDocBean;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingPresentationData;
import org.talend.presentation.onboarding.ui.runtimedata.OnBoardingRegistedResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * created by cmeng on Sep 15, 2015 Detailled comment
 *
 */
public class OnBoardingResourceManager {

    public static final String ALL_PERSPECTIVES = "ALL_PERSPECTIVES"; //$NON-NLS-1$

    private Object jsonString;

    private List<OnBoardingDocBean> onBoardingDocs;

    private Map<String, List<OnBoardingPresentationData>> onBoardingPresentationDataMap;

    private IOnBoardingJsonI18n onBoardingJsonI18n;

    private static OnBoardingResourceManager defaultOnBoardingResourceManager;

    public static OnBoardingResourceManager getDefaultResourceManager() {
        if (defaultOnBoardingResourceManager == null) {
            OnBoardingRegistedResource resource = OnBoardingExtentionHandler.getOnBoardingResourceLastRegisted();
            if (resource == null) {
                defaultOnBoardingResourceManager = null;
            } else {
                defaultOnBoardingResourceManager = new OnBoardingResourceManager();
                IOnBoardingJsonI18n i18n = resource.getI18n();
                defaultOnBoardingResourceManager.setOnBoardingJsonI18n(i18n);
                defaultOnBoardingResourceManager.setJsonString(resource.getUrl());
            }
        }
        return defaultOnBoardingResourceManager;
    }

    private void convertData() {
        if (!(jsonString instanceof String || jsonString instanceof File || jsonString instanceof URL)) {
            throw new RuntimeException(Messages.getString("OnBoardingResourceManager.convertData.invalidJsonInputType")); //$NON-NLS-1$
        }
        try {
            TypeReference<List<OnBoardingDocBean>> typeReference = new TypeReference<List<OnBoardingDocBean>>() {
                // no need to overwrite
            };
            if (jsonString instanceof String) {
                onBoardingDocs = new ObjectMapper().readValue(((String) jsonString).getBytes(), typeReference);
            } else if (jsonString instanceof File) {
                onBoardingDocs = new ObjectMapper().readValue((File) jsonString, typeReference);
            } else if (jsonString instanceof URL) {
                onBoardingDocs = new ObjectMapper().readValue((URL) jsonString, typeReference);
            }
            onBoardingPresentationDataMap = new HashMap<String, List<OnBoardingPresentationData>>();
            for (OnBoardingDocBean docBean : onBoardingDocs) {
                String perspId = docBean.getPerspId();
                if (perspId == null || perspId.isEmpty()) {
                    perspId = ALL_PERSPECTIVES;
                }
                List<OnBoardingPresentationData> presDatas = onBoardingPresentationDataMap.get(perspId);
                if (presDatas == null) {
                    presDatas = new ArrayList<OnBoardingPresentationData>();
                    onBoardingPresentationDataMap.put(perspId, presDatas);
                }
                OnBoardingPresentationData presData = new OnBoardingPresentationData();
                convertI18N(docBean);
                presData.setDocBean(docBean);
                presDatas.add(presData);
            }
        } catch (Throwable e) {
            OnBoardingExceptionHandler.process(e);
        }
    }

    private void convertI18N(OnBoardingDocBean docBean) {
        if (docBean == null) {
            return;
        }
        String title = docBean.getTitle();
        if (title != null && !title.isEmpty()) {
            docBean.setTitle(getI18NString(title));
        }
        String content = docBean.getContent();
        if (content != null && !content.isEmpty()) {
            docBean.setContent(getI18NString(content));
        }
    }

    protected String getI18NString(String key) {
        if (onBoardingJsonI18n == null) {
            return key;
        } else {
            return onBoardingJsonI18n.getI18NString(key);
        }
    }

    public int getDocsSize(String perspectiveId) {
        if (perspectiveId == null) {
            return 0;
        }
        int totalSize = 0;
        Collection<OnBoardingPresentationData> presDatas = onBoardingPresentationDataMap.get(perspectiveId);
        if (presDatas != null) {
            totalSize = totalSize + presDatas.size();
        }
        presDatas = onBoardingPresentationDataMap.get(ALL_PERSPECTIVES);
        if (presDatas != null) {
            totalSize = totalSize + presDatas.size();
        }
        return totalSize;
    }

    public static void main(String args[]) {
        OnBoardingResourceManager on = new OnBoardingResourceManager();
        on.setJsonString("[{\"viewId\":\"org.talend.repository.cnf.view\",\"size\":\"600,400\",\"perspId\":\"org.talend.rcp.perspective\",\"forceShow\":true,\"cssIds\":\"#navigatorLayout || .MPart#org-talend-repository-cnf-view\",\"title\":\"This is a title 1\",\"content\":\"Write contents here: \\\\n abcdefg \\\\n 1234567\"},{\"viewId\":\"org.talend.designer.core.ui.views.properties.ComponentSettingsView\",\"size\":\"600,400\",\"perspId\":\"org.talend.camel.perspective\",\"forceShow\":true,\"cssIds\":\"#bottomLayout\",\"title\":\"This is a title 2\",\"content\":\"Write contents here: \\\\n abcdefg \\\\n 1234567\"}]");
    }

    public Object getJsonString() {
        return this.jsonString;
    }

    /**
     * 
     * @param jsonString now only support types: String, File, URL
     */
    public void setJsonString(Object jsonString) {
        this.jsonString = jsonString;
        convertData();
    }

    public List<OnBoardingDocBean> getOnBoardingDocs() {
        return this.onBoardingDocs;
    }

    public void setOnBoardingDocs(List<OnBoardingDocBean> onBoardingDocs) {
        this.onBoardingDocs = onBoardingDocs;
    }

    public List<OnBoardingPresentationData> getOnBoardingPresentationDatas(String perspectiveId) {
        List<OnBoardingPresentationData> presentationDatas = new ArrayList<OnBoardingPresentationData>();
        List<OnBoardingPresentationData> tempDatas = onBoardingPresentationDataMap.get(ALL_PERSPECTIVES);
        if (tempDatas != null && !tempDatas.isEmpty()) {
            presentationDatas.addAll(tempDatas);
        }
        if (perspectiveId != null) {
            tempDatas = onBoardingPresentationDataMap.get(perspectiveId);
            if (tempDatas != null && !tempDatas.isEmpty()) {
                presentationDatas.addAll(tempDatas);
            }
        }
        return presentationDatas;
    }

    public IOnBoardingJsonI18n getOnBoardingJsonI18n() {
        return this.onBoardingJsonI18n;
    }

    public void setOnBoardingJsonI18n(IOnBoardingJsonI18n onBoardingJsonI18n) {
        this.onBoardingJsonI18n = onBoardingJsonI18n;
    }

}
