package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestAddServerWithStringPortsInput extends Login {
	@Test(groups = { "AddServer" })
	@Parameters({ "LabelServerStringPorts", "NullLabelServerHost" })
	public void testServerStringports(String label, String host) {
		this.waitForElementPresent("!!!menu.executionServers.element!!!",
				WAIT_TIME);
		selenium.click("!!!menu.executionServers.element!!!");
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"), label);
		// host
		this.typeString(other.getString("inputname.id.server.add.host"), host);
		// port1
		this.typeString(other.getString("inputname.id.server.add.commandPort"),
				"String");
		// port2
		this.typeString(
				other.getString("inputname.id.server.add.fileTransfertPort"),
				"String");
		// port3
		this.typeString(
				other.getString("inputname.id.server.add.monitoringPort"),
				"String");
		Assert.assertTrue(selenium.getXpathCount(
				"//img[@class='gwt-Image x-component ' and @role='alert']")
				.intValue() == 3);
		selenium.setSpeed(MID_SPEED);
		selenium.click("idFormSaveButton");
		Assert.assertFalse(
				selenium.isElementPresent("//div[text()='" + label + "']"),
				"Test ports string failed!");
		selenium.setSpeed(MIN_SPEED);

	}

}
