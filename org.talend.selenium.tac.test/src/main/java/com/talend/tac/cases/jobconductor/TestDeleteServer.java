package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestDeleteServer extends Login{
	@Test(groups = { "DeleteServer" },dependsOnGroups = { "Duplicate"})
	@Parameters({ "ServerUnused"})
	public void deleteServerUnused(String unusedServername) throws InterruptedException {
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
		selenium.click("//div[text()='Servers']/ancestor::div[@class='x-panel-body x-panel-body-noheader x-panel-body-noborder x-border-layout-ct']//button[@id='idSubModuleDeleteButton']");
		selenium.getConfirmation();
		Thread.sleep(5000);
		selenium.refresh();
		Thread.sleep(5000);
		Assert.assertFalse(
				selenium.isElementPresent("//div[text()='" + unusedServername
						+ "']"), "Unused server delete failed!");
		selenium.setSpeed(MIN_SPEED);
	}
}
