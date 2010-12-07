// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.utils;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstall2;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;

/**
 * DOC ycbai class global comment. Util class about java.
 */
public class JavaUtil {

    /**
     * DOC ycbai Get default jvm name.
     * 
     * @return
     */
    public static String getDefaultJVMName() {
        IVMInstall install = JavaRuntime.getDefaultVMInstall();
        if (install != null) {
            return install.getName();
        } else {
            return "nothing!";
        }
    }

    /**
     * DOC ycbai Get default javaEE name.
     * 
     * @return
     */
    public static String getDefaultEEName() {
        IVMInstall defaultVM = JavaRuntime.getDefaultVMInstall();

        IExecutionEnvironment[] environments = JavaRuntime.getExecutionEnvironmentsManager().getExecutionEnvironments();
        if (defaultVM != null) {
            for (int i = 0; i < environments.length; i++) {
                IVMInstall eeDefaultVM = environments[i].getDefaultVM();
                if (eeDefaultVM != null && defaultVM.getId().equals(eeDefaultVM.getId()))
                    return environments[i].getId();
            }
        }

        String defaultCC;
        if (defaultVM instanceof IVMInstall2) {
            defaultCC = getCompilerCompliance((IVMInstall2) defaultVM, JavaCore.VERSION_1_4);
        } else {
            defaultCC = JavaCore.VERSION_1_4;
        }

        for (int i = 0; i < environments.length; i++) {
            String eeCompliance = getExecutionEnvironmentCompliance(environments[i]);
            if (defaultCC.endsWith(eeCompliance))
                return environments[i].getId();
        }

        return "J2SE-1.6"; //$NON-NLS-1$
    }

    /**
     * DOC ycbai Get compiler compliance via vminstall and default compliance.
     * 
     * @param vMInstall
     * @param defaultCompliance
     * @return
     */
    public static String getCompilerCompliance(IVMInstall2 vMInstall, String defaultCompliance) {
        String version = vMInstall.getJavaVersion();
        if (version == null) {
            return defaultCompliance;
        } else if (version.startsWith(JavaCore.VERSION_1_6)) {
            return JavaCore.VERSION_1_6;
        } else if (version.startsWith(JavaCore.VERSION_1_5)) {
            return JavaCore.VERSION_1_5;
        } else if (version.startsWith(JavaCore.VERSION_1_4)) {
            return JavaCore.VERSION_1_4;
        } else if (version.startsWith(JavaCore.VERSION_1_3)) {
            return JavaCore.VERSION_1_3;
        } else if (version.startsWith(JavaCore.VERSION_1_2)) {
            return JavaCore.VERSION_1_3;
        } else if (version.startsWith(JavaCore.VERSION_1_1)) {
            return JavaCore.VERSION_1_3;
        }
        return defaultCompliance;
    }

    /**
     * DOC ycbai Get execution environment compliance via execution environment.
     * 
     * @param executionEnvironment
     * @return
     */
    public static String getExecutionEnvironmentCompliance(IExecutionEnvironment executionEnvironment) {
        @SuppressWarnings("rawtypes")
        Map complianceOptions = executionEnvironment.getComplianceOptions();
        if (complianceOptions != null) {
            Object compliance = complianceOptions.get(JavaCore.COMPILER_COMPLIANCE);
            if (compliance instanceof String)
                return (String) compliance;
        }

        String desc = executionEnvironment.getId();
        if (desc.indexOf(JavaCore.VERSION_1_6) != -1) {
            return JavaCore.VERSION_1_6;
        } else if (desc.indexOf(JavaCore.VERSION_1_6) != -1) {
            return JavaCore.VERSION_1_6;
        } else if (desc.indexOf(JavaCore.VERSION_1_5) != -1) {
            return JavaCore.VERSION_1_5;
        } else if (desc.indexOf(JavaCore.VERSION_1_4) != -1) {
            return JavaCore.VERSION_1_4;
        }
        return JavaCore.VERSION_1_3;
    }

    /**
     * DOC ycbai Add the java nature to project.
     * 
     * @param project
     * @param monitor
     * @throws CoreException
     */
    public static void addJavaNature(IProject project, IProgressMonitor monitor) throws CoreException {
        if (monitor != null && monitor.isCanceled()) {
            throw new OperationCanceledException();
        }
        if (project != null && !project.hasNature(JavaCore.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = JavaCore.NATURE_ID;
            description.setNatureIds(newNatures);
            project.setDescription(description, monitor);
        }
        if (monitor != null)
            monitor.worked(1);
    }

}
