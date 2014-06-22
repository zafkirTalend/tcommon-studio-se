// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.data.extractor;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * DOC marvin class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class ModuleNameExtractor {

    /**
     * Extracts all file names from the given collection. This method is suitable the case that collection includes not
     * only file names but also paths. And you just need a set only including file names. So this method cuts the
     * directory of a file only the file name remaining. Added by Marvin Wang on Nov 8, 2012.
     * 
     * @param collection must not be <code>null<code>, otherwise, it will throw NPE.
     * @return a set that only includes file name.
     */
    public static Set<String> extractFileName(Collection<String> collection) {
        Set<String> moduleNameSet = new HashSet<String>();
        if (!collection.isEmpty()) {
            for (String module : collection) {
                if (module != null) {
                    File moduleFile = new File(module);
                    moduleNameSet.add(moduleFile.getName());
                }
            }
        }
        return moduleNameSet;
    }
}
