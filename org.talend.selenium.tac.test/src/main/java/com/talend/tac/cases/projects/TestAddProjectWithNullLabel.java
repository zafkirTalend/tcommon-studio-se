package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddProjectWithNullLabel extends Login {

	@Test(groups = { "Add" },dependsOnGroups = { "cleanbefore" })
	@Parameters({ "SVNurl", "SVNuserName", "SVNuserPassword",
			"AddcommontestProjectname", "Prolanguage", "ProjectType" })
	public void testAddpro(String url, String user, String password,
			String proname, String language, String type) throws Exception {

		testAddprojectwithnulllabel(proname, language, type, url, user, password);

	}

	public void testAddprojectwithnulllabel(String namecommon, String language,
			String type, String svnurl, String user, String password)
			throws Exception {
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.setSpeed(MID_SPEED);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idLabelInput", "");
		// add the type select option selenium.setSpeed("2000");
		selenium.setSpeed(MID_SPEED);
		if (selenium.isVisible("idProjectTypeComboBox")) {
			selenium.click("idProjectTypeComboBox");
			selenium.mouseDown("//div[text()='" + type + "']");
			selenium.fireEvent("idProjectTypeComboBox", "blur");
		}
		selenium.setSpeed(MIN_SPEED);
		// if ("Java".equals(language) || "".equals(language)) {
		// } else {
		// selenium.click("idLanguageInput");
		// selenium.mouseDownAt("//div[@role='listitem'][2]",""+KeyEvent.VK_ENTER);
		// }
		if (!"Java".equals(language) && !"".equals(language)) {
			selenium.click("idLanguageInput");
			selenium.mouseDownAt("//div[@role='listitem'][2]", ""
					+ KeyEvent.VK_ENTER);
		}
		selenium.click("idAdvanceInput");
		this.typeString("idUrlInput", svnurl + "/" + namecommon + "/");// svn
		this.typeString("idLoginInput", user);// svn account
		this.typeString("idPasswordInput", password);// svn password
		selenium.click("idSvnCommitInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		selenium.click("idSvnLockInput");
		selenium.mouseDownAt("//div[@role='listitem'][2]", ""
				+ KeyEvent.VK_ENTER);
		selenium.click("idSvnUserLogInput");
		this.typeString("idDescriptionInput", "adf");
		Assert.assertTrue(selenium.isElementPresent("//input[@id='idLabelInput']//ancestor::div[@class='x-form-item ']//img"), "TestAddProjectWithNullLabel failed !");
		selenium.setSpeed(MIN_SPEED);
	}
}
