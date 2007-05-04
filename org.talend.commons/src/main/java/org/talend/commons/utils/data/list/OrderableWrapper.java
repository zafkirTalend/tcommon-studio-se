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

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
/**
 * 
 * DOC amaumont ListenableList class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 * @param <B> bean which must be ordered by <code>order</code> value
 */
class OrderableWrapper<B> implements Comparable<OrderableWrapper> {

    private int order;

    private B bean;

    /**
     * DOC amaumont PriorityCalledWrapper constructor comment.
     */
    public OrderableWrapper(int order, B bean) {
        super();
        this.order = order;
        this.bean = bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(OrderableWrapper o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        if (o.order == this.order) {
            return 0;
        }
        if (o.order > this.order) {
            return -1;
        }
        if (o.order < this.order) {
            return 1;
        }

        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.bean == null) ? 0 : this.bean.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderableWrapper other = (OrderableWrapper) obj;
        if (this.bean == null) {
            if (other.bean != null) {
                return false;
            }
        } else if (!this.bean.equals(other.bean)) {
            return false;
        }
        return true;
    }

    /**
     * Getter for bean.
     * 
     * @return the bean
     */
    public B getBean() {
        return this.bean;
    }

    /**
     * Sets the bean.
     * 
     * @param bean the bean to set
     */
    public void setBean(B bean) {
        this.bean = bean;
    }

    /**
     * Getter for priorityCalled.
     * 
     * @return the priorityCalled
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Sets the priorityCalled.
     * 
     * @param priorityCalled the priorityCalled to set
     */
    public void setOrder(int priorityCalled) {
        this.order = priorityCalled;
    }

}
