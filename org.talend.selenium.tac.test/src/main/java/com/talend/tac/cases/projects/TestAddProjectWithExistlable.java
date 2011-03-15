package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddProjectWithExistlable extends Login{
  @Test(groups = { "AddBranch" }, dependsOnGroups = { "Add" })
  @Parameters({ "SVNurl", "SVNuserName", "SVNuserPassword",
		"AddcommonProjectname","ProjectType"})
public void testAddpro(String url, String user, String password,
		String proname,String type) throws Exception {

	  testAddexistproject(proname,type, url, user, password);
	selenium.click("idSubModuleRefreshButton");
//	selenium.setSpeed("3000");
	
}
  public void testAddexistproject(String namecommon,String type, String svnurl, String user,
			String password) throws Exception {

		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", namecommon);
		selenium.fireEvent("idLabelInput", "blur");
		selenium.click("idLanguageInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.fireEvent("idLanguageInput", "blur");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		//*********************add the type select option
//		selenium.setSpeed("2000");
//		if(selenium.isElementPresent("idProjectTypeComboBox")){
//		selenium.click("idProjectTypeComboBox");
//		selenium.mouseDown("//div[text()='"+type+"']");
//		selenium.setSpeed("0");
//		}
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
		selenium.click("idFormSaveButton");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				selenium.isTextPresent("Save failed: A project with this name already exists"));
		selenium.click("//button[text()='Ok']");
	
	}

	

}
