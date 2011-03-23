package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeactiveAndReactiveServer extends Login{
	@Test(groups = { "DeleteServer" },dependsOnGroups = { "AddServer" })
	@Parameters({ "DeactiveServerlable" })
	public void serverDeactiveAndReactive(String deactiveServername) {
		selenium.setSpeed(MAX_SPEED);
		if (selenium.isVisible("!!!menu.executionServers.element!!!")) {
			selenium.click("!!!menu.executionServers.element!!!");
			waitForElementPresent("idSubModuleAddButton", 30000);

		} else {
			selenium.click("!!!menu.jobConductor.element!!!");
			selenium.setSpeed(MID_SPEED);
			selenium.click("!!!menu.executionServers.element!!!");
			waitForElementPresent("idSubModuleAddButton", 30000);

		}
		selenium.setSpeed(MAX_SPEED);
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]");
			// uncheck the active optioon
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");
			// click the save button
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			Assert.assertTrue(
					(selenium
							.isElementPresent("//span[@class='serv-value' and (text()='INACTIVE')]")),
					"Server deactive failed!");
			//reactive the server
			selenium.refresh();
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]");
			// checked the active optioon
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");
			// click the save button
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			Assert.assertTrue(
					(selenium
							.isElementPresent("//span[@class='serv-value' and (text()='UP')]")),
					"Server reactive failed!");
		} else {
			Assert.fail("Server named" + deactiveServername
					+ "can not be found!");
		}
		selenium.setSpeed(MIN_SPEED);
	}
}
