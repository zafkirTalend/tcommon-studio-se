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
package org.talend.commons.utils.io.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.commons.utils.io.FilesUtils;


/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public class Unzipper extends AbstractUnarchiver {

    private String archiveFilePath;
    private String parentAbsolutePath;
    
    
    /**
     * DOC amaumont Unzipper constructor comment.
     * @throws FileNotFoundException 
     */
    public Unzipper(String archiveFilePath) throws FileNotFoundException {
        super();
        this.archiveFilePath = archiveFilePath;
        File file = new File(archiveFilePath);
        parentAbsolutePath = file.getParentFile().getAbsolutePath();
    }

    /**
     * DOC amaumont Comment method "createInputStream".
     * @param archiveFilePath
     * @throws FileNotFoundException
     */
    private ZipInputStream createInputStream(String archiveFilePath) throws FileNotFoundException {
        FileInputStream fin = new FileInputStream(archiveFilePath);
        return new ZipInputStream(fin);
    }

    public long countEntries() throws IOException {
        long nbEntries = 0;
        ZipInputStream zin = createInputStream(archiveFilePath);
        while (zin.getNextEntry() != null) {
            nbEntries++;
        }
        zin.close();
        return nbEntries;
    }
    
    public void unarchive() throws IOException {
        ZipInputStream zin = createInputStream(archiveFilePath);
        ZipEntry ze = null;
        long i = 0;
        while ((ze = zin.getNextEntry()) != null) {
            setCurrentEntryIndex(i++);
            String filePath = parentAbsolutePath + "/" + ze.getName();
            FilesUtils.createFoldersIfNotExists(filePath, true);
//            System.out.println("Unzipping " + ze.getName());
            FileOutputStream fout = new FileOutputStream(filePath);
            org.talend.commons.utils.io.StreamCopier.copy(zin, fout);
            zin.closeEntry();
            fout.close();
//            try {
//                System.out.println("slepping");
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
        zin.close();
    }
    
    /**
     * DOC amaumont Comment method "unarchive".
     * 
     * @param i
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static void unarchive(String archiveFilePath) throws IOException {
        Unzipper unzipper = new Unzipper(archiveFilePath);
        unzipper.unarchive();
//        unzipper.close();
    }


}
