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
package org.talend.core;

import java.util.HashMap;
import java.util.Map;

/**
 * DOC qian class global comment. Contains vary factories. <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40 +0000 (星期五, 29 九月 2006) nrousseau $
 * 
 */
public class GlobalServiceRegister {

    // The shared instance
    private static GlobalServiceRegister instance = new GlobalServiceRegister();

    public static GlobalServiceRegister getDefault() {
        return instance;
    }

    private Map<Class, IService> services = new HashMap<Class, IService>();

    /**
     * DOC qian Comment method "registerService".Register the IService.
     * @param klass
     * @param service
     */
    public void registerService(Class klass, IService service) {
        services.put(klass, service);
    }

    /**
     * DOC qian Comment method "getService".Gets the specific IService.
     * @param klass 
     * @return IService
     */
    public IService getService(Class klass) {
        IService service = services.get(klass);
        if (service == null) {
            throw new RuntimeException("This service has not been registered.");
        }
        return service;
    }
}
