package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestDeleteServer extends TestJobconductorClicked {
	@Test(groups = { "DeleteServer" },dependsOnGroups = { "AddServer" })
	@Parameters({ "ServerUnused"})
	public void deleteServerUnused(String unusedServername) {
		this.clickJobconductor();
		selenium.setSpeed("3000");
//		selenium.click("idSubModuleRefreshButton");
		selenium.refresh();//in order to light on the delete button
		selenium.chooseOkOnNextConfirmation();
		System.out.println("before delete:"+unusedServername);
		
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+unusedServername+"')]");
		selenium.click("idSubModuleDeleteButton");
//		selenium.setSpeed("8000");
		selenium.getConfirmation();
//		.matches(
//				other.getString("jobconductor.server.delete.unusedserver.warning.contents"))
		Assert.assertFalse(
				selenium.isElementPresent("//div[text()='" + unusedServername
						+ "']"), "Unused server delete failed!");
	}
}
