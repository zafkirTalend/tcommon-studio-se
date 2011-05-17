package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestAddServer extends Login {
	@Test(groups = { "AddServer" })
	@Parameters({ "ServerLablename","ServerDescription",
			"ServerHost", "ServerCommondport", "ServerFiletransfortport",
			"ServerMonitorport", "ServerTimeout", "ServerUsername",
			"ServerPassword" })
	public void addServer(String lable,
			String description, String host, String commondport,
			String transfortport, String monitorport, String time,
			String username, String password) throws InterruptedException {
		Thread.sleep(5000);
		this.waitForElementPresent("!!!menu.executionServers.element!!!", WAIT_TIME);
		
			selenium.click("!!!menu.executionServers.element!!!");
			waitForElementPresent("idSubModuleAddButton", 30000);
		selenium.click("idSubModuleAddButton");
		Thread.sleep(3000);
			// lable
			selenium.setSpeed(MIN_SPEED);
			this.typeString(other.getString("inputname.id.server.add.label"),
					lable);
			// description
			this.typeString(
					other.getString("inputname.id.server.add.description"),
					description);
			// host
			this.typeString(other.getString("inputname.id.server.add.host"),
					host);
			// port1
			this.typeString(
					other.getString("inputname.id.server.add.commandPort"),
					commondport);
			// port2
			this.typeString(other
					.getString("inputname.id.server.add.fileTransfertPort"),
					transfortport);
			// port3
			this.typeString(
					other.getString("inputname.id.server.add.monitoringPort"),
					monitorport);
			// time
			this.typeString(other
					.getString("inputname.id.server.add.timeoutUnknownState"),
					time);
			// user name
			this.typeString(
					other.getString("inputname.id.server.add.username"),
					username);
			// pass word
			this.typeString(
					other.getString("inputname.id.server.add.password"),
					password);
			// save
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			Thread.sleep(3000);
			// refresh
			selenium.refresh();
			this.waitForElementPresent("//div[text()='" + lable + "']", WAIT_TIME);
			Assert.assertTrue(((selenium.isElementPresent("//div[text()='" + lable + "']"))&&(selenium
					.isElementPresent("//span[@class='serv-value' and (text()='UP')]"))),"") ;
		selenium.setSpeed(MIN_SPEED);

	}

	
	
}
