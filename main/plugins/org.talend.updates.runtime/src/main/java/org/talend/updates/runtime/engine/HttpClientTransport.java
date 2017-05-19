// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.updates.runtime.engine;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.talend.commons.exception.BusinessException;
import org.talend.core.nexus.NexusConstants;
import org.talend.core.runtime.maven.MavenArtifact;
import org.talend.designer.maven.utils.PomUtil;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class HttpClientTransport {

    private String baseURI;

    private String username, password;

    public HttpClientTransport(String baseURI, String username, String password) {
        super();
        this.baseURI = baseURI;
        this.username = username;
        this.password = password;
    }

    URI ceateURI(MavenArtifact artifact) throws URISyntaxException {
        // like https://talend-update.talend.com/nexus/content/repositories/components/
        String baseRepoURI = baseURI;
        if (baseRepoURI == null) {
            throw new IllegalArgumentException("Must provide the nexus base repository uri");
        }
        String artifactPath = PomUtil.getArtifactPath(artifact);

        if (!baseRepoURI.endsWith(NexusConstants.SLASH)) {
            baseRepoURI += NexusConstants.SLASH;
        }
        final URI uri = new URI(baseRepoURI + artifactPath);
        return uri;
    }

    public void doRequest(IProgressMonitor monitor, MavenArtifact artifact) throws Exception {
        doRequest(monitor, ceateURI(artifact));
    }

    public void doRequest(IProgressMonitor monitor, URI requestURI) throws Exception {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        if (monitor.isCanceled()) {
            throw new OperationCanceledException();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            if (StringUtils.isNotBlank(username)) { // set username
                httpClient.getCredentialsProvider().setCredentials(new AuthScope(requestURI.getHost(), requestURI.getPort()),
                        new UsernamePasswordCredentials(username, password));
            }
            HttpResponse response = execute(monitor, httpClient, requestURI);

            processResponseCode(response);
        } catch (Exception e) {
            throw new Exception(requestURI.toString(), e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    void processResponseCode(HttpResponse response) throws BusinessException {
        StatusLine statusLine = response.getStatusLine();
        int responseCode = statusLine.getStatusCode();
        if (responseCode > 399) {
            if (responseCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) { // 500
                // ignore this error , if already exist on server and deploy again will get this error
            } else if (responseCode == HttpStatus.SC_UNAUTHORIZED) { // 401
                throw new BusinessException("Authrity failed");
            } else {
                throw new BusinessException("Do request failed: " + responseCode + ' ' + statusLine.getReasonPhrase());
            }
        }
    }

    protected abstract HttpResponse execute(IProgressMonitor monitor, DefaultHttpClient httpClient, URI targetURI)
            throws Exception;
}
