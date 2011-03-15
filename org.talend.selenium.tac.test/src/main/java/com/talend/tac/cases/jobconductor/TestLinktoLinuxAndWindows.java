package com.talend.tac.cases.jobconductor;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestLinktoLinuxAndWindows extends TestJobconductorClicked {
  @Test(groups = { "AddServer" })
  @Parameters({ "RemoteLinuxServerIp", "RemoteWindowServerIp"})
  public void f(String linuxIp,String windowsIp) {
		this.clickJobconductor();
		addRemoteServerRuninLinux(linuxIp);
		addRemoteServerRuninWindows(windowsIp);
		
		
  }
  public void addRemoteServerRuninLinux(String Linuxserverip){
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
			// lable
			selenium.setSpeed("0");
			selenium.click(other.getString("inputname.id.server.add.label"));
			selenium.type(other.getString("inputname.id.server.add.label"), "RemoteLinux");
			selenium.fireEvent(other.getString("inputname.id.server.add.label"), "blur");
			// host
			selenium.click(other.getString("inputname.id.server.add.host"));
			selenium.type(other.getString("inputname.id.server.add.host"), Linuxserverip);
			selenium.fireEvent(other.getString("inputname.id.server.add.host"), "blur");
			
			// save
			selenium.click("idFormSaveButton");
			selenium.setSpeed("3000");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			selenium.setSpeed("2000");

			if (selenium.isElementPresent("//div[text()='RemoteLinux']")) {

			} else {
				Assert.fail("Remote Linux Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
  }
  public void addRemoteServerRuninWindows(String windowsserverip){
		if (selenium.isElementPresent("idSubModuleAddButton")) {
			selenium.click("idSubModuleAddButton");
			// lable
			selenium.setSpeed("0");
			selenium.click(other.getString("inputname.id.server.add.label"));
			selenium.type(other.getString("inputname.id.server.add.label"), "RemoteWindows");
			selenium.fireEvent(other.getString("inputname.id.server.add.label"), "blur");
			// host
			selenium.click(other.getString("inputname.id.server.add.host"));
			selenium.type(other.getString("inputname.id.server.add.host"), windowsserverip);
			selenium.fireEvent(other.getString("inputname.id.server.add.host"), "blur");
			
			// save
			selenium.click("idFormSaveButton");
			selenium.setSpeed("3000");
			// refresh
			selenium.click("idSubModuleRefreshButton");
			selenium.setSpeed("2000");

			if (selenium.isElementPresent("//div[text()='RemoteWindows']")) {

			} else {
				Assert.fail("Remote Windows Server added failed !");
			}
		} else {
			Assert.fail("add button can not be seen !");

		}
  }
}
