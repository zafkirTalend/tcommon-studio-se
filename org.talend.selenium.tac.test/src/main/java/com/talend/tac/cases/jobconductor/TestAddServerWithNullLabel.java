package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestAddServerWithNullLabel extends Login {
	@Test(groups = { "AddServer" })
	@Parameters({ "NullLabelServerHost" })
	public void testServerNullLabel(String host) {
		this.waitForElementPresent("!!!menu.executionServers.element!!!",
				WAIT_TIME);
		selenium.click("!!!menu.executionServers.element!!!");
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"), "");
		// host
		this.typeString(other.getString("inputname.id.server.add.host"), host);
		Assert.assertTrue(selenium
				.isElementPresent("//img[@class='gwt-Image x-component ' and @role='alert']"));

		selenium.setSpeed(MIN_SPEED);

	}

}
