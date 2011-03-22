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
        selenium.setSpeed("5000");
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
        selenium.setSpeed("0");
		if(existCommon == 0){
		testaddcommon(proname,language,type, url, user, password);
		selenium.setSpeed("3000");
		selenium.click("idSubModuleRefreshButton");
		selenium.setSpeed("0");
		
		}
		
		if(existReference == 0){
			testaddreference(name2,language,type, url, user, password);
		}
	}

	public void testaddcommon(String namecommon,String language,String type, String svnurl, String user,
			String password) throws Exception {
		  this.waitForElementPresent("!!!menu.project.element!!!", 30);
		  selenium.setSpeed("3000");
		  selenium.click("!!!menu.project.element!!!");
		  selenium.click("idSubModuleAddButton");
		  selenium.click("idLabelInput");
		  selenium.setSpeed("0");
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
		  selenium.click("idDescriptionInput");
		  selenium.fireEvent("idDescriptionInput", "blur");
		  selenium.setSpeed("7000");
		  selenium.focus("idFormSaveButton");
		  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
		  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		
		  Assert.assertTrue(
		    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
		      + namecommon + "')]"), "common project added failed");
		  selenium.setSpeed("0");
		 }
	

	public void testaddreference(String namereference,String language,String type, String svnurl,
			String user, String password) throws Exception {

		  this.waitForElementPresent("!!!menu.project.element!!!", 30);
			 
			 
		  selenium.setSpeed("3000");
		  selenium.click("!!!menu.project.element!!!");
		  selenium.click("idSubModuleAddButton");
		  selenium.click("idLabelInput");
		  selenium.setSpeed("0");
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
		  selenium.click("idDescriptionInput");
		  selenium.fireEvent("idDescriptionInput", "blur");
		  selenium.setSpeed("7000");
		  selenium.focus("idFormSaveButton");
		  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
		  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
	
		  Assert.assertTrue(
		    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
		      + namereference + "')]"), "reference project added failed");
		  selenium.setSpeed("0");

	}
	public void testaddreferencepro(Selenium s,String namereference,String language,String type, String svnurl,
			String user, String password) throws Exception {
		s.setSpeed("5000");
//		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		s.click("!!!menu.project.element!!!");
		s.click("idSubModuleAddButton");
		
		s.click("idLabelInput");
		s.setSpeed("0");
		s.type("idLabelInput", namereference);
		s.fireEvent("idLabelInput", "blur");
		if ("Java".equals(language) || "".equals(language)) {

		} else {
			s.click("idLanguageInput");
			s.mouseDownAt(("//div[@role='listitem'][2]"),""+Event.ENTER);
//			s.fireEvent("idLanguageInput", "blur");
		}
		s.click("idReferenceInput");
		s.type("idDescriptionInput", "adf");
		s.fireEvent("idDescriptionInput", "blur");
		//*********************add the type select option
//		selenium.setSpeed("2000");
//		if(selenium.isVisible("idProjectTypeComboBox")){
//		selenium.click("idProjectTypeComboBox");
//		selenium.mouseDown("//div[text()='"+type+"']");
//		selenium.setSpeed("0");
//		}
//	
		//*********************
		s.click("idAdvanceInput");
		s.type("idUrlInput", svnurl + "/" + namereference + "/");// svn
																		// project
																		// url
		s.fireEvent("idUrlInput", "blur");
		s.type("idLoginInput", user);// svn account
		s.fireEvent("idLoginInput", "blur");
		s.type("idPasswordInput", password);// svn password
		s.fireEvent("idPasswordInput", "blur");
		s.click("idSvnCommitInput");
		s.keyPressNative("" + KeyEvent.VK_ENTER);
		s.keyUpNative("" + KeyEvent.VK_ENTER);
		s.click("idSvnLockInput");
		s.keyPressNative("" + KeyEvent.VK_ENTER);
		s.keyUpNative("" + KeyEvent.VK_ENTER);
		s.click("idSvnUserLogInput");
		 s.focus("idFormSaveButton");
		  s.keyDownNative(""+KeyEvent.VK_ENTER);
		 s.keyUpNative(""+KeyEvent.VK_ENTER);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				s.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namereference + "')]"), "reference project added failed");
		selenium.setSpeed("0");
	}
	public boolean addTestproWithdefaultsettings(Selenium s, String proname) {
		boolean ok = false;
		s.setSpeed("8000");
//		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		s.click("!!!menu.project.element!!!");
//		this.waitForElementPresent("idSubModuleAddButton", 30);
		
	
		s.click("idSubModuleAddButton");
//		s.setSpeed("3000");
		s.click("idLabelInput");
		s.type("idLabelInput", proname);
		s.fireEvent("idLabelInput", "blur");
		
		 s.focus("idFormSaveButton");
		  s.keyDownNative(""+KeyEvent.VK_ENTER);
		 s.keyUpNative(""+KeyEvent.VK_ENTER);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(s.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ proname + "')]")){
			System.out.println("Testpro added success!");
			ok = true;
		}
		s.setSpeed("0");
		return ok;
	}

	@AfterMethod
	public void tearDown() throws Exception {
		// selenium.stop();
	}
}
