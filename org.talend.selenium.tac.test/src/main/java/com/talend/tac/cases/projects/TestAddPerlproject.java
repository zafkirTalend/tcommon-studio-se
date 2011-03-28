package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestAddPerlproject extends Login{
	TestDeletepro delete = new TestDeletepro();
  @Test
  @Parameters({ "SVNPerlProjecturl","ProjectType", "SVNuserName", "SVNuserPassword","Prolanguage" })
  public void testAddPerlproject(String perlUrl,String type,String username,String password,String language) throws InterruptedException {
	  this.waitForElementPresent("!!!menu.project.element!!!", 30);
	  String namecommon = "TestPro";
	  String svnUrl =perlUrl;
	  selenium.setSpeed(MID_SPEED);
	  selenium.click("!!!menu.project.element!!!");
	  selenium.click("idSubModuleAddButton");
	  Thread.sleep(5000);
	  selenium.setSpeed(MIN_SPEED);
	  this.typeString("idLabelInput", namecommon);
	// add the type select option selenium.setSpeed("2000");
		if (selenium.isVisible("idProjectTypeComboBox")) {
			selenium.click("idProjectTypeComboBox");
			selenium.mouseDown("//div[text()='" + type + "']");
			selenium.fireEvent("idProjectTypeComboBox", "blur");
		}
//	  if ("Java".equals(language) || "".equals(language)) {
//
//		} else {
//			selenium.click("idLanguageInput");
//			selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
//		}
	  if (!"Java".equals(language) && !"".equals(language)) {
		  selenium.click("idLanguageInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
	  }
	  selenium.click("idAdvanceInput");
	  this.typeString("idUrlInput", svnUrl);
	  this.typeString("idLoginInput", username);
	  this.typeString("idPasswordInput", password);
	  selenium.click("idSvnCommitInput");
	  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
	  selenium.click("idSvnLockInput");
	  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
	  selenium.click("idSvnUserLogInput");
	  selenium.setSpeed(MAX_SPEED);
	  this.typeString("idDescriptionInput", "perlproject");
//	  selenium.focus("idFormSaveButton");
//	  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
//	  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
	  selenium.click("idFormSaveButton");
	  Thread.sleep(5000);
	  Assert.assertTrue(
	    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
	      + namecommon + "')]")&&(selenium.isElementPresent("//img[@title='perl']")), "Perl project added failed");
	 
	  //delete the added perl project
	  delete.okDelete(selenium,namecommon);
	 }
}
