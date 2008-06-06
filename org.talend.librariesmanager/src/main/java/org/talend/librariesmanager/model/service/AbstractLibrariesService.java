// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.librariesmanager.model.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.talend.commons.exception.BusinessException;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.Problem;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.librariesmanager.i18n.Messages;
import org.talend.librariesmanager.model.ModulesNeededProvider;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class AbstractLibrariesService implements ILibrariesService {

    private final List<IChangedLibrariesListener> listeners = new ArrayList<IChangedLibrariesListener>();

    public abstract String getLibrariesPath();

    public abstract URL getRoutineTemplate();

    public ELibraryInstallStatus getLibraryStatus(String libName) throws BusinessException {
        checkInstalledLibraries();
        for (ModuleNeeded current : ModulesNeededProvider.getModulesNeeded()) {
            if (current.getModuleName().equals(libName)) {
                return current.getStatus();
            }
        }
        throw new BusinessException(Messages.getString("ModulesNeededProvider.Module.Exception", libName)); //$NON-NLS-1$
    }

    public void deployLibrary(URL source) throws IOException {
        // TODO SML Allow perl module to be deploy in a folder structure in "lib/perl/..."
        File sourceFile = new File(source.getFile());
        File targetFile = new File(getLibrariesPath() + File.separatorChar + sourceFile.getName());
        FilesUtils.copyFile(sourceFile, targetFile);
        ModulesNeededProvider.userAddImportModules(targetFile.getPath(), sourceFile.getName(),
                ELibraryInstallStatus.INSTALLED);
        if (LanguageManager.getCurrentLanguage().equals(ECodeLanguage.JAVA)) {
            addResolvedClasspathPath(targetFile);
        }
        fireLibrariesChanges();
    }

    protected void addResolvedClasspathPath(File targetFile) {
    }

    public void undeployLibrary(String path) throws IOException {
        File file = new File(path);
        FilesUtils.removeFile(file);
        ModulesNeededProvider.userRemoveUnusedModules(file.getPath());
        fireLibrariesChanges();
    }

    public List<Problem> getProblems(INode node, Element element) {
        List<Problem> toReturn = new ArrayList<Problem>();
        List<ModuleNeeded> list = node.getComponent().getModulesNeeded();
        for (ModuleNeeded current : list) {
            if (current.getStatus() == ELibraryInstallStatus.NOT_INSTALLED && current.isRequired()) {
                toReturn.add(new Problem(element, "Module " + current.getModuleName() + " required", //$NON-NLS-1$ //$NON-NLS-2$
                        ProblemStatus.ERROR));
            }
        }
        return toReturn;
    }

    public void resetModulesNeeded() {
        ModulesNeededProvider.reset();
        checkLibraries();
    }

    public void checkLibraries() {
        this.checkInstalledLibraries();
        fireLibrariesChanges();
    }

    public abstract void checkInstalledLibraries();

    public void addChangeLibrariesListener(IChangedLibrariesListener listener) {
        listeners.add(listener);
    }

    private void fireLibrariesChanges() {
        for (IChangedLibrariesListener current : listeners) {
            current.afterChangingLibraries();
        }
    }
}
