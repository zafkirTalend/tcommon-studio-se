// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.prefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.core.model.general.ConnectionBean;

/**
 * DOC chuger class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class PreferenceManipulator implements ITalendCorePrefConstants {

    private static final String PREF_DELIMITER = "|";

    /** The preference store manipulated. */
    private IPreferenceStore store;

    /**
     * Constructs a new PreferenceManipulator.
     * 
     * @param store The preference store manipulated.
     */
    public PreferenceManipulator(IPreferenceStore store) {
        super();

        this.store = store;
    }

    /**
     * Read a string array in the preference store.
     * 
     * @param prefName Name of the preference to be read.
     * @return an array of strings.
     */
    private String[] readStringArray(final String prefName) {
        String prefs = store.getString(prefName);
        List<String> strings = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(prefs, PREF_DELIMITER);
        while (st.hasMoreTokens()) {
            strings.add(st.nextToken());
        }

        String[] array = new String[strings.size()];
        array = strings.toArray(array);
        return array;
    }

    /**
     * Save a string array in the preference store.
     * 
     * @param prefArray Preferences to be saved.
     * @param prefName Name of the preference to be saved.
     */
    private void saveStringArray(final String[] prefArray, String prefName) {
        StringBuffer prefs = new StringBuffer(256);
        for (int i = 0; i < prefArray.length; i++) {
            prefs.append(prefArray[i]);
            if (i < prefArray.length - 1) {
                prefs.append(PREF_DELIMITER);
            }
        }
        store.setValue(prefName, prefs.toString());
    }

    /**
     * Add a string to an array preference.
     * 
     * @param s String to be added.
     * @param prefName Name of the preference to add.
     */
    private void addStringToArray(String s, String prefName) {
        String[] array = readStringArray(prefName);
        List<String> l = new ArrayList<String>();
        l.addAll(Arrays.asList(array));

        if (!l.contains(s)) {
            l.add(s);

            array = new String[l.size()];
            array = l.toArray(array);
            saveStringArray(array, prefName);
        }
    }

    public List<ConnectionBean> readConnections() {
        List<ConnectionBean> toReturn = new ArrayList<ConnectionBean>();
        for (String currentConnectionToLoad : readStringArray(CONNECTIONS)) {
            toReturn.add(ConnectionBean.writeFromString(currentConnectionToLoad));
        }

        // Recup old data:
        // FIXME SML Move into Migration tool
        if (toReturn.isEmpty()) {
            if (getLastUser() != null && getLastUser().length() > 0) {
                ConnectionBean recup = new ConnectionBean();
                recup.setRepositoryId("local");
                recup.setName("Default");
                recup.setDescription("-Created by Talend-");
                recup.setUser(getLastUser());
                recup.setPassword("");
                recup.setComplete(true);
                toReturn.add(recup);

                saveConnections(toReturn);

                setLastUser("");
            }
        }

        return toReturn;
    }

    public void saveConnections(List<ConnectionBean> cons) {
        String[] prefArray = new String[cons.size()];
        int i = 0;
        for (ConnectionBean currentConnection : cons) {
            prefArray[i++] = currentConnection.readToString();
        }
        saveStringArray(prefArray, CONNECTIONS);
    }

    /**
     * Read all known users in the preference store.
     * 
     * @return all known users.
     */
    public String[] readUsers() {
        return readStringArray(USERS);
    }

    /**
     * Save all known users in the preference store.
     * 
     * @param users all known users.
     */
    public void saveUsers(String[] users) {
        saveStringArray(users, USERS);
    }

    public void addUser(String user) {
        addStringToArray(user, USERS);
    }

    public String getLastConnection() {
        return store.getString(LAST_USED_CONNECTION);
    }

    public void setLastConnection(String connection) {
        store.setValue(LAST_USED_CONNECTION, connection);
    }

    public String getLastProject() {
        return store.getString(LAST_USED_PROJECT);
    }

    public void setLastProject(String project) {
        store.setValue(LAST_USED_PROJECT, project);
    }

    public String getLastUser() {
        return store.getString(LAST_USED_USER);
    }

    public void setLastUser(String user) {
        store.setValue(LAST_USED_USER, user);
    }

}
