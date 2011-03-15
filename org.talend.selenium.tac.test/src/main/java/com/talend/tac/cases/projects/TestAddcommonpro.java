package com.talend.tac.cases.projects;

import java.awt.Event;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

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
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
//		selenium.setSpeed("3000");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", namecommon);
		selenium.fireEvent("idLabelInput", "blur");
		if ("Java".equals(language) || "".equals(language)) {

		} else {
			selenium.click("idLanguageInput");
			selenium.mouseDown("//div[@role='listitem'][2]");
			selenium.fireEvent("idLanguageInput", "blur");
		}
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		//*********************add the type select option
//		selenium.setSpeed("2000");
//		if(selenium.isVisible("idProjectTypeComboBox")){
//		selenium.click("idProjectTypeComboBox");
//		selenium.mouseDown("//div[text()='"+type+"']");
//		selenium.setSpeed("0");
//		}
//		
		//*********************
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", svnurl + "/" + namecommon + "/");// svn
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
		selenium.click("idDescriptionInput");
		selenium.fireEvent("idDescriptionInput", "blur");
		selenium.click("idFormSaveButton");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namecommon + "')]"), "common project added failed");
	
	}

	public void testaddreference(String namereference,String language,String type, String svnurl,
			String user, String password) throws Exception {

		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", namereference);
		selenium.fireEvent("idLabelInput", "blur");
		if ("Java".equals(language) || "".equals(language)) {

		} else {
			selenium.click("idLanguageInput");
			selenium.mouseDown("//div[@role='listitem'][2]");
			selenium.fireEvent("idLanguageInput", "blur");
		}
		selenium.click("idReferenceInput");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		//*********************add the type select option
//		selenium.setSpeed("2000");
//		if(selenium.isVisible("idProjectTypeComboBox")){
//		selenium.click("idProjectTypeComboBox");
//		selenium.mouseDown("//div[text()='"+type+"']");
//		selenium.setSpeed("0");
//		}
//	
		//*********************
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
		selenium.click("idFormSaveButton");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namereference + "')]"), "reference project added failed");

	}

	@AfterMethod
	public void tearDown() throws Exception {
		// selenium.stop();

	}
}
