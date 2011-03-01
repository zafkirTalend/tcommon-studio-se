package com.talend.tac.cases.project;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

public class TestAddcommonpro extends Login {

	@Test(groups = { "Add" })
	@Parameters({ "SVNurl", "SVNuserName", "SVNuserPassword",
			"AddcommonProjectname", "AddreferenceProjectname" })
	public void testAddpro(String url, String user, String password,
			String proname, String name2) throws Exception {

		testaddcommon(proname, url, user, password);
		selenium.click("idSubModuleRefreshButton");
//		selenium.setSpeed("3000");
		testaddreference(name2, url, user, password);
	}

	public void testaddcommon(String namecommon, String svnurl, String user,
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namecommon + "')]"), "common project added failed");
	
	}

	public void testaddreference(String namereference, String svnurl,
			String user, String password) throws Exception {

		this.waitForElementPresent("!!!menu.project.element!!!", 30);
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", namereference);
		selenium.fireEvent("idLabelInput", "blur");
		selenium.click("idLanguageInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.fireEvent("idLanguageInput", "blur");
		selenium.click("idReferenceInput");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", svnurl + "/" + namereference + "/");// svn
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
				selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-label' and (text()='"
						+ namereference + "')]"), "reference project added failed");

	}

	@AfterMethod
	public void tearDown() throws Exception {
		// selenium.stop();

	}
}
