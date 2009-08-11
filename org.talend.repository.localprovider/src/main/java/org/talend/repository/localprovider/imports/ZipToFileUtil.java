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
package org.talend.repository.localprovider.imports;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class ZipToFileUtil {

    /**
     *@author Winty
     *@Usage: 压缩:java Zip -zip "directoryName" 解压:java Zip -unzip "fileName.zip"
     */

    private ZipInputStream zipIn; // 解压Zip

    private ZipOutputStream zipOut; // 压缩Zip

    private ZipEntry zipEntry;

    private static int bufSize; // size of bytes

    private byte[] buf;

    private int readedBytes;

    public ZipToFileUtil() {
        this(512);
    }

    public ZipToFileUtil(int bufSize) {
        this.bufSize = bufSize;
        this.buf = new byte[this.bufSize];
    }

    // 压缩文件夹内的文件
    public void doZip(String zipDirectory) {// zipDirectoryPath:需要压缩的文件夹名
        File file;
        File zipDir;
        zipDir = new File(zipDirectory);
        String zipFileName = zipDir.getName() + ".zip";// 压缩后生成的zip文件名
        try {
            this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
            handleDir(zipDir, this.zipOut);
            this.zipOut.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // 由doZip调用,递归完成目录文件读取
    private void handleDir(File dir, ZipOutputStream zipOut) throws IOException {
        FileInputStream fileIn;
        File[] files;
        files = dir.listFiles();

        if (files.length == 0) {// 如果目录为空,则单独创建之.
            // ZipEntry的isDirectory()方法中,目录以"/"结尾.
            this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/"));
            this.zipOut.closeEntry();
        } else {// 如果目录不为空,则分别处理目录和文件.
            for (File fileName : files) {
                // System.out.println(fileName);
                if (fileName.isDirectory()) {
                    handleDir(fileName, this.zipOut);
                } else {
                    fileIn = new FileInputStream(fileName);
                    this.zipOut.putNextEntry(new ZipEntry(fileName.toString()));
                    while ((this.readedBytes = fileIn.read(this.buf)) > 0) {
                        this.zipOut.write(this.buf, 0, this.readedBytes);
                    }
                    this.zipOut.closeEntry();
                }
            }
        }
    }

    // 解压指定zip文件
    public void unZip(String unZipfileName) {// unZipfileName需要解压的zip文件名
        FileOutputStream fileOut;
        File file;
        String pathPrefix = "/";
        try {
            this.zipIn = new ZipInputStream(new BufferedInputStream(new FileInputStream(unZipfileName)));
            if (unZipfileName.indexOf("/") == 0) {
                pathPrefix = unZipfileName.substring(0, unZipfileName.lastIndexOf("/"));
            } else {
                pathPrefix = unZipfileName.substring(0, unZipfileName.lastIndexOf("/"));
                pathPrefix = pathPrefix + "/";
            }
            while ((this.zipEntry = this.zipIn.getNextEntry()) != null) {
                file = new File(pathPrefix + this.zipEntry.getName());
                if (this.zipEntry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    fileOut = new FileOutputStream(file);
                    while ((this.readedBytes = this.zipIn.read(this.buf)) > 0) {
                        fileOut.write(this.buf, 0, this.readedBytes);
                    }
                    fileOut.close();
                }
                this.zipIn.closeEntry();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // 设置缓冲区大小
    public void setBufSize(int bufSize) {
        this.bufSize = bufSize;
    }

    // delete the File
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }

}
