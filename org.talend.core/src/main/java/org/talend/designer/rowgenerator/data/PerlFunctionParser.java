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
package org.talend.designer.rowgenerator.data;

import java.io.File;
import java.io.FileInputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.utils.data.container.Container;
import org.talend.commons.utils.data.container.Content;
import org.talend.commons.utils.data.container.ContentList;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.CorePlugin;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.RepositoryConstants;

/**
 * class global comment. Detailled comment <br/>
 * 
 * $Id: FunctionParser.java,v 1.3 2007/01/31 05:20:51 pub Exp $
 * 
 */
public class PerlFunctionParser extends AbstractFunctionParser {

    /**
     * @uml.property name="file"
     */
    private File[] files;

    private List<String> systems;

    public PerlFunctionParser() {
        List<File> filesList = new ArrayList<File>();
        systems = new ArrayList<String>();
        // List<URL> list = RowGeneratorPlugin.getDefault().getPerlModuleService().getBuiltInRoutines();
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        // TODO find a better way to find routine files

        try {

            RootContainer<String, IRepositoryObject> routineContainer = factory.getRoutine();
            ContentList<String, IRepositoryObject> routineAbsoluteMembers = routineContainer.getAbsoluteMembers();
            final List<Container<String, IRepositoryObject>> subContainer = routineContainer.getSubContainer();
            for (Container<String, IRepositoryObject> container : subContainer) {
                if (RepositoryConstants.SYSTEM_DIRECTORY.equals(container.getLabel())) {
                    final List<IRepositoryObject> members = container.getMembers();
                    for (IRepositoryObject object : members) {
                        URI uri = CommonPlugin.asLocalURI(object.getProperty().getItem().eResource().getURI());
                        final String[] segments = uri.segments();
                        systems.add(segments[segments.length - 1].replace(".properties", ".item"));
                    }
                }
            }
            for (Content<String, IRepositoryObject> object : routineAbsoluteMembers.values()) {
                IRepositoryObject routine = (IRepositoryObject) object.getContent();
                URI uri = CommonPlugin.asLocalURI(routine.getProperty().getItem().eResource().getURI());
                String filePath = uri.devicePath().replaceAll("%20", " "); // to fix URI bug
                filePath = filePath.replace(".properties", ".item");

                filesList.add(new File(filePath));

                // String completePath = ERepositoryObjectType.getFolderName(ERepositoryObjectType.ROUTINES)
                // + IPath.SEPARATOR + factory.getRoutine().getPath().toString();
                // Resource resource = test.getItemResource(routine.getProperty().getItem());
                // Platform.r
                // IFolder folder = ResourceUtils.getFolder(fsProject, completePath, false);
                // System.out.println(folder);

            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }

        this.files = filesList.toArray(new File[filesList.size()]);
    }

    public void parse() {
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                String strFile = convertFileToString(file);
                String[] strGroups = parseGroupNeeded(strFile);
                parseToFunctions(strGroups, systems.contains(file.getName()));
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionHandler.process(e);
            }
        }

    }

    /**
     * qzhang Comment method "parseToFunctions".
     * 
     * @param strGroups
     * @param b
     */
    private void parseToFunctions(String[] strGroups, boolean isSystem) {
        for (int i = 0; i < strGroups.length; i++) {
            String string = strGroups[i];
            boolean setUserDefined = false;
            String des = parseDescription(string);
            String category = parseCategoryType(string);
            if (category.equals(EMPTY_STRING)) {
                setUserDefined = true;
            }
            String functionType = parseFunctionType(string);

            String[] parameter = parseFunctionParameters(string);
            Parameter[] paras = convertToParameter(parameter);

            String functionName = parseFunctionName(string);

            Function function = new Function();
            function.setName(functionName);
            function.setDescription(des);
            function.setParameters(Arrays.asList(paras));
            function.setCategory(category);
            function.setUserDefined(!isSystem || setUserDefined);

            TalendType talendType = getTalendType(functionType);
            talendType.addFunctions(function);
            function.setTalendType(talendType);

        }

    }

    /**
     * qzhang Comment method "parseFunctionName".
     * 
     * @param string
     * @return
     */
    private String parseFunctionName(String string) {
        try {
            Pattern regex = Pattern.compile(FUNCTION_NAME_REGEX, Pattern.CANON_EQ);
            Matcher matcher = regex.matcher(string);
            if (matcher.find()) {
                String s = matcher.group(2);
                return s;
            }
        } catch (PatternSyntaxException ex) {
            ExceptionHandler.process(ex);
        }
        return EMPTY_STRING;
    }

    /**
     * Use regex to parse the fileString to several groups, from ## to sub FunctionName.
     * 
     * @param strFile
     * @return
     */
    private String[] parseGroupNeeded(String strFile) {
        Pattern regex = Pattern.compile(FUNCTION_REGEX, Pattern.CANON_EQ); //$NON-NLS-1$
        Matcher matcher = regex.matcher(strFile);

        List<String> list1 = new ArrayList<String>();
        while (matcher.find()) {
            String group = matcher.group();
            list1.add(group);
        }
        return list1.toArray(new String[list1.size()]);
    }

    /**
     * Read the file's content to a String.
     * 
     * @param file2
     * @return
     */
    private String convertFileToString(File file2) throws Exception {
        if (!file2.exists()) {
            return EMPTY_STRING;
        }
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        long fileSize = 0;
        MappedByteBuffer mBuf = null;
        try {
            fileInputStream = new FileInputStream(file2);
            fileChannel = fileInputStream.getChannel();
            fileSize = fileChannel.size();
            mBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

            Charset charset = Charset.defaultCharset();
            CharBuffer cb = charset.decode(mBuf);
            String value = String.valueOf(cb);
            return value;
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }

    }

    // public static void main(String[] args) {
    // File f = new File("c:/String.pm"); //$NON-NLS-1$
    // File f1 = new File("c:/Date.pm"); //$NON-NLS-1$
    // File f2 = new File("c:/Numeric.pm"); //$NON-NLS-1$
    // File f3 = new File("c:/Misc.pm"); //$NON-NLS-1$
    // FunctionParser p = new FunctionParser(new File[] { f, f1, f2, f3 });
    // p.parse();
    // List<TalendType> list = p.getList();
    //
    // for (int i = 0; i < list.size(); i++) {
    // Object o = list.get(i);
    // System.out.println(o);
    // }
    // }
}
