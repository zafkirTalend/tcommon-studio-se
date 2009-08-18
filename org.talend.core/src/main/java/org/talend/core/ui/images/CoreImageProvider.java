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
package org.talend.core.ui.images;

import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.IImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.utils.image.ImageUtils.ICON_SIZE;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class CoreImageProvider {

    public static Image getImage(ERepositoryObjectType type) {
        return ImageProvider.getImage(getIcon(type));
    }

    public static ImageDescriptor getImageDesc(ERepositoryObjectType type) {
        return ImageProvider.getImageDesc(getIcon(type));
    }

    public static IImage getIcon(ERepositoryObjectType type) {
        switch (type) {
        case BUSINESS_PROCESS:
            return ECoreImage.BUSINESS_PROCESS_ICON;
        case PROCESS:
            return ECoreImage.PROCESS_ICON;
        case JOBLET:
            return ECoreImage.JOBLET_ICON;
        case CONTEXT:
            return ECoreImage.CONTEXT_ICON;
        case ROUTINES:
            return ECoreImage.ROUTINE_ICON;
        case SNIPPETS:
            return ECoreImage.SNIPPETS_ICON;
        case DOCUMENTATION:
        case JOB_DOC:
        case JOBLET_DOC:
            return ECoreImage.DOCUMENTATION_ICON;
        case METADATA:
            return ECoreImage.METADATA_ICON;
        case METADATA_CONNECTIONS:
            return ECoreImage.METADATA_CONNECTION_ICON;
        case METADATA_SAPCONNECTIONS:
            return ECoreImage.METADATA_SAPCONNECTION_ICON;
        case SQLPATTERNS:
            return ECoreImage.METADATA_SQLPATTERN_ICON;
        case METADATA_CON_TABLE:
            return ECoreImage.METADATA_TABLE_ICON;
        case METADATA_CON_QUERY:
            return ECoreImage.METADATA_QUERY_ICON;
        case METADATA_CON_VIEW:
            return ECoreImage.METADATA_VIEW_ICON;
        case METADATA_CON_SYNONYM:
            return ECoreImage.METADATA_SYNONYM_ICON;
        case METADATA_FILE_DELIMITED:
            return ECoreImage.METADATA_FILE_DELIMITED_ICON;
        case METADATA_FILE_POSITIONAL:
            return ECoreImage.METADATA_FILE_POSITIONAL_ICON;
        case METADATA_FILE_REGEXP:
            return ECoreImage.METADATA_FILE_REGEXP_ICON;
        case METADATA_FILE_XML:
            return ECoreImage.METADATA_FILE_XML_ICON;
        case METADATA_FILE_EXCEL:
            return ECoreImage.METADATA_FILE_EXCEL_ICON;
        case METADATA_FILE_LDIF:
            return ECoreImage.METADATA_FILE_LDIF_ICON;
        case FOLDER:
            return ECoreImage.FOLDER_OPEN_ICON;
        case REFERENCED_PROJECTS:
            return ECoreImage.REFERENCED_ICON;
        case METADATA_GENERIC_SCHEMA:
            return ECoreImage.METADATA_GENERIC_ICON;
        case METADATA_LDAP_SCHEMA:
            return ECoreImage.METADATA_LDAP_SCHEMA_ICON;
        case METADATA_WSDL_SCHEMA:
            return ECoreImage.METADATA_WSDL_SCHEMA_ICON;
        case METADATA_SALESFORCE_SCHEMA:
            return ECoreImage.METADATA_SALESFORCE_SCHEMA_ICON;
        case METADATA_FILE_EBCDIC:
            return ECoreImage.METADATA_EBCDIC_CONNECTION_ICON;
        case METADATA_FILE_RULES:
            return ECoreImage.METADATA_RULES_ICON;
        case METADATA_FILE_LINKRULES:
            return ECoreImage.METADATA_RULES_ICON;
        default:
            return EImage.DEFAULT_IMAGE;
        }
    }

    private static MultiKeyMap componentCachedImages = new MultiKeyMap();

    public static Image getComponentIcon(IComponent component, ICON_SIZE iconSize) {
        if (component != null && iconSize != null) {

            String name = component.getName();
            Image image = (Image) componentCachedImages.get(name, iconSize);
            // PTODO check joblet component

            if (image == null || image.isDisposed()) {
                ImageDescriptor icon = null;
                switch (iconSize) {
                case ICON_16:
                    icon = component.getIcon16();
                case ICON_24:
                    icon = component.getIcon24();
                case ICON_32:
                default:
                    icon = component.getIcon32();
                }
                // default
                if (icon == null) {
                    icon = component.getIcon32();
                }
                image = icon.createImage();
                componentCachedImages.put(name, iconSize, image);
            }
            return image;
        }
        return null;
    }

}
