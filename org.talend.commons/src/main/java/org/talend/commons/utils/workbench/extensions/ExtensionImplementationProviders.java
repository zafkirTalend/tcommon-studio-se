// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.utils.workbench.extensions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.Assert;
import org.talend.commons.exception.IllegalPluginConfigurationException;
import org.talend.commons.i18n.internal.Messages;

/**
 * Utilities class uses to get implementation of extension points defined by plug-ins. <br/>
 * 
 * $Id$
 * 
 */
public final class ExtensionImplementationProviders {

    /**
     * Default Constructor. Must not be used.
     */
    private ExtensionImplementationProviders() {
    }

    /**
     * Method used to get implementation of a extension point that accepts a single plug-in.<br/>
     * 
     * This method asserts that the param ext. point accept maximum one plug-in.<br/>
     * 
     * @param <T> - the type of the implemantation returned
     * @param extensionPoint - the ext. point to searched implementation
     * @return the implementation found or <code>null</code> if none
     * @throws IllegalPluginConfigurationException - if number of found plug-in doesn't match the specified max and min
     * value of the ext. point
     */
    public static <T> T getSingleInstance(ISimpleExtensionPoint extensionPoint) throws IllegalPluginConfigurationException {
        Assert.isTrue(extensionPoint.getMaxOcc() == 1, Messages.getString("ExtensionImplementationProviders.ExtensionPointError")); //$NON-NLS-1$

        List<T> list = getInstance(extensionPoint);

        return (list.isEmpty() ? null : list.get(0));
    }

    /**
     * Convenience method which call <code>getInstance(ISimpleExtensionPoint, String plugInId)</code> with
     * <code>null</code> as plugInId.<br/>
     * 
     * @param <T> - the type of the implemantation returned
     * @param extensionPoint - the ext. point to searched implementation
     * @return a list (may be empty) of found plug-in using this ext. point
     * @throws IllegalPluginConfigurationException - if number of found plug-in doesn't match the specified max and min
     * value of the ext. point
     */
    public static <T> List<T> getInstance(ISimpleExtensionPoint extensionPoint) throws IllegalPluginConfigurationException {
        return getInstance(extensionPoint, null);
    }

    public static List<IConfigurationElement> getInstanceV2(ISimpleExtensionPoint extensionPoint)
            throws IllegalPluginConfigurationException {
        return getInstanceV2(extensionPoint, null);
    }

    public static List<IConfigurationElement> getInstanceV2(ISimpleExtensionPoint extensionPoint, String plugInId)
            throws IllegalPluginConfigurationException {
        List<IConfigurationElement> toReturn = new ArrayList<IConfigurationElement>();

        IExtensionRegistry reg = Platform.getExtensionRegistry();
        // TODO SLM Clean
        // IExtensionPoint pt = reg.getExtensionPoint(extensionPoint.getExtPointId());
        // if (pt == null) {
        // String msg = Messages.getString("utils.workbench.extensions.noExtension", extensionPoint.getExtPointId());
        // throw new IllegalPluginConfigurationException(msg);
        // }

        // IExtension[] ext = pt.getExtensions();

        // for (IExtension currentExt : ext) {

        // if (plugInId == null || currentExt.getNamespaceIdentifier().equals(plugInId)) {
        IConfigurationElement[] configs = reg.getConfigurationElementsFor(extensionPoint.getExtPointId());
        // IConfigurationElement[] configs = currentExt.getConfigurationElements();
        for (IConfigurationElement currentConfig : configs) {
            if (currentConfig.getName().equals(extensionPoint.getElementName())) {
                toReturn.add(currentConfig);
            }
        }
        // }
        // }

        if ((toReturn.size() < extensionPoint.getMinOcc() && extensionPoint.getMinOcc() != -1)
                || (toReturn.size() > extensionPoint.getMaxOcc() && extensionPoint.getMaxOcc() != -1)) {
            String msg = Messages.getString("utils.workbench.extensions.badNumberOfExtension", extensionPoint.getExtPointId(),
                    extensionPoint.getMinOcc(), extensionPoint.getMaxOcc(), toReturn.size());
            throw new IllegalPluginConfigurationException(msg);
        }

        return toReturn;
    }

    /**
     * Method used to get implementations of a extension point.<br/>
     * 
     * @param <T> - the type of the implemantation returned
     * @param extensionPoint - the ext. point to searched implementation
     * @param plugInId - use to search a specific plug-in. If <code>null</code>, all plug-in are returned.
     * @return a list (may be empty) of found plug-in using this ext. point
     * @throws IllegalPluginConfigurationException - if number of found plug-in doesn't match the specified max and min
     * value of the ext. point
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getInstance(ISimpleExtensionPoint extensionPoint, String plugInId)
            throws IllegalPluginConfigurationException {
        List<T> toReturn = new ArrayList<T>();

        IExtensionRegistry reg = Platform.getExtensionRegistry();

        IExtensionPoint pt = reg.getExtensionPoint(extensionPoint.getExtPointId());
        if (pt == null) {
            String msg = Messages.getString("utils.workbench.extensions.noExtension", extensionPoint.getExtPointId());
            throw new IllegalPluginConfigurationException(msg);
        }

        IExtension[] ext = pt.getExtensions();

        for (IExtension currentExt : ext) {

            if (plugInId == null || currentExt.getNamespaceIdentifier().equals(plugInId)) {
                IConfigurationElement[] configs = currentExt.getConfigurationElements();
                for (IConfigurationElement currentConfig : configs) {
                    if (currentConfig.getName().equals(extensionPoint.getElementName())) {
                        try {
                            T factory = (T) currentConfig.createExecutableExtension("class");
                            toReturn.add(factory);
                        } catch (CoreException e) {
                            throw new IllegalPluginConfigurationException(e);
                        }
                    }
                }
            }
        }

        if ((toReturn.size() < extensionPoint.getMinOcc() && extensionPoint.getMinOcc() != -1)
                || (toReturn.size() > extensionPoint.getMaxOcc() && extensionPoint.getMaxOcc() != -1)) {
            String msg = Messages.getString("utils.workbench.extensions.badNumberOfExtension", extensionPoint.getExtPointId(),
                    extensionPoint.getMinOcc(), extensionPoint.getMaxOcc(), toReturn.size());
            throw new IllegalPluginConfigurationException(msg);
        }

        return toReturn;
    }
}
