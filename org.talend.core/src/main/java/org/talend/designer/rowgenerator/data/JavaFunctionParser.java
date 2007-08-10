// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.ui.JavadocContentAccess;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.data.container.Container;
import org.talend.commons.utils.data.container.ContentList;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.repository.IRepositoryObject;
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
            RootContainer<String, IRepositoryObject> routineContainer = factory.getRoutine();
            final List<Container<String, IRepositoryObject>> subContainer = routineContainer.getSubContainer();
            for (Container<String, IRepositoryObject> container : subContainer) {
                if (RepositoryConstants.SYSTEM_DIRECTORY.equals(container.getLabel())) {
                    final List<IRepositoryObject> members = container.getMembers();
                    for (IRepositoryObject object : members) {
                        systems.add(object.getLabel());
                    }
                }
            }

            IJavaProject javaProject = CorePlugin.getDefault().getRunProcessService().getJavaProject();
            if (javaProject != null) {
                IProject project = javaProject.getProject();
                IFolder srcFolder = project.getFolder(JavaUtils.JAVA_SRC_DIRECTORY);
                IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(srcFolder);
                IPackageFragment routinesPkg = root.getPackageFragment(JavaUtils.JAVA_ROUTINES_DIRECTORY);
                IJavaElement[] elements = routinesPkg.getChildren();
                for (IJavaElement element : elements) {
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
                                        parseJavaCommentToFunctions(str.toString(), sourceType.getElementName(), sourceType
                                                .getFullyQualifiedName(), method.getElementName(), systems.contains(sourceType
                                                .getElementName()));
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.rowgenerator.data.AbstractFunctionParser#parseDescription(java.lang.String)
     */
    @Override
    protected String parseDescription(String string) {
        String string2 = "";
        if (string.indexOf("{talendTypes}") > 0) {
            string2 = string.substring(0, string.indexOf("{talendTypes}"));
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
        if (string2 != null && !string2.trim().equals("")) {
            JavaType type = JavaTypesManager.getJavaTypeFromLabel(string2);
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
            if (!functionType.equals(EMPTY_STRING)) {
                Parameter[] paras = convertToParameter(parameter);
                Function function = new Function();
                function.setName(funcName);
                function.setDescription(des);
                function.setParameters(Arrays.asList(paras));
                function.setCategory(category);
                function.setUserDefined(!isSystem);
                TalendType talendType = getTalendType(functionType);
                talendType.addFunctions(function);
                typeMethods.put(functionType + "." + funcName, className + "." + funcName);
                typePackgeMethods.put(functionType + "." + funcName, fullName + "." + funcName);
                function.setTalendType(talendType);
            }
        } catch (Exception e) {
            logger.error("Runtines : \"" + fullName + "." + funcName + "\" parse failed. please check your the Method.", e);
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
