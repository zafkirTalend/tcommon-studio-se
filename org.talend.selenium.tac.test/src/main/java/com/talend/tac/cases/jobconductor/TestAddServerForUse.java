package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.projects.TacCleaner;

public class TestAddServerForUse extends Login {
	@Test(groups = { "left" },dependsOnGroups={"else"})
	@Parameters({ "ServerForUseAvailable", "ServerForUseUnavailable","ServeravailableHost"})
	public void addServerForuse(String lableavailable,String labelunavailable,String availablehost) throws InterruptedException {
	this.waitForElementPresent("!!!menu.executionServers.element!!!", WAIT_TIME);
     new TacCleaner().cleanServersNotused(this.selenium);
     addServer(lableavailable,availablehost);
//     addServer(labelunavailable,"testUnavailable");
	}
	public void addServer(String lable,String host) throws InterruptedException {
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		Thread.sleep(5000);
			// lable
			
			this.typeString(other.getString("inputname.id.server.add.label"),
					lable);
			
			// host
			this.typeString(other.getString("inputname.id.server.add.host"),
					host);
			
			// save
			selenium.setSpeed(MAX_SPEED);
			selenium.click("idFormSaveButton");
			Thread.sleep(3000);
			Assert.assertTrue(selenium.isElementPresent("//div[text()='" + lable + "']"));
		    selenium.setSpeed(MIN_SPEED);

	}
	
}
