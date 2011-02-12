package com.talend.tac.cases.project;

import java.awt.event.KeyEvent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.talend.tac.cases.Login;

public class Addcommonpro extends Login {

	@Test
	@Parameters({ "userName", "userPassword" })
	public void testAddpro(String user, String password) throws Exception {
		String svnUrl = "https://localhost:8443/svn/";
		testaddcommon("commonpro", svnUrl);
		selenium.setSpeed("5000");
		selenium.click("idSubModuleRefreshButton");
		selenium.setSpeed("3000");
		testaddreference("referencepro", svnUrl);
		selenium.setSpeed("5000");
	}

	public void testaddcommon(String namecommon, String svnurl)
			throws Exception {
		String proname = null;
		proname = namecommon;
		selenium.setSpeed("5000");
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", proname);
		selenium.fireEvent("idLabelInput", "blur");
		selenium.click("idLanguageInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.fireEvent("idLanguageInput", "blur");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", svnurl + proname + "/");// svn project url
		selenium.fireEvent("idUrlInput", "blur");
		selenium.type("idLoginInput", "admin");// svn account
		selenium.fireEvent("idLoginInput", "blur");
		selenium.type("idPasswordInput", "admin");// svn password
		selenium.fireEvent("idPasswordInput", "blur");
		selenium.click("idSvnCommitInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnLockInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnUserLogInput");
		selenium.click("idFormSaveButton");
	}

	public void testaddreference(String namereference, String svnurl)
			throws Exception {
		String proname = null;
		proname = namereference;
		selenium.setSpeed("5000");
		selenium.click("!!!menu.project.element!!!");
		selenium.click("idSubModuleAddButton");
		selenium.click("idLabelInput");
		selenium.type("idLabelInput", proname);
		selenium.fireEvent("idLabelInput", "blur");
		selenium.click("idLanguageInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.fireEvent("idLanguageInput", "blur");
		selenium.click("idReferenceInput");
		selenium.type("idDescriptionInput", "adf");
		selenium.fireEvent("idDescriptionInput", "blur");
		selenium.click("idAdvanceInput");
		selenium.type("idUrlInput", svnurl + proname + "/");// svn project url
		selenium.fireEvent("idUrlInput", "blur");
		selenium.type("idLoginInput", "admin");// svn account
		selenium.fireEvent("idLoginInput", "blur");
		selenium.type("idPasswordInput", "admin");// svn password
		selenium.fireEvent("idPasswordInput", "blur");
		selenium.click("idSvnCommitInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnLockInput");
		selenium.keyPressNative("" + KeyEvent.VK_ENTER);
		selenium.keyUpNative("" + KeyEvent.VK_ENTER);
		selenium.click("idSvnUserLogInput");
		selenium.click("idFormSaveButton");

	}

	@AfterMethod
	public void tearDown() throws Exception {
//		selenium.stop();
		
	}
}
