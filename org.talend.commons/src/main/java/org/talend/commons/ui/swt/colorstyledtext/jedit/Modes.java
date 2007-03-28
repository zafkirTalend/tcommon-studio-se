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
package org.talend.commons.ui.swt.colorstyledtext.jedit;

import java.util.Map;
import java.util.TreeMap;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class Modes {

    protected static Modes soleInstace = new Modes();

    protected boolean hasBeenLoaded = false;

    protected Map<String, Mode> modes;

    protected Mode[] modeList;

    public static Modes getSoleInstance() {
        return soleInstace;
    }

    protected Modes() {
        super();
        modes = new TreeMap<String, Mode>();
    }

    public static Mode getMode(String name) {
        return getSoleInstance().getModeNamed(name);
    }

    public static Mode getModeFor(String filename) {
        return getSoleInstance().getModeForFilename(filename);
    }

    private Mode getModeForFilename(String filename) {
        if (filename == null) {
            return getModeNamed("text.xml");
        }
        // check to see if it's already loaded
        String modeName = filenameToModeName(filename);
        if (modeName == null) {
            return getModeNamed("text.xml");
        }
        return getModeNamed(modeName);
    }

    private String filenameToModeName(String filename) {
        Mode[] localModes = getModeList();
        if (localModes == null) {
            return null;
        }
        for (int i = 0; i < localModes.length; i++) {
            Mode mode = localModes[i];
            if (mode.matches(filename)) {
                return mode.getFilename();
            }
        }
        return null;
    }

    /**
     * Answer a sorted array containing all of the modes defined by the catalog.
     * 
     * @return Mode[]
     */
    public Mode[] getModeList() {
        if (modeList == null) {
            loadCatalog();
        }
        return modeList;
    }

    public static void load() {
        getSoleInstance().loadCatalog();
    }

    protected void loadCatalog() {
        CatalogReader reader = new CatalogReader();
        modeList = reader.read("modes/catalog");
        for (int i = 0; i < modeList.length; i++) {
            Mode mode = modeList[i];
            modes.put(mode.getFilename(), mode);
        }
    }

    protected Mode getModeNamed(String name) {
        loadIfNecessary(name);
        return (Mode) modes.get(name);
    }

    private void loadIfNecessary(String name) {
        Mode hull = (Mode) modes.get(name);
        if (hull == null) {
            loadCatalog();
            /*
             * this will happen when there was a problem loading the catalog
             */
            if (modes.size() == 0) {
                return;
            }
            hull = (Mode) modes.get(name);
        }
        if (hull.notLoaded()) {
            hull.load();
        }
    }

    /*
     * Answer the Rule set this delegate/rule resolves to. This may require loading more modes.
     */
    public static Rule resolveDelegate(Mode mode, String delegateName) {
        int index = delegateName.indexOf("::");
        if (index == -1) {
            // Local delegate/rule set
            return mode.getRule(delegateName);
        }
        Mode loadedMode = getMode(delegateName.substring(0, index) + ".xml");
        return loadedMode.getRule(delegateName.substring(index + 2));
    }

}
