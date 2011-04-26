package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddProjectWithExistlable extends Login {

	@Test(dependsOnGroups = { "Add" })
	// @Test(groups = { "AddBranch" }, dependsOnGroups = { "Add" })
	@Parameters({ "SVNurl", "SVNuserName", "SVNuserPassword",
			"AddcommonProjectname", "Prolanguage", "ProjectType" })
	public void testAddpro(String url, String user, String password,
			String proname, String language, String type) throws Exception {

		testAddexistproject(proname, language, type, url, user, password);

	}

	public void testAddexistproject(String namecommon, String language,
			String type, String svnurl, String user, String password)
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
		// selenium.focus("idFormSaveButton");
		// selenium.keyDownNative(""+KeyEvent.VK_ENTER);
		// selenium.keyUpNative(""+KeyEvent.VK_ENTER);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MAX_SPEED);
		Assert.assertTrue(selenium
				.isTextPresent("Save failed: A project with this name already exists"));
		selenium.setSpeed(MIN_SPEED);
	}
}
