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
package org.talend.commons.ui.swt.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 */
public abstract class AbstractPreferencesHelperForTable {

    private static final String ROW_DELIMITER = "#;#"; //$NON-NLS-1$

    private static final String COLUMN_DELIMITER = "#o#"; //$NON-NLS-1$

    private List emptyList = new ArrayList(0);

    protected AbstractPreferencesHelperForTable() {
    }

    public <B> void storeTableData(String keyPreference, List<B> beans) {

        String storableString = getStorableString(beans, getBeanPropertyAccessors());
        getPreferenceStore().setValue(keyPreference, storableString);

    }

    public <B> List<B> loadTableData(String keyPreference, ExtendedTableModel<B> tableModel) {
        String string = getPreferenceStore().getString(keyPreference);
        List<B> beans = getBeans(string, getBeanPropertyAccessors(), tableModel);
        return beans;
    }
    
    /**
     * Combines the given list of items into a single string. This method is the converse of <code>parseString</code>.
     * <p>
     * Subclasses must implement this method.
     * </p>
     * 
     * @param beans the list of items
     * @return the combined string
     * @see #readString
     */
    protected <B> String getStorableString(List<B> beans, List<IBeanPropertyAccessors> columns) {
        int size = beans.size();
        StringBuffer buf = new StringBuffer(size * 50);
        for (int i = 0; i < size; i++) {
            String string = getBeanToString(beans.get(i), columns);
            buf.append(string);
            if (i != size - 1) {
                buf.append(ROW_DELIMITER);
            }
        }
        return buf.toString();
    }

    /**
     * DOC amaumont Comment method "getBeanToString".
     * 
     * @param b
     * @return
     */
    private <B> String getBeanToString(B bean, List<IBeanPropertyAccessors> columns) {
        int columnsListSize = columns.size();
        StringBuilder buf = new StringBuilder(columnsListSize * 50);
        for (int i = 0; i < columnsListSize; i++) {
            IBeanPropertyAccessors column = columns.get(i);
            String string = getStringValue(column, bean);
            buf.append(string);
            if (i != columnsListSize - 1) {
                buf.append(COLUMN_DELIMITER);
            }
        }
        return buf.toString();
    }

    /**
     * DOC amaumont Comment method "getStringValue".
     * 
     * @param column
     * @param bean
     * @return
     */
    protected <B> String getStringValue(IBeanPropertyAccessors column, B bean) {
        return String.valueOf(column.get(bean));
    }

    /**
     * Splits the given string into a list of strings. This method is the converse of <code>createList</code>.
     * <p>
     * Subclasses must implement this method.
     * </p>
     * 
     * @param stringList the string
     * @return an array of <code>String</code>
     * @see #writeString
     */
    protected <B> List<B> getBeans(String stringList, List<IBeanPropertyAccessors> columns,
            ExtendedTableModel<B> tableModel) {

        if (stringList == null || "".equals(stringList)) { //$NON-NLS-1$
            return emptyList;
        }
        // check(stringList);
        ArrayList<B> beans = new ArrayList<B>();
        String[] strings = stringList.split(ROW_DELIMITER);
        for (String rowString : strings) {
            String[] propertiesArray = rowString.split(COLUMN_DELIMITER);
            B bean = tableModel.createBeanInstance();
            bean = parseStringArray(propertiesArray, columns, bean);
            beans.add(bean);
        }
        return beans;

    }

    /**
     * DOC amaumont Comment method "parseStringArray".
     * 
     * @param strings
     * @return
     */
    protected <B> B parseStringArray(String[] strings, List<IBeanPropertyAccessors> columns, B bean) {
        int columnsListSize = columns.size();
        for (int i = 0; i < columnsListSize; i++) {
            String string = null;
            if (i < strings.length) {
                string = strings[i];
            }
            IBeanPropertyAccessors column = columns.get(i);
            parseValue(string, column, bean);
        }
        return bean;
    }

    /**
     * DOC amaumont Comment method "parseValue".
     * 
     * @param strings
     * @param bean
     * @param i
     * @param beanPropertyAccessors
     */
    protected <B> void parseValue(String value, IBeanPropertyAccessors column, B bean) {
        column.set(bean, value);
    }

    public abstract List<IBeanPropertyAccessors> getBeanPropertyAccessors();

    public abstract IPreferenceStore getPreferenceStore();
    
}
