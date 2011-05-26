package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;
import com.talend.tac.cases.projects.TacCleaner;

public class TestAddServerForUse extends Login {
	@Test(groups = { "left" }, dependsOnGroups = { "CleanServer" })
	@Parameters({ "ServerForUseAvailable", "ServerForUseUnavailable","ServerForUseDownlabel",
			"ServeravailableHost" })
	public void addServerForuse(String lableavailable, String labelunactive,String downlabel,
			String availablehost) throws InterruptedException {
		this.waitForElementPresent("!!!menu.executionServers.element!!!",
				WAIT_TIME);
		new TacCleaner().cleanServersNotused(this.selenium);
		addServerAvailable(lableavailable, availablehost);
		addServerUnactive(labelunactive,availablehost);
//		addServerDown(downlabel);
		// addServer(labelunavailable,"testUnavailable");
	}

	public void addServerAvailable(String lable, String host)
			throws InterruptedException {
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		Thread.sleep(2000);
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"), lable);
		// host
		this.typeString(other.getString("inputname.id.server.add.host"), host);
		// save
//		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
		Thread.sleep(3000);
		selenium.refresh();
		this.waitForElementPresent("//div[text()='" + lable
				+ "']", WAIT_TIME);
		Thread.sleep(2000);
		Assert.assertTrue((selenium.isElementPresent("//div[text()='" + lable
				+ "']"))&&selenium.isElementPresent("//span[@class='serv-value' and (text()='UP')]"),"add server available failed!");
		selenium.setSpeed(MIN_SPEED);

	}

	public void addServerUnactive(String unactivelabel, String host)
			throws InterruptedException {
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		Thread.sleep(2000);
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"),
				unactivelabel);
		// host
		this.typeString(other.getString("inputname.id.server.add.host"), host);
		selenium.click("//input[@class=' x-form-checkbox' and @type='checkbox' and @name='active']");
		// save
//		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
		Thread.sleep(8000);
		selenium.refresh();
		this.waitForElementPresent("//div[text()='"
				+ unactivelabel + "']", WAIT_TIME);
		Thread.sleep(2000);
		Assert.assertTrue((selenium.isElementPresent("//div[text()='"
				+ unactivelabel + "']"))
				&& (selenium
						.isElementPresent("//span[@class='serv-value' and (text()='INACTIVE')]")),"add server vaule = inactive failed!");
		selenium.setSpeed(MIN_SPEED);

	}
	public void addServerDown(String serverdown) throws InterruptedException{
		selenium.refresh();
		this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		selenium.click("idSubModuleAddButton");
		Thread.sleep(2000);
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"),
				serverdown);
		// host
		this.typeString(other.getString("inputname.id.server.add.host"), "unknownhost");
		// save
//		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
		Thread.sleep(10000);
		selenium.refresh();
		Thread.sleep(8000);
		selenium.refresh();
		Thread.sleep(8000);
		Assert.assertTrue(((selenium.isElementPresent("//div[text()='"
				+ serverdown + "']"))
				&& (selenium
						.isElementPresent("//span[@class='serv-value' and (text()='DOWN')]"))),"add server value = down failed!");
		selenium.setSpeed(MIN_SPEED);
		
	}

}
