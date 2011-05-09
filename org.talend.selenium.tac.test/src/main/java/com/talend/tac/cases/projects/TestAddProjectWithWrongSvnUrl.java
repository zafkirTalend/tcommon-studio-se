package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddProjectWithWrongSvnUrl extends Login {

	@Test(groups = { "AddElse" })
	@Parameters({"AddcommontestProjectWrongSvn", "Prolanguage", "ProjectType"})
	public void testAddpro(String proname, String language, String type) throws Exception {

		testAddexistproject(proname, language, type);

	}

	public void testAddexistproject(String namecommon, String language,
			String type)
			throws Exception {
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.setSpeed(MID_SPEED);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idLabelInput", namecommon);
		// add the type select option selenium.setSpeed("2000");
		selenium.setSpeed(MID_SPEED);
		if (selenium.isVisible("idProjectTypeComboBox")) {
			selenium.click("idProjectTypeComboBox");
			selenium.mouseDown("//div[text()='" + type + "']");
			selenium.fireEvent("idProjectTypeComboBox", "blur");
		}
		selenium.setSpeed(MIN_SPEED);
		if (!"Java".equals(language) && !"".equals(language)) {
			selenium.click("idLanguageInput");
			selenium.mouseDownAt("//div[@role='listitem'][2]", ""
					+ KeyEvent.VK_ENTER);
		}
		selenium.click("idAdvanceInput");
		this.typeString("idUrlInput", "svnurl");// svn
		this.typeString("idLoginInput", "svnuser");// svn account
		this.typeString("idPasswordInput", "svnpassword");// svn password
		selenium.click("idSvnCommitInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		selenium.click("idSvnLockInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		selenium.click("idSvnUserLogInput");
		this.typeString("idDescriptionInput", "adf");
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ namecommon + "')]"), "TestAddProjectWithWrongSvnUrl failed !");
		selenium.setSpeed(MIN_SPEED);
	}
}
