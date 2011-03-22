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

		selenium.setSpeed(MAX_SPEED);
		
//		waitForElementPresent("idLoginInput", 20);
//		waitForElementPresent("idLoginPasswordInput", 20);
		
		selenium.windowMaximize();
		selenium.setSpeed(MIN_SPEED);
		selenium.type("idLoginInput", user);
		selenium.type("idLoginPasswordInput", password);
	
		selenium.keyDown("idLoginPasswordInput", "\\13");
		selenium.setSpeed(MAX_SPEED);
		
//		// wait for this HTML page fully loaded
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		
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
