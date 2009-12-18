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

    private boolean cleanDirectories;

    private boolean cleanFiles;

    private boolean recursively;

    private boolean doAction = false;

    /**
     * 
     * DOC amaumont FileDirCleaner class global comment. Detailled comment
     */
    public enum STRATEGY {
        CLEAN_FILES(false, true, false),
        CLEAN_DIRECTORIES(true, false, false),
        CLEAN_FILES_AND_DIRECTORIES(true, true, false),
        CLEAN_FILES_RECURSIVELY(false, true, true),
        CLEAN_DIRECTORIES_RECURSIVELY(true, false, true),
        CLEAN_FILES_AND_DIRECTORIES_RECURSIVELY(true, true, true);

        private boolean cleanDirectories;

        private boolean cleanFiles;

        private boolean recursively;

        private STRATEGY(boolean cleanDirectories, boolean cleanFiles, boolean recursively) {
            this.cleanFiles = cleanFiles;
            this.cleanDirectories = cleanDirectories;
            this.recursively = recursively;
        }

        /**
         * Getter for cleanDirectories.
         * 
         * @return the cleanDirectories
         */
        public boolean isCleanDirectories() {
            return cleanDirectories;
        }

        /**
         * Getter for cleanFilesOnly.
         * 
         * @return the cleanFilesOnly
         */
        public boolean isCleanFiles() {
            return cleanFiles;
        }

        /**
         * Getter for recursively.
         * 
         * @return the recursively
         */
        public boolean isRecursively() {
            return recursively;
        }
    }

    /**
     * 
     * DOC amaumont FileDirCleaner class global comment. Detailled comment
     */
    static class CleanResult {

        Throwable firstException;

        int countExceptions;

        int deletedEntries;
    }

    /**
     * 
     * DOC amaumont FileDirCleaner constructor comment.
     * 
     * @param doAction
     * @param maxEntriesByDirectoryAndByType
     * @param maxDurationBeforeCleaning
     * 
     * Default clean strategy is STRATEGY.CLEAN_FILES_ONLY
     */
    public FileDirCleaner(boolean doAction, int maxEntriesByDirectoryAndByType, long maxDurationBeforeCleaning) {
        this(doAction, STRATEGY.CLEAN_FILES, maxEntriesByDirectoryAndByType, maxDurationBeforeCleaning);
    }

    /**
     * 
     * DOC amaumont FileDirCleaner constructor comment.
     * 
     * @param doAction TODO
     * @param strategy strategy to use
     * @param maxEntriesByDirectory max number of files to keep and max number of directories to keep (if
     * <code>cleanFilesOnly</code> is false), example: 5 will keep 5 files and 5 directories max
     * @param cleanAfterThisDuration in seconds, clean files and directories (if <code>cleanFilesOnly</code> is false)
     * according their last modified value
     */
    public FileDirCleaner(boolean doAction, STRATEGY strategy, int maxEntriesByDirectory, long cleanAfterThisDuration) {
        super();
        this.doAction = doAction;
        this.maxEntriesByDirectoryAndByType = maxEntriesByDirectory;
        this.maxDurationBeforeCleaning = cleanAfterThisDuration;
        this.cleanDirectories = strategy.isCleanDirectories();
        this.cleanFiles = strategy.isCleanFiles();
        this.recursively = strategy.isRecursively();
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
     * @param cleanFiles
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
                boolean tooManyDirs = (isRootDirectory || !isRootDirectory && recursively) && isDirectory
                        && maxEntriesByDirectoryAndByType != 0 && indexDir < countDirs - maxEntriesByDirectoryAndByType;
                boolean tooManyFiles = !isDirectory && maxEntriesByDirectoryAndByType != 0
                        && indexFile < countFiles - maxEntriesByDirectoryAndByType;
                boolean timeExceeded = maxDurationBeforeCleaning != 0
                        && currentTime - fileDirJob.lastModified() > maxDurationBeforeCleaning * 1000;
                try {
                    if (timeExceeded || tooManyDirs || tooManyFiles) {
                        if (isDirectory) {
                            dirMatches = directoriesRegExpPattern == null || fileDirName.matches(directoriesRegExpPattern);
                        } else {
                            fileMatches = filesRegExpPattern == null || fileDirName.matches(filesRegExpPattern);
                        }
                        if (isDirectory) {
                            indexDir++;
                            if (cleanDirectories && dirMatches) {
                                if (doAction) {
                                    org.apache.commons.io.FileUtils.deleteDirectory(fileDirJob);
                                } else {
                                    StringBuilder reason = new StringBuilder();
                                    String sep = "";
                                    if (timeExceeded) {
                                        reason.append("timeExceeded");
                                        sep = ", ";
                                    }
                                    if (tooManyDirs) {
                                        reason.append(sep + "tooManyDirs");
                                    }
                                    log.debug("'doAction' has to be true to remove recursively the directory ("
                                            + reason.toString() + "): " + fileDirJob);
                                }
                                cleanResult.deletedEntries++;
                            } else if (recursively) {
                                cleanFilesDirRecursively(fileDirJob, false);
                            }
                        } else {
                            indexFile++;
                            if (cleanFiles && fileMatches) {
                                if (doAction) {
                                    org.apache.commons.io.FileUtils.forceDelete(fileDirJob);
                                } else {
                                    StringBuilder reason = new StringBuilder();
                                    String sep = "";
                                    if (timeExceeded) {
                                        reason.append("timeExceeded");
                                        sep = ", ";
                                    }
                                    if (tooManyFiles) {
                                        reason.append(sep + "tooManyFiles");
                                    }
                                    log.debug("'doAction' has to be true to remove the file (" + reason.toString() + "): "
                                            + fileDirJob);
                                }
                                cleanResult.deletedEntries++;
                            }
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
