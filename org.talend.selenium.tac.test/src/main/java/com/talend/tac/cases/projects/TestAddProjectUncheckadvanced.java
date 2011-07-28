package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddProjectUncheckadvanced extends Login {
	TestDeletepro delete = new TestDeletepro();

	@Test(groups = { "else" })
	@Parameters({"uncheckAdvancedProject" ,"ProjectType", "Prolanguage" })
	public void testAddProjectWithoutClickAdvanced(String namecommon,String type, String language)
			throws InterruptedException {
		selenium.setSpeed(MIN_SPEED);
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
//		String namecommon = "DefaultProUncheck";
//		selenium.setSpeed(MID_SPEED);
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.click("!!!menu.project.element!!!");
		this.waitForElementPresent("idSubModuleAddButton", Base.WAIT_TIME);
		selenium.click("idSubModuleAddButton");
//		Thread.sleep(5000);
//		selenium.setSpeed(MID_SPEED);
		// add the type select option selenium.setSpeed("2000");
		if (selenium.isVisible("idProjectTypeComboBox")) {
			this.selectDropDownList("idProjectTypeComboBox", type);
//			selenium.click("idProjectTypeComboBox");
//			selenium.mouseDownAt(("//div[text()='" + type + "']"),""+KeyEvent.VK_ENTER);
//			selenium.fireEvent("idProjectTypeComboBox", "blur");
			
		}
//		selenium.setSpeed(MIN_SPEED);
		// type project label
		this.typeString("idLabelInput", namecommon);
	
		// select project language
		if (!"Java".equals(language) && !"".equals(language)) {
			selenium.click("idLanguageInput");
			selenium.mouseDownAt("//div[@role='listitem'][2]", ""
					+ KeyEvent.VK_ENTER);
		}
		//check the acvanced check box and type parameters ****************
		//check advanced checkbox
		selenium.click("idAdvanceInput");
		//type project svn url
		this.typeString("idUrlInput",  "wrongurl");
		//type svn username
		this.typeString("idLoginInput", "user");
		//type svn password
		this.typeString("idPasswordInput","password");
		//select svn commit mode 
		selenium.click("idSvnCommitInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		//select svn lock mode
		selenium.click("idSvnLockInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		//check svn user log checkbox
		selenium.click("idSvnUserLogInput");
		
		//**************************************************************
		//then cancel advanced check box
		selenium.click("idAdvanceInput");
		//**************************************************************
		
		this.typeString("idDescriptionInput", "javadefault");
		// selenium.focus("idFormSaveButton");
		// selenium.keyDownNative(""+KeyEvent.VK_ENTER);
		// selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		System.out.println("before click "+selenium.getValue("idProjectTypeComboBox"));
//		selenium.setSpeed(MAX_SPEED);
		selenium.click("idFormSaveButton");
//		Thread.sleep(5000);
		this.waitForElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ namecommon + "')]", Base.WAIT_TIME);
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namecommon + "')]")
						&& (selenium.isElementPresent("//img[@title='java']")),
				"javadefault project added failed");

//		delete.okDelete(selenium, namecommon);
		selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ namecommon + "')]");
		selenium.chooseOkOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		selenium.getConfirmation();
		Thread.sleep(2000);
		Assert.assertFalse(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namecommon + "')]"), "delete project " + namecommon
						+ " failed!");
		selenium.setSpeed(MIN_SPEED);
	}
}
