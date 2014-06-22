// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.repository;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.talend.core.repository.IExtendRepositoryNode;
import org.talend.core.utils.RegistryReader;
import org.talend.repository.model.IRepositoryNode;
import org.talend.repository.model.IRepositoryNode.ENodeType;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
@SuppressWarnings("rawtypes")
public class RepositoryNodeProviderRegistryReader extends RegistryReader {

    private static final Logger log = Logger.getLogger(RepositoryNodeProviderRegistryReader.class);

    private static final String REPOSITORY_NODE_ELEMENT_NAME = "repositoryNode"; //$NON-NLS-1$

    private static final String EXTRA_PRODUCTS_ELEMENT_NAME = "extraProducts"; //$NON-NLS-1$

    private static final String EMPTY = ""; //$NON-NLS-1$

    private static final String SPLIT_CHARS = "\\|"; //$NON-NLS-1$

    private static final String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    private ImageRegistry imageRegistry;

    private Map<ERepositoryObjectType, ReposioryNodeProvider> allDynamicRepositoryNodes;

    static class ReposioryNodeProvider {

        ERepositoryObjectType type;

        IRepositoryNode.ENodeType nodeType = IRepositoryNode.ENodeType.SYSTEM_FOLDER;

        IExtendRepositoryNode extendRepNode;

        String pluginId;

        String iconPath;
    }

    private Map<ERepositoryObjectType, Set<String>> parentTypesMap;

    private Map<String, Set<String>> extraProductsMap;

    private static RepositoryNodeProviderRegistryReader instance;

    public static RepositoryNodeProviderRegistryReader getInstance() {
        if (instance == null) {
            synchronized (RepositoryNodeProviderRegistryReader.class) {
                if (instance == null) {
                    instance = new RepositoryNodeProviderRegistryReader();
                }
            }
        }
        return instance;
    }

    private RepositoryNodeProviderRegistryReader() {
        super("org.talend.core.repository", "repository_node_provider"); //$NON-NLS-1$  //$NON-NLS-2$
    }

    void init() {
        if (this.allDynamicRepositoryNodes == null) {
            synchronized (RepositoryNodeProviderRegistryReader.class) {

                this.allDynamicRepositoryNodes = new HashMap<ERepositoryObjectType, ReposioryNodeProvider>();
                readRegistry();

                // add parent
                Map<ERepositoryObjectType, Set<String>> parentTypes = getParentTypesMap();
                Iterator<ERepositoryObjectType> typesIterator = parentTypes.keySet().iterator();
                while (typesIterator.hasNext()) {
                    ERepositoryObjectType currentType = typesIterator.next();
                    Set<String> set = parentTypes.get(currentType);
                    if (set != null) {
                        Iterator<String> parentIterator = set.iterator();
                        while (parentIterator.hasNext()) {
                            ERepositoryObjectType parentType = ERepositoryObjectType.valueOf(ERepositoryObjectType.class,
                                    parentIterator.next());
                            if (parentType != null) {
                                currentType.setAParent(parentType);
                            }
                        }
                    }
                }

                // add extra products
                Map<String, Set<String>> extraProducts = getExtraProductsMap();
                Iterator<String> productsIterator = extraProducts.keySet().iterator();
                while (productsIterator.hasNext()) {
                    String typeName = productsIterator.next();
                    ERepositoryObjectType type = ERepositoryObjectType.valueOf(ERepositoryObjectType.class, typeName);
                    if (type == null) {
                        log.log(Level.WARN, "Can't find the type " + typeName);
                    } else {
                        Set<String> set = extraProducts.get(typeName);
                        if (set != null) {
                            type.addExtraProducts(set.toArray(new String[0]));
                        }
                    }
                }

            }
        }
    }

    public ERepositoryObjectType[] getAllDynamicTypes() {
        init();

        return this.allDynamicRepositoryNodes.keySet().toArray(new ERepositoryObjectType[0]);
    }

