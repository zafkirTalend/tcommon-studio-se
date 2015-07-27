package org.talend.component.salesforce;

import org.talend.component.ComponentProperties;
import org.talend.component.common.OauthProperties;
import org.talend.component.common.ProxyProperties;
import org.talend.component.common.UserPasswordProperties;

public class SalesforceProperties extends ComponentProperties {

	public enum LoginType {
		BASIC, OAUTH
	};

	protected LoginType loginType;

	protected boolean bulkConnection;

	protected String apiVersion;

	protected String endPoint;

	protected boolean needCompression;

	protected int timeout;

	protected boolean httpTraceMessage;

	protected String clientId;

	ProxyProperties proxy;
	OauthProperties oauth;
	UserPasswordProperties userPassword;

}
