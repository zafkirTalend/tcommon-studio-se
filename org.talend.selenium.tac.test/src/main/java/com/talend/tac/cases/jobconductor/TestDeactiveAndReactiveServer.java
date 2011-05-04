package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeactiveAndReactiveServer extends Login{
	@Test(groups = { "DeactiveReactive" },dependsOnGroups = { "AddServer" })
	@Parameters({ "DeactiveServerlable" })
	public void serverDeactiveAndReactive(String deactiveServername) throws InterruptedException {
		Thread.sleep(5000);
		this.waitForElementPresent("!!!menu.executionServers.element!!!", WAIT_TIME);
		selenium.click("!!!menu.executionServers.element!!!");
		Thread.sleep(5000);
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]", WAIT_TIME);
		
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]");
			// uncheck the active optioon
			Thread.sleep(3000);
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");
			// click the save button
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			Assert.assertTrue(
					(selenium
							.isElementPresent("//span[@class='serv-value' and (text()='INACTIVE')]")),
					"Server deactive failed!");
			selenium.setSpeed(MIN_SPEED);
			//reactive the server
			selenium.refresh();
			this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]", WAIT_TIME);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+deactiveServername+"')]");
			// checked the active optioon
			Thread.sleep(3000);
			selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");
			// click the save button
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			Assert.assertTrue(
					(selenium
							.isElementPresent("//span[@class='serv-value' and (text()='UP')]")),
					"Server reactive failed!");
		
		selenium.setSpeed(MIN_SPEED);
	}
}
