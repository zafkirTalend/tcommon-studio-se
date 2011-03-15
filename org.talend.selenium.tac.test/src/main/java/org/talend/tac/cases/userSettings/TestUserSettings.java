package org.talend.tac.cases.userSettings;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.talend.tac.cases.Login;

public class TestUserSettings extends Login {
	@Test(enabled = false)
	public void testChangeSVNaCCOUNT() {

		selenium.click("userSettingMenu");
		selenium.type("idChangePwdNewPwdInput", "newPasswd");
		selenium.type("idChangePwdConfirmPwdInput", "");
		selenium.click("idChangePwdValidateButton");
	}

	@Test
	@Parameters( { "userSetting.newPasswd" })
	public void testChangePasswdOfCurrentUser(String newPasswd) {

		doModify(newPasswd);
		// change password back to avoid conflict with other cases
		doModify("admin");
	}

	public void doModify(String newPasswd) {
		this.waitForElementPresent("idMenuChangePasswordElement", 30);
		assertTrue(selenium.isElementPresent("idMenuChangePasswordElement"));
		selenium.click("idMenuChangePasswordElement");
		this.waitForElementPresent("idChangePwdNewPwdInput", 30);
		selenium.type("idChangePwdNewPwdInput", newPasswd);
		this.waitForElementPresent("idChangePwdConfirmPwdInput", 30);
		selenium.type("idChangePwdConfirmPwdInput", newPasswd);
		// simulate pressing ENTER to enable button "validate"
		selenium.keyDown("idChangePwdNewPwdInput", "\\13");
		selenium.keyUp("idChangePwdNewPwdInput", "\\13");
		this.waitForElementPresent("idChangePwdValidateButton", 30);
		selenium.click("idChangePwdValidateButton");
//		assertTrue(selenium.isTextPresent(rb.getString("password.ok")));
	}
}