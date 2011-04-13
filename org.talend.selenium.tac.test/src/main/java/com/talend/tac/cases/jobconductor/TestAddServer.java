package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestAddServer extends Login {
	@Test(groups = { "AddServer" })
	@Parameters({ "ServerLablename", "PortInvalidServer", "ServerDescription",
			"ServerHost", "ServerCommondport", "ServerFiletransfortport",
			"ServerMonitorport", "ServerTimeout", "ServerUsername",
			"ServerPassword" })
	public void addServer(String lable, String invalidLable,
			String description, String host, String commondport,
			String transfortport, String monitorport, String time,
			String username, String password) {
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
		// System.out.println("click add before !");
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
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
			selenium.setSpeed(MAX_SPEED);
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			if (selenium.isElementPresent("//div[text()='" + lable + "']")) {

			} else {
				Assert.fail("Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
		selenium.setSpeed(MIN_SPEED);

	}

	
	
}
