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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * DOC amaumont class global comment. Detailled comment
 */
public class TestFileDirCleaner extends TestCase {

    private static final int LEVELS_COUNT = 2;

    static String rootPath;

    static SimpleDateFormat sdf;

    static String datePattern;

    private static final int COUNT_FILES_DIRECTORIES = 4;

    static {
        rootPath = "/tmp/" + TestFileDirCleaner.class.getName();
        String datePattern = "yyyy-MM-dd";
        sdf = new SimpleDateFormat(datePattern);
    }

    @Before
    public void setUp() throws Exception {
        removeFiles();
    }

    @After
    public void tearDown() throws Exception {
        // removeFiles();
    }

    /**
     * 
     * Tests on max entries
     * 
     */

    @Test
    public void testLeaveThreeFilesAndAllSubdirectoriesOnRootDirectory() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testLeaveThreeFilesAndThreeSubdirectoriesOnRootDirectory() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, 3 directories only by directory max, no check on elapsed time, clean files AND
         * directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testLeaveThreeFilesAndAllSubdirectoriesRecursively() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testLeaveThreeFilesAndThreeSubdirectoriesRecursively() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files AND directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    /**
     * 
     * Tests on max entries and elapsed time
     * 
     */

    @Test
    public void testCleanBeforeMarchAndCleanFebruaryLeaveThreeFilesAndAllSubdirectoriesOnRootDirectory() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndThreeSubdirectoriesOnRootDirectory() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files AND directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndAllSubdirectoriesRecursively() {

        int maxEntriesByDirectoryAndByType = 3;
        long cleanAfterThisDuration = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, cleanAfterThisDuration, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndThreeSubdirectoriesRecursively() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * 3 files by directory max, 3 directories by directory max, clean files older than April 2008, clean files AND
         * directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    /**
     * 
     * Tests on elapsed time only
     * 
     */

    @Test
    public void testCleanBeforeMarchAndLeaveAllSubdirectoriesOnRootDirectory() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;

        /*
         * no clean about max entries, clean files older than April 2008, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAtRootOnly() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * no clean about max entries, clean files older than April 2008, clean files AND directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testCleanBeforeMarchRecursively() {

        int maxEntriesByDirectoryAndByType = 0;
        long cleanAfterThisDuration = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * no clean about max entries, clean files older than April 2008, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, cleanAfterThisDuration, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchForAllRecursively() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * no clean about max entries, clean files older than April 2008, clean files AND directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    /**
     * 
     * Tests on max entries, with regexp on files
     * 
     */

    @Test
    public void testLeaveThreeFilesAndAllSubdirectoriesOnRootDirectoryWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 1;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;
        int expectedDeletedEntries = 1;
        String filesRegExpPattern = "(file_level_0_index_2|file_level_2_index_1|file_level_1_index_3)";

        /*
         * 3 files only by directory max, no check on elapsed time, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean, expectedDeletedEntries,
                filesRegExpPattern, null);

    }

    @Test
    public void testLeaveThreeFilesAndThreeSubdirectoriesOnRootDirectoryWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files AND directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testLeaveThreeFilesAndAllSubdirectoriesRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testLeaveThreeFilesAndThreeSubdirectoriesRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = 0;
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, no check on elapsed time, clean files AND directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    /**
     * 
     * Tests on max entries and elapsed time, with regexp on files
     * 
     */

    @Test
    public void testCleanBeforeMarchAndCleanFebruaryLeaveThreeFilesAndAllSubdirectoriesOnRootDirectoryWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndThreeSubdirectoriesOnRootDirectoryWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files AND directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndAllSubdirectoriesRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long cleanAfterThisDuration = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * 3 files only by directory max, clean files older than April 2008, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, cleanAfterThisDuration, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAndLeaveThreeFilesAndThreeSubdirectoriesRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 3;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * 3 files by directory max, 3 directories by directory max, clean files older than April 2008, clean files AND
         * directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    /**
     * 
     * Tests on elapsed time only, with regexp on files
     * 
     */

    @Test
    public void testCleanBeforeMarchAndLeaveAllSubdirectoriesOnRootDirectoryWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = false;

        /*
         * no clean about max entries, clean files older than April 2008, clean files only, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchAtRootOnlyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = false;

        /*
         * no clean about max entries, clean files older than April 2008, clean files AND directories, no recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    @Test
    public void testCleanBeforeMarchRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 0;
        long cleanAfterThisDuration = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = true;
        boolean recursiveClean = true;

        /*
         * no clean about max entries, clean files older than April 2008, clean files only, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, cleanAfterThisDuration, cleanFilesOnly, recursiveClean);

    }

    @Test
    public void testCleanBeforeMarchForAllRecursivelyWithRegexpOnFiles() {

        int maxEntriesByDirectoryAndByType = 0;
        long maxDurationBeforeClean = getCleanDurationBeforeApril2008();
        boolean cleanFilesOnly = false;
        boolean recursiveClean = true;

        /*
         * no clean about max entries, clean files older than April 2008, clean files AND directories, recursive,
         */
        runTest(maxEntriesByDirectoryAndByType, maxDurationBeforeClean, cleanFilesOnly, recursiveClean);
    }

