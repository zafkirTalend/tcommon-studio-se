package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddProjectWithExistlable extends Login{
  @Test(groups = { "AddBranch" }, dependsOnGroups = { "Add" })
  @Parameters({ "SVNurl", "SVNuserName", "SVNuserPassword",
		"AddcommonProjectname","Prolanguage","ProjectType"})
public void testAddpro(String url, String user, String password,
		String proname,String language,String type) throws Exception {

	  testAddexistproject(proname,language,type, url, user, password);
	selenium.click("idSubModuleRefreshButton");
//	selenium.setSpeed("3000");
	
}
  public void testAddexistproject(String namecommon,String language,String type, String svnurl, String user,
			String password) throws Exception {
	  this.waitForElementPresent("!!!menu.project.element!!!", 30);
	  selenium.setSpeed(MID_SPEED);
	  selenium.click("!!!menu.project.element!!!");
	  selenium.click("idSubModuleAddButton");
	  selenium.click("idLabelInput");
	  selenium.setSpeed(MIN_SPEED);
	  selenium.type("idLabelInput", namecommon);
	  selenium.fireEvent("idLabelInput", "blur");
//	  if ("Java".equals(language) || "".equals(language)) {
//		} else {
//			selenium.click("idLanguageInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
//		}
	  if (!"Java".equals(language) && !"".equals(language)) {
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
	  selenium.focus("idFormSaveButton");
	  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
	  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		Assert.assertTrue(
				selenium.isTextPresent("Save failed: A project with this name already exists"));
		selenium.click("//button[text()='Ok']");
	selenium.setSpeed(MIN_SPEED);
	}
}
