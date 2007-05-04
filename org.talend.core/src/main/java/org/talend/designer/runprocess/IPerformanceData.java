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
package org.talend.designer.runprocess;

/**
 * DOC qian  class global comment. Detailled comment
 * <br/>
 *
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 *
 */
public interface IPerformanceData {

    /** Action : Performance report. */
    public static final String ACTION_PERF = "perf"; //$NON-NLS-1$

    /** Action : process stoped. */
    public static final String ACTION_STOP = "stop"; //$NON-NLS-1$

    /** Action : process started. */
    public static final String ACTION_START = "start"; //$NON-NLS-1$

    /** Action : clear performance stats. */
    public static final String ACTION_CLEAR = "clear"; //$NON-NLS-1$

    public String getNodeId();

    public long getLineCount();

    public long getProcessingTime();

    public String getAction();

}
