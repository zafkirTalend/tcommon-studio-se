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
		// ************************************
		selenium.setSpeed(MID_SPEED);
		serverDeactiveAndReactive(lable);
		duplicateServer(lable);
		// addServerwithInvalideports(invalidLable);
		selenium.setSpeed(MIN_SPEED);

	}

	public void serverDeactiveAndReactive(String deactiveServername) {
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
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
					+ deactiveServername + "')]");
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
			// reactive the server
			selenium.refresh();
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
					+ deactiveServername + "')]");
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
		selenium.setSpeed(MIN_SPEED);
	}

	public void addServerwithInvalideports(String invalideServername) {
		selenium.click("idSubModuleAddButton");
		// lable
		this.typeString(other.getString("inputname.id.server.add.label"),
				invalideServername);
		selenium.setSpeed(MIN_SPEED);

		// description
		this.typeString(other.getString("inputname.id.server.add.description"),
				"description");

		// host
		this.typeString(other.getString("inputname.id.server.add.host"),
				"localhost");

		// port1 invalid with characters
		this.typeString(other.getString("inputname.id.server.add.commandPort"),
				"1023");

		// if the warning img present,it's true
		// Assert.assertTrue(selenium.isElementPresent(""), "");
		// port valid
		this.typeString(
				other.getString("inputname.id.server.add.fileTransfertPort"),
				"1024");
		// port invalid with characters
		this.typeString(
				other.getString("inputname.id.server.add.monitoringPort"),
				"888s");
		// count the warning images
		if (selenium.getXpathCount("").intValue() != 2) {// Xpath of warning
															// image
			Assert.fail("ports verify failed!");
		} else {
			selenium.click("idFormCancelButton");
		}
		selenium.setSpeed(MIN_SPEED);

	}

	public void duplicateServer(String duplicatedServername) {
		selenium.setSpeed(MAX_SPEED);
		selenium.refresh();
		selenium.mouseDown("//div[text()='" + duplicatedServername + "']");
		selenium.click("idSubModuleDuplicateButton");
		selenium.click("idFormSaveButton");
		selenium.click("idSubModuleRefreshButton");
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='Copy_of_serverTest')]"),
				"server  duplicated failed!");
		selenium.setSpeed(MIN_SPEED);

	}

	public void typeString(String xpath, String value) {
		selenium.click(xpath);
		selenium.type(xpath, value);
		selenium.fireEvent(xpath, "blur");
	}
}
