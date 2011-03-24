package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;

public class TestAddJavaprojectwithdefault extends Login{
	TestDeletepro delete = new TestDeletepro();
  @Test
  @Parameters({"ProjectType","Prolanguage" })
  public void testAddPerlproject(String protype,String language) {
	  this.waitForElementPresent("!!!menu.project.element!!!", 30);
	  String namecommon = "JavaDefaultPro";
	 
	  selenium.setSpeed(MID_SPEED);
	  selenium.click("!!!menu.project.element!!!");
	  selenium.click("idSubModuleAddButton");
	  selenium.click("idLabelInput");
	  selenium.setSpeed(MIN_SPEED);
	  selenium.type("idLabelInput", namecommon);
	  selenium.fireEvent("idLabelInput", "blur");
	  if (!"Java".equals(language) && !"".equals(language)) {
		  selenium.click("idLanguageInput");
		  selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
	  }
	  selenium.setSpeed(MAX_SPEED);
	  selenium.type("idDescriptionInput", "adf");
//	  selenium.focus("idFormSaveButton");
//	  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
//	  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
	  selenium.click("idFormSaveButton");
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Assert.assertTrue(
	    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
	      + namecommon + "')]")&&(selenium.isElementPresent("//img[@title='java']")), "javadefault project added failed");
	 
	  delete.okDelete(selenium,namecommon);
	  selenium.setSpeed(MIN_SPEED);
	 }
}
