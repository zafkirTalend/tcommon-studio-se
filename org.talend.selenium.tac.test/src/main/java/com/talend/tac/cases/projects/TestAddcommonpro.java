package com.talend.tac.cases.projects;

import java.awt.Event;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestAddcommonpro extends Login {

	@Test(groups = { "Add" })
	@Parameters({ "SVNurl","ProjectType", "SVNuserName", "SVNuserPassword",
			"AddcommonProjectname", "AddreferenceProjectname","Prolanguage"  })
	public void testAddpro(String url,String type, String user, String password,
			String proname, String name2,String language) throws Exception {
		selenium.setSpeed(MAX_SPEED);
        int existCommon = 0;
        int existReference = 0;
        if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ proname + "')]")){
        	existCommon = 1;
        }
        if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ name2 + "')]")){
        	existReference = 1;
        	
        }
        selenium.setSpeed(MIN_SPEED);
		if(existCommon == 0){
			testaddcommon(proname,language,type, url, user, password);
			selenium.setSpeed(MID_SPEED);
			selenium.click("idSubModuleRefreshButton");
			selenium.setSpeed(MIN_SPEED);
		
		}
		
		if(existReference == 0){
			testaddreference(name2,language,type, url, user, password);
		}
	}

	public void testaddcommon(String namecommon,String language,String type, String svnurl, String user,
			String password) throws Exception {
		  this.waitForElementPresent("!!!menu.project.element!!!", 30);
		  selenium.setSpeed(MID_SPEED);
		  selenium.click("!!!menu.project.element!!!");
		  selenium.click("idSubModuleAddButton");
		  selenium.click("idLabelInput");
		  selenium.setSpeed(MIN_SPEED);
		  selenium.type("idLabelInput", namecommon);
		  selenium.fireEvent("idLabelInput", "blur");
		  if ("Java".equals(language) || "".equals(language)) {
			} else {
				selenium.click("idLanguageInput");
				selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
			}
		  selenium.type("idDescriptionInput", "adf");
		  selenium.fireEvent("idDescriptionInput", "blur");
		  selenium.click("idAdvanceInput");
		  selenium.type("idUrlInput", svnurl + "/" + namecommon + "/");// svn
		  selenium.fireEvent("idUrlInput", "blur");
		  selenium.type("idLoginInput", user);// svn account
		  selenium.fireEvent("idLoginInput", "blur");
		  selenium.type("idPasswordInput", password);// svn password
		  selenium.fireEvent("idPasswordInput", "blur");
		  selenium.click("idSvnCommitInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
		  selenium.click("idSvnLockInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
		  selenium.click("idSvnUserLogInput");
		  selenium.setSpeed(MAX_SPEED);
		  selenium.click("idDescriptionInput");
//		  selenium.focus("idFormSaveButton");
//		  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
//		  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		  selenium.click("idFormSaveButton");
		  Thread.sleep(5000);
		  Assert.assertTrue(
		    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
		      + namecommon + "')]"), "common project added failed");
		  selenium.setSpeed(MIN_SPEED);
		 }
	

	public void testaddreference(String namereference,String language,String type, String svnurl,
			String user, String password) throws Exception {

		  this.waitForElementPresent("!!!menu.project.element!!!", 30);
			 
			 
		  selenium.setSpeed(MID_SPEED);
		  selenium.click("!!!menu.project.element!!!");
		  selenium.click("idSubModuleAddButton");
		  selenium.click("idLabelInput");
		  selenium.setSpeed(MIN_SPEED);
		  selenium.type("idLabelInput", namereference);
		  selenium.fireEvent("idLabelInput", "blur");
		 
		  if ("Java".equals(language) || "".equals(language)) {

			} else {
				
				selenium.click("idLanguageInput");
				selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
			}
		  selenium.click("idReferenceInput");
		  selenium.type("idDescriptionInput", "adf");
		  selenium.fireEvent("idDescriptionInput", "blur");
		
		  selenium.click("idAdvanceInput");
		  selenium.type("idUrlInput", svnurl + "/" + namereference + "/");// svn
		  selenium.fireEvent("idUrlInput", "blur");
		  selenium.type("idLoginInput", user);// svn account
		  selenium.fireEvent("idLoginInput", "blur");
		  selenium.type("idPasswordInput", password);// svn password
		  selenium.fireEvent("idPasswordInput", "blur");
		  selenium.click("idSvnCommitInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
		  selenium.click("idSvnLockInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
		  selenium.click("idSvnUserLogInput");
		  selenium.setSpeed(MAX_SPEED);
		  selenium.click("idDescriptionInput");
//		  selenium.focus("idFormSaveButton");
//		  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
//		  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		  selenium.click("idFormSaveButton");
		  Thread.sleep(5000);
		  Assert.assertTrue(
		    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
		      + namereference + "')]"), "reference project added failed");
		  selenium.setSpeed(MIN_SPEED);

	}
	public void testaddreferencepro(Selenium selenium,String namereference,String language,String type, String svnurl,
			String user, String password) throws Exception {
		selenium.setSpeed(MAX_SPEED);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		
		selenium.click("idLabelInput");
		selenium.setSpeed(MIN_SPEED);
		selenium.type("idLabelInput", namereference);
		selenium.fireEvent("idLabelInput", "blur");
		if ("Java".equals(language) || "".equals(language)) {

		} else {
			selenium.click("idLanguageInput");
			selenium.mouseDownAt(("//div[@role='listitem'][2]"),""+Event.ENTER);
//			s.fireEvent("idLanguageInput", "blur");
		}
		selenium.click("idReferenceInput");
		selenium.type("idDescriptionInput","adf");
		selenium.fireEvent("idDescriptionInput", "blur");

/*		//add the type select option
		selenium.setSpeed("2000");
		if(selenium.isVisible("idProjectTypeComboBox")){
		selenium.click("idProjectTypeComboBox");
		selenium.mouseDown("//div[text()='"+type+"']");
		selenium.setSpeed("0");
		}
*/
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", svnurl + "/" + namereference + "/");// svn
																		// project
																		// url
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
		selenium.setSpeed(MAX_SPEED);
		selenium.click("idDescriptionInput");
//		selenium.focus("idFormSaveButton");
//		selenium.keyDownNative(""+KeyEvent.VK_ENTER);
//		selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		 selenium.click("idFormSaveButton");
		Thread.sleep(5000);
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namereference + "')]"), "reference project added failed");
		selenium.setSpeed(MIN_SPEED);
	}
	
	public boolean addTestproWithdefaultsettings(Selenium selenium, String proname) {
		boolean ok = false;
		selenium.setSpeed(MAX_SPEED);
		selenium.click("!!!menu.project.element!!!");
		
	
		selenium.click("idSubModuleAddButton");
//		s.setSpeed("3000");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", proname);
		selenium.fireEvent("idLabelInput", "blur");
		
		selenium.focus("idFormSaveButton");
		selenium.keyDownNative(""+KeyEvent.VK_ENTER);
		selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ proname + "')]")){
			System.out.println("Testpro added success!");
			ok = true;
		}
		selenium.setSpeed(MIN_SPEED);
		return ok;
	}

	@AfterMethod
	public void tearDown() throws Exception {
		// selenium.stop();

	}
}
