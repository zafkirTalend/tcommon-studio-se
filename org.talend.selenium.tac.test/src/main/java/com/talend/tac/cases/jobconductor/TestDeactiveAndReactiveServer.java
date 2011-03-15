package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestDeactiveAndReactiveServer extends TestJobconductorClicked {
	@Test(groups = { "DeleteServer" },dependsOnGroups = { "AddServer" })
	@Parameters({ "DeactiveServerlable" })
	public void serverDeactiveAndReactive(String deactiveServername) {
		this.clickJobconductor();
		selenium.setSpeed("5000");
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
		selenium.setSpeed("0");
	}
}
