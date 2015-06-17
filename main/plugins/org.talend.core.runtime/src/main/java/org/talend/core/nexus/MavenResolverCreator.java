// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.nexus;

import java.util.Dictionary;
import java.util.Hashtable;

import org.ops4j.pax.url.mvn.MavenResolver;
import org.ops4j.pax.url.mvn.MavenResolvers;
import org.ops4j.pax.url.mvn.ServiceConstants;

/**
 * created by wchen on Apr 24, 2015 Detailled comment
 *
 */
public class MavenResolverCreator {

    private static MavenResolverCreator creator;

    private MavenResolverCreator() {

    }

    public static synchronized MavenResolverCreator getInstance() {
        if (creator == null) {
            creator = new MavenResolverCreator();
        }
        return creator;
    }

    public MavenResolver getMavenResolver(Dictionary<String, String> properties) {
        return MavenResolvers.createMavenResolver(properties, ServiceConstants.PID);
    }

    /**
     * 
     * DOC get MavenResolver for RemoteModulesHelper to download jars.
     * 
     * @param nexusServer
     * @return
     */
    public MavenResolver getMavenResolver(NexusServerBean nexusServer) {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        // accept ssl
        // properties.put("maven.wagon.http.ssl.insecure", "true");
        // properties.put("maven.wagon.http.ssl.allowall", "true");

        if (nexusServer != null) {
            String nexusUrl = nexusServer.getServer();
            if (nexusUrl.endsWith(NexusConstants.SLASH)) {
                nexusUrl = nexusUrl.substring(0, nexusUrl.length() - 1);
            }

            String newUrl = nexusUrl + NexusConstants.CONTENT_REPOSITORIES + nexusServer.getRepositoryId()
                    + "@id=" + nexusServer.getRepositoryId();//$NON-NLS-1$
            properties.put("org.ops4j.pax.url.mvn.repositories", newUrl);//$NON-NLS-1$
        }
        return MavenResolvers.createMavenResolver(properties, ServiceConstants.PID);
    }

}
