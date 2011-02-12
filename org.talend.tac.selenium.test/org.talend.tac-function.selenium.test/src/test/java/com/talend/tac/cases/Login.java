package com.talend.tac.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.base.Base;

public class Login extends Base {
	
	@Test
	@Parameters({"userName", "userPassword"})
	public void login(String user, String password) {
		selenium.type("idLoginInput", user);
		selenium.type("idLoginPasswordInput", password);
		selenium.click("idLoginButton");
		selenium.keyDown("idLoginButton",  "\\13");
		selenium.waitForPageToLoad("30000");
	}
	
	@AfterMethod
	public void logout() {
		selenium.click("idLeftMenuTreeLogoutButton");
	}

}
