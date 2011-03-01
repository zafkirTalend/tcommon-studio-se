package com.talend.tac.cases.project;

import java.awt.event.KeyEvent;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestCheckConnection extends Login{

	@Test(groups = { "Second" },dependsOnGroups={"Add"})
	@Parameters({"SVNProjecturl", "WrongSVNProjecturl","SVNuserName", "SVNuserPassword"})
	public void testCheckconnection(String rightUrl,String wrongUrl,String user, String password) throws Exception {
		  if(selenium == null){
			  System.out.println("selenium Ϊ��!");
		  }
//		selenium.setSpeed("5000");
		this.waitForElementPresent("!!!menu.project.element!!!",30);
		String proname = "namepro";
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", proname);
		selenium.fireEvent("idLabelInput", "blur");
		selenium.click("idLanguageInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.fireEvent("idLanguageInput", "blur");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", wrongUrl);// svn project url
		selenium.fireEvent("idUrlInput", "blur");
		selenium.type("idLoginInput", user);// svn account
		selenium.fireEvent("idLoginInput", "blur");
		selenium.type("idPasswordInput", password);// svn password
		selenium.fireEvent("idPasswordInput", "blur");
		selenium.click("idSvnCommitInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnLockInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnUserLogInput");
		selenium.click("idCheckUrlButton");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(selenium.isElementPresent("//div[@class='ext-mb-icon  ext-mb-warning']")){//check connection fail,click ok button
			assert true;
//			selenium.click("//button[@class='x-btn-text ' and (text()='" + rb.getString("executionPlan.errorStatus.ok") + "')]");
			selenium.click("//button[@class='x-btn-text ' and (text()='"+other.getString("add.project.checkconnection.fail")+"')]");
		}
		else{
			assert false :"The check connection with wrong url failed !";
		    selenium.click("//button[text()='"+other.getString("add.project.checkconnection.ok")+"']");
		}
		selenium.type("idUrlInput", rightUrl);// svn project url
		selenium.fireEvent("idUrlInput", "blur");
		selenium.click("idCheckUrlButton");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(selenium.isElementPresent("//div[@class='ext-mb-icon  ext-mb-info']")){
			assert true;
			selenium.click("//button[text()='"+other.getString("add.project.checkconnection.ok")+"']");
			
		}
		else{
			assert false:"The check connection with right url failed !";;
			selenium.click("//button[@class='x-btn-text ' and (text()='"+other.getString("add.project.checkconnection.fail")+"')]");
		}
	
	}
}
