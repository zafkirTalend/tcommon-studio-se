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
package org.talend.presentation.onboarding.ui.runtimedata;

import org.eclipse.swt.graphics.Point;
import org.talend.presentation.onboarding.exceptions.OnBoardingExceptionHandler;

/**
 * created by cmeng on Sep 15, 2015 Detailled comment
 *
 */
public class OnBoardingDocBean {

    public static final String SIZE_SEPERATOR = ","; //$NON-NLS-1$

    public static final String CSS_SEPERATOR = "\\|\\|"; //$NON-NLS-1$

    private String perspId;

    private String cssIds;

    private Point size;

    private String title;

    private String content;

    public String getPerspId() {
        return this.perspId;
    }

    public void setPerspId(String perspId) {
        this.perspId = perspId;
    }

    public String[] getCssIds() {
        if (cssIds == null || cssIds.trim().isEmpty()) {
            return new String[0];
        }
        return cssIds.split(CSS_SEPERATOR);
    }

    public void setCssIds(String cssIds) {
        this.cssIds = cssIds;
    }

    public Point getSize() {
        return this.size;
    }

    public void setSizePoint(Point size) {
        this.size = size;
    }

    /**
     * 
     * @param sizeStr let this bean support json phaser
     */
    public void setSize(String sizeStr) {
        if (sizeStr == null) {
            return;
        }
        String[] sizes = sizeStr.split(SIZE_SEPERATOR);
        if (sizes == null || sizes.length != 2) {
            return;
        }
        try {
            this.size = new Point(Integer.valueOf(sizes[0]), Integer.valueOf(sizes[1]));
        } catch (Throwable e) {
            OnBoardingExceptionHandler.process(e);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
