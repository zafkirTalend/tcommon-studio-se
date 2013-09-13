// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.osgi.hook.notification;

import java.util.Observable;

/**
 * This observable is registered as an OSGI service and can be used to register Observers by clients that whishes to be
 * notified when a jar is missing in a "org.talend.*" bundle. the notification is done in a synchronous manner. The
 * notification is done with a JarMissingEvent parameter. created by sgandon on 12 sept. 2013 Detailled comment
 * 
 */
public class JarMissingObservable extends Observable {

    public static class JarMissingEvent {

        private final String jarName;

        private final String bundleName;

        private final String expectedLibFolder;

        /**
         * DOC sgandon JarMissingObservable.JarMissingEvent constructor comment.
         */
        public JarMissingEvent(String jarName, String bundleName, String expectedLibFolder) {
            this.jarName = jarName;
            this.bundleName = bundleName;
            this.expectedLibFolder = expectedLibFolder;

        }

        /**
         * return the name of the missing jar.
         * 
         * @return the jarName
         */
        public String getJarName() {
            return this.jarName;
        }

        /**
         * return the name of the bundle that has a missing jar.
         * 
         * @return the bundleName
         */
        public String getBundleSymbolicName() {
            return this.bundleName;
        }

        /**
         * return the folder path where the jar is excepted to be found
         * 
         * @return the expectedLibFolder
         */
        public String getExpectedLibFolder() {
            return this.expectedLibFolder;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#notifyObservers(java.lang.Object)
     */
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();// this is required for notification to actually really happend.
        super.notifyObservers(arg);
    }
}