    private void runTest(int maxEntriesByDirectoryAndByType, long cleanAfterThisDuration, boolean cleanFilesOnly,
            boolean recursiveClean) {
        runTest(maxEntriesByDirectoryAndByType, cleanAfterThisDuration, cleanFilesOnly, recursiveClean, null, null, null);
    }

    private void runTest(int maxEntriesByDirectoryAndByType, long cleanAfterThisDuration, boolean cleanFilesOnly,
            boolean recursiveClean, Integer expectedDeletedEntries, String filesRegExpPattern, String directoriesRegExpPattern) {
        try {
            generateFiles(LEVELS_COUNT);

            FileDirCleaner fileDirCleaner = new FileDirCleaner(maxEntriesByDirectoryAndByType, cleanAfterThisDuration,
                    cleanFilesOnly, recursiveClean);
            int deletedEntries = fileDirCleaner.clean(rootPath, filesRegExpPattern, directoriesRegExpPattern);

            if (expectedDeletedEntries != null && !expectedDeletedEntries.equals(deletedEntries)) {
                fail("expectedDeletedEntries is " + expectedDeletedEntries + " but real deleted entries is " + deletedEntries);
            }

            FileDirCheckerForFSManager checkerOperations = new FileDirCheckerForFSManager();
            checkerOperations.setDirectoriesCount(COUNT_FILES_DIRECTORIES);
            checkerOperations.setFilesCount(COUNT_FILES_DIRECTORIES);
            checkerOperations.setMaxEntriesByDirectoryAndByType(maxEntriesByDirectoryAndByType);
            checkerOperations.setCleanAfterThisDuration(cleanAfterThisDuration);
            checkerOperations.setCleanFilesOnly(cleanFilesOnly);
            checkerOperations.setRecursively(recursiveClean);
            checkerOperations.setFilesRegExpPattern(filesRegExpPattern);
            checkerOperations.setDirectoriesRegExpPattern(directoriesRegExpPattern);

            FSManager fsManager = new FSManager(checkerOperations);
            fsManager.process(rootPath, LEVELS_COUNT, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long getCleanDurationBeforeApril2008() {
        String cleanBeforeThisDateStr = "2008-03-30";
        Date cleanBeforeThisDate = null;
        try {
            cleanBeforeThisDate = sdf.parse(cleanBeforeThisDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        long cleanAfterThisDuration = System.currentTimeMillis() - cleanBeforeThisDate.getTime();

        return cleanAfterThisDuration / 1000;
    }

    private void generateFiles(int levelsCount) throws IOException {

        System.out.println("Temp files/directories created from rootPath: " + rootPath);

        FileGeneratorForFSManager operationGenerateFiles = new FileGeneratorForFSManager();
        FSManager fsManager = new FSManager(operationGenerateFiles);
        fsManager.process(rootPath, levelsCount, 0);

    }

    private void removeFiles() {

        try {
            org.apache.commons.io.FileUtils.forceDelete(new File(rootPath));
        } catch (IOException e) {
            System.out.println("Error when trying to remove root dir: " + rootPath);
        }
    }

    /**
     * 
     * DOC amaumont FileDirCleaner2Test class global comment. Detailled comment
     */
    public static class FSManager {

        int dirCount = COUNT_FILES_DIRECTORIES;

        int filesCount = COUNT_FILES_DIRECTORIES;

        private OperationForFSManager operation;

        public FSManager(OperationForFSManager operation) {
            super();
            this.operation = operation;
        }

        public void process(String fromPath, int levelsCount, int currentLevel) throws IOException {

            File dir = new File(fromPath);

            this.operation.processDirectoryBegin(dir, currentLevel, 0);

            for (int indexFile = 1; indexFile <= filesCount; indexFile++) {
                File file = new File(fromPath + "/" + "file_" + "level_" + currentLevel + "_index_" + indexFile);
                operation.processFile(file, currentLevel, indexFile);
            }
            processRecursively(fromPath, levelsCount, currentLevel + 1);

            this.operation.processDirectoryEnd(dir, currentLevel, 0);

        }

        public void processRecursively(String fromPath, int levelsCount, int currentLevel) throws IOException {

            for (int indexDir = 1; indexDir <= dirCount; indexDir++) {
                String pathDir = fromPath + "/" + "dir_" + "level_" + currentLevel + "_index_" + indexDir;
                File dir = new File(pathDir);
                this.operation.processDirectoryBegin(dir, currentLevel, indexDir);
                System.out.println("Created directory: " + pathDir);
                for (int indexFile = 1; indexFile <= filesCount; indexFile++) {
                    File file = new File(pathDir + "/" + "file_" + "level_" + currentLevel + "_index_" + indexFile);
                    operation.processFile(file, currentLevel, indexFile);
                }
                if (currentLevel < levelsCount) {
                    processRecursively(dir.getAbsolutePath(), levelsCount, currentLevel + 1);
                }
                this.operation.processDirectoryEnd(dir, currentLevel, indexDir);

            }

        }

    }

    /**
     * 
     * DOC amaumont FileDirCleaner2Test class global comment. Detailled comment
     */
    interface OperationForFSManager {

        public void processFile(File file, int currentLevel, int indexFile) throws IOException;

        public void processDirectoryBegin(File dir, int currentLevel, int indexDir);

        public void processDirectoryEnd(File dir, int currentLevel, int indexDir);

    }

    /**
     * 
     * DOC amaumont FileDirCleaner2Test class global comment. Detailled comment
     */
    class FileDirCheckerForFSManager implements OperationForFSManager {

        private int maxEntriesByDirectoryAndByType;

        private long cleanAfterThisDuration;

        private boolean cleanFilesOnly;

        private boolean recursively;

        private int filesCount;

        private int directoriesCount;

        private long currentTime;

        private String filesRegExpPattern;

        private String directoriesRegExpPattern;

        Set<String> dirsRemoved = new HashSet<String>();

        public FileDirCheckerForFSManager() {
            super();
            currentTime = System.currentTimeMillis();

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processDirectoryBegin(java.io.File,
         * int)
         */
        public void processDirectoryBegin(File dir, int currentLevel, int indexDir) {

            if (currentLevel != 0) {
                boolean exists = dir.exists();
                boolean timeExceeded = cleanAfterThisDuration != 0
                        && currentTime - dir.lastModified() > cleanAfterThisDuration * 1000;
                boolean countExceeded = !cleanFilesOnly && maxEntriesByDirectoryAndByType != 0
                        && indexDir <= directoriesCount - maxEntriesByDirectoryAndByType;

                String dirName = dir.getName();
                boolean dirMatches = directoriesRegExpPattern == null || dirName.matches(directoriesRegExpPattern);

                if (currentLevel == 1 && !recursively || recursively) {

                    if (exists && dirMatches && (timeExceeded || countExceeded) && !cleanFilesOnly) {
                        fail("Directory '" + dir.getAbsolutePath() + "' SHOULD NOT exist when configuration is" + toString());
                    }

                    if (!exists && !timeExceeded && !countExceeded && !checkCurrentDirIsParentOfRemovedDir(dir.getAbsolutePath())) {
                        fail("Directory '" + dir.getAbsolutePath() + "' SHOULD exist when configuration is" + toString());
                    } else if (!exists) {
                        dirsRemoved.add(dir.getAbsolutePath());
                    }

                }

            }

        }

        public String toString() {
            return "FileDirCheckerForFSManager[maxEntriesByDirectoryAndByType=" + maxEntriesByDirectoryAndByType
                    + ", maxDurationBeforeCleaning=" + cleanAfterThisDuration + ", cleanFilesOnly=" + cleanFilesOnly
                    + ", recursively=" + recursively + "]";
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processDirectoryEnd(java.io.File, int)
         */
        public void processDirectoryEnd(File dir, int currentLevel, int indexDir) {

        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processFile(java.io.File, int)
         */
        public void processFile(File file, int currentLevel, int indexFile) throws IOException {

            boolean exists = file.exists();

            boolean timeExceeded = cleanAfterThisDuration != 0
                    && currentTime - file.lastModified() > cleanAfterThisDuration * 1000;
            boolean countExceeded = maxEntriesByDirectoryAndByType != 0
                    && indexFile <= filesCount - maxEntriesByDirectoryAndByType;

            String fileName = file.getName();

            boolean fileMatches = filesRegExpPattern == null || fileName.matches(filesRegExpPattern);

            if (currentLevel == 1 && !recursively || recursively) {

                if (exists && (recursively || !recursively && currentLevel == 0) && fileMatches
                        && (timeExceeded || countExceeded)) {
                    fail("File '" + file.getAbsolutePath() + "' SHOULD NOT exist when configuration is " + toString());
                }

                String parentPath = file.getParent();

                if (!exists && !timeExceeded && !countExceeded && !checkCurrentDirIsParentOfRemovedDir(parentPath)) {
                    fail("File '" + file.getAbsolutePath() + "' SHOULD exist when configuration is " + toString());
                }
            }

        }

        private boolean checkCurrentDirIsParentOfRemovedDir(String parentPath) {
            String[] array = dirsRemoved.toArray(new String[0]);
            for (int i = 0; i < array.length; i++) {
                if (parentPath.startsWith(array[i])) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Getter for maxEntriesByDirectory.
         * 
         * @return the maxEntriesByDirectory
         */
        public int getMaxEntriesByDirectoryAndByType() {
            return maxEntriesByDirectoryAndByType;
        }

        /**
         * Sets the maxEntriesByDirectory.
         * 
         * @param maxEntriesByDirectory the maxEntriesByDirectory to set
         */
        public void setMaxEntriesByDirectoryAndByType(int maxEntriesByDirectory) {
            this.maxEntriesByDirectoryAndByType = maxEntriesByDirectory;
        }

        /**
         * Getter for maxDurationBeforeCleaning.
         * 
         * @return the maxDurationBeforeCleaning
         */
        public long getCleanAfterThisDuration() {
            return cleanAfterThisDuration;
        }

        /**
         * Sets the maxDurationBeforeCleaning.
         * 
         * @param maxDurationBeforeCleaning the maxDurationBeforeCleaning to set
         */
        public void setCleanAfterThisDuration(long maxDurationBeforeCleaning) {
            this.cleanAfterThisDuration = maxDurationBeforeCleaning;
        }

        /**
         * Getter for cleanFilesOnly.
         * 
         * @return the cleanFilesOnly
         */
        public boolean isCleanFilesOnly() {
            return cleanFilesOnly;
        }

        /**
         * Sets the cleanFilesOnly.
         * 
         * @param cleanFilesOnly the cleanFilesOnly to set
         */
        public void setCleanFilesOnly(boolean cleanFilesOnly) {
            this.cleanFilesOnly = cleanFilesOnly;
        }

        /**
         * Getter for recursively.
         * 
         * @return the recursively
         */
        public boolean isRecursively() {
            return recursively;
        }

        /**
         * Sets the recursively.
         * 
         * @param recursively the recursively to set
         */
        public void setRecursively(boolean recursively) {
            this.recursively = recursively;
        }

        /**
         * Getter for filesCount.
         * 
         * @return the filesCount
         */
        public int getFilesCount() {
            return filesCount;
        }

        /**
         * Sets the filesCount.
         * 
         * @param filesCount the filesCount to set
         */
        public void setFilesCount(int filesCount) {
            this.filesCount = filesCount;
        }

        /**
         * Getter for directoriesCount.
         * 
         * @return the directoriesCount
         */
        public int getDirectoriesCount() {
            return directoriesCount;
        }

        /**
         * Sets the directoriesCount.
         * 
         * @param directoriesCount the directoriesCount to set
         */
        public void setDirectoriesCount(int directoriesCount) {
            this.directoriesCount = directoriesCount;
        }

        /**
         * Getter for filesRegExpPattern.
         * 
         * @return the filesRegExpPattern
         */
        public String getFilesRegExpPattern() {
            return filesRegExpPattern;
        }

        /**
         * Sets the filesRegExpPattern.
         * 
         * @param filesRegExpPattern the filesRegExpPattern to set
         */
        public void setFilesRegExpPattern(String filesRegExpPattern) {
            this.filesRegExpPattern = filesRegExpPattern;
        }

        /**
         * Getter for directoriesRegExpPattern.
         * 
         * @return the directoriesRegExpPattern
         */
        public String getDirectoriesRegExpPattern() {
            return directoriesRegExpPattern;
        }

        /**
         * Sets the directoriesRegExpPattern.
         * 
         * @param directoriesRegExpPattern the directoriesRegExpPattern to set
         */
        public void setDirectoriesRegExpPattern(String directoriesRegExpPattern) {
            this.directoriesRegExpPattern = directoriesRegExpPattern;
        }

    }

    /**
     * 
     * DOC amaumont FileDirCleaner2Test class global comment. Detailled comment
     */
    class FileGeneratorForFSManager implements OperationForFSManager {

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processDirectoryBegin(java.io.File,
         * int)
         */
        public void processDirectoryBegin(File dir, int currentLevel, int indexDir) {
            dir.mkdirs();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processDirectoryEnd(java.io.File, int)
         */
        public void processDirectoryEnd(File dir, int currentLevel, int indexDir) {
            Date parsedDateDir = null;
            try {
                parsedDateDir = sdf.parse("2008-" + indexDir + "-01");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            boolean setLastModified = dir.setLastModified(parsedDateDir.getTime());
            System.out.println("setLastModified=" + parsedDateDir + ", ok? " + setLastModified);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.talend.utils.files.FileDirCleaner2Test.OperationForFSManager#processFile(java.io.File, int)
         */
        public void processFile(File file, int currentLevel, int indexFile) throws IOException {
            file.createNewFile();
            Date parsedDateFile = null;
            try {
                parsedDateFile = sdf.parse("2008-" + indexFile + "-01");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            file.setLastModified(parsedDateFile.getTime());
            System.out.println("Created file: " + file.getAbsolutePath());
        }

    }

}
