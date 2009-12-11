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
package org.talend.utils.files;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;

/**
 * 
 * DOC amaumont class global comment. Detailled comment
 */
public class FileDirCleaner {

    private static Logger log = Logger.getLogger(FileDirCleaner.class);

    private long currentTime;

    private CleanResult cleanResult;

    private String filesRegExpPattern;

    private String directoriesRegExpPattern;

    private int maxEntriesByDirectoryAndByType;

    private long maxDurationBeforeCleaning;

    private boolean cleanFilesOnly;

    private boolean recursively;

    /**
     * 
     * DOC amaumont FileDirCleaner class global comment. Detailled comment
     */
    static class CleanResult {

        Throwable firstException;

        int countExceptions;

        int deletedEntries;
    }

    public FileDirCleaner(int maxEntriesByDirectoryAndByType, long maxDurationBeforeCleaning) {
        this(maxEntriesByDirectoryAndByType, maxDurationBeforeCleaning, true, false);
    }

    /**
     * 
     * DOC amaumont FileDirCleaner constructor comment.
     * 
     * @param maxEntriesByDirectory max number of files to keep and max number of directories to keep (if
     * <code>cleanFilesOnly</code> is false), example: 5 will keep 5 files and 5 directories max
     * @param cleanAfterThisDuration in seconds, clean files and directories (if <code>cleanFilesOnly</code> is false)
     * according their last modified value
     * @param cleanFilesOnly if true, clean files only
     * @param recursively clean recursively
     */
    public FileDirCleaner(int maxEntriesByDirectory, long cleanAfterThisDuration, boolean cleanFilesOnly, boolean recursively) {
        super();
        this.maxEntriesByDirectoryAndByType = maxEntriesByDirectory;
        this.maxDurationBeforeCleaning = cleanAfterThisDuration;
        this.cleanFilesOnly = cleanFilesOnly;
        this.recursively = recursively;
    }

    final Comparator<File> datComparatorFiles = new Comparator<File>() {

        public int compare(File o1, File o2) {

            // if (o1.isDirectory() && o2.isFile()) {
            // return -1;
            // } else if (o1.isFile() && o2.isDirectory()) {
            // return 1;
            // } else {
            long compareResult = o1.lastModified() - o2.lastModified();
            if (compareResult == 0) {
                return 0;
            }
            return (int) (long) (compareResult / Math.abs(compareResult));
            // }
        }

    };

    public int clean(String pathFolder) {
        return clean(pathFolder, null, null);
    }

    public int clean(String pathFolder, String filesRegExpPattern) {
        return clean(pathFolder, filesRegExpPattern, null);
    }

    public int clean(String pathFolder, String filesRegExpPattern, String directoriesRegExpPattern) {
        if (pathFolder == null) {
            throw new IllegalArgumentException("pathFolder can't be null");
        }
        this.cleanResult = new CleanResult();
        this.directoriesRegExpPattern = directoriesRegExpPattern;
        this.filesRegExpPattern = filesRegExpPattern;
        this.currentTime = System.currentTimeMillis();
        File dir = new File(pathFolder);
        if (dir.isDirectory()) {
            cleanFilesDirRecursively(dir, true);
        } else {
            throw new IllegalArgumentException("pathFolder is not a directory: " + pathFolder);
        }
        return cleanResult.deletedEntries;
    }

    /**
     * DOC amaumont Comment method "removeFolder".
     * 
     * @param cleanFilesOnly
     * @param recursively
     * 
     * @param current
     * @param removeRecursivly
     */
    public void cleanFilesDirRecursively(File dir, boolean isRootDirectory) {
        try {
            File[] listFilesDirs = dir.listFiles();
            Arrays.sort(listFilesDirs, datComparatorFiles);
            int countDirs = 0;
            int countFiles = 0;
            int indexDir = 0;
            int indexFile = 0;
            for (int i = 0; i < listFilesDirs.length; i++) {
                File fileDirJob = listFilesDirs[i];
                boolean isDirectory = fileDirJob.isDirectory();
                if (isDirectory) {
                    countDirs++;
                } else {
                    countFiles++;
                }

            }
            for (int i = 0; i < listFilesDirs.length; i++) {
                File fileDirJob = listFilesDirs[i];
                String fileDirName = fileDirJob.getName();
                boolean fileMatches = false;
                boolean dirMatches = false;
                boolean isDirectory = fileDirJob.isDirectory();
                if (isDirectory) {
                    dirMatches = directoriesRegExpPattern == null || fileDirName.matches(directoriesRegExpPattern);
                } else {
                    fileMatches = filesRegExpPattern == null || fileDirName.matches(filesRegExpPattern);
                }
                if (dirMatches || fileMatches) {
                    boolean tooManyDirs = (isRootDirectory || !isRootDirectory && recursively) && isDirectory
                            && maxEntriesByDirectoryAndByType != 0 && indexDir < countDirs - maxEntriesByDirectoryAndByType;
                    boolean tooManyFiles = !isDirectory && maxEntriesByDirectoryAndByType != 0
                            && indexFile < countFiles - maxEntriesByDirectoryAndByType;
                    boolean timeExceeded = maxDurationBeforeCleaning != 0
                            && currentTime - fileDirJob.lastModified() > maxDurationBeforeCleaning * 1000;
                    try {
                        if (timeExceeded || tooManyDirs || tooManyFiles) {
                            if (isDirectory) {
                                indexDir++;
                                if (!cleanFilesOnly) {
                                    org.apache.commons.io.FileUtils.deleteDirectory(fileDirJob);
                                    cleanResult.deletedEntries++;
                                } else if (recursively) {
                                    cleanFilesDirRecursively(fileDirJob, false);
                                }
                            } else {
                                indexFile++;
                                org.apache.commons.io.FileUtils.forceDelete(fileDirJob);
                                cleanResult.deletedEntries++;
                            }
                        } else if (recursively && isDirectory) {
                            indexDir++;
                            cleanFilesDirRecursively(fileDirJob, false);
                        }
                    } catch (Throwable t) {
                        cleanResult.countExceptions++;
                        if (cleanResult.firstException == null) {
                            cleanResult.firstException = t;
                        }
                    }

                }
            }
            if (cleanResult.firstException != null) {
                log.warn("TempDataCleaner: " + cleanResult.countExceptions
                        + " error(s) have occured when trying to clean the following file or directory '" + dir.getAbsolutePath()
                        + "', the first error is the following : ", cleanResult.firstException);
            }

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }
}
