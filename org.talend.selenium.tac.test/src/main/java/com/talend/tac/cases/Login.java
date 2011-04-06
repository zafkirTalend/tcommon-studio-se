package com.talend.tac.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.talend.tac.base.Base;

public class Login extends Base {

	@BeforeMethod
	@Parameters( { "userName", "userPassword" })
	public void login(String user, String password) {

		selenium.setSpeed(MID_SPEED);
		
		selenium.windowMaximize();
		selenium.type("idLoginInput", user);
		selenium.type("idLoginPasswordInput", password);
		
		// KeyEvent.VK_ENTER
		selenium.keyPressNative("9");
		
		selenium.keyDown("idLoginPasswordInput", "\\13");
		
		if (selenium
				.isTextPresent("Failed to log on: user admin@company.com already logged on to webapp")) {
			selenium.click("idLoginForceLogoutButton");
			selenium.keyDown("idLoginPasswordInput", "\\13");
		}
		selenium.setSpeed(MIN_SPEED);
		// selenium.waitForPageToLoad("30000");// no need
	}

	@AfterMethod
	public void logout() {
		selenium.click("idLeftMenuTreeLogoutButton");
		// selenium.stop();
	}

	@AfterClass
	public void killBroswer() {
		selenium.stop();
	}

}
