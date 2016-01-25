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
package org.talend.designer.rowgenerator.data;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.ui.JavadocContentAccess;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.data.container.Container;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.general.Project;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;

/**
 * qzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2007-3-28 下午02:37:17 (星期五, 29 九月 2006) qzhang $
 * 
 */
public class JavaFunctionParser extends AbstractFunctionParser {

    private static Logger logger = Logger.getLogger(JavaFunctionParser.class);

    // k: (Talend type Name).(method Name) v:(class Name).(method Name)
    private static Map<String, String> typeMethods = new HashMap<String, String>();

    private static Map<String, String> typePackgeMethods = new HashMap<String, String>();

    private List<String> systems;

    /**
     * qzhang JavaFunctionParser constructor comment.
     */
    public JavaFunctionParser() {
        super();
    }

    @SuppressWarnings("restriction")
    public void parse() {
        typeMethods.clear();
        systems = new ArrayList<String>();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        try {
            RootContainer<String, IRepositoryViewObject> routineContainer = factory.getMetadata(ERepositoryObjectType.ROUTINES);
            final List<Container<String, IRepositoryViewObject>> subContainer = routineContainer.getSubContainer();
            for (Container<String, IRepositoryViewObject> container : subContainer) {
                if (RepositoryConstants.SYSTEM_DIRECTORY.equals(container.getLabel())) {
                    final List<IRepositoryViewObject> members = container.getMembers();
                    for (IRepositoryViewObject object : members) {
                        systems.add(object.getLabel());
                    }
                }
            }

            IJavaProject javaProject = CorePlugin.getDefault().getRunProcessService().getJavaProject();
            if (javaProject != null) {
                IProject project = javaProject.getProject();
                IFolder srcFolder = project.getFolder(JavaUtils.JAVA_SRC_DIRECTORY);

                IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(srcFolder);

                List<IJavaElement> elements = new ArrayList<IJavaElement>();

                addEveryProjectElements(root, elements);

                // for (IJavaElement element : elements) {
                // see bug 8055,reversal the getLastName() method
                for (int i = elements.size(); i > 0; i--) {
                    IJavaElement element = elements.get(i - 1);
                    if (element instanceof ICompilationUnit) {
                        ICompilationUnit compilationUnit = (ICompilationUnit) element;
                        IType[] types = compilationUnit.getAllTypes();
                        if (types.length > 0) {
                            SourceType sourceType = (SourceType) types[0];
                            IMethod[] methods = sourceType.getMethods();
                            for (IMethod method : methods) {
                                try {
                                    Reader reader = JavadocContentAccess.getContentReader(method, true);
                                    if (reader != null) {
                                        char[] charBuffer = new char[1024];
                                        StringBuffer str = new StringBuffer();
                                        int index = 0;
                                        while ((index = reader.read(charBuffer)) != -1) {
                                            str.append(charBuffer, 0, index);
                                            index = 0;
                                        }
                                        reader.close();
                                        parseJavaCommentToFunctions(str.toString(), sourceType.getElementName(),
                                                sourceType.getFullyQualifiedName(), method.getElementName(),
                                                systems.contains(sourceType.getElementName()));
                                    }
                                } catch (Exception e) {
                                    ExceptionHandler.process(e);
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    private void addEveryProjectElements(IPackageFragmentRoot root, List<IJavaElement> elements) throws JavaModelException {
        if (root == null || elements == null) {
            return;
        }
        // system
        IPackageFragment routinesPkg = root.getPackageFragment(JavaUtils.JAVA_ROUTINES_DIRECTORY);
        if (routinesPkg != null && routinesPkg.exists()) {
            elements.addAll(Arrays.asList(routinesPkg.getChildren()));
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        Project currentProject = projectManager.getCurrentProject();
        // current project
        IPackageFragment userRoutinesPkg = root.getPackageFragment(JavaUtils.JAVA_ROUTINES_DIRECTORY + "." //$NON-NLS-1$
                + currentProject.getLabel().toLowerCase());
        if (userRoutinesPkg != null && userRoutinesPkg.exists()) {
            elements.addAll(Arrays.asList(userRoutinesPkg.getChildren()));
        }
        // referenced project.
        projectManager.retrieveReferencedProjects();
        for (Project p : projectManager.getReferencedProjects()) {
            userRoutinesPkg = root.getPackageFragment(JavaUtils.JAVA_ROUTINES_DIRECTORY + "." + p.getLabel().toLowerCase()); //$NON-NLS-1$
            if (userRoutinesPkg != null && userRoutinesPkg.exists()) {
                elements.addAll(Arrays.asList(userRoutinesPkg.getChildren()));
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parseDescription(java.lang.String)
     */
    @Override
    protected String parseDescription(String string) {
        String string2 = ""; //$NON-NLS-1$
        if (string.indexOf("{talendTypes}") > 0) { //$NON-NLS-1$
            string2 = string.substring(0, string.indexOf("{talendTypes}")); //$NON-NLS-1$
        }
        return string2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parseFunctionType(java.lang.String)
     */
    @Override
    protected String parseFunctionType(String string) {
        String string2 = super.parseFunctionType(string);
        if (string2 != null && !string2.trim().equals("")) { //$NON-NLS-1$
            JavaType type = JavaTypesManager.getJavaTypeFromLabel(string2);
            if (type == null) {
                type = JavaTypesManager.getJavaTypeFromName(string2);
            }
            if (type != null) {
                return type.getId();
            }
        }
        return EMPTY_STRING;
    }

    /**
     * qzhang Comment method "parseJavaCommentToFunctions".
     * 
     * @param string
     * @param isSystem
     */
    private void parseJavaCommentToFunctions(String string, String className, String fullName, String funcName, boolean isSystem) {
        try {
            String des = parseDescription(string);
            String category = parseCategoryType(string);
            String functionType = parseFunctionType(string);
            String[] parameter = parseFunctionParameters(string);
            if (functionType != null && category.trim().length() > 0) {
                Parameter[] paras = convertToParameter(parameter);
                Function function = new Function();
                function.setClassName(className);
                function.setName(funcName);
                function.setDescription(des);
                function.setParameters(Arrays.asList(paras));
                if (!category.equals(EMPTY_STRING)) {
                    function.setCategory(category);
                }
                function.setUserDefined(!isSystem);
                TalendType talendType = getTalendType(functionType);
                talendType.addFunctions(function);
                typeMethods.put(functionType + "." + funcName, className + "." + funcName); //$NON-NLS-1$ //$NON-NLS-2$
                typePackgeMethods.put(functionType + "." + funcName, fullName + "." + funcName); //$NON-NLS-1$ //$NON-NLS-2$
                function.setTalendType(talendType);
            }
        } catch (Exception e) {
            logger.error(Messages.getString("JavaFunctionParser.checkMethod", fullName, funcName), e); //$NON-NLS-1$
        }
    }

    public static Map<String, String> getTypeMethods() {
        return typeMethods;
    }

    /**
     * Getter for typePackgeMethods.
     * 
     * @return the typePackgeMethods
     */
    public static Map<String, String> getTypePackgeMethods() {
        return typePackgeMethods;
    }
}
