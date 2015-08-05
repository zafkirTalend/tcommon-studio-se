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
package org.talend.core.ui.images;

import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.talend.commons.ui.runtime.image.IImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.runtime.image.ImageUtils.ICON_SIZE;
import org.talend.core.CorePlugin;
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
        return RepositoryImageProvider.getIcon(type);
    }

    private static MultiKeyMap componentCachedImages = new MultiKeyMap();

    public static Image getComponentIcon(IComponent component, ICON_SIZE iconSize) {
        if (component != null && iconSize != null) {

            String name = component.getName();
            Image image = null;
            if (iconSize == ICON_SIZE.ICON_32 && CorePlugin.getDefault().getDesignerCoreService().isDummyComponent(component)) {
                image = component.getIcon32().createImage();
            } else {
                image = (Image) componentCachedImages.get(name, iconSize);
            }
            if (image == null || image.isDisposed()) {
                ImageDescriptor icon = null;
                switch (iconSize) {
                case ICON_16:
                    icon = component.getIcon16();
                    break;
                case ICON_24:
                    icon = component.getIcon24();
                    break;
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

    public static void removeComponentImage(String name) {
        if (name != null && !name.equals("")) {
            componentCachedImages.remove(name, ICON_SIZE.ICON_16);
            componentCachedImages.remove(name, ICON_SIZE.ICON_24);
            componentCachedImages.remove(name, ICON_SIZE.ICON_32);
        }
    }
}
