package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
import com.thoughtworks.selenium.Selenium;

public class TestAddJavaprojectwithdefault extends Login{
	TestDeletepro delete = new TestDeletepro();
  @Test
  @Parameters({"ProjectType","Prolanguage" })
  public void addPerlproject(String protype,String language) {
	  this.waitForElementPresent("!!!menu.project.element!!!", 30);
	  String namecommon = "JavaDefaultPro";
	 
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
	  selenium.setSpeed("7000");
	  selenium.focus("idFormSaveButton");
	  selenium.keyDownNative(""+KeyEvent.VK_ENTER);
	  selenium.keyUpNative(""+KeyEvent.VK_ENTER);
	
	  Assert.assertTrue(
	    selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
	      + namecommon + "')]")&&(selenium.isElementPresent("//img[@title='java']")), "common project added failed");
	 
	  deleteProject(selenium,namecommon);
	  selenium.setSpeed("0");
	 }
 public void deleteProject(Selenium s,String proname){
	  
	  delete.okDelete(s,proname);
	  
  }
}
