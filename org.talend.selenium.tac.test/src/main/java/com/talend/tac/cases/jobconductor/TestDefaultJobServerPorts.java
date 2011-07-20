package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import com.talend.tac.cases.Login;

public class TestDefaultJobServerPorts extends Login {
	@Test(groups = { "AddServer" })
	@Parameters({ "ServerCommondport","ServerFiletransfortport","ServerMonitorport" })
	public void testServerLabelSpecial(String commondport,String filetransport,String monitorport){
		this.waitForElementPresent("!!!menu.executionServers.element!!!",
				WAIT_TIME);
		selenium.click("!!!menu.executionServers.element!!!");
		this.clickWaitForElementPresent("idSubModuleAddButton");
		this.sleep(3000);
        Assert.assertTrue(selenium.getValue("//input[@name='commandPort']").toString().equals(commondport), "test check default commondport failed");
        Assert.assertTrue(selenium.getValue("//input[@name='fileTransfertPort']").toString().equals(filetransport), "test check default filetransport failed");
        Assert.assertTrue(selenium.getValue("//input[@name='monitoringPort']").toString().equals(monitorport), "test check default monitor port failed");

	}

}
