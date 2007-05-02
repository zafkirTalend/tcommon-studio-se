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

import java.io.IOException;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public abstract class AbstractUnarchiver {

    private long totalEntries = -1;

    private long currentEntryIndex = -1;

    public abstract void unarchive() throws IOException;

    public abstract long countEntries() throws IOException;

    public int getPercentUnarchived() throws IOException {
        if (totalEntries == -1) {
            totalEntries = countEntries();
        }
        if (this.currentEntryIndex == -1) {
            return 0;
        } else if (totalEntries < 0) {
            return 100;
        } else {
            int p = (int) ((this.currentEntryIndex + 1) * 100 / totalEntries);
            return p;
        }
    }

    protected void setCurrentEntryIndex(long currentEntryIndex) {
        this.currentEntryIndex = currentEntryIndex;
    }

}
