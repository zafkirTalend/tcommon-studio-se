// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.talend.utils.eclipse.IPath;
import org.talend.utils.eclipse.Path;
import org.talend.utils.sugars.ReturnCode;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ven., 29 sept. 2006) nrousseau $
 * 
 */
public final class FilesUtils {

    public static final String SVN_FOLDER_NAMES[] = new String[] { ".svn", "_svn" }; //$NON-NLS-1$  //$NON-NLS-2$

    public static boolean isSVNFolder(String name) {
        if (name != null) {
            name = name.toLowerCase();
            for (int i = 0; i < SVN_FOLDER_NAMES.length; i++) {
                if (SVN_FOLDER_NAMES[i].equals(name) || name.endsWith(SVN_FOLDER_NAMES[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSVNFolder(File file) {
        if (file != null) {
            return isSVNFolder(file.getName());
        }
        return false;
    }

    private FilesUtils() {
        super();
    }

    public static void copyFolder(File source, File target, boolean emptyTargetBeforeCopy, final FileFilter sourceFolderFilter,
            final FileFilter sourceFileFilter, boolean copyFolder) throws IOException {
        if (!target.exists()) {
            target.mkdirs();
        }

        if (emptyTargetBeforeCopy) {
            emptyFolder(target);
        }

        FileFilter folderFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.isDirectory() && (sourceFolderFilter == null || sourceFolderFilter.accept(pathname));
            }

        };
        FileFilter fileFilter = new FileFilter() {

            public boolean accept(File pathname) {
                return !pathname.isDirectory() && (sourceFileFilter == null || sourceFileFilter.accept(pathname));
            }

        };

        for (File current : source.listFiles(folderFilter)) {
            if (copyFolder) {
                File newFolder = new File(target, current.getName());
                newFolder.mkdir();
                copyFolder(current, newFolder, emptyTargetBeforeCopy, sourceFolderFilter, sourceFileFilter, copyFolder);
            } else {
                copyFolder(current, target, emptyTargetBeforeCopy, sourceFolderFilter, sourceFileFilter, copyFolder);
            }
        }

        for (File current : source.listFiles(fileFilter)) {
            File out = new File(target, current.getName());
            copyFile(current, out);
        }
    }

    private static void emptyFolder(File toEmpty) {
        for (File current : toEmpty.listFiles()) {
            if (current.isDirectory()) {
                emptyFolder(current);
            }
            current.delete();
        }
    }

    public static void copyFile(File source, File target) throws IOException {
        // Need to recopy the file in one of these cases:
        // 1. target doesn't exists (never copied)
        // 2. target exists but source has been modified recently

        if (!target.exists() || source.lastModified() > target.lastModified()) {
            copyFile(new FileInputStream(source), target);
        }
    }

    public static void copyFile(InputStream source, File target) throws IOException {
        FileOutputStream fos = null;
        try {
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
            }

            fos = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = source.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } finally {
            try {
                source.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }

    }

    // TODO check that this method is not a duplicate of org.talend.utils.files.FileUtils#replaceInFile(String path,
    // String oldString, String newString)
    public static void replaceInFile(String regex, String fileName, String replacement) throws IOException {
        InputStream in = new FileInputStream(fileName);
        StringBuffer buffer = new StringBuffer();
        try {
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null) {
                buffer.append(line.replaceAll(regex, replacement)).append("\n");
            }
        } finally {
            in.close();
        }

        OutputStream os = new FileOutputStream(fileName);
        os.write(buffer.toString().getBytes());
        os.close();
    }

    public static FileFilter getExcludeSystemFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return !isSVNFolder(pathname);
            }

        };
        return filter;
    }

