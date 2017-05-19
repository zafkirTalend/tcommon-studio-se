// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.updates.runtime.engine.factory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.talend.commons.CommonsPlugin;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.updates.runtime.engine.component.ComponentNexusP2ExtraFeature;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.model.ExtraFeature;
import org.talend.updates.runtime.model.FeatureCategory;
import org.talend.updates.runtime.model.P2ExtraFeature;
import org.talend.updates.runtime.model.P2ExtraFeatureException;
import org.talend.updates.runtime.nexus.component.ComponentIndexBean;
import org.talend.updates.runtime.nexus.component.ComponentIndexManager;
import org.talend.updates.runtime.nexus.component.NexusComponentsTransport;

/**
 * DOC Talend class global comment. Detailled comment
 */
public class ComponentsNexusInstallFactory extends AbstractExtraUpdatesFactory {

    protected File downloadFolder;

    private ComponentIndexManager indexManager = new ComponentIndexManager();

    public ComponentsNexusInstallFactory() {
        super();
    }

    protected File getDownloadFolder() {
        if (this.downloadFolder == null) {
            final String downloadedName = ".DownloadedComponents"; //$NON-NLS-1$
            try {
                this.downloadFolder = new File(Platform.getConfigurationLocation().getDataArea(downloadedName).getPath());
            } catch (IOException e) {
                this.downloadFolder = new File(System.getProperty("user.dir") + '/' + downloadedName); //$NON-NLS-1$
            }
        }
        return this.downloadFolder;
    }

    public String getP2ProfileId() {
        return "_SELF_"; //$NON-NLS-1$
    }

    public URI getP2AgentUri() {
        return null;
    }

    protected Set<P2ExtraFeature> getAllExtraFeatures(IProgressMonitor monitor) {
        try {
            ComponentNexusP2ExtraFeature defaultFeature = new ComponentNexusP2ExtraFeature();
            return retrieveComponentsFromIndex(monitor, defaultFeature);
        } catch (Exception e) {
            if (CommonsPlugin.isDebugMode()) {
                ExceptionHandler.process(e);
            }
            return Collections.emptySet();
        }
    }

    protected Set<P2ExtraFeature> retrieveComponentsFromIndex(IProgressMonitor monitor,
            ComponentNexusP2ExtraFeature defaultFeature) throws Exception {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        if (monitor.isCanceled()) {
            throw new OperationCanceledException();
        }

        NexusComponentsTransport transport = new NexusComponentsTransport(defaultFeature.getNexusURL(),
                defaultFeature.getNexusUser(), defaultFeature.getNexusPass());
        if (transport.isAvailable()) {
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            final Document doc = transport.downloadXMLDocument(monitor, defaultFeature.getIndexArtifact());

            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            final Set<P2ExtraFeature> p2Features = createFeatures(defaultFeature, doc);
            return p2Features;
        }
        return Collections.emptySet();
    }

    Set<P2ExtraFeature> createFeatures(ComponentNexusP2ExtraFeature defaultFeature, Document doc) {
        if (doc == null) {
            return Collections.emptySet();
        }
        Set<P2ExtraFeature> p2Features = new LinkedHashSet<>();
        if (doc != null) {
            final List<ComponentIndexBean> indexBeans = indexManager.parse(doc);
            for (ComponentIndexBean b : indexBeans) {
                final String[] products = b.getProducts();
                if (products != null && products.length > 0) {
                    String acronym = getAcronym();
                    if (!Arrays.asList(products).contains(acronym)) {
                        continue; // ignore it in product
                    }
                }
                final ComponentNexusP2ExtraFeature cnFeature = new ComponentNexusP2ExtraFeature(b.getName(), b.getVersion(),
                        b.getDescription(), b.getProduct(), b.getMvnURI(), b.getBundleId());

                // use same url and user with index
                cnFeature.setNexusURL(defaultFeature.getNexusURL());
                cnFeature.setNexusUser(defaultFeature.getNexusUser());
                cnFeature.setNexusPass(defaultFeature.getNexusPass());
                cnFeature.setDownloadFolder(getDownloadFolder()); // use same folder

                p2Features.add(cnFeature);
            }
        }
        return p2Features;
    }

    @Override
    public void retrieveUninstalledExtraFeatures(IProgressMonitor progress, Set<ExtraFeature> uninstalledExtraFeatures)
            throws Exception {
        SubMonitor mainSubMonitor = SubMonitor.convert(progress, 5);
        mainSubMonitor.worked(1);
        Set<P2ExtraFeature> allExtraFeatures = getAllExtraFeatures(mainSubMonitor);
        mainSubMonitor.worked(1);
        if (mainSubMonitor.isCanceled()) {
            return;
        }
        FeatureCategory category = new FeatureCategory();
        SubMonitor checkSubMonitor = SubMonitor.convert(mainSubMonitor.newChild(1), allExtraFeatures.size() * 2);
        for (P2ExtraFeature extraF : allExtraFeatures) {
            try {
                P2ExtraFeature extraFeature = extraF.getInstalledFeature(checkSubMonitor.newChild(1));
                if (extraFeature != null) {
                    addToCategory(category, extraFeature);
                }
                checkSubMonitor.worked(1);
            } catch (P2ExtraFeatureException e) {
                ExceptionHandler.process(e);
            }
        }
        int componentsSize = category.getChildren().size();
        if (componentsSize > 0) {
            category.setName(Messages.getString("ComponentsNexusInstallFactory.categorytitile", componentsSize)); //$NON-NLS-1$
            addToSet(uninstalledExtraFeatures, category);
        }
    }

}
