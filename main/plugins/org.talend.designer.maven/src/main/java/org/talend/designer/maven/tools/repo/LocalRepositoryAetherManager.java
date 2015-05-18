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
package org.talend.designer.maven.tools.repo;

import io.tesla.aether.connector.AetherRepositoryConnectorFactory;

import java.io.File;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.deployment.DeployRequest;
import org.eclipse.aether.deployment.DeployResult;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.installation.InstallRequest;
import org.eclipse.aether.installation.InstallResult;
import org.eclipse.aether.installation.InstallationException;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.core.resources.IContainer;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.runtime.maven.MavenArtifact;
import org.talend.core.runtime.maven.MavenUrlHelper;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 * FIXME, not impl yet
 */
public class LocalRepositoryAetherManager extends LocalRepositoryManager {

    private RepositorySystem repositorySystem;

    private LocalRepository localRepo;

    public LocalRepositoryAetherManager(File baseDir) {
        super(baseDir);
        this.repositorySystem = newRepositorySystem();
        this.localRepo = newRepository();
    }

    public LocalRepositoryAetherManager(IContainer container) {
        this(container.getLocation().toFile());
    }

    protected RepositorySystem getRepositorySystem() {
        return repositorySystem;
    }

    protected LocalRepository getLocalRepo() {
        return localRepo;
    }

    protected RepositorySystem newRepositorySystem() {
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class, AetherRepositoryConnectorFactory.class);

        // locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
        // locator.addService(TransporterFactory.class, FileTransporterFactory.class);
        // locator.addService(TransporterFactory.class, HttpTransporterFactory.class);

        locator.setErrorHandler(new DefaultServiceLocator.ErrorHandler() {

            @Override
            public void serviceCreationFailed(Class<?> type, Class<?> impl, Throwable exception) {
                ExceptionHandler.process(exception);
            }
        });
        return locator.getService(RepositorySystem.class);
    }

    protected LocalRepository newRepository() {
        File dir = getRepoFolder();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new LocalRepository(dir);
    }

    protected RepositorySystemSession newSession() {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepo));

        // session.setTransferListener(new ConsoleTransferListener());
        // session.setRepositoryListener(new ConsoleRepositoryListener());

        // uncomment to generate dirty trees
        // session.setDependencyGraphTransformer( null );

        return session;
    }

    public void install(File file, MavenArtifact artifact) throws Exception {
        Artifact jarArtifact = createArtifact(file, artifact);

        InstallRequest installRequest = new InstallRequest().addArtifact(jarArtifact);

        // TODO, so far, can't generate the pom auto, so there is no pom in m2/repository.
        // Artifact pomArtifact = null;
        // pomArtifact = new SubArtifact(jarArtifact, "", "pom").setFile(new File("pom.xml"));
        // if (pomArtifact != null) {
        // installRequest.addArtifact(pomArtifact);
        // }

        InstallResult result = getRepositorySystem().install(newSession(), installRequest);
        // result.getArtifacts();
    }

    @Override
    public void deploy(File file, MavenArtifact artifact) throws Exception {
        Artifact jarArtifact = createArtifact(file, artifact);

        DeployRequest deployRequest = new DeployRequest().addArtifact(jarArtifact);

        // Artifact pomArtifact = null;
        // pomArtifact = new SubArtifact(jarArtifact, "", "pom").setFile(new File("pom.xml"));
        // if (pomArtifact != null) {
        // installRequest.addArtifact(pomArtifact);
        // }

        DeployResult result = getRepositorySystem().deploy(newSession(), deployRequest);
    }

    protected Artifact createArtifact(File file, MavenArtifact artifact) throws InstallationException {
        if (!file.exists()) {
            throw new InstallationException("The deploy file " + file + " is not existed.");
        }
        if (artifact == null) { // use default settings
            String mvnUrl = MavenUrlHelper.generateMvnUrlForJarName(file.getName());
            artifact = MavenUrlHelper.parseMvnUrl(mvnUrl);
            if (artifact == null) {
                throw new InstallationException("Can't deploy the file: " + file);
            }
        }
        Artifact jarArtifact = new DefaultArtifact(artifact.getGroupId(), artifact.getArtifactId(), artifact.getClassifier(),
                artifact.getType(), artifact.getVersion()).setFile(file);

        return jarArtifact;
    }

}