    public IExtendRepositoryNode getExtendNodeByType(ERepositoryObjectType type) {
        init();
        if (type != null) {
            ReposioryNodeProvider provider = this.allDynamicRepositoryNodes.get(type);
            if (provider != null) {
                return provider.extendRepNode;
            }

        }
        return null;
    }

    private ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    public Image getImage(ERepositoryObjectType type) {
        ReposioryNodeProvider provider = this.allDynamicRepositoryNodes.get(type);
        Image image = null;
        if (provider != null) {
            String icon = provider.iconPath;
            if (icon != null && !EMPTY.equals(icon.trim())) {
                image = getImageRegistry().get(icon);
                if (image == null || image.isDisposed()) {
                    ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(provider.pluginId, icon);
                    if (imageDescriptor != null) {
                        image = imageDescriptor.createImage();
                        if (image != null) {
                            getImageRegistry().put(icon, image);
                        }
                    }
                }
            }
            // if (image == null) { // set a default
            // image = ImageProvider.getImage(EImage.DEFAULT_IMAGE);
            // }
        }
        return image;
    }

    public IRepositoryNode.ENodeType getNodeType(ERepositoryObjectType type) {
        ReposioryNodeProvider provider = this.allDynamicRepositoryNodes.get(type);
        if (provider != null) {
            return provider.nodeType;
        }
        return null;
    }

    private Map<ERepositoryObjectType, Set<String>> getParentTypesMap() {
        if (this.parentTypesMap == null) {
            this.parentTypesMap = new HashMap<ERepositoryObjectType, Set<String>>();
        }
        return this.parentTypesMap;
    }

    private void addAParent(ERepositoryObjectType childType, String parentTypeName) {
        if (childType != null && parentTypeName != null && !EMPTY.equals(parentTypeName.trim())) {
            Set<String> set = getParentTypesMap().get(childType);
            if (set == null) {
                set = new HashSet<String>();
                getParentTypesMap().put(childType, set);
            }
            set.add(parentTypeName);
        }
    }

    private Map<String, Set<String>> getExtraProductsMap() {
        if (this.extraProductsMap == null) {
            this.extraProductsMap = new HashMap<String, Set<String>>();
        }
        return this.extraProductsMap;
    }

