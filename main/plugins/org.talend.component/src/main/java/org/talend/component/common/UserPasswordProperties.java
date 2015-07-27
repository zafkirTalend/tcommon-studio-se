package org.talend.component.common;

import org.talend.component.ComponentProperties;

public class UserPasswordProperties extends ComponentProperties {

	String userId;
	String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