    public static FileFilter getAcceptJARFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.toString().endsWith(".jar");
            }

        };
        return filter;
    }

    public static FileFilter getAcceptPMFilesFilter() {
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                return pathname.toString().endsWith(".pm");
            }

        };
        return filter;
    }

    /**
     * Load in a list all lines of the given file.
     * 
     * @throws IOException
     */
    public static List<String> getContentLines(String filePath) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        List<String> lines = new ArrayList<String>();
        try {
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            in.close();
        }
        return lines;
    }

    /**
     * Create all the folders if they don't exist for the following path.
     * 
     * @param path
     * @return the return code with a non null error message if the state is false. When the state is ok, there is no
     * message.
     * @throws IOException
     */
    public static ReturnCode createFoldersIfNotExists(String path) throws IOException {
        return createFoldersIfNotExists(path, false);
    }

    /**
     * .
     * 
     * @param path
     * @param pathIsFilePath if true the given path has a filename at last segment so this segment is not processed
     * @return the return code with a non null error message if the state is false. When the state is ok, there is no
     * message.
     * @throws IOException
     */
    public static ReturnCode createFoldersIfNotExists(String path, boolean pathIsFilePath) throws IOException {
        ReturnCode rc = new ReturnCode();
        Path completePath = new Path(path);
        IPath pathFolder = null;
        if (pathIsFilePath) {
            pathFolder = completePath.removeLastSegments(1);
        } else {
            pathFolder = completePath;
        }

        File folder = new File(pathFolder.toOSString());

        if (!folder.exists()) {
            int size = pathFolder.segmentCount();
            for (int i = 0; i < size; i++) {
                folder = new File(pathFolder.uptoSegment(i + 1).toOSString());
                if (!folder.exists()) {
                    if (!folder.mkdir()) {
                        rc.setOk(false);
                        rc.setMessage("Failed to create the directory: " + folder.getAbsolutePath());
                        break;
                    }
                }
            }
        }
        return rc;
    }

    public static void main(String[] args) {
        try {
            ReturnCode rc = createFoldersIfNotExists("/c:\\test\\test1/test2", false);
            printFailure(rc);
            rc = createFoldersIfNotExists("c:\\test10\\test11/test20/test.pl", true);
            printFailure(rc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * DOC amaumont Comment method "removeDirectory".
     * 
     * @param b
     */
    public static boolean removeFolder(String pathFolder, boolean recursiveRemove) {
        File folder = new File(pathFolder);
        if (folder.isDirectory()) {
            return removeFolder(folder, recursiveRemove);
        }
        return false;
    }

    /**
     * DOC amaumont Comment method "removeFolder".
     * 
     * @param current
     * @param removeRecursivly
     */
    public static boolean removeFolder(File folder, boolean removeRecursivly) {
        if (removeRecursivly) {
            for (File current : folder.listFiles()) {
                if (current.isDirectory()) {
                    removeFolder(current, true);
                } else {
                    current.delete();
                }
            }
        }
        return folder.delete();
    }

    public static String extractPathFolderFromFilePath(String filePath) {
        Path completePath = new Path(filePath);
        return completePath.removeLastSegments(1).toOSString();
    }

    public static long getChecksumAlder32(File file) throws IOException {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

        // Compute Adler-32 checksum
        CheckedInputStream cis = new CheckedInputStream(bufferedInputStream, new Adler32());
        byte[] tempBuf = new byte[128];
        while (cis.read(tempBuf) >= 0) {
            // do nothing
        }
        return cis.getChecksum().getValue();
    }

    /**
     * DOC amaumont Comment method "getBytes".
     * 
     * @param archiveFile
     * @throws IOException
     */
    public static byte[] getBytes(File archiveFile) throws IOException {
        long length = archiveFile.length();

        int lengthFinalSize = 0;

        if (length > Integer.MAX_VALUE) {
            throw new IllegalStateException("Capacity is over !");
        } else {
            lengthFinalSize = (int) length;
        }

        FileInputStream fis = new FileInputStream(archiveFile);
        int bufferSize = 1024;
        byte[] buf = new byte[bufferSize];
        int readBytes = 0;

        ByteArrayOutputStream bos = new ByteArrayOutputStream(lengthFinalSize);

        while ((readBytes = fis.read(buf)) != -1) {
            bos.write(buf, 0, readBytes);
        }

        return bos.toByteArray();
    }

    /**
     * DOC amaumont Comment method "getFile".
     * 
     * @param jobScriptArchive
     * @throws IOException
     */
    public static File getFile(String filePath, byte[] jobScriptArchive) throws IOException {
        File file = new File(filePath);

        ByteArrayInputStream bis = new ByteArrayInputStream(jobScriptArchive, 0, jobScriptArchive.length);

        FileOutputStream fos = new FileOutputStream(file);

        int bufferSize = 1024;
        byte[] buf = new byte[bufferSize];

        int readBytes = 0;

        while ((readBytes = bis.read(buf)) != -1) {
            fos.write(buf, 0, readBytes);
        }

        bis.close();
        fos.close();

        return file;
    }

    private static void printFailure(ReturnCode rc) {
        if (!rc.isOk()) {
            System.err.println("Failure: " + rc.getMessage());
        }
    }

    /**
     * zip the file to the user folder.
     * 
     * @param sourceFile
     * @param zippedFileName
     * @throws Exception
     */
    public static void zip(File sourceFile, String zippedFileName) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zippedFileName));
        zip(out, sourceFile, null);
    }

    /**
     * zip a new file with specified name to the user folder.
     * 
     * @param sourceFileName
     * @param zippedFileName
     * @throws Exception
     */
    public static void zip(String sourceFileName, String zippedFileName) throws Exception {
        File sourceFile = new File(sourceFileName);
        zip(sourceFile, zippedFileName);
    }

    /**
     * zip the file stream to the user folder.
     * 
     * @param out
     * @param f
     * @param base
     * @throws Exception
     */
    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fc = f.listFiles();
            if (base != null)
                out.putNextEntry(new ZipEntry(base + "/"));
            base = base == null ? "" : base + "/";
            for (int i = 0; i < fc.length; i++) {
                zip(out, fc[i], base + fc[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(f.getName()));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            out.flush();
            in.close();
        }

        out.close();
    }

    /**
     * Unzip the component file to the user folder.
     * 
     * @param zipFile The component zip file
     * @param targetFolder The user folder
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void unzip(String zipFile, String targetFolder) throws Exception {
        Exception exception = null;
        ZipFile zip = new ZipFile(zipFile);
        byte[] buf = new byte[8192];

        try {
            Enumeration<ZipEntry> enumeration = (Enumeration<ZipEntry>) zip.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry entry = enumeration.nextElement();

                File file = new File(targetFolder, entry.getName());

                if (entry.isDirectory()) {
                    if (!file.exists()) {
                        file.mkdir();
                    }
                } else {

                    InputStream zin = zip.getInputStream(entry);
                    OutputStream fout = new FileOutputStream(file);
                    // check if parent folder exists
                    File dir = file.getParentFile();
                    if (dir.isDirectory() && !dir.exists()) {
                        dir.mkdirs();
                    }

                    try {
                        while (true) {
                            int bytesRead = zin.read(buf);
                            if (bytesRead == -1) { // end of file
                                break;
                            }
                            fout.write(buf, 0, bytesRead);

                        }
                        fout.flush();
                    } catch (Exception e) {
                        exception = e;
                        // stop looping
                        return;
                    } finally {
                        zin.close();
                        fout.close();
                    }
                }
            }
        } catch (Exception e) {
            exception = e;
        } finally {
            zip.close();

            if (exception != null) {
                // notify caller with exception
                throw exception;
            }
        }
    }

}
