package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteServer extends Login{
	@Test(groups = { "DeleteServer" },dependsOnGroups = { "Duplicate"})
	@Parameters({ "ServerUnused"})
	public void deleteServerUnused(String unusedServername) {
		selenium.setSpeed(MID_SPEED);
		if (selenium.isVisible("!!!menu.executionServers.element!!!")) {
			selenium.click("!!!menu.executionServers.element!!!");
			waitForElementPresent("idSubModuleAddButton", 30000);

		} else {
			selenium.click("!!!menu.jobConductor.element!!!");
			selenium.setSpeed(MID_SPEED);
			selenium.click("!!!menu.executionServers.element!!!");
			waitForElementPresent("idSubModuleAddButton", 30000);

		}
		
		selenium.refresh();//in order to light on the delete button
		selenium.chooseOkOnNextConfirmation();
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+unusedServername+"')]");
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idSubModuleDeleteButton");
		selenium.getConfirmation();
		Assert.assertFalse(
				selenium.isElementPresent("//div[text()='" + unusedServername
						+ "']"), "Unused server delete failed!");
		selenium.setSpeed(MIN_SPEED);
	}
}
