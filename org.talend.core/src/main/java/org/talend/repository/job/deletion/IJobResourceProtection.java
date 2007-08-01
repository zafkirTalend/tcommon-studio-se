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
package org.talend.repository.job.deletion;

/**
 * Add protection on the resource for prescribed job.
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: IResourceProtection.java 下午03:39:51 2007-7-6 +0000 (2007-7-6) yzhang $
 * 
 */
public interface IJobResourceProtection {

    /**
     * Calculate the id of the resources need to be protected. The id of resource need add protection.
     * 
     * yzhang Comment method "getProtectionIds".
     * 
     * @return
     */
    public String[] calculateProtectedIds();

    /**
     * Return the ids of protected resources. The protected id need to be removed from protection.
     * 
     * yzhang Comment method "getProjectedIds".
     * 
     * @return
     */
    public String[] getProtectedIds();

    /**
     * The the job resource under the specific id.
     * 
     * yzhang Comment method "getJobResource".
     * 
     * @return
     */
    public JobResource getJobResource(String id);
}
