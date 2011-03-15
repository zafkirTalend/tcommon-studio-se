package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

public class TestAddServer extends TestJobconductorClicked {
	@Test(groups = { "AddServer" })
	@Parameters({ "ServerLablename", "PortInvalidServer", "ServerDescription",
			"ServerHost", "ServerCommondport", "ServerFiletransfortport",
			"ServerMonitorport", "ServerTimeout", "ServerUsername",
			"ServerPassword" })
	public void addServer(String lable, String invalidLable,
			String description, String host, String commondport,
			String transfortport, String monitorport, String time,
			String username, String password) {
		this.clickJobconductor();
		selenium.setSpeed("5000");
		// System.out.println("click add before !");
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
			// lable
			selenium.setSpeed("0");
			selenium.click(other.getString("inputname.id.server.add.label"));
			selenium.type(other.getString("inputname.id.server.add.label"),
					lable);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.label"), "blur");
			// description
			selenium.click(other
					.getString("inputname.id.server.add.description"));
			selenium.type(
					other.getString("inputname.id.server.add.description"),
					description);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.description"),
					"blur");
			// host
			selenium.click(other.getString("inputname.id.server.add.host"));
			selenium.type(other.getString("inputname.id.server.add.host"), host);
			selenium.fireEvent(other.getString("inputname.id.server.add.host"),
					"blur");
			// port1
			selenium.click(other
					.getString("inputname.id.server.add.commandPort"));
			selenium.type(
					other.getString("inputname.id.server.add.commandPort"),
					commondport);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.commandPort"),
					"blur");
			// port2
			selenium.click(other
					.getString("inputname.id.server.add.fileTransfertPort"));
			selenium.type(other
					.getString("inputname.id.server.add.fileTransfertPort"),
					transfortport);
			selenium.fireEvent(other
					.getString("inputname.id.server.add.fileTransfertPort"),
					"blur");
			// port3
			selenium.click(other
					.getString("inputname.id.server.add.monitoringPort"));
			selenium.type(
					other.getString("inputname.id.server.add.monitoringPort"),
					monitorport);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.monitoringPort"),
					"blur");
			// time
			selenium.click(other
					.getString("inputname.id.server.add.timeoutUnknownState"));
			selenium.type(other
					.getString("inputname.id.server.add.timeoutUnknownState"),
					time);
			selenium.fireEvent(other
					.getString("inputname.id.server.add.timeoutUnknownState"),
					"blur");
			// user name
			selenium.click(other.getString("inputname.id.server.add.username"));
			selenium.type(other.getString("inputname.id.server.add.username"),
					username);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.username"), "blur");
			// pass word
			selenium.click(other.getString("inputname.id.server.add.password"));
			selenium.type(other.getString("inputname.id.server.add.password"),
					password);
			selenium.fireEvent(
					other.getString("inputname.id.server.add.password"), "blur");
			// save
			selenium.setSpeed("5000");
			selenium.click("idFormSaveButton");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			// selenium.setSpeed("2000");

			// Assert.assertTrue(selenium.isElementPresent(""));
			// Assert.fail("");
			// Assert.assertTrue(selenium.isElementPresent(""),
			// "Server added failed !");
			if (selenium.isElementPresent("//div[text()='" + lable + "']")) {

			} else {
				Assert.fail("Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
		selenium.setSpeed("0");
		// ************************************
		selenium.setSpeed("3000");
		duplicateServer(lable);
//		addServerwithInvalideports(invalidLable);
		

		selenium.setSpeed("0");
		// ************************************

	}

	public void addServerwithInvalideports(String invalideServername) {
		selenium.click("idSubModuleAddButton");
		System.out.println("after tianjian");
		// lable
		selenium.setSpeed("0");
		selenium.click(other.getString("inputname.id.server.add.label"));
		selenium.type(other.getString("inputname.id.server.add.label"),
				invalideServername);
		selenium.fireEvent(other.getString("inputname.id.server.add.label"),
				"blur");
		// description
		selenium.click(other.getString("inputname.id.server.add.description"));
		selenium.type(other.getString("inputname.id.server.add.description"),
				"0");
		selenium.fireEvent(
				other.getString("inputname.id.server.add.description"), "blur");
		// host
		selenium.click(other.getString("inputname.id.server.add.host"));
		selenium.type(other.getString("inputname.id.server.add.host"),
				"localhost");
		selenium.fireEvent(other.getString("inputname.id.server.add.host"),
				"blur");
		// port1 invalid with characters
		selenium.click(other.getString("inputname.id.server.add.commandPort"));
		selenium.type(other.getString("inputname.id.server.add.commandPort"),
				"1023");
		selenium.fireEvent(
				other.getString("inputname.id.server.add.commandPort"), "blur");
		// if the warning img present,it's true
		Assert.assertTrue(selenium.isElementPresent(""), "");// ************************************************
		// port valid
		selenium.click(other
				.getString("inputname.id.server.add.fileTransfertPort"));
		selenium.type(
				other.getString("inputname.id.server.add.fileTransfertPort"),
				"1024");
		selenium.fireEvent(
				other.getString("inputname.id.server.add.fileTransfertPort"),
				"blur");

		// port invalid with characters
		selenium.click(other
				.getString("inputname.id.server.add.monitoringPort"));
		selenium.type(
				other.getString("inputname.id.server.add.monitoringPort"),
				"888s");
		selenium.fireEvent(
				other.getString("inputname.id.server.add.monitoringPort"),
				"blur");
		// count the warning images
		if (selenium.getXpathCount("").intValue() != 2) {// Xpath of warning
															// image
			Assert.fail("ports verify failed!");
		} else {
			selenium.click("idFormCancelButton");
		}
		selenium.setSpeed("0");

	}

	/*
	 * public void addServerWithInvalidIp(String lable, String description,
	 * String commondport, String transfortport, String monitorport, String
	 * time, String username, String password) { this.clickJobconductor();
	 * selenium.setSpeed("5000"); // System.out.println("click add before !");
	 * if (selenium.isElementPresent("idSubModuleAddButton")) {
	 * 
	 * selenium.click("idSubModuleAddButton");
	 * 
	 * // lable selenium.setSpeed("0"); selenium.click(""); selenium.type("",
	 * "Invalid" + lable); selenium.fireEvent("", "blur"); // description
	 * selenium.click(""); selenium.type("", description);
	 * selenium.fireEvent("", "blur"); // an invalid host selenium.click("");
	 * selenium.type("", "123456789"); selenium.fireEvent("", "blur"); // port
	 * selenium.click(""); selenium.type("", commondport);
	 * selenium.fireEvent("", "blur"); // port selenium.click("");
	 * selenium.type("", transfortport); selenium.fireEvent("", "blur"); // port
	 * selenium.click(""); selenium.type("", monitorport);
	 * selenium.fireEvent("", "blur"); // time selenium.click("");
	 * selenium.type("", time); selenium.fireEvent("", "blur"); // user name
	 * selenium.click(""); selenium.type("", username); selenium.fireEvent("",
	 * "blur"); // pass word selenium.click(""); selenium.type("", password);
	 * selenium.fireEvent("", "blur"); // save
	 * selenium.click("idFormSaveButton"); selenium.setSpeed("3000"); // refresh
	 * selenium.click("idSubModuleRefreshButton"); selenium.setSpeed("2000");
	 * 
	 * // Assert.assertTrue(selenium.isElementPresent("")); // Assert.fail("");
	 * // Assert.assertTrue(selenium.isElementPresent(""), //
	 * "Server added failed !"); if
	 * (selenium.isElementPresent("//span[text()='Invalid" + lable + "']")) {
	 * Assert.assertTrue(
	 * selenium.isElementPresent("//span[@class='serv-value' and (text()='DOWN')]"
	 * ), "test with invalid server Ip");
	 * 
	 * } else { Assert.fail("Server added failed !"); } } else {
	 * Assert.fail("add button can not be seen !");
	 * 
	 * } selenium.setSpeed("0"); }
	 */
	public void duplicateServer(String duplicatedServername) {
		selenium.setSpeed("5000");
		selenium.mouseDown("//div[text()='" + duplicatedServername + "']");
		selenium.click("idSubModuleDuplicateButton");
		selenium.click("idFormSaveButton");
		selenium.click("idSubModuleRefreshButton");
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='Copy_of_serverTest')]"),
				"server  duplicated failed!");
		selenium.setSpeed("0");

	}
}
