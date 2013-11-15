package org.talend.core.repository;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ISVNProviderServiceInCoreRuntime;
import org.talend.core.PluginChecker;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.runtime.CoreRuntimePlugin;

public class DepolySvnLibLoginTask implements IRunnableWithProgress {

    public static final String TALEND_LIBRARY_PATH = "talend.library.path";

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        File libFile = new File(getLibrariesPath(ECodeLanguage.JAVA));
        if (PluginChecker.isSVNProviderPluginLoaded()) {
            ISVNProviderServiceInCoreRuntime service = (ISVNProviderServiceInCoreRuntime) GlobalServiceRegister.getDefault()
                    .getService(ISVNProviderServiceInCoreRuntime.class);
            if (service != null && service.isSvnLibSetupOnTAC() && !getRepositoryContext().isOffline()) {
                boolean exist = libFile.exists();
                boolean flag = service.isInSvn(libFile.getAbsolutePath());
                if (exist && flag) { // means lib/java already link to svn
                    service.doUpdateAndCommit(libFile.getAbsolutePath());
                } else if (!exist) { // means lib/java not exist,should create lib/java and checkout
                    service.createFolderAndLinkToSvn(libFile.getAbsolutePath());
                } else if (exist && !flag) {// means lib/java is exist ,but not link to svn.
                    service.synToSvn(libFile.getAbsolutePath());
                }
                // move the jar from project/libs to svnlib
                service.synProjectLib(libFile.getAbsolutePath());
            }
        }
    }

    private static String getLibrariesPath(ECodeLanguage language) {
        String libPath = System.getProperty(TALEND_LIBRARY_PATH);
        if (libPath != null) {
            return libPath;
        }
        return Platform.getInstallLocation().getURL().getFile() + "lib/java";
    }

    private RepositoryContext getRepositoryContext() {
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        return (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
    }
}
