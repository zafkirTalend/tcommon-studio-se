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
package org.talend.core.model.process;


/**
 * DOC nrousseau  class global comment. Detailled comment
 * <br/>
 *
 */
public interface IConnectionCategory {

    /**
     * For FLOW_MAIN / ITERATE / TABLE.
     * Links considered as the Main links.
     */
    public static final int MAIN = 1;
    /**
     * For FLOW_MAIN / FLOW_REF / TABLE
     * Links which contains metadata.
     */
    public static final int DATA = 1 << 1;
    /**
     * For RUN_IF, RUN_IF_OK, RUN_IF_ERROR
     * Links with a condition for the execution (components will not run all the time).
     */
    public static final int CONDITION = 1 << 2;
    /**
     * For RUN_AFTER, RUN_BEFORE.
     * Links that will add an execution order.
     */
    public static final int EXECUTION_ORDER = 1 << 3;
    /**
     * For RUN_AFTER, RUN_BEFORE, RUN_IF, RUN_IF_OK, RUN_IF_ERROR.
     * Links that will add a dependency to run a sub process.
     */
    public static final int DEPENDENCY = 1 << 4;
    /**
     * For FLOW_MAIN, FLOW_REF, FLOW_MERGE.
     * Flow links (or main links), so in the menu Row > Main.
     */
    public static final int FLOW = 1 << 5;
    /**
     * For FLOW_REF.
     * Links that will create a hidden hash file (tHash component).
     */
    public static final int USE_HASH = 1 << 6;
    /**
     * For FLOW_MAIN, FLOW_REF, FLOW_MERGE, TABLE.
     * Links that can have a specific name.
     */
    public static final int CUSTOM_NAME = 1 << 7;
    /**
     * For FLOW_MAIN, FLOW_REF, FLOW_MERGE, LOOKUP.
     * Links that can have a specific name.
     */
    public static final int UNIQUE_NAME = 1 << 8;
}