    private void addExtraProducts(String type, String[] products) {
        if (type != null && products != null && products.length > 0) {
            Set<String> set = getExtraProductsMap().get(type);
            if (set == null) {
                set = new HashSet<String>();
                getExtraProductsMap().put(type, set);
            }
            for (String prod : products) {
                set.add(prod.trim());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.utils.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
     */
    @Override
    protected boolean readElement(final IConfigurationElement element) {
        if (REPOSITORY_NODE_ELEMENT_NAME.equals(element.getName())) {
            SafeRunner.run(new RegistryReader.RegistrySafeRunnable() {

                @Override
                public void run() throws Exception {
                    String key = element.getAttribute("key");
                    String label = element.getAttribute("label");
                    String alias = element.getAttribute("alias");
                    String type = element.getAttribute("type");
                    String folder = element.getAttribute("folder");
                    if (folder == null) {
                        folder = EMPTY;
                    }
                    if (key == null || key.trim().length() == 0) {
                        key = type;
                    }
                    String isResouce = element.getAttribute("isResouce");
                    boolean isResource = false;
                    if (isResouce != null) {
                        isResource = Boolean.parseBoolean(isResouce);
                    }
                    boolean[] resource = new boolean[] { isResource };

                    String ordinalStr = element.getAttribute("ordinal");
                    int ordinal = 0;
                    if (ordinalStr != null && ordinalStr.length() > 0) {
                        try {
                            ordinal = Integer.parseInt(ordinalStr);
                        } catch (NumberFormatException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                    String iconPath = element.getAttribute("icon");
                    // IImage icon = null;
                    // PTODO
                    String namePatternAttribute = element.getAttribute("name_pattern");

                    String[] products = getProducts(element);

                    String rightAttribute = element.getAttribute("user_right");
                    if (rightAttribute == null) {
                        rightAttribute = EMPTY;
                    }

                    String[] user_right = rightAttribute.split(";");
                    if (user_right == null) {
                        user_right = new String[] { rightAttribute };
                    }

                    IExtendRepositoryNode extendNode = null;
                    String className = element.getAttribute(CLASS_ATTRIBUTE);
                    if (className != null && !EMPTY.equals(className.trim())) {
                        Object extensionNode = element.createExecutableExtension(CLASS_ATTRIBUTE);
                        if (extensionNode instanceof IExtendRepositoryNode) {
                            extendNode = (IExtendRepositoryNode) extensionNode;
                            ordinal = extendNode.getOrdinal();
                            // if (extendNode.getNodeImage() != null) {
                            // icon = extendNode.getNodeImage();
                            // }
                        }
                    }
                    Object instance = null;
                    if (namePatternAttribute == null || "".equals(namePatternAttribute.trim())) {
                        Constructor dynamicConstructor = getConstructor(ERepositoryObjectType.class, new Class[] { String.class,
                                String.class, String.class, String.class, int.class, boolean.class, String.class, String[].class,
                                String[].class, boolean[].class });
                        instance = dynamicConstructor.newInstance(key, label, folder, type, ordinal, false, alias, products,
                                user_right, resource);

                    } else {
                        Constructor dynamicConstructor = getConstructor(ERepositoryObjectType.class, new Class[] { String.class,
                                String.class, String.class, String.class, int.class, boolean.class, String.class, String[].class,
                                String[].class, String.class, boolean[].class });
                        instance = dynamicConstructor.newInstance(key, label, folder, type, ordinal, false, alias, products,
                                user_right, namePatternAttribute.trim(), resource);
                    }

                    if (instance != null && instance instanceof ERepositoryObjectType) {
                        ERepositoryObjectType currentType = (ERepositoryObjectType) instance;
                        String parentTypesAttr = element.getAttribute("parentNodeType");
                        if (parentTypesAttr != null) {
                            String[] parentTypes = parentTypesAttr.split(SPLIT_CHARS);
                            for (String parentTypeName : parentTypes) {
                                addAParent(currentType, parentTypeName);
                            }
                        }
                        ReposioryNodeProvider provider = new ReposioryNodeProvider();
                        provider.type = currentType;
                        provider.extendRepNode = extendNode;
                        provider.iconPath = iconPath;
                        provider.pluginId = element.getDeclaringExtension().getNamespaceIdentifier();
                        ENodeType nodeType = getNodeType(element);
                        if (nodeType != null) {
                            provider.nodeType = nodeType;
                        }
                        allDynamicRepositoryNodes.put(currentType, provider);
                    } else {
                        log.log(Level.WARN, "The type can't be initialized.");
                    }

                }
            });
            return true;
        } else if (EXTRA_PRODUCTS_ELEMENT_NAME.equals(element.getName())) {
            SafeRunner.run(new RegistryReader.RegistrySafeRunnable() {

                @Override
                public void run() throws Exception {
                    String type = element.getAttribute("type");
                    String[] products = getProducts(element);
                    addExtraProducts(type, products);
                }
            });
            return true;

        }
        return false;
    }

    private String[] getProducts(final IConfigurationElement element) {
        String[] products = null;
        String productsAttribute = element.getAttribute("products"); //$NON-NLS-1$
        if (productsAttribute != null && productsAttribute.length() > 0) {
            products = productsAttribute.split(SPLIT_CHARS);
            if (products == null) {
                products = new String[] { productsAttribute };
            }
        }

        return products != null ? products : new String[0];
    }

    private IRepositoryNode.ENodeType getNodeType(final IConfigurationElement element) {
        String nodeTypeStr = element.getAttribute("nodeType");
        if (nodeTypeStr != null && nodeTypeStr.trim().length() > 0) {
            ENodeType[] types = IRepositoryNode.ENodeType.values();
            for (ENodeType type : types) {
                if (type.name().equals(nodeTypeStr)) {
                    return type;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <E> Constructor<E> getConstructor(Class<E> clazz, Class<?>[] argTypes) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            try {
                return (Constructor<E>) c.getDeclaredConstructor(argTypes);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
}
