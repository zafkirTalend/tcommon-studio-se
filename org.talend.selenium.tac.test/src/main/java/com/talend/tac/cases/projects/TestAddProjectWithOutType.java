package com.talend.tac.cases.projects;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;
import com.talend.tac.cases.Login;

public class TestAddProjectWithOutType extends Login {

	@Test(groups = { "AddElse" })
	@Parameters({"SVNProjecturl","SVNuserName", "SVNuserPassword","ProjectWithOutType", "Prolanguage", "ProjectType"})
	public void testAddpro(String url,String username,String password,String proname, String language, String type) throws Exception {

		testAddProjectWithOutType(url,username,password,proname, language, type);

	}

	public void testAddProjectWithOutType(String url,String username,String password,String proname, String language, String type)
			throws Exception {
		this.waitForElementPresent("!!!menu.project.element!!!", Base.WAIT_TIME);
		selenium.setSpeed(MID_SPEED);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		Thread.sleep(5000);
		selenium.setSpeed(MIN_SPEED);
		this.typeString("idLabelInput", proname);
		selenium.setSpeed(MIN_SPEED);
		if (!"Java".equals(language) && !"".equals(language)) {
			selenium.click("idLanguageInput");
			selenium.mouseDownAt("//div[@role='listitem'][2]", ""
					+ KeyEvent.VK_ENTER);
		}
		selenium.click("idAdvanceInput");
		this.typeString("idUrlInput", url);// svn
		this.typeString("idLoginInput", username);// svn account
		this.typeString("idPasswordInput", password);// svn password
		this.typeString("idDescriptionInput", "projectnotype");
//		System.out.println(selenium.getValue("idProjectTypeComboBox"));
		Assert.assertTrue(selenium.getValue("idProjectTypeComboBox").equals("Select a type..."));
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
				+ proname + "')]"), "TestAddProjectWithWrongSvnUrl failed !");
		selenium.setSpeed(MIN_SPEED);
	}
}
