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
package org.talend.designer.webservice.ui;

import java.util.ArrayList;
import java.util.List;

import org.talend.designer.webservice.data.InputMappingData;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class ParameterInfoUtil {

    private int currentindex;

    public List<ParameterInfo> getAllMostParameterInfo(ParameterInfo para, String mark, Object obj) {
        ParameterInfo parentPara = para.getParent();
        ParameterInfo paraToCom = null;
        if (mark.equals("IN")) {
            paraToCom = ((InputMappingData) obj).getParameter();
        } else if (mark.equals("OUT")) {
            paraToCom = (ParameterInfo) obj;
        }
        List<ParameterInfo> list = new ArrayList<ParameterInfo>();
        if (parentPara != paraToCom) {
            list.add(para);
            List<ParameterInfo> pali = getAllMostParameterInfo(parentPara, mark, obj);
            list.addAll(pali);
        } else {
            list.add(para);
        }
        return list;
    }

    public int getCurrentindex() {
        return this.currentindex;
    }

    public void setCurrentindex(int currentindex) {
        this.currentindex = currentindex;
    }

    public List<ParameterInfo> getAllChildren(ParameterInfo para) {
        List<ParameterInfo> list = new ArrayList<ParameterInfo>();
        List<ParameterInfo> childList = para.getParameterInfos();
        list.addAll(childList);
        for (ParameterInfo paraC : childList) {
            if (paraC.getParameterInfos().size() > 0) {
                list.addAll(getAllChildren(paraC));
            }
        }
        return list;
    }

    public List<ParameterInfo> getAllParameterInfo(ParameterInfo para) {
        ParameterInfo parentPara = para.getParent();
        List<ParameterInfo> list = new ArrayList<ParameterInfo>();
        if (parentPara != null) {
            list.add(para);
            List<ParameterInfo> pali = getAllParameterInfo(parentPara);
            list.addAll(pali);
        } else {
            list.add(para);
        }
        return list;
    }

    public String getParentName(ParameterInfo para) {
        List<ParameterInfo> paraList = getAllParameterInfo(para);
        StringBuffer buffer = new StringBuffer();
        ParameterInfo nearArrayParent = null;
        goout: for (ParameterInfo pa : paraList) {
            if (pa.getArraySize() != 0) {
                nearArrayParent = pa;
                break goout;
            }
        }
        for (int i = paraList.size() - 1; i >= 0; i--) {
            ParameterInfo parentPara = paraList.get(i);
            if (parentPara == null) {
                continue;
            }
            buffer.append(parentPara.getName());

            if (nearArrayParent != null && parentPara == nearArrayParent) {
                if (para.getArraySize() == 0 && para.getParameterInfos().size() == 0 && getCurrentindex() != -1 && i != 0) {
                    buffer.append("[" + getCurrentindex() + "]");
                }
            }

            if (i != 0) {
                buffer.append(".");
            }
            if (para.getArraySize() != 0) {
                if (i == 0) {
                    buffer.append("[]");
                }
            }

        }
        return buffer.toString();
    }
}
