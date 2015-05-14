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
package org.talend.designer.maven.ui.dialog.model;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public enum EMavenScriptCategory {
    AutonomousJob("Autonomous Job", ImageProvider.getImageDesc(EImage.ADD_ALL_ICON)),
    OSGIBundle("OSGI Bundle", ImageProvider.getImageDesc(EImage.ADD_ICON)), ;

    private String label;

    private ImageDescriptor imageDesc;

    private EMavenScriptCategory(String label, ImageDescriptor imageDesc) {
        this.label = label;
        this.imageDesc = imageDesc;
    }

    public String getLabel() {
        return label;
    }

    public ImageDescriptor getImageDesc() {
        return imageDesc;
    }

}
