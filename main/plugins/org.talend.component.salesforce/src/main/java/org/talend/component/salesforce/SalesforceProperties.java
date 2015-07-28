package org.talend.component.salesforce;

import org.talend.component.ComponentProperties;
import org.talend.component.common.OauthProperties;
import org.talend.component.common.ProxyProperties;
import org.talend.component.common.UserPasswordProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("salesforceProperties")
public class SalesforceProperties extends ComponentProperties {

	public enum LoginType {
		BASIC, OAUTH
	};

	@JsonProperty
	protected LoginType loginType;

	@JsonProperty
	protected boolean bulkConnection;

	@JsonProperty
	protected String apiVersion;

	@JsonProperty
	protected String endPoint;

	@JsonProperty
	protected boolean needCompression;

	@JsonProperty
	protected int timeout;

	@JsonProperty
	protected boolean httpTraceMessage;

	@JsonProperty
	protected String clientId;

	@JsonProperty
	protected ProxyProperties proxy;
	
	@JsonProperty
	protected OauthProperties oauth;

	@JsonProperty
	protected UserPasswordProperties userPassword;

}
