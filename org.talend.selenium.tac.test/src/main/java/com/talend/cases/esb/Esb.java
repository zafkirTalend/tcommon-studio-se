package com.talend.cases.esb;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;


public class Esb extends Login {
	
	
	/**
	 * this method is only to open activity monitor page
	 */
	public void openServiceActivityMonitor(){
		this.clickWaitForElementPresent("!!!menu.serviceactivity.element!!!");
		this.waitForElementPresent("//div[@class='header-title' and text()='Service Activity Monitoring']", WAIT_TIME);
	}
}
