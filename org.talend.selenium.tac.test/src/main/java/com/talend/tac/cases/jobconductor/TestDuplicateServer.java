package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestDuplicateServer extends Login {
	@Test(groups = { "Duplicate"},dependsOnGroups={"DeactiveReactive"} )
	@Parameters({ "ServerLablename", "PortInvalidServer", "ServerDescription",
			"ServerHost", "ServerCommondport", "ServerFiletransfortport",
			"ServerMonitorport", "ServerTimeout", "ServerUsername",
			"ServerPassword" })
	public void testDuplicateServer(String lable, String invalidLable,
			String description, String host, String commondport,
			String transfortport, String monitorport, String time,
			String username, String password) throws InterruptedException {
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
	
		duplicateServer(lable);
		// addServerwithInvalideports(invalidLable);
		selenium.setSpeed(MIN_SPEED);

	}

	public void duplicateServer(String duplicatedServername) throws InterruptedException {
		selenium.setSpeed(MAX_SPEED);
		selenium.refresh();
		selenium.mouseDown("//div[text()='" + duplicatedServername + "']");
		selenium.click("idSubModuleDuplicateButton");
		selenium.click("idFormSaveButton");
		Thread.sleep(5000);
//		selenium.click("idSubModuleRefreshButton");
		selenium.refresh();
		Thread.sleep(3000);
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='Copy_of_"+duplicatedServername+"')]"),
				"server  duplicated failed!");
		selenium.setSpeed(MIN_SPEED);

	}

	public void typeString(String xpath, String value) {
		selenium.click(xpath);
		selenium.type(xpath, value);
		selenium.fireEvent(xpath, "blur");
	}
}
