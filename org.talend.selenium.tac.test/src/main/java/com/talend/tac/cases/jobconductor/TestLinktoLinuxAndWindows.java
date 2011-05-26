package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestLinktoLinuxAndWindows extends Login {
  @Test(groups = { "left" }, dependsOnGroups = { "CleanServer" })
  @Parameters({ "RemoteLinuxServerIp", "RemoteWindowServerIp"})
  public void linktoLinuxAndWindows(String linuxIp,String windowsIp) throws InterruptedException {
//	    Thread.sleep(5000);
//	    selenium.setSpeed(MAX_SPEED);
	  
	    this.clickWaitForElementPresent("!!!menu.executionServers.element!!!");
//		if (selenium.isVisible("!!!menu.executionServers.element!!!")) {
//			selenium.click("!!!menu.executionServers.element!!!");
//			waitForElementPresent("idSubModuleAddButton", 30000);
//
//		} else {
//			selenium.click("!!!menu.jobConductor.element!!!");
//			selenium.setSpeed(MID_SPEED);
//			selenium.click("!!!menu.executionServers.element!!!");
//			waitForElementPresent("idSubModuleAddButton", 30000);
//
//		}
		addRemoteServerRuninLinux(linuxIp);
		deleteServer("RemoteLinux");
		addRemoteServerRuninWindows(windowsIp);
		deleteServer("RemoteWindows");
		
  }
  public void addRemoteServerRuninLinux(String Linuxserverip){
	  this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
			// lable
			selenium.setSpeed(MIN_SPEED);
			this.typeString(other.getString("inputname.id.server.add.label"), "test_RemoteLinux");
			// host
			this.typeString(other.getString("inputname.id.server.add.host"), Linuxserverip);
			// save
			selenium.click("idFormSaveButton");
//			selenium.setSpeed(MID_SPEED);
			// refresh
			selenium.refresh();
			this.waitForElementPresent("//div[text()='test_RemoteLinux']", WAIT_TIME);
			
			if ((selenium.isElementPresent("//div[text()='test_RemoteLinux']"))&&(selenium
					.isElementPresent("//span[@class='serv-value' and (text()='UP')]"))) {

			} else {
				Assert.fail("Remote Linux Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
  }
  public void addRemoteServerRuninWindows(String windowsserverip){
	  this.waitForElementPresent("idSubModuleAddButton", WAIT_TIME);
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
			// lable
			selenium.setSpeed(MIN_SPEED);
			this.typeString(other.getString("inputname.id.server.add.label"), "test_RemoteWindows");
			// host
			this.typeString(other.getString("inputname.id.server.add.host"), windowsserverip);
			// save
			selenium.setSpeed(MID_SPEED);
			selenium.click("idFormSaveButton");
			// refresh
			selenium.refresh();
			this.waitForElementPresent("//div[text()='test_RemoteWindows']", WAIT_TIME);
			if ((selenium.isElementPresent("//div[text()='test_RemoteWindows']"))&&(selenium
					.isElementPresent("//span[@class='serv-value' and (text()='UP')]"))) {

			} else {
				Assert.fail("Remote Windows Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
  }
  public void deleteServer(String serverlabel) throws InterruptedException{
	  selenium.setSpeed(MID_SPEED);
	  if(selenium.isElementPresent("//div[text()='"+serverlabel+"']")){
			selenium.refresh();
			this.waitForElementPresent("//div[text()='"+serverlabel+"']", WAIT_TIME);
			selenium.chooseOkOnNextConfirmation();
			Thread.sleep(2000);
			selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"+serverlabel+"')]");
			selenium.click("idSubModuleDeleteButton");
			selenium.getConfirmation();
			Assert.assertFalse(
					selenium.isElementPresent("//div[text()='" + serverlabel
							+ "']"), "Server delete failed!");
			
		}
	  selenium.setSpeed(MIN_SPEED);
	  
  }
}
