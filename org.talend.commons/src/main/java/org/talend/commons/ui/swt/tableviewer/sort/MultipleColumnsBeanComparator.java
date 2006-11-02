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
package org.talend.commons.ui.swt.tableviewer.sort;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.data.AccessorUtils;

/**
 * @author amaumont <br/> $Id$
 */
public class MultipleColumnsBeanComparator implements Comparator {

    /**
     * 
     * @author amaumont
     */

    private static final short ASCENDANT_ORDER = 1;

    private static final short DESCENDANT_ORDER = -1;

    private TableViewerCreatorColumn[] columnsToOrder;

    private int ascendingInt;

    private boolean ignoreCase;

    public MultipleColumnsBeanComparator(boolean ascendingOrder, TableViewerCreatorColumn column) {
        this(ascendingOrder, new TableViewerCreatorColumn[] { column });
    }

    public MultipleColumnsBeanComparator(boolean ascendingOrder, TableViewerCreatorColumn... columns) {
        this.columnsToOrder = columns;
        this.ascendingInt = ascendingOrder ? ASCENDANT_ORDER : DESCENDANT_ORDER;
    }

    public MultipleColumnsBeanComparator(TableViewerCreatorColumn... columnsToOrder) {
        this(true, columnsToOrder);
    }

    public MultipleColumnsBeanComparator(TableViewerCreatorColumn columnToOrder) {
        this(new TableViewerCreatorColumn[] { columnToOrder });
    }

    public final int compare(Object object1, Object object2) {
        int returnValue = 0;
        if (columnsToOrder != null) {
            try {
                if (columnsToOrder.length == 1 && columnsToOrder[0] != null) {
                    returnValue = compare(columnsToOrder[0], object1, object2);
                } else {
                    for (int i = 0; i < this.columnsToOrder.length; i++) {
                        TableViewerCreatorColumn property = this.columnsToOrder[i];
                        if (property == null) {
                            continue;
                        }

                        returnValue = compare(property, object1, object2);

                        if (returnValue != 0) {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return returnValue;
    }

    private int compare(TableViewerCreatorColumn column, Object object1, Object object2) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        Object value1 = null;
        Object value2 = null;
        if (column != null) {
            if (object1 != null) {
                value1 = AccessorUtils.get(object1, column);
            }
            if (object2 != null) {
                value2 = AccessorUtils.get(object2, column);
            }
        }
        return checkNullsAndCompare(value1, value2);
    }

    @SuppressWarnings("unchecked")
    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
        if (object1 instanceof String && object2 instanceof String) {
            returnValue = compareStrings((String) object1, (String) object2);
        } else if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            // si les objets sont différents de null on les compare avec le
            // toString
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1; // un des deux objet est null, on inverse le tri
        } else if (object1 != null && object2 == null) {
            returnValue = -1; // un des deux objet est null
        } else {
            // les deux objet sont null
            returnValue = 0;
        }

        return ascendingInt * returnValue;
    }

    private int compareStrings(String string1, String string2) {
        if (this.ignoreCase) {
            return string1.compareToIgnoreCase(string2);
        } else {
            return string1.compareTo(string2);
        }
    }

    @SuppressWarnings("unchecked")
    public static void sort(boolean orderAscendant, List< ? extends Object> collection, TableViewerCreatorColumn... propertiesByPriority) {
        Collections.sort(collection, new MultipleColumnsBeanComparator(orderAscendant, propertiesByPriority));
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public TableViewerCreatorColumn[] getColumnsToOrder() {
        return columnsToOrder;
    }

    public void setColumnsToOrder(TableViewerCreatorColumn[] columnsToOrder) {
        this.columnsToOrder = columnsToOrder;
    }

    public void setColumnToOrder(TableViewerCreatorColumn property) {
        this.columnsToOrder = new TableViewerCreatorColumn[] { property };
    }

    public void setAscendingOrder(boolean ascendingOrder) {
        ascendingInt = ascendingOrder ? ASCENDANT_ORDER : DESCENDANT_ORDER;
    }

}
