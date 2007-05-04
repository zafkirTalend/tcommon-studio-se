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
package org.talend.commons.utils.data.list;

import java.util.List;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <B> type of beans
 */
public abstract class UniqueStringGenerator<B> {

    private String baseValue;

    private List<B> beans;

    /**
     * DOC amaumont NameGenerator constructor comment.
     */
    public UniqueStringGenerator(String baseValue, List<B> beans) {
        super();
        this.baseValue = baseValue;
        this.beans = beans;
    }

    public String getUniqueString() {

        int lstSize = beans.size();
        String[] labels = new String[lstSize];
        for (int i = 0; i < lstSize; i++) {
            B bean = (B) beans.get(i);
            labels[i] = getBeanString(bean);
        }

        boolean found = false;
        int indexNewColumn = 0;
        String newColumnName = null;
        boolean firstTime = true;
        while (!found) {
            newColumnName = baseValue + (firstTime ? "" : (++indexNewColumn));
            firstTime = false;
            boolean allAreDifferent = true;
            for (int j = 0; j < labels.length; j++) {
                String label = labels[j];
                if (label.equals(newColumnName)) {
                    allAreDifferent = false;
                    break;
                }
            }
            if (allAreDifferent) {
                found = true;
            }
        }

        return newColumnName;
    }

    protected abstract String getBeanString(B bean);

}
